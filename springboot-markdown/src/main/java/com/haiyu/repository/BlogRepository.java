package com.haiyu.repository;


import com.haiyu.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Title: BlogRepository
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/1 15:14
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {
    public Blog findBlogById(Long id);
}
