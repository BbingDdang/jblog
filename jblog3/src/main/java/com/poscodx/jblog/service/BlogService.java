package com.poscodx.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	/*
	 * post view
	 */
	public PostVo findPostByNoAndCategoryNo(Long no, Long categoryNo) {
		return postRepository.findPostByNoAndCategoryNo(no, categoryNo);
	}
	
	/*
	 * postlist view
	 */
	public List<PostVo> findAllCategoryPostByCategoryNo(Long categoryNo) {
		return postRepository.findAllCategoryPostByCategoryNo(categoryNo);
	}
	
	/*
	 * blog main view 
	 */
	public Map<String, Object> getMain(String id, Long postNo, Long categoryNo){
		PostVo pvo = findPostByNoAndCategoryNo(postNo, categoryNo);
		List<PostVo> plist = findAllCategoryPostByCategoryNo(categoryNo);
		List<CategoryVo> clist = findAllCategoryById(id);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pvo", pvo);
		map.put("plist", plist);
		map.put("clist", clist);
		
		return map;
	}
	
	public void updateBlog(BlogVo vo) {
		blogRepository.update(vo);
	}

	public void insert(BlogVo vo) {
		blogRepository.insert(vo);
	}

	public BlogVo findById(String id) {
		return blogRepository.findById(id);
	}
	
	/*
	 * category list 출력 
	 */
	
	public List<CategoryVo> findAllCategoryById(String id) {
		return categoryRepository.findAllCategoryById(id);
	}
	
	/*
	 * category 추가 
	 */
	
	public void addCategory(CategoryVo vo) {
		categoryRepository.insert(vo);
	}
	
	/*
	 * category 삭제 
	 */
	
	public void deleteCategory(String id, Long no) {
		categoryRepository.deleteByIdAndNo(id, no);
	}

	/*
	 * post 추가  
	 */
	
	public void addPost(PostVo vo) {
		postRepository.insert(vo);
	}
	
	
	
	

}
