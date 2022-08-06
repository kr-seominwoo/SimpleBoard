package com.board.web.controller.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PostController {
	
	@RequestMapping("detail")
	public String detail() {
		return "detail";
	}	
}
