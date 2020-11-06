package com.jdc.mdy.service;

import static com.jdc.mdy.utils.ConnectionManager.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.jdc.mdy.entity.Item;
import com.jdc.mdy.entity.Stock;
import com.jdc.mdy.entity.StockDetail;
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
			stmt.setTimestamp(2, Timestamp.valueOf(stock.getDate()));
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

	public int totalStockInByItem(Item item,boolean isStockIn) {
		String sql = "select sum(sd.qty) from stock_detail_tbl sd left join stock_tbl s on sd.stock_id=s.id where s.isStockIn=? and sd.item_id=?";
		int total = 0;

		try (Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setBoolean(1, isStockIn);
			stmt.setInt(2, item.getId());		
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				total=rs.getInt(1);
				return total;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new StockException(e.getMessage());
		}
		
		return 0;
	}
}
