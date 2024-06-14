package com.poscodx.jblog.controller;

import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.security.Auth;
import com.poscodx.jblog.security.AuthUser;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@RequestMapping({"","/{categoryNo}","/{categoryNo}/{postNo}"})
	public String index(@PathVariable("id") String id, @PathVariable("categoryNo") Optional<Long> categoryNo, @PathVariable("postNo") Optional<Long> postNo, UserVo vo, BlogVo bVo) {
		System.out.println(vo);
		System.out.println(bVo + "2");
		return "blog/main";
	}
	
	/*
	 * admin-basic 페이지 보기
	 */
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id,@AuthUser UserVo vo) {
		UserVo authUser = userService.getUser(vo.getId());
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		return "blog/admin-basic";
	}
	
	/*
	 * admin-basic 페이지 업데이트 실
	 */
	@Auth
	@PostMapping("/admin/basic")
	public String adminBasic(BlogVo vo, MultipartFile file) {
		String logo = fileUploadService.restore(file);
		if (logo != null) {
			vo.setLogo(logo);
		}
		
		blogService.updateBlog(vo);
		servletContext.setAttribute("blogvo", vo);
		return "redirect:/admin/basic";
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, UserVo vo) {
		UserVo authUser = userService.getUser(vo.getId());
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		
		return "blog/admin-category";
	}
	
	@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, UserVo vo) {
		UserVo authUser = userService.getUser(vo.getId());
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		return "blog/admin-write";
	}
	
}
