package com.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Student;
import com.dao.UserLogDAO;

public class UserchkLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserchkLoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		Student stu = new Student();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		stu.setUsername(username);
		stu.setPasswd(password);
		UserLogDAO userlog = new UserLogDAO();

		if (stu.getUsername() != null && stu.getUsername() != ""
				&& stu.getPasswd() != null && stu.getPasswd() != "") {

			if (userlog.check(stu.getUsername(), stu.getPasswd())) {
				request.getRequestDispatcher("/userMenu.jsp").forward(request, response);;
//				response.sendRedirect(request.getContextPath()
//						+ "/userMenu.jsp");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/failLogin.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/userindex.jsp");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		System.out.println("Init starting..");
	}

}
