package Filter;


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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/*")
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		System.out.println(((HttpServletRequest)request).getServletPath()); //filterチェック用

		String check = ((HttpServletRequest)request).getServletPath();
		
		if(check.equals("/login") != true && (check.substring(check.length()-3)).equals("css") != true){
			System.out.println(check.substring(check.length()-3));
		HttpSession session = ((HttpServletRequest)request).getSession(false);
			String logok= (String)session.getAttribute( "logok" );

			 if( logok != "1"){
				 ((HttpServletResponse)response).sendRedirect("/PracticeWebApp/login");
				 return;
		 }
 }

		// pass the request along the filter chain
		chain.doFilter(request, response);
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
