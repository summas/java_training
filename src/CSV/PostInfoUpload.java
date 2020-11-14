package CSV;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Classes.SqlConnection;
import Classes.SqlManager;
import Classes.URL;

/**
 * Servlet implementation class PostInfoUpload
 */
@WebServlet("/PostInfoUpload")
public class PostInfoUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostInfoUpload() {
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

		URL url = new URL();
     	 String path=getServletContext().getRealPath("/WEB-INF/data");
		 
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload sfu = new ServletFileUpload(factory);

	    // アップロードファイル最大サイズ（-1は無限）
		    sfu.setSizeMax(-1);  
	    // バッファサイズ
		    factory.setSizeThreshold(1024);
	     // 一時ファイルの保存先フォルダ 
		    factory.setRepository(new File(path)); //一時的に保存する際のディレクトリ
	    // ヘッダの文字エンコーディング
		    sfu.setHeaderEncoding("Windows-31J");  

		    try {
	       // アップロードされたファイル情報を
	      // FileItemオブジェクトのリストとして取得
	    	 List<FileItem> objLst =sfu.parseRequest(request);
	    	 Iterator<FileItem> objItr =objLst.iterator();

			 String pid = null;

	    	 for(FileItem item:objLst) {

	    	              pid = item.getString("UTF-8");

	    			 	 System.out.println(pid);
	    		
	    			 	ArrayList<String[]> data = new ArrayList<String[]>();
	    			 	 String[] line2 = pid.split("\r\n", 0);
	
	    			 	 for (int i=0; i < line2.length; i++){
	    				 data.add(line2[i].split(",", 0));
	    			 	 }
	    				 
	    			 	 request.setAttribute("pid", pid);
	    			 	 System.out.println(pid.length());
	    			 	 System.out.println(line2.length);
	    			 	 
	    			 	 //SQL INSERT実行
	    			 	  //SQL INSERT実行
	    			 	  SqlConnection db = new SqlConnection();
	    		          SqlManager Sql = new SqlManager();
	    		          Sql.PostSqlInsertAll(db.Connect(),data);
	    					db.close(db.Connect());
	    	         }

	      // リストから順にファイルデータを取り出し、
	      // 「/WEB-INF/data/元のファイル名」の形式で
	      // アップロードファイルを保存
	      while(objItr.hasNext()){
	         FileItem objFi=(FileItem)objItr.next();
	         if(!objFi.isFormField()){
	           // アップロードファイルの元ファイル名を取得
	          String strNam=objFi.getName();
	           if(strNam!=null && !strNam.equals("")){
	             strNam=(new File(strNam)).getName();
	             objFi.write(new File(path + "/" + strNam));
	           }
	         }
	       }
	     } catch (FileUploadException e) {
	       e.printStackTrace();
	     } catch (Exception e) {
	       e.printStackTrace();
	     }

		 	request.getRequestDispatcher(url.CUSTOMER).forward(request, response);
	}

}
