package com.poscodx.jblog.repository;


import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	
	private SqlSession sqlSession;
	
	@Autowired
	public UserRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/*
	 * 유저 추가
	 */
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}
	
	/*
	 * 유저 찾기 id + password
	 */

	public UserVo findByIdAndPassword(String id, String password) {
		return sqlSession.selectOne("user.findByIdAndPassword", Map.of("id", id, "password", password));
	}
	
	/*
	 * 유저 찾기 id
	 */

	public UserVo findById(String id) {
		return sqlSession.selectOne("user.findById", id);
	}

}
