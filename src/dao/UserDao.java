package dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import pojo.User;
import utils.JDBC1;

//���ݷ��ʲ�
//���ݿ��Ʋ�
public class UserDao{
	//ע�����
	public void insert(User user) throws ClassNotFoundException, IOException, SQLException{
	    //1.�������ݿ��ȡ����
		Connection con = JDBC1.getConnection();
	    //2.��дSQL���
		String sql= "insert into t_user(phone,uname,upwd,email,role) values(?,?,?,?,?)";
	    //3.��ȡ����Ԥ��������׳�С������
		PreparedStatement ps=con.prepareStatement(sql);
		//4.��Ԥ���������и�ֵ
		ps.setString(1, user.getPhone());
		ps.setString(2, user.getUname());
		ps.setString(3, user.getUpwd());
		ps.setString(4, user.getEmail());
		ps.setInt(5, user.getRole());
		//5.ִ��SQL���
		ps.executeUpdate();
		con.close();
	
	}
	//��¼��ѯ
	public User selectByLogin(String uname,String upwd,int role){
		//����һ���û������
		User user=null;
		String sql="select * from t_user where uname=? and upwd=? and role=?";
		//��ȡ��ѯ����
		try {
			Connection conn = JDBC1.getConnection();
			//--------------------------------
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upwd);
			ps.setInt(3, 0);
			//
			ResultSet rs = ps.executeQuery();
			//
			if(rs.next()){
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
			conn.close();
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
		
		
		//��дSQL���
		return user;
	}
	//ajax�첽У��ע���û���
	public User ajaxCheckUname(String uname,int role){
		User user=null;
		//����SQL���
		String sql="select * from t_user where uname=? and role=?";
		//��ȡ���ݿ�����
		Connection conn;
			try {
				conn = JDBC1.getConnection();
				//��ȡ���Ԥ�������
				PreparedStatement ps=conn.prepareStatement(sql);
				//����ֵ
				ps.setString(1,uname);
				ps.setInt(2, role);
				//ִ��SQL���
				ResultSet rs=ps.executeQuery();
				//�ѽ�������ݷ�װ��user������
				if(rs.next()){
					user=new User();
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setRole(rs.getInt("role"));
					user.setUname(rs.getString("uname"));
					user.setUpwd(rs.getString("upwd"));
				}
				conn.close();
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return user;
		
	}
	//ajax�첽������֤����
	public User ajaxCheckEmail(String email,int role){
		User user=null;
		String sql="select * from t_user where email=? and role=?";
		//�������ݿ�
		try {
			Connection con=JDBC1.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setInt(2, role);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				user=new User();
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
			con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}
	//ajax�첽����-��֤�ֻ���
	public User ajaxCheckPhone(String phone,int role){
		User user=null;
		String sql="select * from t_user where phone=? and role=?";
		try {
			Connection conn=JDBC1.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,phone);
			ps.setInt(2, role);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				user=new User();
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}
	public User ajaxCheckUpwd(String upwd,String phone,int role) throws ClassNotFoundException, IOException, SQLException{
		User user=null;
		
		Connection conn=JDBC1.getConnection();
		String sql="select * from t_user where phone=? and upwd=? and role=?";
		
		PreparedStatement ps=conn.prepareStatement(sql);
		
		ps.setString(1, phone);
		ps.setString(2, upwd);
		ps.setInt(3, role);
		
		ResultSet res=ps.executeQuery();
		if(res.next()){
			user=new User();
			
			user.setEmail(res.getString("email"));
			user.setPhone(res.getString("phone"));
			user.setRole(res.getInt("role"));
			user.setUname(res.getString("uname"));
			user.setUpwd(res.getString("upwd"));
			
			
		}
		return user;
			
			
			
		
	}
	public void updateUpwd(User user, String npwd) throws ClassNotFoundException, IOException, SQLException {
		//1.访问数据库获取链接
		Connection con=JDBC1.getConnection();
		//2、编写SQL语句
		String SQL="update t_user set upwd=? where phone=?";
		try {
			//3.获取语句的预编译对象（statement小货车）
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(SQL);
			//4，对于编译对象进行赋值（装车）
			ps.setString(1,npwd);
			ps.setString(2,user.getPhone());
			//5、执行SQL语句
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
