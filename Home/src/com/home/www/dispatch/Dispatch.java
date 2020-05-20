package com.home.www.dispatch;

import javax.servlet.http.HttpServlet;

import com.home.www.controller.*;

/**
 * 이 클래스는 확장자가 home로 요청이 되는 경우 Dispatch시킬 서블릿 클래스이다.
 * 
 * @author 우현우
 *
 */
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("*.home") // URL패턴 지정
public class Dispatch extends HttpServlet {
	//요청 내용과 클래스의 인스턴스를 매핑할 맵이 필요함
	
	public HashMap<String, HomeController> map;
	
	public void init(ServletConfig config) throws ServletException{
		//이 함수는 서버가 기동된 이후 요청내용이 .home로 끝나는 요청이 왔을 경우 딱 한번만 실행되는 함수이다.
		
		Properties prop = new Properties();
		FileInputStream fin = null;
		try {
			String path = this.getClass().getResource("").getPath();
			fin = new FileInputStream(path + "../resource/home.properties");
			prop.load(fin);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch (Exception e) {}
		}
		
		map = new HashMap<String, HomeController>();
		
		//키값 뽑아오기
		Set keys = prop.keySet();
		
		ArrayList<String> list = new ArrayList<String>(keys); //list라는 배열에 뽑아온 키를 넣는다.
		
		for (int i = 0; i < list.size(); i++) {			//0부터 list의 사이즈까지 증감키겨 반복한다.
			String key = list.get(i);					//list 배열의 i번째의 키값을 가져온다.
			String sclass = (String)prop.get(key);		//scass라는 변수에 문자열 타입으로 properties의 키값을 가져온다.
			
			//실행 가능한 클래스로 변환 해준다.
			try {
				Class tmp = Class.forName(sclass);		//클래스 타입으로 sclass의 이름을 가져온다.
				HomeController home = (HomeController) tmp.newInstance();
				
				map.put(key, home);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//실제 요청 내용에 맞는 처리를 담당하는 함수

	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//1. 요청내용을 알아낸다.
		String full = req.getRequestURI();
		System.out.println("요청내용 : "+full);
		//2. http://location/Home/member/login.home에서 도메인을 알아낸다.
		String domain = req.getContextPath();
		System.out.println("도메인"+domain);
		//3. 실제 요청내용을 알아낸다.
		String realPath = full.substring(domain.length());
		System.out.println("##### realPath : "+realPath);
		
		//4. 요청내용을 알아냈으니 실제 실행할 클래스를 가져온다.
		HomeController home = map.get(realPath);
		req.setAttribute("isRedirect", false);  	//속성값을 isRedirect로 설정하고 false로 지정한다.
		String view = home.exec(req, resp);			//뷰를 서버 리퀘스트와 서버리스폰스 객체를 이용해서 실행 시키기 위한 변수
		System.out.println(home);
		boolean bool = (boolean)req.getAttribute("isRedirect");
		
		if (bool) {
			resp.sendRedirect(view);   			//서버 리스폰스 객체를 이용해 뷰를 보낸다.
		}else {
			try {
				RequestDispatcher rd = req.getRequestDispatcher(view);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
