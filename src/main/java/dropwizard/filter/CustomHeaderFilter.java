package dropwizard.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/employee")
public class CustomHeaderFilter  implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		   
		    HttpServletRequest httpServletRequest= (HttpServletRequest)servletRequest;
		
          	HttpServletResponse httpServletResponse= 	(HttpServletResponse) servletResponse;
		
		if("anuj".equals(servletRequest.getParameter("user"))) {
			chain.doFilter(httpServletRequest, httpServletResponse);
			
		}else {
			httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Not authorized");
		}
		
	}
	@Override
	public void destroy() {
		
	}

	
}
