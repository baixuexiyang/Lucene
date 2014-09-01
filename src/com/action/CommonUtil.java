package com.action;

public class CommonUtil {

	/**
	 * 使HTML的标签失去作用
	 * 
	 * @param input
	 *            被操作的字符串
	 * @return String
	 */
	public static final String escapeHTMLTag(String input) {
		StringBuilder stringBuilder = new StringBuilder();
		if (input == null||input.equals("")) {
			input = "";
			return input;
		}
		for (char charT : input.toCharArray()) {
			switch (charT) {
			case '<':
				stringBuilder.append("&lt;");
				break;
			case '>':
				stringBuilder.append("&gt;");
				break;
			case '"':
				stringBuilder.append("&quot;");
				break;
			case '&':
				stringBuilder.append("&amp;");
				break;
			default:
				stringBuilder.append(charT);
				break;
			}
		}
		return stringBuilder.toString();
	}
}

