package com.dw.test;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Driver;

public class Pro1 {
	
	private final static String DB_DRIVER = "com.mysql.jdbc.Driver";
	private final static String DB_URL = "jdbc:mysql://localhost:3306/daiwei?useUnicode=true&characterEncoding=utf-8";
	private final static String DB_USER = "root";
	private final static String DB_PASSWORD = "root";
	
	public static void main(String[] args) throws Exception {
		//Pro1.mytestProcNoOut();
		//Pro1.mytestProcNoIN();
		//Pro1.mytest2ProcNoOut();
		//Pro1.bachPro();
		Pro1.mytest3Proc();
	}
	
	public static void mytestProcNoOut() throws Exception{
		System.out.println("----- start 测试调用存储过程：无返回值 --------");
		Connection conn = null;
		CallableStatement callSt = null;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			callSt = conn.prepareCall("call mytest(?, ?, ?, ?)");
			callSt.setString(1, "jdbc");
			callSt.setString(2, "JDBC");
			callSt.setDouble(3, 8000.23);
			callSt.setString(4, "http://sjsky.it.com");
			callSt.execute();
			System.out.println("----- Test END . ---------");
		}catch(Exception e){
			e.printStackTrace(System.out);
		}finally {
			if(null != callSt)
				callSt.close();
			if(null != conn)
				conn.close();
		}
	}
	
	public static void mytestProcNoIN() throws Exception{
		System.out.println("----- start 测试调用存储过程：有返回值 --------");
		Connection conn = null;
		CallableStatement callSt = null;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			callSt = conn.prepareCall("call mytest1(?)");
			callSt.setInt(1, java.sql.Types.INTEGER);
			callSt.execute();
			System.out.println("count = " + callSt.getInt(1));
			System.out.println("----- Test END . ---------");
		}catch(Exception e){
			e.printStackTrace(System.out);
		}finally {
			if(null != callSt)
				callSt.close();
			if(null != conn)
				conn.close();
		}
	}
	
	public static void mytest2ProcNoOut() throws Exception{
		System.out.println("----- start 测试2调用存储过程：无返回值 --------");
		Connection conn = null;
		CallableStatement callSt = null;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			callSt = conn.prepareCall("call mytest2(?, ?, ?, ?)");
			callSt.setString(1, "jdbc2");
			callSt.setString(2, "JDBC2");
			callSt.setDouble(3, 8242.23);
			callSt.setString(4, "www.baidu.com");
			callSt.execute();
			System.out.println("----- Test END . ---------");
		}catch(Exception e){
			e.printStackTrace(System.out);
		}finally {
			if(null != callSt)
				callSt.close();
			if(null != conn)
				conn.close();
		}
	}
	
	public static void bachPro() throws Exception{
		System.out.println("----- start bach调用存储过程：无返回值 --------");
		Connection conn = null;
		CallableStatement callSt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			callSt = conn.prepareCall("call bach_pro()");
			callSt.execute();
			rs = callSt.getResultSet();
			//遍历第一个结果集
			while(rs.next()) {
				System.out.println(rs.getString("user_id"));
			}
			System.out.println("----- tmp_michael ---------");
			//获取下一个结果集
			while(callSt.getMoreResults()) {
				rs1 = callSt.getResultSet();
				while(rs1.next()) {
					System.out.println(rs1.getString("user_id"));
				}
			}
			System.out.println("----- Test END . ---------");
		}catch(Exception e) {	
			e.printStackTrace(System.out);
		}finally {
			if(null != callSt)
				callSt.close();
			if(null != conn)
				conn.close();
		}
	}
	
	public static void mytest3Proc() throws Exception{
		System.out.println("----- start mytest3调用存储过程：无返回值 --------");
		Connection conn = null;
		CallableStatement callSt = null;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			callSt = conn.prepareCall("call mytest3(?, ?, ?)");
			callSt.setString(1, "lisi");
			callSt.setDouble(2, 0.68);
			callSt.setBigDecimal(3, new BigDecimal(0));
			callSt.execute();
			System.out.println("lisi's salary is " + callSt.getBigDecimal(3));
			System.out.println("----- Test END . ---------");
		}catch(Exception e) {
			e.printStackTrace(System.out);
		}finally {
			if(null != callSt)
				callSt.close();
			if(null != conn)
				conn.close();
		}
	}
}
