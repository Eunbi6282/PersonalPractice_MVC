package utils;

import javax.imageio.metadata.IIOMetadataNode;
import javax.servlet.jsp.JspWriter;

public class JSFunction {
	// �޼��� �˸�â�� ��� �� ����� URL�� �̵�
	public static void alertLocation (String msg, String url, JspWriter out) {
		try {
			String script = ""
					+ " <script>"
					+ " 	alert ('" + msg+ "');"
					+ " 	location.href = '" + url + "';"
					+ " </script>";
			
			out.print(script); //�ڹٽ�ũ��Ʈ �ڵ带 out���� ��ü�� ���
		} catch (Exception  e) {}
	}
	
	// �޽��� �˸�â�� ��� �� ���� �������� ���ư���.
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
