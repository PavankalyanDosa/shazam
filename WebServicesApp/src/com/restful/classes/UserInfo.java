package com.restful.classes;

//users table
public class UserInfo {
	private String employeeId;
	private String userName;
	private String firstName;
	private String lastName;
	private String location;
	private String email;
	private String status;
	private ForGroup forGroup;

	public UserInfo() {
	}

	public UserInfo(String employeeId, String userName, String firstName, String lastName, String location,
			String email, String status, ForGroup forGroup) {
		super();
		this.employeeId = employeeId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = location;
		this.email = email;
		this.status = status;
		this.forGroup = forGroup;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ForGroup getForGroup() {
		return forGroup;
	}

	public void setForGroup(ForGroup forGroup) {
		this.forGroup = forGroup;
	}

	@Override
	public String toString() {
		return "UserInfo [employeeId=" + employeeId + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", location=" + location + ", email=" + email + ", status=" + status
				+ ", forGroup=" + forGroup + "]";
	}

}
