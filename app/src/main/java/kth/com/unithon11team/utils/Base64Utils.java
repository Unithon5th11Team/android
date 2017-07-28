package kth.com.unithon11team.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by kinamare on 2017-07-15.
 */

public class Base64Utils {
	/**
	 * Base64 인코딩
	 */
	public static String getBase64encode(String content) {
		return Base64.encodeToString(content.getBytes(), 0);
	}

	/**
	 * Base64 디코딩
	 */
	public static String getBase64decode(String content) {
		return new String(Base64.decode(content, 0));
	}

	/**
	 * getURLEncode
	 */
	public static String getURLEncode(String content) {
		try {
//          return URLEncoder.encode(content, "utf-8");   // UTF-8
			return URLEncoder.encode(content, "euc-kr");  // EUC-KR
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * getURLDecode
	 */
	public static String getURLDecode(String content) {
		try {
//          return URLDecoder.decode(content, "utf-8");   // UTF-8
			return URLDecoder.decode(content, "euc-kr");  // EUC-KR
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encodeTobase64(Bitmap image)
	{
		Bitmap immagex=image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
		return imageEncoded;
	}

	public static Bitmap decodeBase64(String input)
	{
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}
