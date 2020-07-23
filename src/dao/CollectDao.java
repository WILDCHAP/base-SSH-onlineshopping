package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Book;
import pojo.Collect;
import pojo.User;
import utils.JDBC1;

public class CollectDao {
	public Collect selectByCollect(Collect co){
		Collect collect=null;
		//��дsql���
		String sql="select * from t_collect where uid=? and book=? ";
		//��ȡ���ݿ������
		try {
			Connection con=JDBC1.getConnection();
			//��ȡԤ�������
			PreparedStatement ps=con.prepareStatement(sql);
			//Ϊ��丳ֵ
			ps.setString(1, co.getUid());
			ps.setString(2, co.getBook());
			//ִ��SQL���
			ResultSet rs=ps.executeQuery();
			//�ѽ����װ��collect������
			if(rs.next()){
				collect=new Collect();
				collect.setRid(rs.getInt("rid"));
				collect.setUid(rs.getString("uid"));
				collect.setBook(rs.getString("book"));
			}
			//�ر�����
			con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collect;
		
	}
	
	//ajax����ղ�
	public void insert(Collect collect){
		//��дSQL���
		String sql="insert into t_collect(uid,book) values(?,?)";
		try {
			//��ȡ���ݿ�����
			Connection conn=JDBC1.getConnection();
			//Ԥ�������
			PreparedStatement ps=conn.prepareStatement(sql);
			//λ��丳ֵ
			ps.setString(1, collect.getUid());
			ps.setString(2, collect.getBook());
			//ִ��SQL���
			ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ajaxȡ���ղ�
	public void delete(Collect collect){
		String sql="delete from t_collect where uid=? and book=?";
		try {
			Connection conn=JDBC1.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, collect.getUid());
			ps.setString(2, collect.getBook());
			ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<Book> selectByCollect(User user){
		ArrayList<Book> list =new ArrayList<Book>();
		//��дSQL���
		String sql="select * from t_book where isbn in (select book from t_collect where uid=?)";
		//��ȡ���ݿ�����
		try {
			Connection con=JDBC1.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, user.getPhone());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Book book=new Book();
				book.setAuthor(rs.getString("author"));
				book.setEdition(rs.getInt("edition"));
				book.setForm(rs.getString("form"));
				book.setFormat(rs.getString("format"));
				book.setIsbn(rs.getString("isbn"));
				book.setPackaging(rs.getString("packaging"));
				book.setPages(rs.getString("pages"));
				book.setPress(rs.getString("press"));
				book.setPrice(rs.getDouble("price"));
				book.setPublished(rs.getDate("published"));
  		    	book.setTitle(rs.getString("title"));
				book.setWords(rs.getInt("words"));
				list.add(book);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

}
