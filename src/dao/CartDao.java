package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.CartAndBook;
import pojo.Book;
import pojo.Cart;
import pojo.User;
import utils.JDBC1;

public class CartDao {
	public void insert(Cart cart) throws ClassNotFoundException, IOException, SQLException
	{
		//1.��ȡ��������
		Connection coon = JDBC1.getConnection();
		//2.��дsql���
		String sql="insert into t_cart(uid,book,count) values(?,?,?)";
		//3.��ȡ���Ԥ�������
		PreparedStatement ps=coon.prepareStatement(sql);
		//4.Ϊ������ֵ
		ps.setString(1,cart.getUid());
		ps.setString(2, cart.getBook());
		ps.setInt(3, cart.getCount());
		//5.ִ��sql���
		ps.executeUpdate();
		JDBC1.closeConnection(coon);
		//6.�ر�����
	}
	
	public int selectByUidAndIsbn(String uid,String isbn) throws ClassNotFoundException, IOException, SQLException
	{

		int icount=0;
		//1.��ȡ��������
		Connection coon = JDBC1.getConnection();
		//2.��дsql���
		String sql="select * from t_cart where uid=? and book=?";
		//3.��ȡ���Ԥ�������
		PreparedStatement ps=coon.prepareStatement(sql);
		//4.Ϊ������ֵ
		ps.setString(1,uid);
		ps.setString(2, isbn);
		//5.ִ��sql���
		ResultSet rs= ps.executeQuery();
		if(rs.next())
		{
			icount=rs.getInt("count");
		}
		JDBC1.closeConnection(coon);
		//6.�ر�����
		return icount;
	}
	
	public void update(String uid,String isbn,int allcount) throws ClassNotFoundException, IOException, SQLException
	{
		//1.��ȡ��������
		Connection coon = JDBC1.getConnection();
		//2.��дsql���
		String sql="update t_cart set count=? where uid=? and book=?";
		//3.��ȡ���Ԥ�������
		PreparedStatement ps=coon.prepareStatement(sql);
		//4.Ϊ������ֵ
		ps.setInt(1, allcount);
		ps.setString(2, uid);
		ps.setString(3, isbn);
		//5.ִ��sql���
		ps.executeUpdate();
		JDBC1.closeConnection(coon);
		//6.�ر�����
	}
	
	public List<CartAndBook> selectAllCartAndBook(User user) throws ClassNotFoundException, IOException, SQLException
	{
		List<CartAndBook> list=new ArrayList<CartAndBook>();
		//1.��ȡ���ݿ�����
		Connection coon=JDBC1.getConnection();
		//2.��дsql���
		String sql="select * from t_cart tc inner join t_book tb on tc.book=tb.isbn where tc.uid=?";
		//3.��ȡԤ�������
		PreparedStatement ps=coon.prepareStatement(sql);
		//4.Ϊ������ֵ
		ps.setString(1, user.getPhone());
		//5.ִ��sql���
		ResultSet rs = ps.executeQuery();
		//6.���������
		while(rs.next())
		{
			Cart cart =new Cart();
			cart.setCount(rs.getInt("count"));
			cart.setUid(rs.getString("uid"));
			cart.setRid(rs.getInt("rid"));
			
			Book book =new Book();
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
			
			CartAndBook cartAndBook =new CartAndBook();
			cartAndBook.setBook(book);
			cartAndBook.setCart(cart);
			list.add(cartAndBook);
		}
		JDBC1.closeConnection(coon);
		return list;
	}

	public void deleteByRid(int rid) throws ClassNotFoundException, IOException, SQLException {
		
		Connection conn=JDBC1.getConnection();
		try {
		//2.��дsql���
		String sql="delete from t_cart where rid=?";
		
		
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, rid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC1.closeConnection(conn);
		}
		
	}

	public void updateCartNumByRid(int rid, int num) throws ClassNotFoundException, IOException, SQLException {
		Connection conn=JDBC1.getConnection();
		try {
		//2.��дsql���
		String sql="update  t_cart set count=? where rid=?";
		
		
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setInt(2, rid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC1.closeConnection(conn);
		}
		
	}
}

