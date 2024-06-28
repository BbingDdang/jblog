package com.poscodx.jblog.repository;


import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	public UserVo findByIdForCheckEmail(String id) {
		return sqlSession.selectOne("user.findByIdForCheckEmail", id);
	}

	public <R> R findById(String Id, Class<R> resultType) {
		FindByIdResultHandler<R> findByIdResultHandler = new FindByIdResultHandler<>(resultType);
		sqlSession.select("user.findById", Id, findByIdResultHandler);		
		return findByIdResultHandler.result;
	}
	

	private class FindByIdResultHandler<R> implements ResultHandler<Map<String, Object>> {

		private R result;
		private Class<R> resultType;
		
		public FindByIdResultHandler(Class<R> resultType) {
            this.resultType = resultType;
        }
		
		@Override
		public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {
			Map<String, Object> resultMap = resultContext.getResultObject();
			result = new ObjectMapper().convertValue(resultMap, resultType);
			
		}
	}


}
