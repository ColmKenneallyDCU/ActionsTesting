<html>
<head>
<title>Payee</title>
<link rel="stylesheet" href="css/transfers.css">
</head>    
<body>
    
    <!-- Banner -->
    <jsp:include page="components/banner.jsp"/>  
    
    
	<div class="transfers">
		<div class="mytransfers">
			<h2> Add Payee <h2>
		</div>
		<div class="container">
			<form class="transferform">
                <label for="payee"><b>Payee Name</b></label><br>
                <input type="text" placeholder="Enter Payee Name" name="payee" id="payee" required><br>

                <label for="Iban"><b>Payee Iban</b></label><br>
                <input type="text" placeholder="Enter Payee Iban" name="Iban" id="Iban" required><br>

                <label for="email"><b>Payee Email</b></label><br>
                <input type="text" placeholder="Enter Email" name="email" id="email" required><br>

				<input type="checkbox" id="tandc" name="tandc">
				<label for="tandc"> I accept all the Terms and Conditions to add new Payee</label><br>
				<div class="submit">
					<button type="submit" id="submitbtn" class="submitbtn"> Add Payee </button>
					<button type="cancel" id="cancelbtn" class="cancelbtn"> Cancel </button>
				</div>
			</form>
		</div>
	</div>
	
	<!-- Footer -->
    <jsp:include page="components/footer.jsp"/>  
    
	
	
</body>
</html>