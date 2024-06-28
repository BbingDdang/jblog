package com.poscodx.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 회원가입 페이지 보기 
	 */
	
	@GetMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	/*
	 * 회원가입 실행 
	 */
	
	@PostMapping("/join")
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	/*
	 * 회원가입 성공 페이지 보기 
	 */
	
	@GetMapping("/joinsuccess")
	public String joinSuccess(@ModelAttribute UserVo vo) {
		return "user/joinsuccess";
	}
	
	/*
	 * 로그인 페이지 보기 
	 */
	
	@RequestMapping(value = "/login")
	public String login() {
		return "user/login";
	}
	
}
