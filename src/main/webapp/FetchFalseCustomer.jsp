<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="dto.Customer"%>
<%@page import="java.util.List"%>
<%@page import="dao.CustomerDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Customer</title>
</head>
<body>
<%CustomerDao customerDao=new CustomerDao();
List<Customer> list=customerDao.fetchFalse();
%>
<table border="1">
<tr>
<th>Customer Id</th>
<th>Name</th>
<th>Mobile</th>
<th>Image</th>
<th>Status</th>
<th>Change Status</th>
</tr>
<%for(Customer customer:list){ %>
<tr>
<th><%=customer.getCustid() %></th>
<th><%=customer.getName() %></th>
<th><%=customer.getMobile() %></th>
<th>
<%
String base64 = Base64.encodeBase64String(customer.getPan()); %>
<img height="100" width="100" alt="unknown" src="data:image/jpeg;base64,<%= base64 %>">
</th>
<th><%=customer.isStatus() %></th>
<th><a href="changecuststatus?id=<%=customer.getCustid()%>>"><button>Change Status</button></a></th>
</tr>
<%} %>
</table>
<br>
<a href="Home.html"><button>Home</button> </a>
</body>
</html>