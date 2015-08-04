package com.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterChr implements Filter {
	public static String toChinese(String str) {
		try {
			if (str == null) {										//当变量str为null时
				str="";											//将变量str赋值为空
			} else {
				str = new String(str.getBytes("ISO8859_1"), "utf8");	//将字符串转换为GBK编码
				str = str.trim();								//去除字符串的首尾空格
			}
		} catch (Exception e) {
			str="";												//将变量str赋值为空
		}
		return str;												//返回转换后的输入变量str
	}

	// 处理字符串中的空值
	public static final String nullToString(String v, String toV) {
	    if (v == null || "".equals(v)) {			//当输入变量v为空时
	        v = toV;						//将输入变量v赋值为输入变量toV
	    }
	    return v;							//返回转换后的输入变量v
	}
	//过滤危险字符
	public static final String filterStr(String str){
		str=str.replaceAll(";","");
		str=str.replaceAll("&","&amp;");
		str=str.replaceAll("<","&lt;");
		str=str.replaceAll(">","&gt;");
		str=str.replaceAll("'","");
		str=str.replaceAll("--"," ");
		str=str.replaceAll("/","");
		str=str.replaceAll("%","");
		return str;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
