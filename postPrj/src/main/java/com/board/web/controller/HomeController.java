package com.board.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping(value={"", "index"})
	public String index() {
		System.out.print("index");
		return "index";
	}
}
