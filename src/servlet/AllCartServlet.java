package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import pojo.CartAndBook;
import pojo.User;

public class AllCartServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取用户信息
		User user =(User)request.getSession().getAttribute("user");
		System.out.println(user==null);
		//2.调用dao层
		CartDao dao =new CartDao();
			try {
				List<CartAndBook> list=dao.selectAllCartAndBook(user);
				//3.封装数据
				request.setAttribute("cartAndBook", list);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//4.页面跳转
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		
		
		
	}
}
