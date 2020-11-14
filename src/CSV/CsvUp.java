package CSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Beans.alldispbean;
import Classes.SqlConnection;
import Classes.SqlManager;
import Classes.URL;


/**
 * Servlet implementation class CsvUpTest
 */

@WebServlet(name = "FileUploadServlet", urlPatterns = { "/upload" })
@MultipartConfig(maxFileSize = 100000, maxRequestSize = 100000, fileSizeThreshold = 100000)
public class CsvUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static String REPOSITORY_PATH="/WEB-INF/data/";
    @Override
    public void init() throws ServletException {
        new File(getServletContext().getRealPath(REPOSITORY_PATH)).mkdirs();
    }

    private static final ZoneId HERE = ZoneId.of("Asia/Tokyo");
    
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH-mm-ss-SSS");

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CsvUp() {
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
	@SuppressWarnings({ "resource", "static-access" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		URL url = new URL();
	     Part part = request.getPart("fileupload");
	     
	        ZonedDateTime now = ZonedDateTime.now(HERE);
	        String fileName = now.format(PATTERN);
	        System.out.println(fileName);
	        
	        String path = getServletContext().getRealPath(REPOSITORY_PATH+fileName);
	        if(fileName!=null) {
	            part.write(getServletContext().getRealPath(REPOSITORY_PATH+fileName));
	        }

	        //追加CSV
	        FileInputStream fis = new FileInputStream(path);

			InputStreamReader csv = new InputStreamReader(fis,"UTF-8");

			BufferedReader br = new BufferedReader(csv);

			ArrayList<String> data = new ArrayList<String>();
			List<alldispbean> list = new ArrayList<alldispbean>();
	
			String line;
		
			// 最終行まで読み込む
			while ((line = br.readLine()) != null) {
			
			  // 1行をデータの要素に分割
			  StringTokenizer st = new StringTokenizer(line, ",");

			  String id = st.nextToken();
			  String[] idst = {""};
			  data.add(id);

			  String name = st.nextToken();
			  idst[0] = name;
		      data.add(name);
			  list.add(new alldispbean(id,name));
			}
			
			//SQL　INSERT実行
			  //SQL INSERT実行
		 	  SqlConnection db = new SqlConnection();
	          SqlManager Sql = new SqlManager();
	          Sql.SqlInsertAll2( db.Connect(), data);
				db.close(db.Connect());
		 	  
			String title = "CSV取込み";
		   	request.setAttribute("title", title);

			  request.setAttribute("AllDisp", list);

			  request.getRequestDispatcher(url.ALLDISP).forward(request, response);
		
				return;
	}
}
