package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.ManagementDao;
import dto.Customer;
import dto.Management;

@WebServlet("/login")
public class LoginCustomer extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String password = req.getParameter("password");

		ManagementDao managementDao = new ManagementDao();
		CustomerDao customerDao = new CustomerDao();

		Management management = managementDao.find(id);
		if (management == null) {
			Customer customer = customerDao.find(id);
			if (customer == null) {
				resp.getWriter().print("<h1>Invalid Id</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			} else {
				if (customer.getPassword().equals(password)) {
					if (customer.isStatus()) {
						resp.getWriter().print("<h1>Login Success</h1>");
						req.getRequestDispatcher("CustomerHome.html").include(req, resp);
					} else {
						resp.getWriter().print("<h1>Still in Process Contact Management</h1>");
						req.getRequestDispatcher("Login.html").include(req, resp);

					}
				} else {
					resp.getWriter().print("<h1>Invalid Password</h1>");
					req.getRequestDispatcher("Login.html").include(req, resp);
				}
			}
		} else {
			if (management.getPassword().equals(password)) {
				resp.getWriter().print("<h1>Login Success</h1>");
				req.getRequestDispatcher("ManagementHome.html").include(req, resp);
			} else {
				resp.getWriter().print("<h1>Invalid Password</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
		}
	}
}
