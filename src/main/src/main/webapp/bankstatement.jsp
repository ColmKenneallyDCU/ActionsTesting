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
<title>Transfers</title>
<link rel="stylesheet" href="css/bankstatement.css">
</head>    
<body>

	<!-- Banner -->
    <jsp:include page="components/banner.jsp"/>  
    
	<div class="container">
		<div class="heading">
			<h2> Account Statement <h2>
		</div>
		<div class="container1">
			<form class="form">
				<label for="account"> Account: </label><br>
				<select name="account" id="account">
					<option disabled selected value> -- Choose Account  -- </option>
					<% for(Account ac: listaccs){ %>
						<option value="<%= ac.account_number %>"><%= ac.account_type.toString() %> Account (IBAN:<%= ac.iban %>)</option>
					<%} %>
					<!-- <option value="acc1">Savings Account (IBAN:500753485)</option>
					<option value="acc2">Current Account (IBAN:2009753148)</option> -->
				</select><br>
				<div class="container3">
					<div class="startdate">
						<label for="sdate"> Start Date: </label><br>
						<input type="date" name="sdate" class="sdte" id="dte1">
					</div>
					<div class="enddate">
						<label for="edate"> End Date: </label><br>
						<input type="date" name="edate" class="edte" id="dte2">
					</div>
				</div>
				<div class="submit">
					<button type="submit" id="submitbtn" class="submitbtn"> Show Statement </button>
				</div>
			</form>
		</div>
	</div>
	
	<% if(listtrans != null){ %>
	
	<div class="transcations">
		<div class="statement">
			<h3> Statement <h3>
		</div>
		<!--Added some hardcoded values in table for understanding. Table values should be fetched dynamically from database and row should be addeded dynamically as per the number of entries-->
		<div class="tablecontainer" id="tablebox">
			
			
				<table id="myTable" class="table-bordered">
				<thead>
				<tr>
					<th>Date</th>
					<th>Time</th>
					<th style="width: 300px;">Description</th>
					<th>Amount</th>
					<th>Type</th>
				</tr>	
				<%for(Transaction t: listtrans){ %>
					<tr>
						<td><%= new SimpleDateFormat("dd/MM/yy").format(t.date_issued) %></td>
						<td><%= new SimpleDateFormat("HH:mm:ss").format(t.date_issued) %></td>
						<td><%= t.description %></td>
						<td><%= t.amount.toString() %></td>
						<td><%= t.transaction_type.toString() %></td>
					</tr>
				<%} %>		
			</thead>
			</table>
		</div>
	</div>
	<%} %>
	
	<!-- Footer -->
    <jsp:include page="components/footer.jsp"/>  
    
	
</body>
</html>