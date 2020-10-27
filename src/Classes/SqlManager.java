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
		//SELECT���s
		public String select(Connection conn ,Product Proudct){
			name = doSql.GetName(Proudct, conn);
			return name;
		}

		//INSERT���s
		public void insert(Connection conn ,Product Proudct){
			doSql.Insert(Proudct, conn);
		}
		
		//DELETE���s
		public void delete(Connection conn ,Product Proudct){
			doSql.Delete(Proudct, conn);
		}
		
		//UPDATE���s
		public void update(Connection conn ,Product Proudct){
			doSql.Update(Proudct, conn);
		}
		
	
	//�S���i�\��
	public List<alldispbean> SqlDispAll(Connection conn) {
		this.list = doSqlall.GetAllName(conn);
				return this.list;
	}
	
	//CSV�A�b�v���[�hjakalta
	public void SqlInsertAll(Connection conn , ArrayList<String[]> data){
			doSqlall.InsertAll(data, conn);
		}
	
	//CSV�A�b�v���[�h
	public void SqlInsertAll2( Connection conn,ArrayList<String> data){
		
		doSqlall.InsertAll2(data, conn);
	}
	
	//�X�֏��A�b�v���[�h
		public void PostSqlInsertAll(Connection conn, ArrayList<String[]> data){
			
			doSqlall.InsertPostAll(data, conn);
		}

	public User SqlLogin(Connection conn, User user){
		//User loguser = null;
		
		
		
	
			return doSql.login(user, conn);
	}
     
}