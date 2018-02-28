package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.WishDAO;
import model.vo.WishVO;

@WebServlet("/wish")
public class WishServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comm = request.getParameter("comm");
		RequestDispatcher rd = null;
		WishDAO dao = null;	
		
		if(!(comm == null)){
			if(comm.equals("loadCate")){
				System.out.println(comm);
				String cateName = request.getParameter("cateName");				
				System.out.println(cateName);
				ArrayList<WishVO> set = new ArrayList<WishVO>();
				set = dao.loadCate(cateName);				
				request.setAttribute("cateSet", set);
			}
			rd = request.getRequestDispatcher("/jsp/test.jsp");				
		}		
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
