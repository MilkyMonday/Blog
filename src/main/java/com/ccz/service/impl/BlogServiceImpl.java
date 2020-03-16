package com.ccz.service.impl;

import com.ccz.dao.BlogRepository;
import com.ccz.po.Blog;
import com.ccz.po.Type;
import com.ccz.po.User;
import com.ccz.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Blog> listBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Blog> listBlogsByFlag() {
        return blogRepository.findBlogsByFlagEquals("首页推荐");
    }

    @Override
    public List<Blog> listBlogsByType(Type type) {
        return blogRepository.findBlogsByTypeEquals(type);
    }

    @Override
    public Page<Blog> listBlogsByType(Type type, Pageable pageable) {
        return blogRepository.findBlogsByTypeEquals(type, pageable);
    }

    @Override
    public Page<Blog> listBlogsByUser(User user, Pageable pageable) {
        return blogRepository.findBlogsByUserEquals(user, pageable);
    }

    @Transactional
    @Override
    public Blog updateBlog(Blog blog) {
        Blog one = blogRepository.getOne(blog.getId());
        one.setTitle(blog.getTitle());
        one.setIntroduction(blog.getIntroduction());
        one.setType(blog.getType());
        one.setFlag(blog.getFlag());
        one.setContent(blog.getContent());
        one.setUpdateTime(new Date());
        return blogRepository.save(one);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public void deleteBlog(List<Blog> list) {
        for (Blog blog : list) {
            blogRepository.delete(blog);
        }
    }
}
