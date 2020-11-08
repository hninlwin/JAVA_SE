package com.jdc.mdy.service;

import static com.jdc.mdy.utils.ConnectionManager.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jdc.mdy.entity.Category;
import com.jdc.mdy.entity.Item;
import com.jdc.mdy.entity.Stock;
import com.jdc.mdy.entity.StockDetail;
import com.jdc.mdy.entity.Supplier;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.StockException;
import com.jdc.mdy.utils.MessageManager.MessageStyle;

public class StockService {

	public void save(Stock stock) {
		String sql = "insert into stock_tbl (isStockIn,stock_date,user_id,supplier_id,active)values(?,?,?,?,?)";
		int stock_id = 0;

		try (Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setBoolean(1, stock.isStockIn());
			stmt.setDate(2,Date.valueOf(stock.getDate()));
			stmt.setInt(3, stock.getUser().getId());
			stmt.setInt(4, stock.getSupplier() == null ? 1 : stock.getSupplier().getId());
			stmt.setBoolean(5, stock.isActive());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				stock_id = rs.getInt(1);
			}

			if (stock_id > 0) {
				saveStockDetail(stock_id, stock.getStDetails());
			}

			MessageManager.showMessage("Successfully Save Stock", MessageStyle.success);
		} catch (Exception e) {
			throw new StockException(e.getMessage());
		}
	}

	private void saveStockDetail(int stock_id, List<StockDetail> stDetails) {

		String sql = "insert into stock_detail_tbl (qty,active,item_id,stock_id)values(?,?,?,?)";

		try (Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			for (StockDetail stockDetail : stDetails) {
				stmt.setInt(1, stockDetail.getQty());
				stmt.setBoolean(2, stockDetail.isActive());
				stmt.setInt(3, stockDetail.getItem().getId());
				stmt.setInt(4, stock_id);

				stmt.executeUpdate();
			}

			MessageManager.showMessage("Successfully Save StockDetail", MessageStyle.success);
		} catch (Exception e) {
			throw new StockException(e.getMessage());
		}
	}

	public int totalStockInByItem(Item item, boolean isStockIn) {
		String sql = "select sum(sd.qty) from stock_detail_tbl sd left join stock_tbl s on sd.stock_id=s.id where s.isStockIn=? and sd.item_id=?";
		int total = 0;

		try (Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setBoolean(1, isStockIn);
			stmt.setInt(2, item.getId());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
				return total;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new StockException(e.getMessage());
		}

		return 0;
	}

	public List<StockDetail> search(Category cat,Supplier supplier,String name,boolean isStockIn,LocalDate from,LocalDate to) {

		StringBuilder sb= new StringBuilder("select i.id,i.name,i.active,c.id,c.name,i.price,sd.id,sd.qty,sup.id,sup.name,s.id,s.stock_date,s.isStockIn "
				+ "from stock_detail_tbl sd join item_tbl i on sd.item_id=i.id"
				+ " join stock_tbl s on sd.stock_id=s.id "
				+ "join category_tbl c on i.category_id=c.id "
				+ "join supplier_tbl sup on s.supplier_id=sup.id where sd.active=1");
		
		List<StockDetail>list=new ArrayList<>();
		List<Object>temp=new ArrayList<>();
		
		if(name !=null && !name.isEmpty()) {
			temp.add(name.toLowerCase());
			sb.append(" and i.name=?");
		}
		if(cat!=null) {
			temp.add(cat.getId());
			sb.append(" and i.category_id=?");
		}
		if(supplier!=null) {
			temp.add(supplier.getId());
			sb.append(" and sup.id=?");
		}
		
		if(isStockIn) {
			temp.add(isStockIn);
			sb.append(" and s.isStockIn=?");
		}
		if(from!=null) {
			temp.add(from);
			sb.append(" and s.stock_date >=?");
			
		}
		
		if(to!=null) {
			temp.add(to);
			sb.append(" and s.stock_date <=?");
			
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
				StockDetail sd=new StockDetail();
				Supplier sup=new Supplier();
				Stock s=new Stock();
				s.setId(rs.getInt("s.id"));
				s.setDate(rs.getDate("s.stock_date").toLocalDate());
				s.setStockIn(rs.getBoolean("s.isStockIn"));
				
				
				sup.setId(rs.getInt("sup.id"));
				sup.setName(rs.getString("sup.name"));
				
				sd.setId(rs.getInt("sd.id"));
				sd.setQty(rs.getInt("sd.qty"));
				
				c.setId(rs.getInt("c.id"));
				c.setName(rs.getString("c.name"));
				
				i.setId(rs.getInt("i.id"));
				i.setName(rs.getString("i.name"));
				i.setPrice(rs.getInt("i.price"));				
				i.setActive(rs.getBoolean("i.active"));
				
				i.setCategory(c);
				s.setSupplier(sup);
				sd.setItem(i);
				sd.setStock(s);
				
				list.add(sd);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new StockException(e.getMessage());
		}
		return list;
	}
}
