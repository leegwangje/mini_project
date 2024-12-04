package jdbc;

import java.sql.*;

import java.util.Scanner;

public class Update {

	public static void main(String[] args) {
		
		Connection con= null;
		
		PreparedStatement pstmt= null;
					
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		
		String user="basic";
		
		String password ="1234";
		
		Scanner sc= new Scanner(System.in);
		
		System.out.print("수정할 회원 번호 입력 : ");
		String num = sc.nextLine();
	
		System.out.print("수정할 회원 나이 입력 : ");
		String age= sc.nextLine();
		
		System.out.print("수정할 회원 마일리지 입력 : ");
		String mileage= sc.nextLine();
		
		System.out.print("수정할 회원 직업 입력 : ");
		String job= sc.nextLine();
		
		System.out.print("수정할 회원 주소 입력 : ");
		String addr= sc.nextLine();
		
		try {
			// 1단계 : 오라클 드라이버 동적으로 메모리로 로딩.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2단계 : 오라클 데이터베이스와 연결 시도.
			con =DriverManager.getConnection(url, user, password);
			
			// 3단계 : 데이터베이스에 전송할 SQL문 작성.
			String sql = "update member10 SET age = ?, mileage = ?, job = ?, addr = ? WHERE num = ?";
					
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(age));
			pstmt.setInt(2, Integer.parseInt(mileage));
			pstmt.setString(3, job);
			pstmt.setString(4, addr);
			pstmt.setInt(5, Integer.parseInt(num));
					
			// 4단계 : 데이터베이스에 SQL문을 전송 및 실행.
			int res= pstmt.executeUpdate();
			
			if(res >0) {
				System.out.println("회원 수정 성공!!!");
			}else {
				System.out.println("회원 수정 실패~~~");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			// 5단계 : 연결되어 있던 자원을 종료시켜줘야 한다.
			try {
					pstmt.close();
					con.close();
					sc.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}

}

}