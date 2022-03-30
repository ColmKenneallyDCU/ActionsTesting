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
<title>My Account</title>
<link rel="stylesheet" href="css/account.css">
</head>    
<body>
    
    <!-- Banner -->
    <jsp:include page="components/banner.jsp"/>  
    
    
	<div class="row">	
	<div class="column">
	<div class="account">
		<div class="myaccount">
			<h2> My Accounts <h2>
		</div>
		<!-- <div class="acctype1">
			<span id="account1"><b>Savings Account</b></span>
			<span id="balance1" style="float: right;"><b> $2000 </b></span> 
			
			<div class="accountinfo1">
				<span id="accountinfo1">IBAN: </span></br>
				<span id="accountinfo1">Account Number: </span>
			</div>
		</div>
		<div class="acctype2">
			<span id="account2"><b>Current Account</b></span>
			<span id="balance2" style="float: right;"><b> $5000 </b></span>
			<div class="accountinfo2">
				<span id="accountinfo2">IBAN: </span></br>
				<span id="accountinfo2">Account Number: </span>
			</div>
		</div> -->
		<% for(Account ac: listaccs){ %>
			<div class="acctype1">
			<span id="account1"><b><%= ac.account_type.toString() %> Account</b></span>
			<span id="balance1" style="float: right;"><b> $ <%= ac.balance.toString() %> </b></span> 
			
			<div class="accountinfo1">
				<span id="accountinfo1">IBAN: <%= ac.iban %></span></br>
				<span id="accountinfo1">Account Number: <%= ac.account_number %></span>
			</div>
		</div>
		<%} %>
	</div>
	</div>
	<div class="column">
		<div class="transcations">
		<div class="Last5entry">
			<h3> Last 5 Transcations <h3>
		</div>
		<!--Added some hardcoded values in table for understanding. Table values should be fetched dynamically from database-->
		<div class="tablecontainer" id="tablebox">
			<table id="myTable" class="table-bordered">
			<thead>
				<tr>
					<th>Date</th>
					<th>Time</th>
					<th style="width: 300px;">Description</th>
					<th>Credit</th>
				
				<% for(Transaction t: listtrans){ %>
					<th><%= new SimpleDateFormat("dd.MM.yyyy").format(t.date_issued) %></th>
					<th><%= new SimpleDateFormat("HH:mm:ss").format(t.date_issued) %></th>
					<th style="width: 300px;"><%= t.description %></th>
					<th><%= t.amount.toString() %></th>
				<%} %>
				</tr>
			</thead>
			</table>
		</div>
	</div>
		
	</div>
	</div>

	
	<div class="logininfo">
		<div class="container">
			<span><b>Last Login </b></span><br>
			<img src="images/desktop.png" class="loginlogo">
			<span id="logintime">Time: <%=new SimpleDateFormat("HH:mm:ss").format(l.last_online) %></span><br>  <!--Hardcoded values for demonstration. Should be a real-time value-->
			<span id="logindevice">Date: <%=new SimpleDateFormat("d MMM yyyy").format(l.last_online) %></span><br>
		</div>
	</div>
	
	<div class="Links">
		<button type="button" id="btn1" class="btn">Send Money</button>
		<button type="button" id="btn2" class="btn">Bank Statements</button>
		<button type="button" id="btn3" class="btn">Manage Payee</button>
	</div>
	
	<div class="footer-basic">
        <footer>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="HomePage.html">Home</a></li>
                <li class="list-inline-item"><a href="#">Services</a></li>
                <li class="list-inline-item"><a href="#">About</a></li>
                <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
            </ul>
            <p class="copyright">Bank of DCU Â© 2022</p>
        </footer>
    </div>
</body>
</html>
		
