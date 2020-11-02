package com.jdc.mdy.service;

import com.jdc.mdy.entity.User;
import com.jdc.mdy.entity.User.Role;
import com.jdc.mdy.utils.StockException;

import static com.jdc.mdy.utils.ConnectionManager.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

	public User searchId(String text) {
		
		String sql="select * from user_tbl where loginId=?";
		
		try(Connection con=getConnection();
			PreparedStatement stmt=con.prepareStatement(sql)){
			
			stmt.setString(1, text);
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setLoginId(rs.getString("loginId"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setActive(rs.getBoolean("active"));
				
				return user;
			}
			
		}catch (Exception e) {
			throw new StockException("Db searchId error");
		}
		
		return null;
	}

}
