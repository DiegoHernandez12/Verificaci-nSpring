package springbootholmundo;

import javax.servlet.http.HttpServletRequest;

public class Utilidad {
	public static String getSitioUrl(HttpServletRequest request) {
		String sitioUrl = request.getRequestURL().toString();
		return sitioUrl.replace(request.getServletPath(), "");
	}
}
