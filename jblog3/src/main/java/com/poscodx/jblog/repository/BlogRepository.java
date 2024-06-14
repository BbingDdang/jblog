package com.poscodx.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void update(BlogVo vo) {
		sqlSession.update("vo", vo);
	}

	public void insert(BlogVo vo) {
		sqlSession.insert("vo", vo);
	}

}
