package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC1 {
	//打开数据库链接
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
		//定义一个连接
		Connection con = null;
		//定义一个Properti类
		Properties pro=new Properties();
		//读取配置文件信息
		pro.load(JDBC1.class.getClassLoader().getResourceAsStream("db.properties"));
		//获取配置文件的信息
		String driver=pro.getProperty("jdbc.driver");
		String url=pro.getProperty("jdbc.url");
		String username=pro.getProperty("jdbc.username");
		String password=pro.getProperty("jdbc.password");
		//5.加载驱动
		Class.forName(driver);
		//获取
		con=DriverManager.getConnection(url,username,password);
		return con;
	}
	public static void closeConnection(Connection con) throws IOException{
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Connection con;
		try {
			con = JDBC1.getConnection();
			boolean flag = con.isClosed();
			System.out.println(flag);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
