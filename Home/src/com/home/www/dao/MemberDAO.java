package com.home.www.dao;

import java.sql.*;
import com.home.www.DB.*;
import com.home.www.sql.*;
public class MemberDAO {
	JDBC db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	MemberSQL mSQL;
	
	public MemberDAO() {
		db = new JDBC();
		mSQL = new MemberSQL();
	}
	
	//로그인 데이터베이스 처리 전담 함수
	public int getLoginCnt(String id, String pw) {
		int cnt = 0;
		con = db.getCon();
		
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN_CNT);
		
		//pstmt가져오기
		pstmt = db.getPSTMT(con, sql);
		try {
			//질의 명령 완성
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			//질의 명령보내고 결과 받고
			rs = pstmt.executeQuery();
			//데이터 꺼내서 변수에 담고
			rs.next();
			cnt = rs.getInt("cnt");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
}
