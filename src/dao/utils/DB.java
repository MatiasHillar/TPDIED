package dao.utils;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	private static final String url ="jdbc:postgresql://ruby.db.elephantsql.com:5432/smohrdja";
	private static final String user="smohrdja";
	private static final String pass="H58Kz6hWghoq49JLBnOkvw2QbPqQPqGe";
	public static void main(String args[]) {
	      Connection c = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	           .getConnection(url,user,pass);
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }
	}

