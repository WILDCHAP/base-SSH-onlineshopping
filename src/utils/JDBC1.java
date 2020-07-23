package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC1 {
	//�����ݿ�����
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
		//����һ������
		Connection con = null;
		//����һ��Properti��
		Properties pro=new Properties();
		//��ȡ�����ļ���Ϣ
		pro.load(JDBC1.class.getClassLoader().getResourceAsStream("db.properties"));
		//��ȡ�����ļ�����Ϣ
		String driver=pro.getProperty("jdbc.driver");
		String url=pro.getProperty("jdbc.url");
		String username=pro.getProperty("jdbc.username");
		String password=pro.getProperty("jdbc.password");
		//5.��������
		Class.forName(driver);
		//��ȡ
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
