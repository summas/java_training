package Productdisp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Beans.Product;
import Classes.SqlConnection;
import Classes.SqlManager;
import Classes.URL;

/**
 * Servlet implementation class ProductDelete
 */
@WebServlet("/ProductDelete")
public class ProductDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    	  String pid = request.getParameter("PID");
          String name = null;
          Product deleteP = new Product(pid,name);
          URL url = new URL();
			//登録チェックSQL実行
          SqlConnection db = new SqlConnection();
          SqlManager Sql = new SqlManager();
          name = Sql.select(db.Connect(), deleteP);

			 if (name == null) { //登録されていなければ画面の再表示
				request.setAttribute("id", pid );

				request.setAttribute("name", "登録されていません");
				request.getRequestDispatcher(url.DISP).forward(request, response);
			    return;
			}
	          //SQL DELETE実行
			 	Sql.delete(db.Connect(),deleteP);
			 	db.close(db.Connect());

			    request.setAttribute("id", request.getParameter("PID"));
			 	request.setAttribute("name", "削除さ れました");
			 	request.getRequestDispatcher(url.DISP).forward(request, response);
			 	return;
	}

}
