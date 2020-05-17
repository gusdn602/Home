package com.home.www.DB;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import javax.swing.*;
public class JDBC {
	//커넥션 풀을 관리할 클래스 변수 주ㅗㄴ비
	public DataSource ds;
	
	public JDBC() {
		try {
			//1. context.xml파일에 등록된 자원을 알아낸다.
			InitialContext context = new InitialContext();
			//2. 그 중에서 필요한 자원을 얻어낸다.
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/TestDB");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "커넥션 풀 가져오기 실패!!!");
		}
		
	}
	public Connection getCon() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//Statement 반환해주는 함수
			public Statement getSTMT(Connection con) {
				Statement stmt = null;
				try {
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return stmt;
			}
			
	//PreparedStatement 반환해주는 함수
			public PreparedStatement getPSTMT(Connection con, String sql) {
				PreparedStatement pstmt = null;
				try {
					pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return pstmt;
			}
			
	//사용하지 않는 자원 반환해 주는 함수.
		public void close(Object o) {
			try {
			if(o instanceof Connection) {
				((Connection) o).close();
			} else if(o instanceof Statement){
				((Statement)o).close();
			} else if(o instanceof PreparedStatement) {
				((PreparedStatement)o).close();
			} else if(o instanceof ResultSet) {
				((ResultSet)o).close();
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
}
