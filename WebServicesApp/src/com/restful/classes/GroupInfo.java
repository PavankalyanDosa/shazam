package com.restful.classes;

//group table
public class GroupInfo {
	private String capid;
	private String discription;

	public GroupInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupInfo(String capid, String discription) {
		super();
		this.capid = capid;
		this.discription = discription;
	}

	public String getCapid() {
		return capid;
	}

	public void setCapid(String capid) {
		this.capid = capid;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public String toString() {
		return "GroupInfo [capid=" + capid + ", discription=" + discription + "]";
	}

}
