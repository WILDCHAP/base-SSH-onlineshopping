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
		//��ȡ��������
		String uname=request.getParameter("uname");
		//����dao��
		UserDao dao=new UserDao();
		User user=dao.ajaxCheckUname(uname, 0);
		//�����������֤��Ϣ��ǰ��ҳ����ʾ
		Writer out=response.getWriter();
		//�ж��Ƿ���ڸ��û�
		if(user==null){
			out.write("no");
		}else{
			out.write("yes");
		}
		out.close();
	}

}
