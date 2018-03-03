package com.hd.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class URLUtils  extends org.springframework.web.util.UriUtils {
	/**
	 * url编码
	 * @param source url
	 * @param charset 字符集
	 * @return
	 */
	public static String encodeURL(String source, Charset charset) {
		try {
			return URLUtils.encode(source, charset.name());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}

}
