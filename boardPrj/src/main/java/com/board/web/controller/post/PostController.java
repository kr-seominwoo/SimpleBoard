package com.board.web.controller.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post/")
public class PostController {
	@RequestMapping("list") 
	public String list() {
		return "/post/list";
	}
	
	@RequestMapping("write")
	public String write() {
		return "/post/write";
	}
	
	@RequestMapping("regist")
	public String regist() {
		return "redirect:/post/list";
	}	
	
	@RequestMapping("detail")
	public String detail() {
		return "/post/detail";
	}
	
	@RequestMapping("edit")
	public String edit() {
		return "/post/edit";
	}
	
	@RequestMapping("update")
	public String update() {
		return "redirect:/post/detail";
	}
	
	@RequestMapping("delete")
	public String delete() {
		return "redirect:/post/list";
	}
}
