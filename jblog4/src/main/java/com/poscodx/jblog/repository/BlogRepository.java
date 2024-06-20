package com.poscodx.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	/*
	 * 블로그 업데이트
	 */
	
	public void update(BlogVo vo) {
		sqlSession.update("blog.update", vo);
	}
	
	/*
	 * 블로그 추가 
	 */

	public void insert(BlogVo vo) {
		sqlSession.insert("blog.insert", vo);
	}
	
	/*
	 * 블로그 id로 찾기 
	 */

	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}

}
