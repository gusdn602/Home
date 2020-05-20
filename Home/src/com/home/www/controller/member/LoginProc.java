package com.home.www.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.home.www.controller.HomeController;
import com.home.www.dao.MemberDAO;

public class LoginProc implements HomeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/Home/main.home";
		req.setAttribute("isRedirect", true);
		
		//파리미터 가져오고
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		MemberDAO mDAO = new MemberDAO();
		int cnt = mDAO.getLoginCnt(sid, spw);
		
		if(cnt == 1) {
			HttpSession session = req.getSession();
			session.setAttribute("SID", sid);
		} else {
			view = "/Home/member/login.home";
		}
		return view;
	}

}
