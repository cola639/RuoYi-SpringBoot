package com.ruoyi.qdpz.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * article sheet对象 qdpz_articles
 *
 * @author ruoyi
 * @date 2023-08-08
 */
public class QdpzArticles extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * article id
     */
    private Long id;

    /**
     * author
     */
    @Excel(name = "author")
    private String author;

    /**
     * comment account
     */
    @Excel(name = "comment account")
    private Long commentNums;

    /**
     * content
     */
    @Excel(name = "content")
    private String content;

    /**
     * cover
     */
    @Excel(name = "cover")
    private String cover;

    /**
     * image list
     */
    @Excel(name = "image list")
    private String imgList;

    /**
     * like account
     */
    @Excel(name = "like account")
    private Long likesNum;

    /**
     * page views
     */
    @Excel(name = "page views")
    private Long seeNum;

    /**
     * article state
     */
    @Excel(name = "article state")
    private Integer state;

    /**
     * article title
     */
    @Excel(name = "article title")
    private String title;

    /**
     * article type
     */
    @Excel(name = "article type")
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCommentNums() {
        return commentNums;
    }

    public void setCommentNums(Long commentNums) {
        this.commentNums = commentNums;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public Long getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Long likesNum) {
        this.likesNum = likesNum;
    }

    public Long getSeeNum() {
        return seeNum;
    }

    public void setSeeNum(Long seeNum) {
        this.seeNum = seeNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("author", getAuthor())
                .append("commentNums", getCommentNums())
                .append("content", getContent())
                .append("createTime", getCreateTime())
                .append("cover", getCover())
                .append("imgList", getImgList())
                .append("likesNum", getLikesNum())
                .append("seeNum", getSeeNum())
                .append("state", getState())
                .append("title", getTitle())
                .append("type", getType())
                .toString();
    }
}
