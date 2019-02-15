package com.cug.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PrintController {

	@RequestMapping(value = "/print.page")
	public String login() {
		return "print";
	}
}
