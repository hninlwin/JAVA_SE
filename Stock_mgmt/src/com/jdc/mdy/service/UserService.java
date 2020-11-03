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
import java.util.ArrayList;
import java.util.List;

public class UserService {

	public User searchId(String text) {

		String sql = "select * from user_tbl where loginId=?";

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, text);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginId(rs.getString("loginId"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setActive(rs.getBoolean("active"));

				return user;
			}

		} catch (Exception e) {
			throw new StockException("Db searchId error");
		}

		return null;
	}

	public void saveUser(User user) {
		String sql = "insert into user_tbl (name,loginId,password,role,active)values(?,?,?,?,?)";

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getLoginId());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getRole().name());
			stmt.setBoolean(5, user.isActive());

			stmt.executeUpdate();
			MessageManager.showMessage("successfully save user !", MessageStyle.success);

		} catch (Exception e) {
			throw new StockException("Db saveUser error");
		}
	}

	public List<User> findAll() {
		String sql = "select * from user_tbl where active=1";
		List<User> list = new ArrayList<>();

		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginId(rs.getString("loginId"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setActive(true);
				list.add(user);
			}

		} catch (Exception e) {
			throw new StockException("Db search error");
		}
		return list;
	}

	public void update(User user) {

		String sql = "update user_tbl set name=?,password=?,role=?,active=? where loginId=?";

		try (Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getRole().name());
			stmt.setBoolean(4, user.isActive());
			stmt.setString(5, user.getLoginId());
			
			stmt.executeUpdate();
			MessageManager.showMessage("successfully update user !", MessageStyle.success);


		} catch (Exception e) {
			throw new StockException("Db search error");
		}
	}
	
	public List<User> search(String name){
		String sql = "select * from user_tbl where active=1 and name=?";
		List<User>list=new ArrayList<>();
		
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginId(rs.getString("loginId"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setActive(rs.getBoolean("active"));

				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new StockException("Db searchId error");
		}

		return list;
	}
}
