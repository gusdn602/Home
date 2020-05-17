package com.home.www.controller;

import javax.servlet.http.*;

public class MainController implements HomeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/main/main.jsp";
		System.out.println("실행"+view);
		return view;
	}

}
