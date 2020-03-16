package com.ccz.dao;

import com.ccz.po.Blog;
import com.ccz.po.Type;
import com.ccz.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findBlogsByFlagEquals(String flag);

    List<Blog> findBlogsByTypeEquals(Type type);

    Page<Blog> findBlogsByTypeEquals(Type type, Pageable pageable);

    Page<Blog> findBlogsByUserEquals(User user, Pageable pageable);
}
