package com.restful.classes;

//3rd table class
public class ForGroup {
	private String userId;
	private String capId;

	public ForGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForGroup(String userId, String capId) {
		super();
		this.userId = userId;
		this.capId = capId;
	}

	public String getCapId() {
		return capId;
	}

	public void setCapId(String capId) {
		this.capId = capId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ForGroup [userId=" + userId + ", capId=" + capId + "]";
	}

}
