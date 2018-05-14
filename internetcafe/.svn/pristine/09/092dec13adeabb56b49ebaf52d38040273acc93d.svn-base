package com.ideal.oms.util;

import java.io.File;

public class FileUtils {

	public static String getFileSufix(String inputFile) {

		return inputFile.substring(inputFile.lastIndexOf(".") + 1);
	}

	public static boolean isImage(String inputFile) {
		String tmpName = inputFile.substring(inputFile.lastIndexOf(".") + 1,
				inputFile.length());
		// 声明图片后缀名数组
		String imgeArray[] = { "bmp", "dib", "gif", "jfif", "jpe", "jpeg",
				"jpg", "png", "tif", "tiff", "ico" };
		// 遍历名称数组
		for (int i = 0; i < imgeArray.length; i++) {
			// 判断单个类型文件的场合
			if (imgeArray[i].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static String getFilePrefix(String inputFile) {
		File file = new File(inputFile);
		if (file.exists()) {
			return file.getName().split("\\.")[0];
		}
		return "";
	}
}
