package CSV;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.alldispbean;
import Classes.SqlConnection;
import Classes.SqlManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class CsvOut2
 */
@WebServlet("/CsvOut")
public class CsvOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CsvOut() {
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

		String filename = request.getParameter("FILENAME");
		String filenameCsv = filename + ".csv";	
		List<alldispbean> list = new ArrayList<alldispbean>();	

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filenameCsv)));
		bw.write("商品ID,商品名");bw.newLine();

		  //SQL Selectで 全商品リスト取得
	      SqlConnection db = new SqlConnection();
	      SqlManager Sql = new SqlManager();
	      list =  Sql.SqlDispAll(db.Connect());
	      db.close(db.Connect());
	      
		for(int i=0;i<list.size();i++){
			bw.write(list.get(i).getId());bw.write(",");
			bw.write(list.get(i).getName());bw.newLine();
		}

		bw.close();

		File file = new File(filenameCsv);
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			//HTTPヘッダの出力
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(),"UTF-8"));
			response.setContentLength((int)file.length());
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0,pre-check=0");
			response.setHeader("Pragma", "private");

			in = new BufferedInputStream(new FileInputStream(file));
			out = new BufferedOutputStream(response.getOutputStream());
			           byte buf[]=new byte[1024];
			           int len;
			           while((len=in.read(buf))!=-1){
			               out.write(buf,0,len);
			           }
			       } catch (SocketException e) {
			           //●ダウンロード処理中にダウンロードダイアログの「キャンセル」が
			           //クリックされた場合の例外。
			           //●ただし、ダウンロードダイアログが表示されているバックグラウンドで
			           //ブラウザへのダウンロードが行われていることに留意すること。
			           //●つまり小さいファイルでは、ダイアログが表示される時には、ダウンロード
			           //処理は完了し、サーブレットは終了してしまっており、SocketExceptionの
			           //も発生しないということです。
			       } catch (Exception e) {
			           //ファイルダウンロード用のHTTPヘッダをリセットします。
			           response.reset();
			           response.sendError(HttpURLConnection.HTTP_INTERNAL_ERROR , e.toString());
			       } finally {
			           if (in != null) {
			               in.close();
			           }
			           if (out != null) {
			               out.flush();
			               out.close();
			           }
			       }
			       return;
	}
}
