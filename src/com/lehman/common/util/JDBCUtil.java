package com.lehman.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Test;

enum DBType {
	ORACLE, SQLSERVER, MYSQL, DB2, SYBASE;
}

public class JDBCUtil {
	private static DBType dateBase = DBType.MYSQL; // 数据库类型
	private static String DBName = "my_db"; // 数据库名称
	private static String Ip = "127.0.0.1"; // 数据库服务器IP
	private static String port = "3306"; // 数据库服务器端口
	private static String userName = "root"; // 用户名
	private static String passWord = "root"; // 密码

	/**
	 * 获得数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		String url = "";
		String driverUrl = "";
		if ( Ip == null || Ip.length() < 7 ) {
			Ip = "127.0.0.1";
			// IP = "localhost";
		}
		switch ( dateBase ) {
		case ORACLE:
			driverUrl = "oracle.jdbc.driver.OracleDriver";
			url = "jdbc:oracle:thin:@" + Ip + ":" + port + ":" + DBName;
			break;
		case SQLSERVER:
			driverUrl = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
			url = "jdbc:microsoft:sqlserver://" + Ip + ":" + port + ";databaseName=" + DBName;
			break;
		case DB2:
			driverUrl = "com.ibm.db2.jdbc.net.DB2Driver";
			url = "jdbc:db2://" + Ip + ":" + port + "/" + DBName;
			break;
		case MYSQL:
			driverUrl = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://" + Ip + ":" + port + "/" + DBName;
			break;
		case SYBASE:
			driverUrl = "com.sybase.jdbc2.jdbc.SybDriver";
			url = "jdbc:sybase:Tds:" + Ip + ":" + port;
			break;
		}
		try {
			Class.forName( driverUrl );
			System.out.println( url );
			conn = DriverManager.getConnection( url, userName, passWord );
			System.out.println( DBName + "：数据库连接打开成功！" );
		} catch ( Exception e ) {
			System.err.println( DBName + ":数据库连接打开失败！" );
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 */
	public static boolean DBClose( Connection conn, Statement pre, ResultSet rs ) {
		try {
			if ( rs != null )
				rs.close();
			if ( pre != null )
				pre.close();
			if ( conn != null )
				conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Map<String, String>> query( String sql ) {
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();

		try {
			Connection conn = getConnection();
			if ( !conn.isClosed() ) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery( sql );

				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				// 输出数据
				while ( rs.next() ) {
					Map<String, String> column = new LinkedHashMap<String, String>();
					for ( int i = 1; i <= columnCount; i++ ) {
						String title = rsmd.getColumnName( i );
						column.put( title, rs.getString( i ) );
					}
					results.add( column );
				}
				DBClose( conn, stmt, rs );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return results;
	}

	@Test
	public void test() {
		String sql = "select * from user";
		List<Map<String, String>> resutls = query( sql );
		for ( Map<String, String> resutl : resutls ) {
			for ( Entry<String, String> entry : resutl.entrySet() ) {
				System.out.print( entry.getKey() + " : " + entry.getValue() + "    " );
			}
			System.out.println();
		}
	}
}