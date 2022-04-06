<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>
  <title>Dcu Bank Login</title>
  <meta name="viewport" content="with=device-width, initial-scale=1.0"> 
  <link rel="stylesheet" href="css/style.css">
</head>
<form class="form" method="POST">
  <div class="title">Member Login</div>
  
  <% String s1 = (String) request.getAttribute("loginmessage"); %>
  <% if(s1 == null){%>
  	<div class="subtitle">Please Log in!</div>
  <%}else{ %>
    <div class="subtitle"><%=s1%></div>
  <%}%>
  
  <div class="input-container ic2">
    <input id="email" class="input" type="text" name="email" placeholder=" " />
    <div class="cut cut-long"></div>
    <label for="email" class="placeholder">Email</label>
  </div>
  <div class="input-container ic2">
    <input id="password" class="input" type="password" name="password" placeholder=" "/>
    <div class="cut cut-short"></div>
    <label for="email" class="placeholder">Password</>
  </div>
  <button type="text" class="submit">Submit</button>
</form>