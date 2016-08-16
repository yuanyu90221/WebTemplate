package com.exfantasy.template.vo.response;

import com.exfantasy.template.constant.ResultCode;
import com.exfantasy.template.exception.OperationException;

public class ResponseVo {
	private ResultCode resultCode;
	
	private Object data;

	public ResponseVo(ResultCode resultCode, Object data) {
		this.resultCode = resultCode;
		this.data = data;
	}
	
	public ResponseVo(OperationException ex) {
		this.resultCode = ex.getErrorCode();
		this.data = ex.getErrorMessage();
	}

	public int getResultCode() {
		return resultCode.getCode();
	}

	public Object getData() {
		return data;
	}
}
