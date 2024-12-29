package com.course.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.course.util.LoggerUtil;

public class ConnectionManager
{

	static
	{

	}

	public static final String db_drivers = "com.mysql.cj.jdbc.Driver";
	public static final String DB_PORT = "3306";
	public static final String hostName = "localhost";
	public static final String db_UserName = "root";
	public static final String db_password = "";
	public static final String db_name = "courses";

	public static Connection con;
	static String url;

	public static void getConn()
	{
		con = ConnectionManager.getConnection();
	}

	public static Connection getConnection()
	{

		try
		{
			// String url = "jdbc:odbc:" + "DataSource";
			// assuming "DataSource" is your DataSource name

			Class.forName(db_drivers);

			try
			{
				con = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + DB_PORT + "/" + db_name
						+ "?allowPublicKeyRetrieval=true&useSSL=false", db_UserName, db_password);
//        	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/winkdoc?allowPublicKeyRetrieval=true&useSSL=false", "root", "Welcome*123"); ; 
				LoggerUtil.log("\u001B[1m_________________Winkdoc Database Connected__________________\u001B[0m");
			}

			catch (SQLException ex)
			{
				LoggerUtil.log(
						"\u001B[1m_________________Error In Connecting Winkdoc Database__________________\u001B[0m");
				ex.printStackTrace();
			}
		}

		catch (ClassNotFoundException e)
		{
			LoggerUtil.log("\u001B[1m_________________Re-Connecting With Winkdoc Database__________________\u001B[0m");
			ConnectionManager.getConn();
			e.printStackTrace();
		} catch (Exception e)
		{
			LoggerUtil.log(
					"\u001B[1m_________________Something Went Wrong Re-Connecting With Winkdoc Database__________________\u001B[0m");
			ConnectionManager.getConn();
			e.printStackTrace();
		}

		return con;
	}

	public static Connection getNewTempConnection()
	{
		Connection tempConnection = null;
		try
		{
			// String url = "jdbc:odbc:" + "DataSource";
			// assuming "DataSource" is your DataSource name

			Class.forName(db_drivers);

			try
			{
				tempConnection = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + DB_PORT + "/" + db_name
						+ "?allowPublicKeyRetrieval=true&useSSL=false", db_UserName, db_password);
//        	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/winkdoc?allowPublicKeyRetrieval=true&useSSL=false", "root", "Welcome*123"); ; 
				LoggerUtil.log("\u001B[1m_________________Winkdoc Database Connected__________________\u001B[0m");
			}

			catch (SQLException ex)
			{
				LoggerUtil.log(
						"\u001B[1m_________________Error In Connecting Winkdoc Database__________________\u001B[0m");
				ex.printStackTrace();
			}
		}

		catch (ClassNotFoundException e)
		{
			LoggerUtil.log("\u001B[1m_________________Re-Connecting With Winkdoc Database__________________\u001B[0m");
			ConnectionManager.getConn();
			e.printStackTrace();
		} catch (Exception e)
		{
			LoggerUtil.log(
					"\u001B[1m_________________Something Went Wrong Re-Connecting With Winkdoc Database__________________\u001B[0m");
			ConnectionManager.getConn();
			e.printStackTrace();
		}

		return tempConnection;
	}
}