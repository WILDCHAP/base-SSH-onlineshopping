package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

public class CheckUnameServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取请求数据
		String uname=request.getParameter("uname");
		//调用dao层
		UserDao dao=new UserDao();
		User user=dao.ajaxCheckUname(uname, 0);
		//定义流输出验证消息到前端页面显示
		Writer out=response.getWriter();
		//判断是否存在该用户
		if(user==null){
			out.write("no");
		}else{
			out.write("yes");
		}
		out.close();
	}

}
