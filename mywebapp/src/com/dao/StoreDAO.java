package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import com.model.Store;
import com.core.Dbconn;

public class StoreDAO {
	private Dbconn conn = new Dbconn();
	// 运用语句块初始化一下
	private StoreDAO storeDAO = null;
	{
		this.storeDAO = new StoreDAO();
	}

	public Collection<Store> query(String str) {
		Store store = null;
		Collection<Store> bookColl = new ArrayList<Store>();
		String sql = "";
		if (str != "all" && str != null && str != "") {
			sql = "select * from store where ISBN='" + str + "'";
		} else {
			sql = "select * from store";
		}
		System.out.println("执行查询sql" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				store = new Store();
				store.setISBN(rs.getString(1));
				store.setCount(Integer.valueOf(rs.getString(2)));
				bookColl.add(store);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return bookColl;
	}

	public Store queryM(Store store) {
		Store store1 = null;
		String sql = "select * from store where ISBN='" + store.getISBN() + "'";
		System.out.println("执行查询SQL" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				store1 = new Store();
				store1.setISBN(rs.getString(1));
				store1.setCount(Integer.valueOf(rs.getString(2)));
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return store1;
	}

	public int insert(Store store) {
		String sql1 = "SELECT * FROM store WHERE ISBN ='" + store.getISBN()
				+ "'";
		ResultSet rs = conn.executeQuery(sql1);
		String sql = "";
		int flag = 0;
		try {
			if (rs.next()) {
				flag = 1;
			} else {
				sql = "Insert into store (ISBN,bookName,count) values('"
						+ store.getISBN() + "','" + store.getBookName() + "','"
						+ store.getISBN() + "')";
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

	public int update(Store store) {
		String sql = "Update store set bookName='" + store.getBookName()+",count='"+store.getCount()
				+ "' where ISBN='" + store.getISBN() + "'";
		int falg = conn.executeUpdate(sql);
		System.out.println("执行更新SQL" + sql);
		conn.close();
		return falg;
	}

	public int delete(Store store) {
		String sql_1 = "SELECT * FROM store WHERE ISBN='" + store.getISBN()
				+ "'";
		ResultSet rs = conn.executeQuery(sql_1);
		int flag = 0;
		try {
			if (!rs.next()) {
				String sql = "Delete from store where ISBN='" + store.getISBN()
						+ "'";
				flag = conn.executeUpdate(sql);
				System.out.println("执行删除SQL" + sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
