package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.Book;
import utils.JDBC1;

public class BookDao {
	//自定义方法，查看数据库中t_user表
	public List<Book> selectAll() throws SQLException, ClassNotFoundException, IOException{
		List<Book> list=new ArrayList<Book>();
		Connection con= JDBC1.getConnection();
		String sql="select * from t_book";
		PreparedStatement ps=con.prepareStatement(sql);
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
		return list;
		
	}
	//自定义方法
	public Book selectBookByIsbn(String isbn) throws ClassNotFoundException, IOException, SQLException{
		//定义一本书对象
		Book book=new Book();
		//链接
		Connection con = JDBC1.getConnection();
		String sql="select * from t_book where isbn=?";
	    PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,isbn );
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
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
		}
	
		return book;
		
	}

}
