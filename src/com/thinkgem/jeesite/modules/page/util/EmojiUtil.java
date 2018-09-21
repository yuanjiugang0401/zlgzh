package com.thinkgem.jeesite.modules.page.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiUtil {
	/**
	*将emoji标签转换成utf8字符集保存进数据库
	*@param str 需要转换的字符串
	*@return
	*/
	public static String emojiConvert(String str) {
	String patternString = "([\\x{10000}-\\x{10ffff}\\ud800-\\udfff])";
	    Pattern pattern = Pattern.compile(patternString);
	    Matcher matcher = pattern.matcher(str);
	    StringBuffer sb = new StringBuffer();
	    while(matcher.find()) {
	        try {
	            matcher.appendReplacement(sb,"[[" + URLEncoder.encode(matcher.group(1),"UTF-8") + "]]");
	        } catch(UnsupportedEncodingException e) {
	            return str;
	        }
	    }
	    matcher.appendTail(sb);
	    return sb.toString();
	}

	  /**
	* @Description 还原utf8数据库中保存的含转换后emoji表情的字符串
	* @param str
	* 转换后的字符串
	* @return 转换前的字符串
	*/
	public static String emojiRecovery(String str) {
	    String patternString = "\\[\\[(.*?)\\]\\]";

	    Pattern pattern = Pattern.compile(patternString);
	    Matcher matcher = pattern.matcher(str);

	    StringBuffer sb = new StringBuffer();
	    while(matcher.find()) {
	        try {
	            matcher.appendReplacement(sb,
	                    URLDecoder.decode(matcher.group(1), "UTF-8"));
	        } catch(UnsupportedEncodingException e) {
	            return "";
	        }
	    }
	    matcher.appendTail(sb);
	    return sb.toString();
	}
}
