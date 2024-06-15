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
	
	public void update(CategoryVo vo) {
		sqlSession.update("category.update", vo);
	}

	public void insert(CategoryVo vo) {
		sqlSession.insert("category.insert", vo);
	}

	public CategoryVo findById(String id) {
		return sqlSession.selectOne("category.findById", id);
	}

	public List<CategoryVo> findAllCategoryById(String id) {
		return sqlSession.selectList("category.findAllCategoryById", id);
	}

	/*
	 * category 삭제 
	 */
	public void deleteByIdAndNo(String id, Long no) {
		sqlSession.delete("category.deleteByIdAndNo", Map.of("id", id, "no", no));
	}

}
