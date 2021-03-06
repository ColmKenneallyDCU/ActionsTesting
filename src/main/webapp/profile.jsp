<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="bankapp.Login" %>
<%@page import="bankapp.Account" %>
<%@page import="bankapp.Customer" %>
<%@page import="java.text.SimpleDateFormat" %>
<%Login l = (Login)request.getAttribute("login");%>
<%Customer customer = (Customer)request.getAttribute("customer");%>


<html>
<head>
<title>Profile</title>
<link rel="stylesheet" href="css/transfers.css">
</head>    
<body>
   
   	<!-- Banner -->
    <jsp:include page="components/banner.jsp"/>  
   
	<div class="transfers">
		<div class="mytransfers">
			<h2> Profile Infomation <h2>
		</div>
		<div class="container">
			<form class="transferform" method="POST">
                <label for="fname"><b>First Name</b></label><br>
                <input type="text" placeholder="Enter First Name" name="fname" id="fname" value="<%= customer.first_name %>" required><br>

                <label for="lname"><b>Last Name</b></label><br>
                <input type="text" placeholder="Enter Last Name" name="lname" id="lname" value="<%= customer.last_name %>" required><br>

                <label for="email"><b>Email</b></label><br>
                <input type="text" placeholder="Enter Email" name="email" id="email" value="<%= customer.email %>" required><br>

                <label for="address"><b>Address</b></label><br>
                <input type="text" placeholder="Enter Address" name="Address" id="Address" value="<%= customer.address_1 %>" required><br>

                <label for="number"><b>Phone Number</b></label><br>
                <input type="text" placeholder="Enter Number" name="number" id="number" value="<%= customer.phone_num %>" required><br>

				<input type="checkbox" id="tandc" name="tandc">
				<label for="tandc">All Above infomation is Correct?</label><br>
				<div class="submit">
					<button type="submit" id="submitbtn" class="submitbtn"> Save Changes </button>
					<button type="cancel" id="cancelbtn" class="cancelbtn"> Cancel </button>
				</div>
			</form>
		</div>
	</div>
	
	<!-- Footer -->
    <jsp:include page="components/footer.jsp"/>  
    
	
</body>
</html>