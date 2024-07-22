package com.FK.Comp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4J_Programming {
	// Enable the logging
	private static Logger logger = LoggerFactory.getLogger(SLF4J_Programming.class);

	public static void main(String[] args) {
		logger.debug("Start of main method ");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			logger.debug("try block executon started");
			// Load the sql driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.debug("JDBC Driver Loading Succed");
			// Established the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "c##class", "alok");
			logger.info("JDBC connection succed");
			// create jdbc statement object
			if (con != null) {
				stmt = con.createStatement();
				logger.debug("Createing a Statement object");
			}
			// sent a query to Database S/W and return a resultset
			if (stmt != null) {
				rs = stmt.executeQuery("select * from emp");
				logger.debug("Sending a query to database product and returning a resultset");
			}
			// Process the resultset object
			if (rs != null) {
				while (rs.next()) {
					System.out
							.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4));
					logger.warn("Retriving the Data from Database Software");
				} // while
				logger.debug("Knowing the ResultSet is null or not null");
			} // if
		} // try
		catch (SQLException e) {
			e.printStackTrace();
			logger.error("getting the SQLException" + e.getMessage());
		} // catch
		catch (Exception e) {
			e.printStackTrace();
			logger.error("getting the UnKnown Error" + e.getMessage());
		} // catch
		finally {
			logger.debug("finally block execution start");
			try {
				if (rs != null) {
					rs.close();
					logger.debug("ResultSet object connection close");
				}
			} // try
			catch (Exception e) {
				e.printStackTrace();
				logger.error("Finally block resultset error");
			} // catch
			try {
				if (stmt != null) {
					stmt.close();
					logger.debug("Statement object close");
				}
			} // try
			catch (Exception e) {
				e.printStackTrace();
				logger.error("Statement Object not close successfully");
			} // catch
			try {
				if (con != null) {
					con.close();
					logger.debug("Connection object connection close");
				}
			} // try
			catch (Exception e) {
				e.printStackTrace();
				logger.error("Connection object failed to close");
			} // catch
			logger.debug("Finally block execution completed");
		} // finally
		logger.debug("main method execution completed");
	}
}
