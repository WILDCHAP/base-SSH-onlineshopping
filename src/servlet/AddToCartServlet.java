package servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import pojo.Cart;
import pojo.User;

public class AddToCartServlet  extends HttpServlet{

	CartDao dao=new CartDao(); 
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		//1.获取当前用户信息
		User user=(User)request.getSession().getAttribute("user");
		//2.获取页面输入的商品数量肯商品标号
		String product =request.getParameter("product");
		int count =Integer.parseInt(request.getParameter("count"));
		//3.把数据封装到Cart购物车对象中
		Cart cart=new Cart();
		cart.setBook(product);
		cart.setCount(count);
		cart.setUid(user.getPhone());
		//System.out.println("1111111111");
		//System.out.println(cart.getBook());
		//System.out.println(cart.getCount());
		//System.out.println(cart.getUid());
		//4.调用dao层在方法，插入数据库
		try {
			//2.1查询数据表中的信息（返回值）
			int oldcount=dao.selectByUidAndIsbn(user.getPhone(), product);
			//2.2先判断数据表中是否存在这本书的购物信息
			//如果有，更新数据表中的count值
			//如果没有直接插入数据
			if(oldcount==0)
			{
				dao.insert(cart);
			}
			else
			{
				int allcount=oldcount+count;
				dao.update(user.getPhone(), product, allcount);
			}
			
		    } catch (ClassNotFoundException | SQLException e) 
		   {
					// TODO Auto-generated catch block
			e.printStackTrace();
		   }

			//5.ajax返回对象

		//System.out.println("插入");
		Writer out=response.getWriter();
		out.write("yes");
		out.close();
		
	}
}
