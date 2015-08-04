package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegDAO {

	public boolean insert(String name, String password, String email) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet res = null;
		int resi = 0;
		int num = 0;
		String sql = "select * from student where username=?";
		String sqli = "insert into student (username,passwd,email) values(?,?,?)";
		// String sql =
		// "select count(*) as CNT from admin where username= ? and passwd = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/librarybook", "root", "");

			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			res = stm.executeQuery();
			if (!res.next()) {
				stm.close();
				stm = conn.prepareStatement(sqli);
				stm.setString(1, name);
				stm.setString(2, email);
				stm.setString(3, password);
				resi = stm.executeUpdate();

				if (resi != 0) {
					num = resi;
					stm.close();
					conn.close();
				}
				if (num > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
