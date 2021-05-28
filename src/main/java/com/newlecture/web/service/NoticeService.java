package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	public List<Notice> getNoticeList(){
		
		return getNoticeList("title", "", 1);
	}

	public List<Notice> getNoticeList(int page){
			
		return getNoticeList("title", "", page);
	}

	public List<Notice> getNoticeList(String field/*TITLE, WRITER_ID */, String query/*A*/, int page){
		
		/* 목록 List 개체를 만든다 */
		List<Notice> list = new ArrayList<>(); // ctr+shift+O로 패키지 추가
		/* field는 문자열의 값으로 들어가지 않기 때문에 ?로 채울수 없다. */
		String sql = "SELECT * FROM( "
				+ "SELECT ROWNUM NUM, N.* "
				+ "FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N "
				+ ") "
				+ "WHERE NUM BETWEEN ? AND ?";
		
		//1, 11, 21, 31, .. -> an = 1+(page-1)*10
		// 10, 20, 30, 40 -> page*10
			
		String url = "jdbc:oracle:thin:@localhost:1521/XE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "programmers", "111111");
			/* ?가 포함된 쿼리문을 사용 할 겨웅에는 preparedStatement를 사용한다. */
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			
			ResultSet rs = st.executeQuery();

			/* 아래에 있던 코드블럭을 가져와서 위의 코드를 HTML과 JAVA로 분리 */
			while(rs.next()){
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				
				/* Notice라는 개체를 이용해서 */
				Notice notice = new Notice(
						id,
						title,
						writerId,
						regdate,
						hit,
						files,
						content
					);
				list.add(notice);	
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		/* count를 통해 검색결과 데이터가 몇개인지 알아낸다. -> 출력 할때 몇 페이지 수를 만들기 위해서 */
		int count = 0;
		/* COUNT(ID)는 아이디의 개수를 알아낸다. */
		String sql = "SELECT COUNT(ID) COUNT FROM( "
				+ "SELECT ROWNUM NUM, N.* "
				+ "FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N "
				+ ") ";
		
		String url = "jdbc:oracle:thin:@localhost:1521/XE";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "programmers", "111111");
			/* ?가 포함된 쿼리문을 사용 할 겨웅에는 preparedStatement를 사용한다. */
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			
			ResultSet rs = st.executeQuery();
			/* 결과 저장소에 있는 count를 받아서 count변수에 저장한다. */
			count  = rs.getInt("count");
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* count 값을 반환 */
		return count;
	}
	
	public Notice getNotice(int id) {
		/* notice객체를 null로 선언 */
		Notice notice = null;
		/* ID검색을 통해 모든 내용을 출력*/
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/XE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "programmers", "111111");
			/* ?가 포함된 쿼리문을 사용 할 겨웅에는 preparedStatement를 사용한다. */
			PreparedStatement st = con.prepareStatement(sql);
			/* id는 숫자라서 int형으로*/
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();

			if(rs.next()){
				/* id가 위의 id와 충돌, nid로 사용*/
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				
				/* Notice라는 개체를 이용해서 */
				notice = new Notice(
						nid,
						title,
						writerId,
						regdate,
						hit,
						files,
						content
					);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public Notice getNextNotice(int id) {
		Notice notice = null;
		/* 다음페이지 구현*/
		String sql = "SELECT * FROM NOTICE "
				+ "WHERE ID  = ( "
				+ "SELECT ID FROM NOTICE "
				+ "WHERE REGDATE > (SELECT REGDATE  FROM NOTICE WHERE ID=?) "
				+ "AND ROWNUM = 1)";
		
		String url = "jdbc:oracle:thin:@localhost:1521/XE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "programmers", "111111");
			/* ?가 포함된 쿼리문을 사용 할 겨웅에는 preparedStatement를 사용한다. */
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();

			/* 아래에 있던 코드블럭을 가져와서 위의 코드를 HTML과 JAVA로 분리 */
			if(rs.next()){
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				
				/* Notice라는 개체를 이용해서 */
				notice = new Notice(
						nid,
						title,
						writerId,
						regdate,
						hit,
						files,
						content
					);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}

	public Notice getPrevNotice(int id) {
		Notice notice = null;
		/* 이전페이지 구현 */
		String sql = "SELECT ID FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) "
				+ "WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID=?) AND ROWNUM = 1";
		
		String url = "jdbc:oracle:thin:@localhost:1521/XE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "programmers", "111111");
			/* ?가 포함된 쿼리문을 사용 할 겨웅에는 preparedStatement를 사용한다. */
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();

			/* 아래에 있던 코드블럭을 가져와서 위의 코드를 HTML과 JAVA로 분리 */
			if(rs.next()){
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				
				/* Notice라는 개체를 이용해서 */
				notice = new Notice(
						nid,
						title,
						writerId,
						regdate,
						hit,
						files,
						content
					);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return notice;
	}
}