package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CollectDao;
import pojo.Collect;

public class CollectYesOrNoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取请求中的数据
		String uid=request.getParameter("uid");
		String isbn=request.getParameter("isbn");
		//封装数据
		Collect collect=new Collect();
		collect.setUid(uid);
		collect.setBook(isbn);
		//调用dao层
		CollectDao cdao=new CollectDao();
		Collect result=cdao.selectByCollect(collect);
		//判断是否收藏该商品
		if(result!=null){
			cdao.delete(collect);
			request.setAttribute("isCollect", "");
		}else{
			cdao.insert(collect);
			request.setAttribute("isCollect", "2");
		}
		//定义流输出
		Writer out=response.getWriter();
		out.write("yes");
		out.close();
		
	}

}
