package com.exfantasy.template.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ErrorMsgUtil {

	public static String getErrorMsgs(BindingResult result) {
		StringBuilder buffer = new StringBuilder();
		
		List<ObjectError> allErrors = result.getAllErrors();
		for (int i = 0; i < allErrors.size(); i++) {
			buffer.append(allErrors.get(i).getDefaultMessage());
			if (i != allErrors.size() - 1) {
				buffer.append("\n");
			}
		}
		String errorMsg = buffer.toString();
		return errorMsg;
	}
}
