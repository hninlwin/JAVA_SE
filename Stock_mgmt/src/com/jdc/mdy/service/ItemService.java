package com.jdc.mdy.service;

import static com.jdc.mdy.utils.ConnectionManager.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jdc.mdy.entity.Category;
import com.jdc.mdy.entity.Item;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.MessageManager.MessageStyle;
import com.jdc.mdy.utils.StockException;

public class ItemService {

	public void save(Item item) {
		
		String sql="insert into item_tbl(name,price,category_tbl,active)values(?,?,?,?)";
		
		try(Connection con=getConnection();
				PreparedStatement stmt=con.prepareStatement(sql)){
			
			stmt.setString(1, item.getName());
			stmt.setInt(2, item.getPrice());
			stmt.setInt(3, item.getCategory().getId());
			stmt.setBoolean(4, true);
			stmt.executeUpdate();
			
			MessageManager.showMessage("Successfully Save Item", MessageStyle.success);
		}catch (Exception e) {
			throw new StockException(e.getMessage());
		}
		
	}

	public List<Item>findAll() {
		String sql="select i.name,i.price,i.active,c.id,c.name from item_tbl i left join category_tbl c on i.category_tbl=c.id where i.active=1";
		List<Item>list=new ArrayList<>();
		
		try(Connection con=getConnection();
				PreparedStatement stmt=con.prepareStatement(sql)){
			
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				
				Item i=new Item();
				Category c=new Category();
				
				i.setName(rs.getString("i.name"));
				i.setPrice(rs.getInt("i.price"));
				c.setId(rs.getInt("c.id"));
				c.setName(rs.getString("c.name"));
				i.setCategory(c);
				i.setActive(rs.getBoolean("i.active"));
				list.add(i);
				
			}
		}catch (Exception e) {
			throw new StockException(e.getMessage());
		}
		return list;
	}
}
