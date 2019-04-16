package util;

public class StringUtil {
	/**
	 * 判断字符串是否为空对象或空串
	 * @param content 字符串
	 * @return  如果为空对象或空串，返回true，否则返回false
	 */
	public static boolean isNullOrEmpty(String content) {
		return content == null || "".equals(content.trim());
	}
}
