<html>
<head>
<title>Dcu Bank</title>
<link rel="stylesheet" href="css/home.css">
</head>    
<body>
    
    <div class="banner">
        <div class="navbar">
            <img src="images/sitelogo.png" class="logo"><!--Need a transparent photo-->
            <ul>
                <li><a href="loginpage">Login</a> </li> <!--Will make a drop down section here to contain  button-->
            </ul>    
        </div>
    
        <div class ="content">
        <h1>WELCOME TO BANK OF DCU</h1>
        <p>The Bank of all DCU students where all your needs are met,<br>choose one of two options below</p>
        <div>
            <button type="button" onclick="location.href='account'"><span></span>View Accounts</button>
            <button type="button" onclick="location.href='transfers'"><span></span>Transfer Money</button>
        </div>
        </div> 
    </div>
    <section class="services">
        <div class="ServiceTitle">
        <h1>Services We Provide</h1>
        <p>For The Various Accouts</p></div>
        
        <!--The css to redo the images is in thestyle.css + the following is straightforward-->
        <div class="row">
            <div class="socialMedia">
                <img src="images/loans.jpg">
                <h3>Loans</h3>
                <p>
                        Need some extra Pennies,
                        <br>Quickly apply for a quick loan
                        <br>Less than 10 minutes to apply for
                        <br>Quick and flexible!
                    </p>
            </div>
            <div class="socialMedia">
                <img src="images/insurance.png">
                <h3>Insurance</h3>
                <p>
                        Need Car insurance,
                        <br>Quickly apply for car insurance
                        <br>Less than 10 minutes to apply for
                        <br>Quick and flexible!e</p>
            </div>
            <div class="socialMedia">
                <img src="images/overdraft.png">
                <h3>Overdrafts</h3>
                <p>
                     Have an extra bill to pay for
                    <br>Set the amount of overdraft you need
                    <br>Very smaall interest needed to pay it back
                    <br>Quick and flexible!e</p>
            </div>
            <div class="socialMedia">
                <img src="images/account.png">
                <h3>Account Managment</h3>
                <p>
                    Thining of the future?
                    <br>Quickly apply for a credit card 
                    <br>Apply for a graduate, 3rd level or a child account 
                    <br>Less than 10 minutes to apply for
                </p>
            </div>
        </div>
          </section>
    	
          <section class="cta"> 
            <h1> Sign Up For One Of Our Services</h1>
            <button type="button"><span></span>Contact Us</button>
        </section>

    <!-- Footer -->
    <jsp:include page="components/footer.jsp"/>  
    
</body>
</html>
