package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import com.model.BookClass;
import com.core.Dbconn;

public class BookClassDAO {
	private Dbconn conn = new Dbconn();
	// 运用语句块初始化一下
	private BookClassDAO bookClassDAO = null;
	{
		this.bookClassDAO = new BookClassDAO();
	}

	public Collection<BookClass> query(String str) {
		BookClass bookClass = null;
		Collection<BookClass> bookColl = new ArrayList<BookClass>();
		String sql = "";
		if (str != "all" && str != null && str != "") {
			sql = "select * from bookClass where classId='" + str + "'";
		} else {
			sql = "select * from bookClass";
		}
		System.out.println("执行查询sql" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				bookClass = new BookClass();
				bookClass.setClassId(Integer.valueOf(rs.getString(1)));
				bookClass.setClassName(rs.getString(2));
				bookColl.add(bookClass);
				System.out.print(bookClass.getClassName());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return bookColl;
	}

	public BookClass queryM(BookClass bookClass) {
		BookClass bookClass1 = null;
		String sql = "select * from bookClass where classId='"
				+ bookClass.getClassId() + "'";
		System.out.println("执行查询SQL" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				bookClass1 = new BookClass();
				bookClass1.setClassId(Integer.valueOf(rs.getString(1)));
				bookClass1.setClassName(rs.getString(2));
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return bookClass1;
	}

	public int insert(BookClass bookClass) {
		String sql1 = "SELECT * FROM bookClass WHERE className ='"
				+ bookClass.getClassName() + "'";
		ResultSet rs = conn.executeQuery(sql1);
		String sql = "";
		int flag = 0;
		try {
			if (rs.next()) {
				flag = 1;
			} else {
				sql = "Insert into bookClass (classId,className) values('"
						+ bookClass.getClassId() + "','"
						+ bookClass.getClassName() + "')";
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

	public int update(BookClass bookClass) {
		String sql = "Update bookClass set className='"
				+ bookClass.getClassName() + "' where classId='"
				+ bookClass.getClassId() + "'";
		int flag = conn.executeUpdate(sql);
		System.out.println("执行更新SQL" + sql);
		conn.close();
		return flag;
	}

	public int delete(BookClass bookClass) {
		String sql_1 = "SELECT * FROM bookClass WHERE classId='"
				+ bookClass.getClassId() + "'";
		ResultSet rs = conn.executeQuery(sql_1);
		int flag = 0;
		try {
			if (!rs.next()) {
				String sql = "Delete from bookClass where classId='"
						+ bookClass.getClassId() + "'";
				flag = conn.executeUpdate(sql);
				System.out.println("执行删除SQL" + sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
