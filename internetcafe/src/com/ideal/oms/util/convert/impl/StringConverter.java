package com.ideal.oms.util.convert.impl;

import com.ideal.oms.util.convert.AbstractConverter;

public class StringConverter extends AbstractConverter {

	public StringConverter(String defaultValue) {
		super(defaultValue);
	}

	public Object convert(Class type, Object value) {
		return value == null ? defaultValue : value.toString();
	}

}
