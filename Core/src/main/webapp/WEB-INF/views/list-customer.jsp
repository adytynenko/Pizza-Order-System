<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer List</title>
</head>
<body>
<h2>Customer List</h2>
	<table> 
		<tr>
			<th> First name </th>
			<th> Last Name </th>
			<th> Street </th>
			<th> City </th>
			<th> State </th>
			<th> Zip Code </th>
		</tr>
		<c:forEach var="eachCustomer" items="${customers}">

		<tr>
			<td>${eachCustomer.firstName}</td>
			<td>${eachCustomer.lastName}</td>
			<td>${eachCustomer.address.street}</td>
			<td>${eachCustomer.address.city}</td>
			<td>${eachCustomer.address.state}</td>
			<td>${eachCustomer.address.zip}</td>
			
			<td><form action = "updateCustomer" method = "post">
				<input type="hidden" name="customerId" value="${eachCustomer.id}" />
				<input type="submit" value="Update Customer Info" />
			</form></td>
			
			<td><form action = "orderHistory" method = "post">
				<input type="hidden" name="customerId" value="${eachCustomer.id}" />
				<input type="submit" value="Order History" />
			</form></td>
			
			<td><form action = "orderPizza"  modelAttribute = "newPizza" method = "post">
				<input type="hidden" name="customerId" value="${eachCustomer.id}" />
				<input type="submit" value="Order Pizza" />
			</form></td>
			
		</tr>
		</c:forEach>
	</table>
	<br>
	<form action = "addCustomer" method = "post">
		<input type = "submit" value="Add New Customer" />
	</form>
</body>
</html>