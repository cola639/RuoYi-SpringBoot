### **🔍 在 Redis 存储 `UUID -> Token` 的效果**

在你的 `TokenService` 里，**UUID 作为 Token 存入 Redis**，而 **JWT 里只存 `UUID`**，然后客户端每次请求时，**先解析 JWT 获取
UUID**，再用 UUID 去 Redis 取用户信息。

---

## **✅ 这种方案的特点**

| **特点**          | **效果**                                 |
|-----------------|----------------------------------------|
| **Token 变短**    | 只存 `UUID`，Token 更短，避免 JWT 过长。          |
| **可控失效**        | 令牌失效时间可手动在 Redis 设置，随时可以修改、删除 Token。   |
| **支持 Token 续期** | 只要刷新 Redis 里的 `expireTime`，Token 依然有效。 |
| **支持单点登录（踢人）**  | 可以让一个用户只有一个有效 Token，避免重复登录。            |
| **安全性更高**       | JWT 里不存敏感信息，即使被解码，也不会泄露用户数据。           |

---

## **✅ 这种方案的实现**

### **1️⃣ 生成 Token**

```java
public String createToken(LoginUser loginUser) {
    // 1️⃣ 生成 UUID 作为 Token
    String uuid = IdUtils.fastUUID();
    loginUser.setToken(uuid);

    // 2️⃣ 记录用户设备信息
    setUserAgent(loginUser);

    // 3️⃣ 存入 Redis
    refreshToken(loginUser);

    // 4️⃣ 生成 JWT（只存 UUID）
    return Jwts.builder()
            .claim(Constants.LOGIN_USER_KEY, uuid) // 只存 UUID
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expireTime * MILLIS_MINUTE)) // 30分钟
            .signWith(SignatureAlgorithm.HS512, secret) // 采用 HMAC-SHA512 加密
            .compact();
}
```

---

### **2️⃣ 解析 Token（从 Redis 获取用户信息）**

```java
public LoginUser getLoginUser(HttpServletRequest request) {
    String token = getToken(request);
    if (StringUtils.isNotEmpty(token)) {
        try {
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);

            // 通过 UUID 从 Redis 获取用户信息
            String userKey = getTokenKey(uuid);
            return redisCache.getCacheObject(userKey);
        } catch (Exception e) {
            return null;
        }
    }
    return null;
}
```

---

### **3️⃣ Token 续期（刷新 Redis 过期时间）**

```java
public void refreshToken(LoginUser loginUser) {
    loginUser.setLoginTime(System.currentTimeMillis());
    loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);

    // 重新存入 Redis，延长过期时间
    String userKey = getTokenKey(loginUser.getToken());
    redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
}
```

---

## **✅ 这种做法的优势**

### **🚀 1. 可以手动失效 Token**

- 如果用户 **退出登录**，可以直接删除 Redis 里的 `UUID`，让 Token 失效：
  ```java
  public void delLoginUser(String token) {
      if (StringUtils.isNotEmpty(token)) {
          String userKey = getTokenKey(token);
          redisCache.deleteObject(userKey);
      }
  }
  ```
- 如果用户 **被管理员踢下线**，也可以立即失效。

---

### **🚀 2. 支持单点登录（一个用户只有一个 Token）**

- 假设一个用户同时在 PC 和 手机端登录：
    - 旧方案：两端都会有独立的 JWT，无法控制同时在线。
    - 现在方案：每次登录时，都会生成新的 `UUID` 并存入 Redis，**旧 Token 立即失效**：
      ```java
      public void setLoginUser(LoginUser loginUser) {
          if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
              refreshToken(loginUser);
          }
      }
      ```
    - 这样用户 **在新设备登录时，旧设备的 Token 失效**，避免多人共用账户。

---

### **🚀 3. Token 可以续期（自动刷新）**

- 传统 JWT 方案：
    - 过期时间写死，用户必须重新登录。
- 现在的 `UUID -> Token` 方案：
    - 每次请求时，检查 Redis 剩余时间：
      ```java
      if (expireTime - currentTime <= MILLIS_MINUTE_TEN) { // 剩余不足 20 分钟
          refreshToken(loginUser); // 续期
      }
      ```
    - 这样，**只要用户在活跃状态，Token 永远不会过期**，但如果长时间不访问，Redis 过期后 Token 也就失效。

---

## **❌ 这种方案的缺点**

1. **增加 Redis 依赖**
    - 如果 Redis 宕机，Token 校验就会失败。
    - 需要 Redis **高可用架构**（如 Redis Sentinel、Cluster）。

2. **每次验证 Token 都要查询 Redis**
    - 传统 JWT 方案，服务器**不存 Token**，只用公钥解密即可验证。
    - 现在的方案，需要每次查询 Redis，多了一次 I/O 。
    - 解决方案：
        - **减少 Redis 访问频率**，可以使用本地缓存 `Caffeine` 缓存 Token。

---

## **🎯 总结**

| 方案                | 传统 JWT              | `UUID -> Token` 存 Redis |
|-------------------|---------------------|-------------------------|
| **是否能手动失效 Token** | ❌ 不行，JWT 只能等过期      | ✅ 可以随时让 Token 失效        |
| **是否能单点登录**       | ❌ 不能，一个用户可以多个 Token | ✅ 可以，新的 Token 会覆盖旧的     |
| **是否能续期**         | ❌ 不能，JWT 过期就要重新登录   | ✅ 可以，Redis 续期 Token     |
| **性能**            | ✅ 只需解密，无需数据库/Redis  | ❌ 需要 Redis 查询           |
| **安全性**           | ⚠️ JWT 如果泄露，无法撤销    | ✅ Redis 可控制 Token 失效    |

**🚀 适用场景：**

- 如果你的系统**对安全性要求高**（如后台管理系统）→ ✅ **推荐 `UUID -> Token` 方案**。
- 如果是**公有 API，性能要求高** → ✅ **推荐纯 JWT 方案**。

---

## **🔗 结论**

✅ **`UUID -> Token` 存入 Redis** 方案的 **最大优势**：

1. **可控失效**（用户退出/被踢，可以删除 Token）。
2. **支持单点登录**（新设备登录，旧 Token 失效）。
3. **Token 可续期**（只要活跃就不会过期）。

❌ **缺点**：

1. **需要 Redis**（无 Redis 依赖 JWT）。
2. **每次验证 Token 都要查 Redis**（性能略低）。

**🎯 结论：**
**如果你的系统需要高安全性，推荐 `UUID -> Token` 方案！** 🚀