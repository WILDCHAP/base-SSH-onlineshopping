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
		//1.��ȡ��ǰ�û���Ϣ
		User user=(User)request.getSession().getAttribute("user");
		//2.��ȡҳ���������Ʒ��������Ʒ���
		String product =request.getParameter("product");
		int count =Integer.parseInt(request.getParameter("count"));
		//3.�����ݷ�װ��Cart���ﳵ������
		Cart cart=new Cart();
		cart.setBook(product);
		cart.setCount(count);
		cart.setUid(user.getPhone());
		//System.out.println("1111111111");
		//System.out.println(cart.getBook());
		//System.out.println(cart.getCount());
		//System.out.println(cart.getUid());
		//4.����dao���ڷ������������ݿ�
		try {
			//2.1��ѯ���ݱ��е���Ϣ������ֵ��
			int oldcount=dao.selectByUidAndIsbn(user.getPhone(), product);
			//2.2���ж����ݱ����Ƿ�����Ȿ��Ĺ�����Ϣ
			//����У��������ݱ��е�countֵ
			//���û��ֱ�Ӳ�������
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

			//5.ajax���ض���

		//System.out.println("����");
		Writer out=response.getWriter();
		out.write("yes");
		out.close();
		
	}
}
