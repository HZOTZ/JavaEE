package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.model.BookClass;
import com.model.BorrowInfo;
import com.core.Dbconn;

public class BorrowInfoDAO {

	private Dbconn conn = new Dbconn();
	// 运用语句块初始化一下
	private BorrowInfoDAO borrowInfoDAO = null;
	{
		this.borrowInfoDAO = new BorrowInfoDAO();
	}

	public Collection<BorrowInfo> query(String str) {
		BorrowInfo borrowInfo = null;
		Collection<BorrowInfo> bookColl = new ArrayList<BorrowInfo>();
		String sql = "";
		if (str != "all" && str != null && str != "") {
			sql = "select * from borrowInfo where ISBN='" + str + "'";
		} else {
			sql = "select * from borrowInfo";
		}
		System.out.println("执行查询sql" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				borrowInfo = new BorrowInfo();
				borrowInfo.setId(Integer.valueOf(rs.getString(1)));
				borrowInfo.setISBN(rs.getString(2));
				borrowInfo.setStudentId(rs.getString(3));
				borrowInfo.setReturnFlag(Boolean.valueOf((rs.getString(4))));
				borrowInfo.setBorrowDate((rs.getDate((5))));
				borrowInfo.setReturnDate((rs.getDate(6)));
				bookColl.add(borrowInfo);
				System.out.print(borrowInfo.getISBN());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return bookColl;
	}

	public BorrowInfo queryM(BorrowInfo borrowInfo) {
		BorrowInfo borrowInfo1 = null;
		String sql = "select * from borrowInfo where ISBN='"
				+ borrowInfo.getISBN() + "'";
		System.out.println("执行查询SQL" + sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				borrowInfo1 = new BorrowInfo();
				borrowInfo1.setId(Integer.valueOf(rs.getString(1)));
				borrowInfo1.setISBN((rs.getString(2)));
				borrowInfo1.setStudentId(rs.getString(3));
				borrowInfo1.setReturnFlag(Boolean.valueOf(rs.getString(4)));
				borrowInfo1.setBorrowDate(rs.getDate(5));
				borrowInfo1.setReturnDate(rs.getDate(6));
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return borrowInfo1;
	}

	public int insert(BorrowInfo borrowInfo) {//借书后，标志量returnflag为1
		BorrowInfo borrowInfo_id = null;
		String sql1 = "SELECT * FROM borrowInfo WHERE ISBN ='"
				+ borrowInfo.getISBN() + "'";
		ResultSet rs = conn.executeQuery(sql1);
		String sql = "";
		int flag = 0;
		try {
			if (rs.next()) {
				flag = 1;
			} else {
				sql = "Insert into borrowInfo (id,ISBN,studentId,returnFlag,borrowDate,returnDate) values('"
						+ borrowInfo.getId()
						+ "','"
						+ borrowInfo.getISBN()
						+ "','"
						+ borrowInfo.getStudentId()
						+ "','"
						+ borrowInfo.getReturnFlag()
						+ "','"
						+ borrowInfo.getBorrowDate()
						+ "','"
						+ borrowInfo.getReturnDate() + ")";
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
	
	public int update(BorrowInfo borrowInfo) {//还书后借书记录的标志量returnflag为0
		String sql = "Update borrowInfo set returnFlag='"
				+ borrowInfo.getReturnDate() + "' where ISBN='"
				+ borrowInfo.getISBN() + "'";
		int flag = conn.executeUpdate(sql);
		System.out.println("执行更新SQL" + sql);
		conn.close();
		return flag;
	}
	
	public int delete(BorrowInfo borrowInfo) {
		String sql_1 = "SELECT * FROM borrowInfo WHERE ISBN='"
				+ borrowInfo.getISBN() + "'";
		ResultSet rs = conn.executeQuery(sql_1);
		int flag = 0;
		try {
			if (!rs.next()) {
				String sql = "Delete from borrowInfo where ISBN='"
						+ borrowInfo.getISBN() + "'";
				flag = conn.executeUpdate(sql);
				System.out.println("执行删除SQL" + sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
