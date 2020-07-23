package servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AddressDao;
import pojo.Address;
import pojo.User;

public class AddAddressServlet extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User)request.getSession().getAttribute("user");
		String receiver=request.getParameter("receiver");
		String address=request.getParameter("address");
		String receiverphone=request.getParameter("receiverPhone");
		String uid=user.getPhone();
		
		System.out.println(uid);
		
		Address a=new Address();
		a.setUid(uid);
		a.setAddress(address);
		a.setAdded(new Date());
		a.setReceiver(receiver);
		a.setRphone(receiverphone);
		AddressDao ad=new AddressDao();
		try {
			ad.insert(a);
		} catch (Exception e) {
			
		}
		Writer out=response.getWriter();
		out.write("yes");
		out.close();
	}
}
