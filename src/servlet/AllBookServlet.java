package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import pojo.Book;

public class AllBookServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��װ������ģ��book
		BookDao bd=new BookDao();
		
		//2.����dao�����ݿ��������
		List<Book> list;
			try {
				list = bd.selectAll();
				request.setAttribute("books", list);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//3.ҳ����ת
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}