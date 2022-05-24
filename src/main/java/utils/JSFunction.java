package utils;

import javax.imageio.metadata.IIOMetadataNode;
import javax.servlet.jsp.JspWriter;

public class JSFunction {
	// 메세지 알림창을 띄운 후 명시한 URL로 이동
	public static void alertLocation (String msg, String url, JspWriter out) {
		try {
			String script = ""
					+ " <script>"
					+ " 	alert ('" + msg+ "');"
					+ " 	location.href = '" + url + "';"
					+ " </script>";
			
			out.print(script); //자바스크립트 코드를 out내장 객체로 출력
		} catch (Exception  e) {}
	}
	
	// 메시지 알림창을 띄운 후 이전 페이지로 돌아간다.
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = ""
					+ " <script>"
					+ "  alert ('" + msg + "');"
					+ " 	history.back();"
					+ " </script>";
			out.print(script);
		} catch (Exception e) {}
	}
	
	
}
