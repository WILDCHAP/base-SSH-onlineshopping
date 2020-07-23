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
		//编写sql语句
		String sql="select * from t_collect where uid=? and book=? ";
		//获取数据库的链接
		try {
			Connection con=JDBC1.getConnection();
			//获取预编译对象
			PreparedStatement ps=con.prepareStatement(sql);
			//为语句赋值
			ps.setString(1, co.getUid());
			ps.setString(2, co.getBook());
			//执行SQL语句
			ResultSet rs=ps.executeQuery();
			//把结果封装到collect对象中
			if(rs.next()){
				collect=new Collect();
				collect.setRid(rs.getInt("rid"));
				collect.setUid(rs.getString("uid"));
				collect.setBook(rs.getString("book"));
			}
			//关闭链接
			con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collect;
		
	}
	
	//ajax添加收藏
	public void insert(Collect collect){
		//编写SQL语句
		String sql="insert into t_collect(uid,book) values(?,?)";
		try {
			//获取数据库链接
			Connection conn=JDBC1.getConnection();
			//预编译对象
			PreparedStatement ps=conn.prepareStatement(sql);
			//位语句赋值
			ps.setString(1, collect.getUid());
			ps.setString(2, collect.getBook());
			//执行SQL语句
			ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ajax取消收藏
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
		//编写SQL语句
		String sql="select * from t_book where isbn in (select book from t_collect where uid=?)";
		//获取数据库链接
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
