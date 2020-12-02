package com.jdc.mdy.service;

import com.jdc.mdy.model.Student;
import com.jdc.mdy.utis.Message;
import com.jdc.mdy.utis.Message.MStyle;
import static com.jdc.mdy.utis.ConnectionManager.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentService {

	public void save(Student st) {
		
		String sql="insert into contact_tbl (phone,city,address)values(?,?,?)";
		try(Connection con=getConnection();PreparedStatement stmt=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			
			stmt.setString(1, st.getContact().getPhone());
			stmt.setString(2, st.getContact().getCity().name());
			stmt.setString(3, st.getContact().getAddress());			
			stmt.executeUpdate();
			ResultSet rs=stmt.getGeneratedKeys();			
			int contact_id=0;		
			while(rs.next()) {
				contact_id=rs.getInt(1);
			}		
			saveStudent(st, contact_id);
			Message.showMessage("Successfully save student ", MStyle.SUCCESS);			
		}catch (Exception e) {		
			Message.showMessage(e.getMessage(), MStyle.ERROR);
		}
	}
	
	public void saveStudent(Student st,int id) {
		
		String sql="insert into student_tbl (name,roll,contact_id)values(?,?,?)";
		try(Connection con=getConnection();PreparedStatement stmt=con.prepareStatement(sql)){
			
			stmt.setString(1, st.getName());
			stmt.setString(2, st.getRoll());
			stmt.setInt(3, id);			
			stmt.executeUpdate();
			
		}catch (Exception e) {
			Message.showMessage(e.getMessage(), MStyle.ERROR);}
	}
	
}
