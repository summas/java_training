package Classes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Beans.Product;
import Beans.User;
import Beans.alldispbean;

public class SqlManager {
	
		List<alldispbean> list = new ArrayList<alldispbean>();
		SqlAll doSqlall = new SqlAll();
		Sql doSql = new Sql();
		String name = null;
		//SELECT実行
		public String select(Connection conn ,Product Proudct){
			name = doSql.GetName(Proudct, conn);
			return name;
		}

		//INSERT実行
		public void insert(Connection conn ,Product Proudct){
			doSql.Insert(Proudct, conn);
		}
		
		//DELETE実行
		public void delete(Connection conn ,Product Proudct){
			doSql.Delete(Proudct, conn);
		}
		
		//UPDATE実行
		public void update(Connection conn ,Product Proudct){
			doSql.Update(Proudct, conn);
		}
		
	
	//全商品表示
	public List<alldispbean> SqlDispAll(Connection conn) {
		this.list = doSqlall.GetAllName(conn);
				return this.list;
	}
	
	//CSVアップロードjakalta
	public void SqlInsertAll(Connection conn , ArrayList<String[]> data){
			doSqlall.InsertAll(data, conn);
		}
	
	//CSVアップロード
	public void SqlInsertAll2( Connection conn,ArrayList<String> data){
		
		doSqlall.InsertAll2(data, conn);
	}
	
	//郵便情報アップロード
		public void PostSqlInsertAll(Connection conn, ArrayList<String[]> data){
			
			doSqlall.InsertPostAll(data, conn);
		}

	public User SqlLogin(Connection conn, User user){
		//User loguser = null;
		
		
		
	
			return doSql.login(user, conn);
	}
     
}