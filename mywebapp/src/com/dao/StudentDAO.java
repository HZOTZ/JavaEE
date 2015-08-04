package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import com.core.Dbconn;
import com.model.Student;

public class StudentDAO {
	private Dbconn conn = new Dbconn();
	// 运用语句块初始化一下
	private StudentDAO studentDAO = null;
	{
		this.studentDAO = new StudentDAO();
	}

	public Collection<Student> query(String str) {
		Student student = null;
		Collection<Student> bookColl = new ArrayList<Student>();
		String sql = "";
		if (str != "all" && str != null && str != "") {
			sql = "select * from student where studentId='" + str + "'";
		} else {
			sql = "select * from student";
		}
		System.out.println("执行查询sql" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				student = new Student();
				student.setStudentId(rs.getString(1));
				student.setStudentName(rs.getString(2));
				student.setAge(Integer.parseInt(rs.getString(3)));
				
				bookColl.add(student);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return bookColl;
	}

	public Student queryM(Student student) {
		Student student1 = null;
		String sql = "select * from student where studentName='" + student.getStudentName() + "'";
		System.out.println("执行查询SQL" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				student1 = new Student();
				student1.setStudentId(rs.getString(1));
				student1.setStudentName(rs.getString(2));
				student1.setAge(Integer.parseInt(rs.getString(3)));
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return student1;
	}



}
