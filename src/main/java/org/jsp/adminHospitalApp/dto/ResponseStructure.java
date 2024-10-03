package org.jsp.adminHospitalApp.dto;

public class ResponseStructure<T> {

	private int statucCode;
	private String message;
	private T Data;

	public int getStatucCode() {
		return statucCode;
	}

	public void setStatucCode(int statucCode) {
		this.statucCode = statucCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return Data;
	}

	public void setData(T data) {
		Data = data;
	}
}
