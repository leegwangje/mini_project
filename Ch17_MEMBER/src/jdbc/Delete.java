package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Delete {

	public static void main(String[] args) {
		
				// 오라클 데이터베이스와 연결하는 객체
				Connection con =null;
				
				//	SQL문을 데이터베이스에 전송하는 객체.
				PreparedStatement pstmt=null;
				
				//	SQL문을 실행한 후에 결과값을 가지고 있는 객체.
				// ResultSet rs= null;
				
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				
				String user="basic";
				
				String password= "1234";
				
				Scanner sc= new Scanner(System.in);
				
				System.out.print("삭제할 회원 번호 입력 : ");
				String num = sc.nextLine();
				
				try {
					// 1단계 : 오라클 드라이버 동적으로 메모리로 로딩.
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					// 2단계 : 오라클 데이터베이스와 연결 시도.
					con =DriverManager.getConnection(url, user, password);
								
					// 3단계 : 데이터베이스에 전송할 SQL문 작성.
					String sql = "delete from member10 where num=?";
					
					pstmt=con.prepareStatement(sql);
					
					pstmt.setInt(1, Integer.parseInt(num));
					
								
					// 4단계 : 데이터베이스에 SQL문을 전송 및 실행.
					int res= pstmt.executeUpdate();
					
					if(res >0) {
						System.out.println("회원 삭제 성공!!!");
					}else {
						System.out.println("회원 삭제 실패~~~");
					}
					
					// 중간에 있는 회원 번호 삭제 시 번호 수정 작업 진행.
					sql ="update member10 set num = num-1 where num>?";
					
					pstmt =con.prepareStatement(sql);
					
					pstmt.setInt(1, Integer.parseInt(num));
					
					pstmt.executeUpdate();
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}finally {
					// 5단계 : 연결되어 있던 자원을 종료시켜줘야 한다.
					try {
						
						if(pstmt!=null)pstmt.close();
						if(con!= null)con.close();
						if(sc!=null) sc.close();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}

		}

	}
