package com.exfantasy.template.constant;

public enum ResultCode {

	/** 成功 */
	SUCCESS(0, "Succeed"),
	/** 格式錯誤 */
	INVALID_FORMAT(1001, "Invalid format"),
	/** 查無此使用者 */
	CANNOT_FIND_USER(1002, "Cannot find this user"),
	/** 密碼錯誤 */
	INCORRECT_PASSWORD(1003, "Incorrect password"),
	/** 系統發生錯誤 */
	SYSTEM_EXCEPTION(9999, "");
	
	private final int code;
	private final String errorMsg;
	
	private ResultCode(int code, String errorMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getErrorMsg() {
		return this.errorMsg;
	}
}
