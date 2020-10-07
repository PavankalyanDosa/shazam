package com.restful.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UIRepository {

	private static Connection getConnection() {
		final String url = "jdbc:mysql://localhost:3306?user=root&password=pkhardRock$21";
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public List<UserInfo> getAllUsers() {
		List<UserInfo> listOfUserInfo = new LinkedList<>();
		String query = "select * from trusted_db.webappUsers join trusted_db.webappid_capid on trusted_db.webappUsers.userid=trusted_db.webappid_capid.user_id;";

		try {
			Statement statement = UIRepository.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String employeeId = resultSet.getString("userid");
				String username = resultSet.getString("username");
				String fname = resultSet.getString("firstname");
				String lname = resultSet.getString("lastname");
				String location = resultSet.getString("city");
				String mail = resultSet.getString("email");
				String status = resultSet.getString("status");
				String cid = resultSet.getString("cap_id");
				String uid = resultSet.getString("user_id");
				ForGroup fg = new ForGroup(uid, cid);
				UserInfo a = new UserInfo(employeeId, username, fname, lname, location, mail, status, fg);
				listOfUserInfo.add(a);
				System.out.println(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfUserInfo;
	}

	public UserInfo getUser(String userId) {
		String query = "select * from trusted_db.webappUsers where userid='" + userId + "'";
		UserInfo userInfo = null;
		try {
			Statement statement = UIRepository.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String empoyeeId = resultSet.getString("userid");
				String userName = resultSet.getString("username");
				String fname = resultSet.getString("firstname");
				String lname = resultSet.getString("lastname");
				String location = resultSet.getString("city");
				String mail = resultSet.getString("email");
				String status = resultSet.getString("status");
				userInfo = new UserInfo(empoyeeId, userName, fname, lname, location, mail, status, null);
				System.out.println("successful...");
				System.out.println(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	public void createUser(UserInfo userInfo) {

		String query = "insert into trusted_db.webappUsers(userid, username, firstname, lastname, city, email, status) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = UIRepository.getConnection().prepareStatement(query);
			preparedStatement.setString(1, userInfo.getEmployeeId());
			preparedStatement.setString(2, userInfo.getUserName());
			preparedStatement.setString(3, userInfo.getFirstName());
			preparedStatement.setString(4, userInfo.getLastName());
			preparedStatement.setString(5, userInfo.getLocation());
			preparedStatement.setString(6, userInfo.getEmail());
			preparedStatement.setString(7, "Enabled");
			System.out.println(userInfo.toString());
			int status = preparedStatement.executeUpdate();
			System.out.println(status);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(UserInfo userInfo) {
		String queryAttributes = UIRepository.queryGenerate(userInfo);
		String query = "update trusted_db.webappUsers set " + queryAttributes + " where userid=?";
		System.out.println(query);
		try {
			PreparedStatement preparedStatement = UIRepository.getConnection().prepareStatement(query);
			preparedStatement.setString(1, userInfo.getEmployeeId());
			System.out.println(userInfo.toString());
			int status = preparedStatement.executeUpdate();
			System.out.println(status);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void enableAccount(String userId) {

		String query = "update trusted_db.webappUsers set status=? where userid='" + userId + "'";
		try {
			PreparedStatement preparedStatement = UIRepository.getConnection().prepareStatement(query);
			preparedStatement.setString(1, "Enabled");
			int status = preparedStatement.executeUpdate();
			System.out.println(status);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disableAccount(String userId) {

		String query = "update trusted_db.webappUsers set status=? where userid='" + userId + "'";
		try {
			PreparedStatement preparedStatement = UIRepository.getConnection().prepareStatement(query);
			preparedStatement.setString(1, "Disabled");
			int status = preparedStatement.executeUpdate();
			System.out.println(status);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(String userId) {
		String query = "DELETE FROM trusted_db.webappUsers WHERE userid='" + userId + "'";
		System.out.println(query);
		try {
			Statement statement = UIRepository.getConnection().createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addEntitlement(ForGroup forGroup) {

		String query = "insert into trusted_db.webappid_capid(user_id,cap_id) values(?,?)";
		try {
			PreparedStatement preparedStatement = UIRepository.getConnection().prepareStatement(query);
			preparedStatement.setString(1, forGroup.getUserId());
			preparedStatement.setString(2, forGroup.getCapId());
			System.out.println(forGroup.toString());
			int status = preparedStatement.executeUpdate();
			System.out.println(status);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeEntitlement(ForGroup forGroup) {
		String query = "DELETE FROM trusted_db.webappid_capid WHERE user_id='" + forGroup.getUserId() + "'"
				+ " and cap_id=" + "'" + forGroup.getCapId() + "'";
		System.out.println(query);
		try {
			Statement statement = UIRepository.getConnection().createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<GroupInfo> getAllGroups() {
		List<GroupInfo> list_Of_Group_Info = new LinkedList<>();
		String query = "select * from trusted_db.capabilities1";

		try {
			Statement statement = UIRepository.getConnection().createStatement();

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String cid = resultSet.getString("capid");
				String disc = resultSet.getString("description");
				GroupInfo groupInfo = new GroupInfo(cid, disc);
				list_Of_Group_Info.add(groupInfo);
				System.out.println(groupInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_Of_Group_Info;
	}

	private static String queryGenerate(UserInfo userInfo) {
		List<String> listOfQueryAttributes = new LinkedList<String>();
		String queryAttributes = "";

		if (userInfo.getFirstName() != null) {
			listOfQueryAttributes.add("firstname=" + "'" + (userInfo.getFirstName()) + "'");
		}
		if (userInfo.getLastName() != null) {
			listOfQueryAttributes.add("lastname=" + "'" + (userInfo.getLastName()) + "'");
		}
		if (userInfo.getLocation() != null) {
			listOfQueryAttributes.add("city=" + "'" + (userInfo.getLocation()) + "'");
		}
		if (userInfo.getEmail() != null) {
			listOfQueryAttributes.add("email=" + "'" + (userInfo.getEmail()) + "'");
		}
		System.out.println("Size of an list of attr 'ats'= " + listOfQueryAttributes.size());
		if (listOfQueryAttributes.size() > 1) {
			for (int i = 0; i < listOfQueryAttributes.size(); i++) {
				queryAttributes += listOfQueryAttributes.get(i); // adding list data to QUERY attribute 'att'
				if (i <= listOfQueryAttributes.size() - 2) {
					queryAttributes += ","; // adding comma(,) in b/w to the QUERY attributes
				}
			}
		}
		return queryAttributes;
	}
}
