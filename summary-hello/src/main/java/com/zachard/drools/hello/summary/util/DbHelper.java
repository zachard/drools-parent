/*
 *  Copyright 2015-2017 zachard, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.zachard.drools.hello.summary.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.drools.hello.summary.constant.Constant;

/**
 * 数据库操作工具类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DbHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(DbHelper.class);
	
	/**
	 * 数据库连接属性
	 */
	private static Properties dbProperties;
	
	/**
	 * 数据库连接对象
	 */
	private Connection conn = null;
	
	//加载数据库驱动
	static {
		try {
			dbProperties = PropertyUtil.getPropertiesByFile("jdbc.properties");
			Class.forName(dbProperties.getProperty(Constant.JDBC_DRIVER));
		} catch (Exception e) {
			logger.error("加载数据库驱动失败", e);
		}
	}
	
	/**
	 * 根据SQL语句及参数查询对象列表
	 * 
	 * @param sql     SQL语句
	 * @param params  参数列表
	 * @return        查询结果列表
	 */
	public List<Map<String, Object>> queryObjectList(String sql, List<Object> params) {
		List<Map<String, Object>> list = new ArrayList<>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			setParams(statement, params);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				list.add(convertResultToMap(resultSet));
			}
		} catch (Exception e) {
			logger.error("查询多个对象列表异常", e);
		}finally {
			closeJdbcObject(resultSet, statement, conn);
		}
		
		return list;
	}
	
	/**
	 * 根据SQL及参数对象查询单个对象
	 * 
	 * @param sql       SQL语句
	 * @param params    参数列表
	 * @return          单个查询结果
	 */
	public Map<String, Object> queryObject(String sql, List<Object> params) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			setParams(statement, params);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				return convertResultToMap(resultSet);
			}
		} catch (Exception e) {
			logger.error("查询单个对象异常", e);
		} finally {
			closeJdbcObject(resultSet, statement, conn);
		}
		
		return new HashMap<>();
	}
	
	/**
	 * 更新单个对象
	 * 
	 * @param sql      更新单个对象SQL语句
	 * @param params   SQL语句参数
	 * @return         更新的行数
	 */
	public int updateObject(String sql, List<Object> params) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			setParams(statement, params);
			
			return statement.executeUpdate();
		} catch (Exception e) {
			logger.error("更新对象失败", e);
		} finally {
			closeJdbcObject(resultSet, statement, conn);
		}
		
		return -1;
	}
	
	/**
	 * 批量执行SQL语句
	 * 
	 * @param sqlList     SQL语句列表
	 * @param paramsList  参数列表
	 * @return            更新行数
	 */
	public int batchUpdateObject(List<String> sqlList, List<List<Object>> paramsList) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int result = -1;
		
		try {
			// 因为需要设置事务非自动提交,所以重新获取数据库连接
			conn = getConnection();
			conn.setAutoCommit(false);
			
			if (CollectionUtils.isNotEmpty(sqlList) && CollectionUtils.isNotEmpty(paramsList)) {
				for (int i = 0; i < sqlList.size(); i++) {
					if (CollectionUtils.isNotEmpty(paramsList.get(i))) {
						statement = conn.prepareStatement(sqlList.get(i));
						setParams(statement, paramsList.get(i));
						result = statement.executeUpdate();
					}
				}
			}
			
			// 手动提交事务
			conn.commit();
		} catch (Exception e) {
			logger.error("批量更新数据库对象异常", e);
		} finally {
			closeJdbcObject(resultSet, statement, conn);
		}
		
		return result;
	}
	
	/**
	 * 根据SQL语句及参数查询总数数量
	 * 
	 * @param sql     SQL语句
	 * @param params  SQL语句对应参数
	 * @return        结果个数
	 */
	public Long getCount(String sql, List<Object> params) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Long result = 0L;
		
		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			setParams(statement, params);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				result = resultSet.getLong(1);
			}
		} catch (Exception e) {
			logger.error("获取总数异常", e);
		} finally {
			closeJdbcObject(resultSet, statement, conn);
		}
		
		return result;
	}
	
	/**
	 * 获取数据库连接
	 * 
	 * @return    数据库连接对象
	 * @throws SQLException   SQL异常
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbProperties.getProperty(Constant.JDBC_URL), 
				dbProperties.getProperty(Constant.JDBC_USER), dbProperties.getProperty(Constant.JDBC_PASS));
	}
	
	/**
	 * 关闭数据库对象
	 * 
	 * @param resultSet    结果集
	 * @param statement    SQL语句对象
	 * @param conn         数据库连接
	 */
	private void closeJdbcObject(ResultSet resultSet, PreparedStatement statement, Connection conn) {
		// 关闭结果集对象
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error("关闭JDBC结果集异常", e);
			}
		}
		
		// 关闭执行语句对象
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error("关闭JDBC执行语句异常", e);
			}
		}
		
		// 关闭数据库连接对象
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("关闭JDBC数据库连接异常", e);
			}
		}
	}
	
	/**
	 * 为数据库SQL语句设置参数
	 * <pre>
	 *     jdbc的SQL参数下标从<code>1</code>开始
	 * </pre>
	 * 
	 * @param statement    SQL数据对象
	 * @param params       参数列表
	 * @throws Exception   异常对象
	 */
	private void setParams(PreparedStatement statement, List<Object> params) throws Exception {
		if (CollectionUtils.isNotEmpty(params)) {
			for (int i = 0; i < params.size(); i++) {
				
				//针对参数为文件的特殊处理
				if (params.get(i) instanceof File) {
					File file = (File) params.get(i);
					InputStream input = new FileInputStream(file);
					statement.setBinaryStream(i + 1, input, (int) (file.length()));
					return;
				}
				
				//非文件类型参数设置
				statement.setString(i + 1, Objects.toString(params.get(i)));
			}
		}
	}
	
	/**
	 * 将一个{@link ResultSet}对象转换为{@link Map}对象
	 * 
	 * @param resultSet      jdbc查询结果集
	 * @return               map对象
	 * @throws SQLException  SQL异常
	 */
	private Map<String, Object> convertResultToMap(ResultSet resultSet) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		if (resultSet != null) {
			int count = resultSet.getMetaData().getColumnCount();
			
			for (int i = 0; i < count; i++) {
				String columnName = resultSet.getMetaData().getColumnName(i + 1);
				map.put(columnName, resultSet.getObject(i));
			}
		}
		
		return map;
	}

}
