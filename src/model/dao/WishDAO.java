package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.vo.WishVO;

public class WishDAO {

	private Connection connectDBCP() {
		Connection conn = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/oraDB");
			conn = ds.getConnection();
		
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public boolean insert(WishVO vo) {
		Connection conn = connectDBCP();
		boolean curr_situation = true;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			pstmt1 = conn.prepareStatement("insert into wishlist values (seq_id.nextval, ?, ?, ?, sysdate, 0)");
			pstmt2 = conn.prepareStatement("insert into whylist values (seq_id.currval, ?)");
			pstmt1.setString(1, vo.getCategory());
			pstmt1.setString(2, vo.getTitle());
			pstmt1.setString(3, vo.getContent());
			pstmt2.setString(1, vo.getReason());
			pstmt1.executeUpdate();
			pstmt2.executeUpdate();
		} catch (Exception e) {
			curr_situation = false;
			e.printStackTrace();
		} finally {
			try {
				if (pstmt1 != null)
					pstmt1.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("close 오류 발생");
			}
		}
		return curr_situation;
	}

	public boolean update(WishVO vo) {
		boolean curr_situation = true;
		Connection conn = connectDBCP();
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			pstmt1 = conn.prepareStatement("update WishList set category=?, title=?, content=? where id=?");
			pstmt1.setString(1, vo.getCategory());
			pstmt1.setString(2, vo.getTitle());
			pstmt1.setString(3, vo.getContent());
			pstmt1.setInt(4, vo.getId());
			pstmt1.executeUpdate();

			pstmt2 = conn.prepareStatement("update whylist set reason = ? where id = ?");
			pstmt2.setString(1, vo.getReason());
			pstmt2.setInt(2, vo.getId());
			pstmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt1 != null)
					pstmt1.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("close 오류 발생");
			}
		}
		return curr_situation;
	}

	public boolean delete(int id) {
		boolean curr_situation = true;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = connectDBCP();
			stmt = conn.createStatement();
			String query1 = "delete wishlist where id = " + id;
			String query2 = "delete whylist where id =" + id;
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("close 오류 발생");
			}
		}
		return curr_situation;
	}

	public ArrayList<WishVO> loadCate(String cateName) {
		ArrayList<WishVO> list = null;
		Connection conn = connectDBCP();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query1 = "select * from wishlist join whylist on wishlist.id=whylist.id where = "+ cateName;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query1);

			list = new ArrayList<WishVO>();

			while (rs.next()) {
				WishVO vo = new WishVO();
				vo.setId(rs.getInt(1));
				vo.setCategory(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setWritedate(rs.getString(5));
				vo.setCnt(rs.getInt(6));
				vo.setReason(rs.getString(8));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("close 오류 발생");
			}
		}

		return list;

	}

	public WishVO loadOne(int id) {
		WishVO vo = null;
		Connection conn = connectDBCP();
		ResultSet rs = null;
		Statement stmt = null;
		String updateCnt = "update wishlist set cnt=cnt+1 where id =" + id;
		String query = "select wishlist.id, wishlist.category, wishlist.title, wishlist.content, wishlist.writedate, wishlist.cnt, whylist.reason "
				+ "from wishlist join whylist on wishlist.id = whylist.id where wishlist.id = " + id;
		try {

			stmt = conn.createStatement();
			stmt.executeUpdate(updateCnt);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				vo = new WishVO();
				vo.setId(rs.getInt(1));
				vo.setCategory(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setWritedate(rs.getString(5));
				vo.setCnt(rs.getInt(6));
				vo.setReason(rs.getString(8));
			}
		} catch (Exception e) {

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("close 오류 발생");
			}
		}

		return vo;
	}

	public ArrayList<Integer> cnt() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		//문제가 생기면 DB에 한글을 보낼때 깨짐 현상에 대해서 생각해보자
		String[] query = {
				"select count(*) from wishlist where category ='게임'",
				"select count(*) from wishlist where category ='취미'",
				"select count(*) from wishlist where category ='책'",
				"select count(*) from wishlist where category ='관계'",
				"select count(*) from wishlist where category ='직업'"	
		};
		String cntGame = query[0];
		String cntHobby = query[1];
		String cntBook = query[2];
		String cntRelatn = query[3];
		String cntJob = query[4];
		try {
			conn = connectDBCP();
			stmt = conn.createStatement();
			
			for(String index : query){
				rs = stmt.executeQuery(index);
				while(rs.next())
					result.add(rs.getInt(1));
				}			
			for(Integer index : result)
				System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
