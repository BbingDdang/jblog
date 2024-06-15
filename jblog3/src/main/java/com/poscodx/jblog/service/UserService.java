package com.poscodx.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void join(UserVo vo) {
		userRepository.insert(vo);
		BlogVo blogVo = new BlogVo();
		blogVo.setId(vo.getId());
		blogVo.setTitle(vo.getName() + "'s Blog");
		blogVo.setLogo("/assets/images/spring-logo.jpg");
		blogRepository.insert(blogVo);
		CategoryVo cVo = new CategoryVo();
		cVo.setId(vo.getId());
		cVo.setName("미분류");
		cVo.setDescription("미분류 컨텐츠입니다.");
		categoryRepository.insert(cVo);
			
	}
	
	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}


}
