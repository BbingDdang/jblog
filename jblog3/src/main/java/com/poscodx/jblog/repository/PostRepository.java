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
	
	public void insert(PostVo vo) {
		sqlSession.insert("post.insert", vo);
	}

	public Long findCountCategoryNo(Long categoryNo) {
		return sqlSession.selectOne("post.findCountByCategoryNo", categoryNo);
	}

	public PostVo findPostByNoAndCategoryNo(Long no, Long categoryNo) {
		return sqlSession.selectOne("post.findPostByNoAndCategoryNo", Map.of("no", no, "categoryNo", categoryNo));
	}

	public List<PostVo> findAllCategoryPostByCategoryNo(Long categoryNo) {
		return sqlSession.selectList("post.findAllCategoryPostByCategoryNo", categoryNo);
	}
	
	public Long findNoByCategoryNo(Long categoryNo) {
		return sqlSession.selectOne("post.findNoByCategoryNo", categoryNo);
	}

}
