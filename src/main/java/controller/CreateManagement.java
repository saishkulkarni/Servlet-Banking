package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagementDao;
import dto.Management;

@WebServlet("/createmanagement")
public class CreateManagement extends HttpServlet
{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Management management=new Management();
	management.setId(123321);
	management.setPassword("123321");
	
	ManagementDao dao=new ManagementDao();
	dao.save(management);
	
	resp.getWriter().print("<h1>Management Account Got Created</h1>");
	req.getRequestDispatcher("Home.html").include(req, resp);
}
}
