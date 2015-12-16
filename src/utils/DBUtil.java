package utils;

import java.sql.*;
import java.util.*;
import java.io.*;

import java.beans.PropertyVetoException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

/**
 * Created by Yanqi on Mon Jul 27 11:19:46 CST 2015
 */

public class DBUtil{

	private static ComboPooledDataSource dataSource;
	static Logger logger = Logger.getLogger(DBUtil.class.getName());

	private DBUtil(){
		
	}

	static {
		dataSource = new ComboPooledDataSource();
		Properties properties = new Properties();
		InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("c3p0.properties");
		try {
			properties.load(is);
		} catch (Exception e) {
			logger.error("找不到c3p0的配置文件！",e);
			throw new Error("系统出错！");
		}

		try {
			dataSource.setDriverClass(properties.getProperty("c3p0.driverClass"));
		} catch (PropertyVetoException e) {
			logger.error("c3p0配置DriverClass出错！",e);
			throw new Error("系统出错！");
		}
		dataSource.setJdbcUrl(properties.getProperty("c3p0.jdbcUrl"));
		dataSource.setUser(properties.getProperty("c3p0.user"));
		dataSource.setPassword(properties.getProperty("c3p0.password"));
		dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("c3p0.minPoolSize")));
		dataSource.setInitialPoolSize(Integer.parseInt(properties.getProperty("c3p0.initialPoolSize")));
		dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("c3p0.maxPoolSize")));
		dataSource.setMaxIdleTime(Integer.parseInt(properties.getProperty("c3p0.maxIdleTime")));
		dataSource.setAcquireIncrement(Integer.parseInt(properties.getProperty("c3p0.acquireIncrement")));
		dataSource.setIdleConnectionTestPeriod(Integer.parseInt(properties.getProperty("c3p0.idleConnectionTestPeriod")));
	}

	/**
	 * 数据库获得连接方法
	 */
	public static Connection getConnection(){

		Connection connection=null;
		try {
			connection=dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("获取数据连接池连接错误！",e);
			throw new RuntimeException("系统出错！");
		}
		return connection;
	}

	/**
	 * 更新方法
	 * @param sql
	 */
	public static void executeUpdate(String sql){
		executeUpdate(sql,null);
	}

	/**
	 * 带参数更新方法
	 * 请保持页面的编码为UTF-8编码，否则会显示传值错误
	 * @param sql
	 * @param objs
	 */
	public static void executeUpdate(String sql,Object ... objs){
		Connection connection=getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			if(objs!=null){
				for (int i = 0; i < objs.length; i++) {
					preparedStatement.setObject(i + 1, objs[i]);
				}
			}
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("更新通用方法出错！请检查参数数量是否正确！",e);
			throw new RuntimeException("系统出错！");
		} finally {
			releaseConnection(null,preparedStatement,connection);
		}
	}

	/**
	 * 数据库查询方法
	 * @param sql
	 * @return
	 */
	public static Rs executeQuery(String sql){
		return executeQuery(sql,null);
	}

	/**
	 * param查询method，请注意，如果是String类型，请确保所有文件的编码为UTF-8
	 * @param sql
	 * @param objs
	 * @return Rs
	 */
	public static Rs executeQuery(String sql,Object ... objs){
		Connection connection=getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Rs rs=new Rs();
		try {
			preparedStatement=connection.prepareStatement(sql);
			if(objs!=null){
				for (int i = 0; i < objs.length; i++) {
					preparedStatement.setObject(i+1,objs[i]);
				}
			}
			resultSet=preparedStatement.executeQuery();
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			int count=resultSetMetaData.getColumnCount();

			while(resultSet.next()){
				for (int i = 0; i < count; i++) {
					String columnName=resultSetMetaData.getColumnName(i+1);
					rs.setObject(columnName, resultSet.getObject(i+1));
				}
				rs.saveColumn();
			}
		} catch (SQLException e) {
			logger.error("查询通用方法出错！请检查参数数量是否正确！",e);
			throw new RuntimeException("系统出错！");
		} finally {
			releaseConnection(resultSet,preparedStatement,connection);
		}
		rs.setFirst();
		return rs;
	}

	/**
	 * 数据库关闭连接方法
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	private static void releaseConnection(ResultSet resultSet,Statement statement,Connection connection){
		try {
			if(resultSet!=null)
				resultSet.close();
			if (statement!=null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			logger.error("关闭数据库连接错误！",e);
			throw new RuntimeException("系统出错！");
		}
	}
}

