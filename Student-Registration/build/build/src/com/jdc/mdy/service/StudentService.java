package com.jdc.mdy.service;

import com.jdc.mdy.model.Contact;
import com.jdc.mdy.model.Contact.City;
import com.jdc.mdy.model.Student;
import com.jdc.mdy.utis.Message;
import com.jdc.mdy.utis.Message.MStyle;
import static com.jdc.mdy.utis.ConnectionManager.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

	public void save(Student st) {

		String sql = "insert into contact_tbl (phone,city,address)values(?,?,?)";
		try (Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, st.getContact().getPhone().toLowerCase());
			stmt.setString(2, st.getContact().getCity().name());
			stmt.setString(3, st.getContact().getAddress().toLowerCase());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			int contact_id = 0;
			while (rs.next()) {
				contact_id = rs.getInt(1);
			}
			saveStudent(st, contact_id);
			Message.showMessage("Successfully save student ", MStyle.SUCCESS);
		} catch (Exception e) {
			Message.showMessage(e.getMessage(), MStyle.ERROR);
		}
	}

	public void saveStudent(Student st, int id) {

		String sql = "insert into student_tbl (name,roll,contact_id,active)values(?,?,?,?)";
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, st.getName().toLowerCase());
			stmt.setString(2, st.getRoll().toLowerCase());
			stmt.setInt(3, id);
			stmt.setBoolean(4, true);
			
			
			stmt.executeUpdate();

		} catch (Exception e) {
			
			Message.showMessage(e.getMessage(), MStyle.ERROR);
		}
	}

	public List<Student> findStudents(Student stu) {

		List<Student> list = new ArrayList<Student>();
		List<Object> temp = new ArrayList<>();

		StringBuilder sb = new StringBuilder(
				"select s.id,s.name,s.roll,c.city,c.id,c.phone,c.address from Student_tbl s join Contact_tbl c on s.contact_id=c.id where active=1");

		if (null != stu) {
			
			if (stu.getName() != null && !stu.getName().isEmpty()) {
				
				sb.append(" and s.name=?");
				temp.add(stu.getName().toLowerCase());
			}
			if (stu.getRoll() != null && !stu.getRoll().isEmpty()) {
				sb.append(" and s.roll=?");
				temp.add(stu.getRoll().toLowerCase());
			}
			if (stu.getCity() != null) {
				sb.append(" and c.city=?");
				temp.add(stu.getCity().name());
			}
			if (stu.getPhone() != null && !stu.getPhone().isEmpty()) {
				sb.append(" and c.phone=?");
				temp.add(stu.getPhone().toLowerCase());
			}
		}
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sb.toString())) {

			for (int i = 0; i < temp.size(); i++) {
				stmt.setObject(i + 1, temp.get(i));
			}
			

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Student st = new Student();
				Contact c = new Contact();
				st.setId(rs.getInt("s.id"));
				st.setName(rs.getString("s.name"));
				st.setRoll(rs.getString("s.roll"));
				c.setId(rs.getInt("c.id"));
				c.setCity(City.valueOf(rs.getString("c.city")));
				c.setPhone(rs.getString("c.phone"));
				c.setAddress(rs.getString("c.address"));
				st.setContact(c);

				list.add(st);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Message.showMessage(e.getMessage(), MStyle.ERROR);
		}
		return list;
	}

	public void update(Student student) {
		String sql = "update Contact_tbl  set phone=?,city=?,address=? where id=?";
		try (Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, student.getContact().getPhone().toLowerCase());
			stmt.setString(2, student.getContact().getCity().name());
			stmt.setString(3, student.getContact().getAddress().toLowerCase());
			stmt.setInt(4, student.getContact().getId());
			
			stmt.executeUpdate();
			
			updateStudent(student);
			
			Message.showMessage("Successfully update student ", MStyle.SUCCESS);
		} catch (Exception e) {
			Message.showMessage(e.getMessage(), MStyle.ERROR);
		}
	}
	
	public void updateStudent(Student st) {

		String sql = "update Student_tbl set name=?,roll=?,active=? where id=?";
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, st.getName().toLowerCase());
			stmt.setString(2, st.getRoll().toLowerCase());
			stmt.setBoolean(3, st.isActive());
			stmt.setInt(4, st.getId());
			stmt.executeUpdate();

		} catch (Exception e) {
			
			Message.showMessage(e.getMessage(), MStyle.ERROR);
		}
	}

}
