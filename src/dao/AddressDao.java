package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import pojo.Address;
import pojo.User;

import utils.JDBC1;

public class AddressDao {
	public void insert(Address address) throws ClassNotFoundException, IOException, SQLException{
		Connection con = JDBC1.getConnection();
		String SQL="insert into t_address(uid,address,added,receiver,rphone) values(?,?,?,?,?)";
		try {
			PreparedStatement ps=(PreparedStatement)con.prepareStatement(SQL);
			ps.setString(1, address.getUid());
			ps.setString(2,address.getAddress());
			ps.setDate(3,new java.sql.Date(address.getAdded().getTime()));
			ps.setString(4, address.getReceiver());
			ps.setString(5, address.getRphone());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public	ArrayList<Address> selectAddressByUser(User user) throws ClassNotFoundException, IOException, SQLException{
		ArrayList<Address> list=new ArrayList<Address>();
		Connection con=JDBC1.getConnection();
		String SQL="select * from t_address where uid=?";
		Address address=null;
		try {
			PreparedStatement ps=(PreparedStatement)con.prepareStatement(SQL);
			ps.setString(1, user.getPhone());
			ResultSet rs=(ResultSet) ps.executeQuery();
			while(rs.next()){
				address=new Address();
				address.setRid(rs.getInt("rid"));
				address.setUid(rs.getString("uid"));
				address.setAddress(rs.getString("addressa"));
				address.setAdded(rs.getDate("added"));
				address.setReceiver(rs.getString("receiver"));
				address.setRphone(rs.getString("rphone"));
				list.add(address);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
