package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CollectDao;
import pojo.Book;
import pojo.User;

public class AllCollectServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取登录用户名
		User user=(User) request.getSession().getAttribute("user");
		//调用到层
		CollectDao cdao=new CollectDao();
	    ArrayList<Book> list=cdao.selectByCollect(user);
		//封装数据
		request.setAttribute("books", list);
		//页面跳转
		request.getRequestDispatcher("collect.jsp").forward(request,response);
	}

}
