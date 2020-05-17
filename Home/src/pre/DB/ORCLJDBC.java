package pre.DB;

import java.sql.*;
public class ORCLJDBC {
	public ORCLJDBC(){
		
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//커넥션 얻어오는 함수
	public Connection getCon() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "hello";
		String password = "hello";
		try {
			con = DriverManager.getConnection(url, user, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//statemenet얻어오는 함수 
	public Statement getSTMT(Connection con) {
		Statement stmt = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	//PreparedStatement 얻어오는 함수
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	//필요가 없을 경우 대신 닫아주는 함수
	public void close(Object o) {
		try {
			if(o instanceof Connection) {
				((Connection) o).close();
			} else if( o instanceof Statement) {
				((Statement) o).close();
			} else if(o instanceof PreparedStatement) {
				((PreparedStatement) o).close();
			} else if(o instanceof ResultSet) {
				((ResultSet) o ).close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
