package com.home.www.controller.member;

import javax.servlet.http.*;
import javax.servlet.*;

import com.home.www.controller.*;

public class LoginForm implements HomeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/member/Login.jsp";
		return view;
	}

}
