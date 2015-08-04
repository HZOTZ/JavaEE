package com.dao;

import com.core.Dbconn;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import com.core.Dbconn;
import com.model.Admin;
import com.model.Student;

public class AdminDAO {

	private Dbconn conn = new Dbconn();
	// 运用语句块初始化一下
	private AdminDAO adminDAO = null;
	{
		this.adminDAO = new AdminDAO();
	}

	public Collection<Admin> query(String str) {
		Admin admin = null;
		Collection<Admin> bookColl = new ArrayList<Admin>();
		String sql = "";
		if (str != "all" && str != null && str != "") {
			sql = "select * from admin where Id='" + str + "'";
		} else {
			sql = "select * from admin";
		}
		System.out.println("执行查询sql" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				admin = new Admin();
				admin.setId(Integer.parseInt(rs.getString(1)));
				admin.setUsername(rs.getString(2));
				admin.setPasswd(rs.getString(3));
				bookColl.add(admin);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return bookColl;
	}

	public Admin queryM(Admin admin) {
		Admin admin1 = null;
		String sql = "select * from admin where username='"
				+ admin.getUsername() + "'";
		System.out.println("执行查询SQL" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				admin1 = new Admin();
				admin1.setId(Integer.parseInt(rs.getString(1)));
				admin1.setUsername(rs.getString(2));
				admin1.setPasswd(rs.getString(3));
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return admin1;
	}

	public int insert(Admin admin) {
		String sql1 = "SELECT * FROM admin WHERE Id ='" + admin.getId() + "'";
		ResultSet rs = conn.executeQuery(sql1);
		String sql = "";
		int flag = 0;
		try {
			if (rs.next()) {
				flag = 1;
			} else {
				sql = "Insert into admin (Id,username,passwd) values('"
						+ admin.getId() + "','" + admin.getUsername() + "','"
						+ admin.getPasswd() + "')";
				flag = conn.executeUpdate(sql);
				System.out.println("执行插入SQL" + sql);
				conn.close();
			}
		} catch (SQLException ex) {
			flag = 0;
		}
		System.out.println("flag:" + flag);
		return flag;
	}

	public int update(Admin admin) {
		String sql = "Update admin set username='" + admin.getUsername()
				+ "',passwd='" + admin.getPasswd() + "' where Id='"
				+ admin.getId() + "'";
		int falg = conn.executeUpdate(sql);
		System.out.println("执行更新SQL" + sql);
		conn.close();
		return falg;
	}

	public int delete(Admin admin) {
		String sql_1 = "SELECT * FROM admin WHERE Id='" + admin.getId() + "'";
		ResultSet rs = conn.executeQuery(sql_1);
		int flag = 0;
		try {
			if (!rs.next()) {
				String sql = "Delete from admin where Id='" + admin.getId()
						+ "'";
				flag = conn.executeUpdate(sql);
				System.out.println("执行删除SQL" + sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 添加，删除，修改学生方法
	public int insert(Student student) {
		String sql1 = "SELECT * FROM student WHERE studentId ='"
				+ student.getStudentId() + "'";
		ResultSet rs = conn.executeQuery(sql1);
		String sql = "";
		int flag = 0;
		try {
			if (rs.next()) {
				flag = 1;
			} else {
				sql = "Insert into student (studentId,studentName,username,passwd,email,age) values('"
						+ student.getStudentId()
						+ "','"
						+ student.getStudentName()
						+ "','"
						+ student.getUsername()
						+ "','"
						+ student.getPasswd()
						+ "','"
						+student.getEmail()
						+"','"
						+student.getAge()
						+"')";
				flag = conn.executeUpdate(sql);
				System.out.println("执行插入SQL" + sql);
				conn.close();
			}
		} catch (SQLException ex) {
			flag = 0;
		}
		System.out.println("flag:" + flag);
		return flag;
	}

	public int update(Student student) {
		String sql = "Update student set studentName='"
				+ student.getStudentName() + "',username='"
				+ student.getUsername() + "',passwd='" + student.getPasswd()
				+ "',email='" + student.getEmail() + "',age='"
				+ student.getAge() + "'," + "'where studentId='"
				+ student.getStudentId() + "'";
		int falg = conn.executeUpdate(sql);
		System.out.println("执行更新SQL" + sql);
		conn.close();
		return falg;
	}

	public int delete(Student student) {
		String sql_1 = "SELECT * FROM student WHERE studentId='"
				+ student.getStudentId() + "'";
		ResultSet rs = conn.executeQuery(sql_1);
		int flag = 0;
		try {
			if (!rs.next()) {
				String sql = "Delete from student where studentId='"
						+ student.getStudentId() + "'";
				flag = conn.executeUpdate(sql);
				System.out.println("执行删除SQL" + sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
