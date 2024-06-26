package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private BlogService blogService;
	
	/*
	 * 블로그 메인 페이지 보기 
	 * 
	 * 
	 */
	
	@RequestMapping({"","/{categoryNo}","/{categoryNo}/{postNo}"})
	public String index(@PathVariable("id") String id, @PathVariable("categoryNo") Optional<Long> categoryNo, UserVo vo, @PathVariable("postNo") Optional<Long> postNo, Model model) {
		Map<String, Long> noList = blogService.getCategoryNoAndPostNo(id);
		
		
		if (categoryNo.isEmpty()) {
			categoryNo = Optional.of(noList.get("cNo"));
		}
		Long cNo = categoryNo.get();
		Long pNo = null;
		if (postNo.isEmpty()) {
			Long cnt = blogService.findCountCategoryNo(cNo);
			if (cnt != 0) {
				pNo = blogService.findPostNoByCategoryNo(cNo);
			}
		}
		else {
			pNo = postNo.get();
		}
		Map<String, Object> map = blogService.getMain(id, pNo, cNo);
		PostVo pvo = (PostVo)map.get("pvo");
		List<CategoryVo> clist = (List<CategoryVo>) map.get("clist");
		if (pNo != null) {
			List<PostVo> plist = (List<PostVo>)map.get("plist");
			model.addAttribute("plist", plist);
		}
		BlogVo blogVo = blogService.findById(id);
		model.addAttribute("pvo",pvo);
		model.addAttribute("clist",clist);
		model.addAttribute("blogVo", blogVo);
		return "blog/main";
	}
	
	/*
	 * admin-basic 페이지 보기
	 */
	
	@GetMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, UserVo vo, Model model, Authentication authentication) {
		
		UserVo authUser = (UserVo)authentication.getPrincipal();
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		BlogVo blogVo = blogService.findById(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin-basic";
	}
	
	/*
	 * admin-basic 페이지 업데이트 실행
	 */
	
	@PostMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, BlogVo vo, UserVo uvo, @RequestParam MultipartFile file, Authentication authentication) {
		String logo = fileUploadService.restore(file);
		if (logo != null) {
			vo.setLogo(logo);
		}
		UserVo authUser = (UserVo)authentication.getPrincipal();
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		blogService.updateBlog(vo);
		servletContext.setAttribute("blogvo", vo);
		
		return "redirect:/" + id + "/admin/basic";
	}
	
	/*
	 * admin-category 페이지 보기 
	 */
	
	@GetMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, UserVo vo, Model model, Authentication authentication) {
		UserVo authUser = (UserVo)authentication.getPrincipal();
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		List<CategoryVo> categoryList = blogService.findAllCategoryById(id);
		model.addAttribute("list", categoryList);
		model.addAttribute("categoryVo", new CategoryVo());
		
		return "blog/admin-category";
	}
		
	/*
	 * admin-category 추가 
	 */
	
	@PostMapping("/admin/category/add")
	public String addCategory(@PathVariable("id") String id, @ModelAttribute("categoryVo") CategoryVo vo, Model model) {
	    blogService.addCategory(vo);
	    return "redirect:/" + id + "/admin/category";
	}
	
	/*
	 * admin-category 삭제
	 */
	
	@GetMapping("/admin/category/delete")
	public String deleteCategory(@PathVariable("id") String id, @RequestParam("no") Long no) {
		blogService.deleteCategory(id,no);
		return "redirect:/" + id + "/admin/category";
	}
	
	/*
	 * post insert 페이지 보기 
	 */
	
	@GetMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, UserVo vo, Model model, Authentication authentication) {
		//UserVo authUser = userService.getUser(vo.getId());
		UserVo authUser = (UserVo)authentication.getPrincipal();
		if (!id.equals(authUser.getId())) {
			return "blog/main";
		}
		model.addAttribute("postVo", new PostVo());
		List<CategoryVo> list = blogService.findAllCategoryById(id);
		model.addAttribute("list", list);
		return "blog/admin-write";
	}
	
	/*
	 * post insert 실행 
	 */
	
	@PostMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, @ModelAttribute("postVo") PostVo vo) {
		blogService.addPost(vo);
		return "redirect:/" + id + "/admin/write";
	}
	
}
