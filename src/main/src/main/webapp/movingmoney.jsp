<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="bankapp.Login" %>
<%@page import="bankapp.Account" %>
<%@page import="bankapp.Transaction" %>
<%@page import="java.text.SimpleDateFormat" %>
<%Login l = (Login)request.getAttribute("login");%>
<%List<Account> listaccs = (List<Account>)request.getAttribute("accounts");%>
<%List<Transaction> listtrans = (List<Transaction>)request.getAttribute("transactions");%>


<html>
<head>
<title>Moving Money</title>
<link rel="stylesheet" href="css/transfers.css">
</head>    
<body>
    
    <!-- Banner -->
    <jsp:include page="components/banner.jsp"/>  
    
    
	<div class="transfers">
		<div class="mytransfers">
			<h2> Transfers Between Accounts <h2>
		</div>
		<div class="container">
			<form class="transferform" method="POST">
				<label for="payee"> Account to Transfer: </label><br>
				<select name="payee" id="payee">
					<option disabled selected value> -- Choose Account  -- </option>
					
					<% for(Account ac: listaccs){ %>
						<option value="<%= ac.account_number %>"><%= ac.account_type.toString() %> Account</option>
					<% } %>
					
				</select><br>
				<label for="account"> Account to Recieve: </label><br>
				<select name="account" id="account">
					<option disabled selected value> -- Choose  Account  -- </option>
					<% for(Account ac: listaccs){ %>
						<option value="<%= ac.account_number.toString() %>"><%= ac.account_type.toString() %> Account</option>
					<% } %>
				</select><br>
				
					<label for="amount"> Amount: </label><br>
				<div class="amount">
					<input type="text" id="amount" name="amount">
					<div class="amtbtn">
						<button type="button" class="amtvalue" id="btn1" value="50"> 50 </button>
						<button type="button" class="amtvalue" id="btn2" value="75"> 75 </button>
						<button type="button" class="amtvalue" id="btn3" value="100"> 100 </button>
						<button type="button" class="amtvalue" id="btn4" value="200"> 200 </button>
					</div>
				</div>
				<input type="checkbox" id="tandc" name="tandc">
				<label for="tandc"> I accept all the Terms and Conditions for this transfer.</label><br>
				<div class="submit">
					<button type="submit" id="submitbtn" class="submitbtn"> Transfer Amount </button>
					<button type="cancel" id="cancelbtn" class="cancelbtn"> Cancel </button>
				</div>
			</form>
		</div>
	</div>
	
	<!-- Footer -->
    <jsp:include page="components/footer.jsp"/>  
    
</body>
</html>