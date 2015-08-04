package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import com.model.BookInfo;
import com.core.Dbconn;

public class BoookInfoDAO {

	private Dbconn conn = new Dbconn();
	// 运用语句块初始化一下
	private BoookInfoDAO bookInfoDAO = null;
	{
		this.bookInfoDAO = new BoookInfoDAO();
	}

	public Collection<BookInfo> query(String str) {
		BookInfo bookInfo = null;
		Collection<BookInfo> bookColl = new ArrayList<BookInfo>();
		String sql = "";
		if (str != "all" && str != null && str != "") {
			sql = "select * from bookInfo where ISBN='" + str + "'";
		} else {
			sql = "select * from bookInfo";
		}
		System.out.println("执行查询sql" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				bookInfo = new BookInfo();
				bookInfo.setISBN(rs.getString(1));
				bookInfo.setClassId(Integer.valueOf(rs.getString(2)));
				bookInfo.setBookName(rs.getString(3));
				bookInfo.setAuthor(rs.getString(4));
				bookInfo.setPublisher(rs.getString(5));
				bookInfo.setPublishTime(rs.getDate(6));
				bookColl.add(bookInfo);
				System.out.print(bookInfo.getISBN());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return bookColl;
	}

	public BookInfo queryM(BookInfo bookInfo) {
		BookInfo bookInfo1 = null;
		String sql = "select * from bookInfo where ISBN='"
				+ bookInfo.getISBN() + "'";
		System.out.println("执行查询SQL" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				bookInfo1 = new BookInfo();
				bookInfo1.setISBN(rs.getString(1));
				bookInfo1.setClassId(Integer.valueOf(rs.getString(2)));
				bookInfo1.setBookName(rs.getString(3));
				bookInfo1.setAuthor(rs.getString(4));
				bookInfo1.setPublisher(rs.getString(5));
				bookInfo1.setPublishTime(rs.getDate(6));
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return bookInfo1;
	}

	public int insert(BookInfo bookInfo) {
		BookInfo bookInfo_id = null;
		String sql1 = "SELECT * FROM bookInfo WHERE ISBN ='"
				+ bookInfo.getISBN() + "'";
		ResultSet rs = conn.executeQuery(sql1);
		String sql = "";
		int flag = 0;
		try {
			if (rs.next()) {
				flag = 1;
			} else {
				sql = "Insert into bookInfo (ISBN,classId,bookName,author,publisher,publishTime) values('"
						+ bookInfo.getISBN()
						+ "','"
						+ bookInfo.getClassId()
						+ "','"
						+ bookInfo.getBookName()
						+ "','"
						+ bookInfo.getAuthor()
						+ "','"
						+ bookInfo.getPublisher()
						+ "','"
						+ bookInfo.getPublishTime() + ")";
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

}
