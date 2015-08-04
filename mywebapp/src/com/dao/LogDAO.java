package com.dao;

import java.sql.*;


public class LogDAO {
	public boolean check(String name, String password) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet res = null;
		int num = 0;
		String sql = "select count(*) as CNT from admin where username= ? and passwd = ?";
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
			stm.setString(2, password);
			res = stm.executeQuery();
			if (res.next()) {
				num = res.getInt("CNT");
				res.close();
				stm.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (num > 0)
			return true;
		else
			return false;
	}

}