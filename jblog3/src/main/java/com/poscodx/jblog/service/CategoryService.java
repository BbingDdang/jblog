package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	/*
	 * 카테고리 리스트 생성 
	 */
	
	public List<CategoryVo> findAllCategoryById(String id) {

		return categoryRepository.findAllCategoryById(id);
	}
}
