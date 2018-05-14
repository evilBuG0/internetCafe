package com.ideal.oms.util.convert;

public interface Converter {
	/**
	 * Convert the specified input object into an output object of the specified
	 * type.
	 * 
	 * @param type
	 *            Data type to which this value should be converted
	 * @param value
	 *            The input value to be converted
	 * @return The converted value
	 */
	public <T> T convert(Class<T> type, Object value);

}
