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
        <p>Contact us by visiting one of our Branches<br>Ring us at +013456728<br> or choose one of two options below</p>
        <div>
            <button type="button" onclick="location.href='loginpage'"><span></span>Login</button>
            <button type="button" onclick="location.href='about'"><span></span>About Us</button>
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