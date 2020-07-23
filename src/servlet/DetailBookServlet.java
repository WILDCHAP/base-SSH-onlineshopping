package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.CollectDao;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import pojo.Book;
import pojo.Collect;
import pojo.User;

public class DetailBookServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.获取页面信息
		String isbn=request.getParameter("isbn");
		//2.调用dao层方法
		BookDao bd=new BookDao();
		
		try {
			Book book=bd.selectBookByIsbn(isbn);
		     request.setAttribute("book",book);
		     //收藏
		     Collect col=new Collect();
		     CollectDao cdao=new CollectDao();
		     User user=(User) request.getSession().getAttribute("user");//获取user对象
		     col.setUid(user.getPhone());
		     col.setBook(isbn);
		     Collect result=cdao.selectByCollect(col);//查询数据库
		    if(result==null){
		    	 request.setAttribute("isCollect", "");
		     }
		    else{
		    	 request.setAttribute("isCollect", "2");
		     }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("detail.jsp").forward(request, response);
		
		

	}

}
