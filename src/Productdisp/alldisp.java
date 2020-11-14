package Productdisp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import Beans.alldispbean;
import Classes.SqlConnection;
import Classes.SqlManager;
import Classes.URL;

/**
 * Servlet implementation class alldisp
 */
@WebServlet("/alldisp")
public class alldisp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public alldisp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<alldispbean> list = new ArrayList<alldispbean>();
		URL url = new URL();
		// SQL Selectで 全商品リスト取得
		SqlConnection db = new SqlConnection();
		SqlManager Sql = new SqlManager();

		list = Sql.SqlDispAll(db.Connect());
		db.close(db.Connect());

		request.setAttribute("AllDisp", list);
		request.setAttribute("title", "全商品表示");
		request.getRequestDispatcher(url.ALLDISP).forward(request, response);
		return;
	}
}