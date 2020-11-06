package com.jdc.mdy.service;

import static com.jdc.mdy.utils.ConnectionManager.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jdc.mdy.entity.Supplier;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.StockException;
import com.jdc.mdy.utils.MessageManager.MessageStyle;

public class SupplierService {

	public void save(String text) {
		String sql="insert into supplier_tbl(name,active)values(?,?)";
		try(Connection con=getConnection();PreparedStatement stmt=con.prepareStatement(sql)){
			
			stmt.setString(1, text);
			stmt.setBoolean(2, true);
			
			stmt.executeUpdate();
			
			MessageManager.showMessage("Successfully Save Supplier !", MessageStyle.success);
		}catch (Exception e) {
			throw new StockException(e.getMessage());
		}
		
	}

	public List<Supplier >findAll() {
		String sql="select * from supplier_tbl where active=1";
		List<Supplier>list=new ArrayList<>();
		
		try(Connection con=getConnection();
				PreparedStatement stmt=con.prepareStatement(sql)){
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				Supplier c=new Supplier();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setActive(rs.getBoolean("active"));
				list.add(c);
			}
			
		
		}catch (Exception e) {
			throw new StockException(e.getMessage());
		}
		return list;
	}
}
