package com.haiyu.entity;

import javax.persistence.*;

/**
 * @Title: Blog
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/1 15:12
 */
@Entity
public class Blog {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    //@NotEmpty(message = "标题不能为空")
    @Column(nullable = false, length = 50) // 映射为字段，值不能为空
    private String title;

    //@NotEmpty(message = "摘要不能为空")
    @Column(nullable = true) // 映射为字段，值不能为空
    private String summary;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型

    //@NotEmpty(message = "内容不能为空")
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;
    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    //@NotEmpty(message = "内容不能为空")
    @Column(nullable = false) // 映射为字段，值不能为空
    private String htmlContent; // 将 md 转为 html
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }


    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                '}';
    }
}
