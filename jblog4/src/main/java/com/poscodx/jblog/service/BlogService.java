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
	 * post count
	 */
	
	public Long findCountCategoryNo(Long categoryNo) {
		return postRepository.findCountByCategoryNo(categoryNo);
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
		
		List<PostVo> plist = findAllCategoryPostByCategoryNo(categoryNo);
		List<CategoryVo> clist = findAllCategoryById(id);
		Map<String, Object> map = new HashMap<>();
		map.put("clist", clist);
		map.put("plist", plist);
		if (postNo == null) {
			return map;
		}
		else {
			PostVo pvo = findPostByNoAndCategoryNo(postNo, categoryNo);
			map.put("pvo", pvo);
			return map;
		}
		
	}
	
	/*
	 * 블로그 업데이트 
	 */
	
	public void updateBlog(BlogVo vo) {
		blogRepository.update(vo);
	}
	
	/*
	 * 블로그 추가 (회원가입시 자동 추가)
	 */

	public void insert(BlogVo vo) {
		blogRepository.insert(vo);
	}
	
	/*
	 * 블로그 찾기 {id로}
	 */
	
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
		if (findCountCategoryNo(no) == 0) {
			categoryRepository.deleteByIdAndNo(id, no);
		}
		
	}

	/*
	 * post 추가  
	 */
	
	public void addPost(PostVo vo) {
		postRepository.insert(vo);
	}
	
	/*
	 * post no 받기..
	 */
	public long findPostNoByCategoryNo(Long categoryNo) {
		return postRepository.findNoByCategoryNo(categoryNo);
	}
	
	/*
	 * default page 번호 불러오기 
	 */
	
	public Map<String, Long> getCategoryNoAndPostNo(String id){
		Long cNo = categoryRepository.findCategoryNoByIdAndName(id, "미분류");
		Long pNo = postRepository.findNoByCategoryNo(cNo);
		Map<String, Long> map = new HashMap<>();
		map.put("cNo", cNo);
		map.put("pNo", pNo);
		return map;
	}
	
	

}
