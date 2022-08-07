package com.board.web.controller.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post/")
public class PostController {
	@GetMapping("list") 
	public String list() {
		return "/post/list";
	}
	
	@GetMapping("write")
	public String write() {
		return "/post/write";
	}
	
	@PostMapping("regist")
	public String regist() {
		return "redirect:/post/list";
	}	
	
	@GetMapping("detail")
	public String detail() {
		return "/post/detail";
	}
	
	@GetMapping("edit")
	public String edit() {
		return "/post/edit";
	}
	
	@PostMapping("update")
	public String update() {
		return "redirect:/post/detail";
	}
	
	@PostMapping("delete")
	public String delete() {
		return "redirect:/post/list";
	}
}
