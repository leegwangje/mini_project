package jdbc;

import java.sql.*;

public class Select {

	public static void main(String[] args) {
		
		//	오라클 데이터베이스와 연결하는 객체
		Connection con =null;
		
		//	SQL문을 데이터베이스에 전송하는 객체. 
		PreparedStatement pstmt=null;
		
		//	SQL문을 실행한 후에 결과값을 가지고 있는 객체.
		ResultSet rs= null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String user="basic";
		
		String password= "1234";
		try {
			//	1단계 : 오라클 드라이버를 동적으로 메모리로 로딩.
			//	==> 동적 로딩 : 프로그램을 실행할 때 드라이버를 로딩.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("오라클 연결 성공 !!!");	
			
			// 2단계 : 자바와 오라클 데이터베이스와의 연결시도
			con = DriverManager.getConnection(url, user, password);
			
			if(con != null) {
				System.out.println("데이터베이스 연결 성공");
			}
			
			System.out.println();
			
			// 3단계 : 데이터베이스에 전송할 SQL문 작성.
			String sql="select * from member10 order by num ";
			
			pstmt = con.prepareStatement(sql);
			
			// 4단계 : 데이터베이스에 SQL문 전송 및 실행.
			rs = pstmt.executeQuery();
			
			// 5단계 : 결과 데이터를 반복하여 가져와서 출력.
			while(rs.next()) {
				
				int num = rs.getInt("num");
				String id= rs.getString("memid");
				String name =rs.getString("memname");
				String pwd= rs.getString("pwd");
				int age= rs.getInt("age");
				int mileage= rs.getInt("mileage");
				String job= rs.getString("job");
				String addr= rs.getString("addr");
				String date =rs.getString("regdate").substring(0,10);
				
				System.out.println(num+"\t"+ id+"\t"+name+"\t"+pwd+"\t"+age
						+"\t"+mileage+"\t"+job+"\t"+addr+"\t"+date);
				
				System.out.println("::::::::::::::::::::::::::::::");
				
			}	// while 반복문 end
			
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}	finally {
			//	6단계 : 연결되어 있던 자원을 종료해 주자.
			try {
				rs.close();
				pstmt.close();
				con.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		

	}

}
