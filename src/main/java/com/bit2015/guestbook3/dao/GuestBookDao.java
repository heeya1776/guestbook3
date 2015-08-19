package com.bit2015.guestbook3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.pool.OracleDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2015.guestbook3.exception.DeleteException;
import com.bit2015.guestbook3.exception.GetListException;
import com.bit2015.guestbook3.exception.InsertException;
import com.bit2015.guestbook3.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	OracleDataSource dataSource;
	
	// insert
	public void insert(GuestBookVo vo) {
		
		try{
			
			Connection conn = dataSource.getConnection();
			
			String sql = "insert" +
						 " into guestbook" +
						 " values (guestbook_seq.nextval, ?, ?, ?, sysdate)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException ex){
			throw new InsertException(ex.toString());
		}
		
	}
	
	// delete
	public void delete(GuestBookVo vo) {
		
		try{
			
			Connection conn = dataSource.getConnection();
			
			String sql = "delete" + 
						 " from guestbook" +
						 " where no=?"+
						 " and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException ex){
			throw new DeleteException(ex.toString());
		}
			
	}
	//getList
	public List<GuestBookVo> getList() {
		
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		try{
			
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			
			String sql = "select no, name, password, message, to_char(reg_date, 'YYYY-MM-DD HH:MM:SS') from guestbook order by reg_date desc";
		
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				long no = rs.getLong(1);
				String name = rs.getString(2);
				//String password = rs.getString(3);
				String message = rs.getString(4);
				String regDate = rs.getString(5);
				
				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				//vo.setPassword(password);
				vo.setMessage(message);
				vo.setRegDate(regDate);
				
				list.add(vo);
				
			}
			
			rs.close();
			stmt.close();
			conn.close();
		
		}catch(SQLException ex){
			throw new GetListException(ex.toString());
		}
		
		return list;
	}
}
