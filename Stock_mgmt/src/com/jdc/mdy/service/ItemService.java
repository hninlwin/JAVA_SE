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
		
		String sql="insert into item_tbl(name,price,category_id,active)values(?,?,?,?)";
		
		try(Connection con=getConnection();
				PreparedStatement stmt=con.prepareStatement(sql)){
			
			stmt.setString(1, item.getName().toLowerCase());
			stmt.setInt(2, item.getPrice());
			stmt.setInt(3, item.getCategory().getId());
			stmt.setBoolean(4, true);
			stmt.executeUpdate();
			
			MessageManager.showMessage("Successfully Save Item", MessageStyle.success);
		}catch (Exception e) {
			throw new StockException(e.getMessage());
		}
		
	}

	public List<Item>findAll(String item,Category category) {
		
		StringBuilder sb= new StringBuilder("select i.id,i.name,i.price,i.active,c.id,c.name from item_tbl i left join category_tbl c on i.category_id=c.id where i.active=1");
		List<Item>list=new ArrayList<>();
		List<Object>temp=new ArrayList<>();
		
		if(item !=null && !item.isEmpty()) {
			temp.add(item.toLowerCase());
			sb.append(" and i.name=?");
		}
		if(category!=null) {
			temp.add(category.getId());
			sb.append(" and i.category_id=?");
		}
		
		try(Connection con=getConnection();
				PreparedStatement stmt=con.prepareStatement(sb.toString())){
			
			for(int i=0;i<temp.size();i++) {
				stmt.setObject(i+1, temp.get(i));
			}
			
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				
				Item i=new Item();
				Category c=new Category();
				i.setId(rs.getInt("i.id"));
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
