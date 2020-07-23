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
		//��ȡ��¼�û���
		User user=(User) request.getSession().getAttribute("user");
		//���õ���
		CollectDao cdao=new CollectDao();
	    ArrayList<Book> list=cdao.selectByCollect(user);
		//��װ����
		request.setAttribute("books", list);
		//ҳ����ת
		request.getRequestDispatcher("collect.jsp").forward(request,response);
	}

}
