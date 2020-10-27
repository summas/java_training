package mainmenu;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class escapetest
 */
@WebServlet("/escapetest")
public class escapetest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public escapetest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc
				.getRequestDispatcher("/WEB-INF/JSP/escapetest.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		  String escape = request.getParameter("escape");
	     if(escape==null){
		  ServletContext sc = getServletContext();
				RequestDispatcher rd = sc
						.getRequestDispatcher("/WEB-INF/JSP/escapetest.jsp");
				rd.forward(request, response);
				return;
		 }
		  
		   
		 /** HTML�G���R�[�h���K�v�ȕ��� **/
		  char[] htmlEncChar = {'&', '"', '<', '>'};
		  /** HTML�G���R�[�h���������� **/
		  String[] htmlEncStr = {"&amp;", "&quot;", "&lt;", "&gt;"};

		  /**
		  * HTML�G���R�[�h�����B
		  *   &,",<,>�̒u��
		  **/
		 
		  // HTML�G���R�[�h����
		    StringBuffer strOut = new StringBuffer(escape);
		    // �G���R�[�h���K�v�ȕ��������Ԃɏ���
		    for (int i = 0; i < htmlEncChar.length; i++) {
		      // �G���R�[�h���K�v�ȕ����̌���
		      int idx = strOut.toString().indexOf(htmlEncChar[i]);

		      while (idx != -1) {
		        // �G���R�[�h���K�v�ȕ����̒u��
		        strOut.setCharAt(idx, htmlEncStr[i].charAt(0));
		        strOut.insert(idx + 1, htmlEncStr[i].substring(1));

		        // ���̃G���R�[�h���K�v�ȕ����̌���
		        idx = idx + htmlEncStr[i].length();
		        idx = strOut.toString().indexOf(htmlEncChar[i], idx);
		      }
		    }
		    request.setAttribute("strOut", strOut.toString());
			
			ServletContext sc2 = getServletContext();
			RequestDispatcher rd2 = sc2.getRequestDispatcher("/WEB-INF/JSP/escapetest.jsp");
			rd2.forward(request, response);
			return;
		//    return(strOut.toString());
		  }

		
		
	

}
