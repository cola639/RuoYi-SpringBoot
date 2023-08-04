package com.colaclub.chat.service.impl;

import com.colaclub.chat.domain.UserChat;
import com.colaclub.chat.mapper.UserChatMapper;
import com.colaclub.chat.service.IUserChatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserChatServiceImpl implements IUserChatService {
  @Autowired private UserChatMapper userChatMapper;

  @Override
  public List<UserChat> selectChatList() {
    return userChatMapper.selectChatList();
  }

  @Override
  public List<UserChat> selectUserChat(UserChat userChat) {
    return userChatMapper.selectUserChat(userChat);
  }

  @Override
  @Transactional
  public int insertChat(UserChat userChat) {
    return userChatMapper.insertChat(userChat);
  }

  @Override
  @Transactional
  public int deleteChatById(Long userId) {
    return userChatMapper.deleteChatById(userId);
  }
}
