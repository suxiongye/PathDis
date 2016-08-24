package util;

import java.sql.DriverManager;

import bean.Config;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBConnect {
	public Connection conn = null;
	public PreparedStatement pst = null;

	/**
	 * 数据库初始化
	 */
	public DBConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(Config.URL
					+ Config.DB_NAME, Config.DB_USER, Config.DB_PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库链接
	 * @return
	 */
	public Connection getConnect() {
		try {
			if (this.conn != null && this.conn.isClosed() != true)
				return this.conn;
			else {

				Class.forName("com.mysql.jdbc.Driver");
				conn = (Connection) DriverManager.getConnection(Config.URL
						+ Config.DB_NAME, Config.DB_USER, Config.DB_PASSWORD);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库
	 */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//test
	public static void main(String[] args) {
		DBConnect db = new DBConnect();
		db.close();

	}
}
