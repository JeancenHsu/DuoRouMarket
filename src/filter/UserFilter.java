package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter{
	FilterConfig config;
	public void destroy() {	
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
//		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type","text/html; charset=utf-8");
		
//		String uri = request.getRequestURI();
//		int index = uri.lastIndexOf("/");
//		String file = uri.substring(index+1);
////		System.out.println(file+":"+session.getAttribute("user_id"));
//		Object obj = config.getInitParameter("uri");
//		if(obj == null){
//			System.out.println("para is null");
//		}
//		String str = (String) obj;
//		String[] url = str.split(",");
//		for(String s:url){
//			if(file.equals(s) || file.contains(".css") || file.contains(".png") || file.contains(".jpg")){
//				if(file.equals("login.jsp")){
//					session.setAttribute("page", file);
//				}
//				chain.doFilter(request, response);
//				return;
//			}
//		}
//		if(session.getAttribute("phone") == null){   
//			session.setAttribute("page", file);
//			response.sendRedirect("login.jsp");
//			return;
////			Cookie cookie[] = request.getCookies();
////			if(cookie == null){
////				System.out.println("cookie is null");
////				request.setAttribute("page", file);
////				response.sendRedirect("login.jsp");
////			}
////			boolean flag = false;
////			List<User_info> userinfo = new DBServlet().read();
////			for(User_info u:userinfo){
////				for(Cookie c:cookie){
////					String n = c.getName();
////					n = URLDecoder.decode(n, "utf-8");
////					if(n.equals(u.getUser_phone())){
////						if(c.getValue().equals(u.getPassword())){
////							flag = true;
////							break;
////						}
////					}
////				}
////				if(flag){
////					break;
////				}
////			}
////			if(!flag){
////				request.setAttribute("page", file);
////				response.sendRedirect("login.jsp");
////				return;
////			}
//		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config=config;
	}
}
