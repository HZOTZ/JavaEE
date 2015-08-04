package com.core;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class Dbconn {
	  public Connection conn = null;
	  public Statement stmt = null;
	  public ResultSet rs = null;
	  private static String propFileName = "/com/dbconn.properties";	//指定资源文件保存的位置
	  private static Properties prop = new Properties();
	  private static String dbClassName ="com.mysql.jdbc.Driver";
	  private static String dbUrl =
	      "jdbc:mysql://127.0.0.1:3306/librarybook";
	  public Dbconn(){
	    try {
	      InputStream in=getClass().getResourceAsStream(propFileName);
	      prop.load(in);									//通过输入流对象加载Properties文件
	      dbClassName = prop.getProperty("DB_CLASS_NAME");	//获取数据库驱动
	      dbUrl = prop.getProperty("DB_URL",
	                               "jdbc:mysql://127.0.0.1:3306/librarybook");
	    }
	    catch (Exception e) {
	      e.printStackTrace();		//输出异常信息
	    }
	  }

	  public static Connection getConnection() {
	    Connection conn = null;
	    try {
//	    	System.out.println(dbClassName);
	      Class.forName(dbClassName).newInstance();
	      conn = DriverManager.getConnection(dbUrl,"root","");
	      }
	    catch (Exception ee) {
	      ee.printStackTrace();
	    }
	    if (conn == null) {
	      System.err.println(
	          "警告: DbConnectionManager.getConnection() 获得数据库链接失败.\r\n\r\n链接类型:" +
	          dbClassName + "\r\n链接位置:" + dbUrl);
	    }
	    return conn;
	  }
	  
	  

		/*
		 * 测试数据库连接是否正常
		 * 
		 * public static void main(String[] args) {
			
			try
			{
			   
			   if(dbconn.getConnection()!=null)
			   {     
				   Statement stmt; //创建声明  
	            	stmt = dbconn.getConnection().createStatement();
	            	String selectSql = "SELECT * FROM admin";  
	                ResultSet selectRes = stmt.executeQuery(selectSql);  
	                while (selectRes.next()) { //循环输出结果集  
	                    String username = selectRes.getString("username");  
	                    String password = selectRes.getString("passwd");  
	                    System.out.print("\r\n\r\n");  
	                    System.out.println("username:\n" + username + "\npassword:\n" + password);  
	                }  
				   System.out.println("数据库连接正常！");
			   }
			   else
			   {
				   System.out.println("数据库连接异常！");
			   }
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		}/**/

	/*
	 * 功能：执行查询语句
	 */
	public ResultSet executeQuery(String sql) {
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return rs;
	}

	/*
	 * 功能:执行更新操作
	 */
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			conn = getConnection();					//调用getConnection()方法构造Connection对象的一个实例conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);		//执行更新操作
		} catch (SQLException ex) {
			result = 0;
		}
		return result;
	}

	/*
	 * 功能:关闭数据库的连接
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
