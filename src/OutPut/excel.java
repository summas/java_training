package OutPut;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import Beans.Product;
import Classes.SqlConnection;
import Classes.SqlManager;
import Classes.URL;

/**
 * Servlet implementation class excel
 */
@WebServlet("/excel")
public class excel extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public excel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/excelOut.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = null;
		String pid = request.getParameter("PID");
		URL url = new URL();

			  try {
				  Integer.parseInt(pid); 
				  } catch (NumberFormatException e) {
					request.setAttribute("id", pid );
				    request.setAttribute("name", "IDには整数を入力してください");
					request.getRequestDispatcher(url.DISP).forward(request, response);
					return;
				  }
	
			  Product updateP = new Product(pid,name);
			  
				//登録チェック
				//SQL SELECT実行
			  SqlConnection db = new SqlConnection();
			  SqlManager Sel = new SqlManager();
			  name =  Sel.select(db.Connect(),updateP);
			  db.close(db.Connect());  

				FileInputStream in = null;
			    Workbook wb = null;

			    try{
			      in = new FileInputStream("/home/owner/eclipse/jee-2019-03/eclipse/納品書テンプレート.xlsx");
			      wb = WorkbookFactory.create(in);
			    }catch(IOException e){
			      System.out.println(e.toString());
			    }catch(InvalidFormatException e){
			      System.out.println(e.toString());
			    }finally{
			      try{
			        in.close();
			      }catch (IOException e){
			        System.out.println(e.toString());
			      }
			    }
			  
//		    Sheet sheet = wb.createSheet();

			    Sheet sheet = wb.getSheetAt(0);

//			    Row row1 = sheet.createRow(1);
//			    Row row2 = sheet.createRow(2);
			    Row row1 = sheet.getRow(13);
	//		    Row row2 = sheet.getRow(14);
	//		    Row row3 = sheet.getRow(15);
			    Row row2 = sheet.createRow(29);
			    
			    CellStyle style = wb.createCellStyle();
			    style.setBorderBottom(CellStyle.BORDER_THIN);
			    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			    style.setBorderLeft(CellStyle.BORDER_THIN);
			    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			    style.setBorderRight(CellStyle.BORDER_THIN);
			    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		//	    style.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
			    style.setBorderTop(CellStyle.BORDER_THIN);
			    style.setTopBorderColor(IndexedColors.BLACK.getIndex());

			//    Cell cell1_0 =cell1_0.setCellStyle(style);

			    Cell cell1_0 = row1.getCell(1);
			    Cell cell1_1 = row1.getCell(2);
			    Cell cell1_2 = row1.getCell(3);
			    Cell cell1_3 = row1.getCell(4);
			    Cell cell1_4 = row1.getCell(5);
			    Cell cell1_5 = row1.getCell(6);
			    Cell cell1_6 = row1.getCell(7);
			    
			    cell1_0.setCellStyle(style);

			    Cell cell2_0 = row2.createCell(1);
			    Cell cell2_1 = row2.createCell(2);
			    Cell cell2_2 = row2.createCell(3);
			    Cell cell2_3 = row2.createCell(4);
			    Cell cell2_4 = row2.createCell(5);
			    Cell cell2_5 = row2.createCell(6);
			    Cell cell2_6 = row2.createCell(7);
			    
			    
		        cell2_0.setCellStyle(style);
		        cell2_1.setCellStyle(style);
		        cell2_2.setCellStyle(style);
		        cell2_3.setCellStyle(style);
		        cell2_4.setCellStyle(style);
		        cell2_5.setCellStyle(style);
		        cell2_6.setCellStyle(style);

		        /**		    
			    Cell cell5_0 = row3.getCell(1);
			    Cell cell5_1 = row3.getCell(2);
			    Cell cell5_2 = row3.getCell(3);

**/        
			  //  String price0 = request.getParameter("PRICE");
			  //  int price = perceInt(price0);

			    cell1_0.setCellValue(pid);
			    cell1_1.setCellValue(name);
			    cell1_2.setCellValue(request.getParameter("AMOUNT"));
			    cell1_3.setCellValue(request.getParameter("UNIT"));
			    cell1_4.setCellValue(request.getParameter("UNIPRICE"));
			    cell1_5.setCellValue(Integer.parseInt(request.getParameter("PRICE")));
			    cell1_6.setCellValue(request.getParameter("MEMO"));


			    cell2_0.setCellValue(11);
			    cell2_1.setCellValue("ボールペン");
			    cell2_2.setCellValue(10);
			    cell2_3.setCellValue("本");
			    cell2_4.setCellValue(100);
			    cell2_5.setCellValue(1000);
			    cell2_6.setCellValue("行追加テスト");

				String filename = request.getParameter("FILENAME");
			  	 String filenameExcel = filename + ".xlsx";

			    FileOutputStream out = null;

			    try{
        		//	String filename = request.getParameter("FILENAME");
			      out = new FileOutputStream(filenameExcel);
			      wb.write(out);
			    }catch(IOException e){
			      System.out.println(e.toString());
			    }finally{
			      try {
			        out.close();
			      //  return;
			      }catch(IOException e){
			        System.out.println(e.toString());
			      }
			    }
  		  
			    //ダウンロードプログラム
			    
			    File file = new File(filenameExcel);
			       BufferedInputStream in2 = null;
			       BufferedOutputStream out2 = null;
			       try {
			           //HTTPヘッダの出力
			           response.setContentType("application/octet-stream");
			           response.setHeader("Content-disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(),"UTF-8"));
			           response.setContentLength((int)file.length());
			           response.setHeader("Expires", "0");
			           response.setHeader("Cache-Control", "must-revalidate, post-check=0,pre-check=0");
			           response.setHeader("Pragma", "private");

			           in2 = new BufferedInputStream(new FileInputStream(file));
			           out2 = new BufferedOutputStream(response.getOutputStream());
			           
			           byte buf[]=new byte[1024];
			           int len;
			           while((len=in2.read(buf))!=-1){
			               out2.write(buf,0,len);
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
			           if (in2 != null) {
			               in2.close();
			           }
			           if (out2 != null) {
			               out2.flush();
			               out2.close();
			           }
			       }
			       return;
		}
}
