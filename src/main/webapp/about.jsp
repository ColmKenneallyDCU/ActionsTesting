<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Dcu Bank</title>
<link rel="stylesheet" href="css/home.css">
</head>    
<body>
    
    <!-- Front Banner -->
    <jsp:include page="components/bannerfrontpage.jsp"/>  
    
    
        <div class ="content">
        <h1>WELCOME TO BANK OF DCU</h1>
        <p>Started Back in 2019, We provide banking services for the needs all of all DCU students, through our various services<br>choose one of two options below</p>
        <div>
            <button type="button" onclick="location.href='loginpage'"><span></span>Login</button>
            <button type="button" onclick="location.href='Contact'"><span></span>Contact Us</button>
        </div>
        </div> 
    </div>
    	  <section class="cta"> 
            <h1>Looking for more Infomation</h1>
            <button type="button"><span></span>Contact Us</button>
        </section>

    <!-- Footer -->
    <jsp:include page="components/footer.jsp"/>  
    
</body>
</html>