package com.barsha.selenium.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

public class DatabaseHelper {
	private Connection conn = null;

	protected Properties config = new Properties();

	public DatabaseHelper() {
		try {
			config.load(new FileInputStream("src/main/resources/selenium.properties"));
			Class.forName(config.getProperty("database.driver.class"));
			conn = DriverManager.getConnection(
					config.getProperty("database.url"), 
					config.getProperty("database.username"), 
					config.getProperty("database.password")
				);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTestDataLen() {
		return Integer.parseInt(config.getProperty("testDataLen"));
	}

	protected Object[][] query(String sql) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.setMaxRows(getTestDataLen());
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData meta = rs.getMetaData();

		int columns = meta.getColumnCount();
		Object[] col = new Object[columns];
		Object[][] rows = new Object[getTestDataLen()][columns];

		int rowCtr = 0;
		while (rs.next()) {
			col = new Object[columns];
			for (int i = 0; i < columns; i++) {
				col[i] = rs.getObject(i + 1);
			}
			rows[rowCtr++] = col;
		}

		if (rowCtr < getTestDataLen()) {
			return Arrays.copyOfRange(rows, 0, rowCtr);
		}
		return rows;
	}

	public static void main(String[] args) throws Exception {
		DatabaseHelper helper = new DatabaseHelper();
		helper.testConnection();
	}

	public void testConnection() throws Exception {
		Object[][] rows = query("select login_name,pass_word,user_role,first_name from asrim_users");
		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < rows[i].length; j++) {
				System.out.print(rows[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
