package com.poscodx.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping({"","/{categoryNo}","/{categoryNo}/{postNo}"})
	public String index(@PathVariable("id") String id, @PathVariable("categoryNo") Optional<Long> categoryNo, @PathVariable("postNo") Optional<Long> postNo) {
		
		return "blog/main";
	}
	
//	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, UserVo vo) {
		UserVo authUser = userService.getUser(vo.getId(), vo.getPassword());
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		
		return "blog/admin-basic";
	}
	
	//@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, UserVo vo) {
		UserVo authUser = userService.getUser(vo.getId(), vo.getPassword());
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		
		return "blog/admin-category";
	}
	
	//@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, UserVo vo) {
		UserVo authUser = userService.getUser(vo.getId(), vo.getPassword());
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		return "blog/admin-write";
	}
	
}
