package com.exfantasy.template.vo.request;

public class QueryVo<T> {
	private T criteriaValue;

	public T getCriteriaValue() {
		return criteriaValue;
	}

	public void setCriteriaValue(T criteriaValue) {
		this.criteriaValue = criteriaValue;
	}
	
}
