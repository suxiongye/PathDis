package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DBConnect;
import bean.Config;
import bean.PathHistory;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class HistoryController {
	public static DBConnect dbConnection = null;
	public static Connection conn = null;

	/*
	 * 初始化数据链接
	 */
	public static void Init() {
		if (dbConnection == null) {
			dbConnection = new DBConnect();
			conn = dbConnection.getConnect();
		}
	}

	/**
	 * 寻找所有路径记录
	 * @return
	 */
	public static ArrayList<PathHistory> findAllPath() {
		Init();
		ArrayList<PathHistory> pathHistories = new ArrayList<PathHistory>();
		try {
			Statement statement = (Statement) conn.createStatement();
			String sql = "select * from " + Config.TABLE_NAME;
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				pathHistories.add(new PathHistory(rs.getInt(1), rs.getString(2), rs.getTimestamp(3)));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pathHistories;
	}

	/**
	 * 增加路径
	 * @param pathName
	 * @param time
	 */
	public static void addPath(String pathName, Timestamp time) {
		Init();
		try {
			Statement statement = (Statement) conn.createStatement();
			String sql = "insert into " + Config.TABLE_NAME
					+ " (name,time) values('" + pathName
					+ "','" + time + "');";
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		HistoryController.findAllPath();
		
		HistoryController.findAllPath();
	}
}
