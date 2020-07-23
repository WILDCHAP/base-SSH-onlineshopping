package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;

public class UpdateCartNumServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemId=request.getParameter("itemId");
		String num=request.getParameter("num");

		CartDao dao=new CartDao();
		try {
			dao.updateCartNumByRid(Integer.parseInt(itemId),Integer.parseInt(num));
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
	}
}
