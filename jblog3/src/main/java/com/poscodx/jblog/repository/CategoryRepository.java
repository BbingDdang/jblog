package com.poscodx.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	/*
	 * 카테고리 업데이트 
	 */
	
	public void update(CategoryVo vo) {
		sqlSession.update("category.update", vo);
	}
	
	/*
	 * 카테고리 추가 
	 */

	public void insert(CategoryVo vo) {
		sqlSession.insert("category.insert", vo);
	}
	
	/*
	 * 카테고리 id로 찾기
	 */

	public CategoryVo findById(String id) {
		return sqlSession.selectOne("category.findById", id);
	}

	/*
	 * 모든 카테고리 id로 찾기 
	 */
	
	public List<CategoryVo> findAllCategoryById(String id) {
		return sqlSession.selectList("category.findAllCategoryById", id);
	}

	/*
	 * category 삭제 
	 */
	public void deleteByIdAndNo(String id, Long no) {
		sqlSession.delete("category.deleteByIdAndNo", Map.of("id", id, "no", no));
	}
	
	/*
	 * 카테고리번호 찾기 (미분류, default page)
	 */
	
	public long findCategoryNoByIdAndName(String id, String name) {
		return sqlSession.selectOne("category.findCategoryNoByIdAndName", Map.of("id", id, "name", name));
	}

}
