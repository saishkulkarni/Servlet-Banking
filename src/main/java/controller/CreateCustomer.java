package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CustomerDao;
import dto.Customer;

@WebServlet("/signup")
@MultipartConfig
public class CreateCustomer extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String email=req.getParameter("email");
	long mobile=Long.parseLong(req.getParameter("mobile"));
	Date dob=Date.valueOf(req.getParameter("dob"));
	
	CustomerDao dao=new CustomerDao();
	if(dao.find(mobile)==null && dao.find(email)==null)
	{
		
		if(Period.between(dob.toLocalDate(), LocalDate.now()).getYears()<18)
		{
			resp.getWriter().print("<h1>You should be 18+ to create an Account</h1>");
			req.getRequestDispatcher("Signup.html").include(req, resp);
		}
		else {
		Customer customer=new Customer();
		customer.setEmail(email);
		customer.setName(req.getParameter("name"));
		customer.setAddress(req.getParameter("address"));
		customer.setGender(req.getParameter("gender"));
		customer.setMobile(mobile);
		customer.setDob(dob);
		customer.setPassword(req.getParameter("password"));
		
		byte[] pic = null;
		Part filepart = req.getPart("proof");
		if (filepart != null) {
			InputStream inputStream = filepart.getInputStream();
			pic = new byte[inputStream.available()];
			inputStream.read(pic);
		}
		
		customer.setPan(pic);
		
		dao.save(customer);
		resp.getWriter().print("<h1>Account Created Successfully and Your Customer Id is : "+dao.find(mobile).getCustid()+"</h1>");
		req.getRequestDispatcher("Home.html").include(req, resp);
		
		}
	}
	else {
	resp.getWriter().print("<h1>Account Exists Check Email or Mobile Number</h1>");
	req.getRequestDispatcher("Signup.html").include(req, resp);
	}
	
}
}
