package Productdisp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Product;
import Beans.alldispbean;
import Classes.SqlConnection;
import Classes.SqlManager;

import com.google.gson.Gson;

/**
 * Servlet implementation class ProductdispAjax
 */
@WebServlet("/ProductdispAjax")
public class ProductdispAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String REQUEST_STRING = "requestJs";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductdispAjax() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String pid = request.getParameter(REQUEST_STRING);
		String name = null;
		 Product updateP = new Product(pid,name);

					//SQL SELECT実行
		  SqlConnection db = new SqlConnection();
           SqlManager Sel = new SqlManager();
           name =  Sel.select(db.Connect(), updateP);
           db.close(db.Connect());

					  if (name != null) {
						  print(request,response,pid,name);
					  }else{
					 name = "登録されていません";
					 print(request,response,pid,name);
				}
	}
//json形式でデータを返す。
	private void print(HttpServletRequest request, HttpServletResponse response, String pid, String name ) throws IOException{
		
		alldispbean info = new alldispbean(pid,name);
		 Gson gson = new Gson();
		  response.setContentType("application/json; charset=utf-8");
	        PrintWriter out = response.getWriter();
		  String callback = request.getParameter("callback");
		  out.println( callback + "(" + gson.toJson(info) + ")");
		
	}

}