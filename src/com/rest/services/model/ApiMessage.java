package com.rest.services.model;

public class ApiMessage {
	private String type;
	private int code;
	private String description;
	public static String TYPE_ERROR = "Error";
	public static String TYPE_SUCCESS = "Ok";
	public static int CODE_SUCCESS = 0;
	public static int CODE_ERROR = 1;
	public static int CODE_APP_ERROR = 2;
	public static int CODE_UNKNOWN_ERROR = 3;

	public ApiMessage(String type, int code, String description) {
		super();
		this.type = type;
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
