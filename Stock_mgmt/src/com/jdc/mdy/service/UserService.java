package com.jdc.mdy.service;

import com.jdc.mdy.entity.User;
import com.jdc.mdy.entity.User.Role;
import com.jdc.mdy.utils.StockException;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.MessageManager.MessageStyle;

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

	public void saveUser(User user) {
		String sql="insert into user_tbl (name,loginId,password,role,active)values(?,?,?,?,?)";
		
		try(Connection con=getConnection();
				PreparedStatement stmt=con.prepareStatement(sql)){
				
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getLoginId());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getRole().name());
			stmt.setBoolean(5, true);
			
			stmt.executeUpdate();
			MessageManager.showMessage("successfully save user !",MessageStyle.success);
			
		}catch (Exception e) {
			throw new StockException("Db saveUser error");
		}
	}

}
