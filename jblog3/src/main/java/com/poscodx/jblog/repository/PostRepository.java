package com.poscodx.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	
	/*
	 * post 쓰기
	 */
	
	public void insert(PostVo vo) {
		sqlSession.insert("post.insert", vo);
	}

	/*
	 * post 개수 세기
	 */
	
	public Long findCountByCategoryNo(Long categoryNo) {
		return sqlSession.selectOne("post.findCountByCategoryNo", categoryNo);
	}
	
	/*
	 * 포스트 no와 카테고리no로 찾기 
	 */

	public PostVo findPostByNoAndCategoryNo(Long no, Long categoryNo) {
		return sqlSession.selectOne("post.findPostByNoAndCategoryNo", Map.of("no", no, "categoryNo", categoryNo));
	}
	
	/*
	 * 카테고리 내 포스트 찾기 
	 */

	public List<PostVo> findAllCategoryPostByCategoryNo(Long categoryNo) {
		return sqlSession.selectList("post.findAllCategoryPostByCategoryNo", categoryNo);
	}
	
	/*
	 * 카테고리no로 포스트no 찾기
	 */
	
	public Long findNoByCategoryNo(Long categoryNo) {
		return sqlSession.selectOne("post.findNoByCategoryNo", categoryNo);
	}

}
