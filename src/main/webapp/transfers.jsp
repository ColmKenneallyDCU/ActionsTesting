<html>
<head>
<title>Transfers</title>
<link rel="stylesheet" href="css/transfers.css">
</head>    
<body>
    
    <!-- Banner -->
    <jsp:include page="components/banner.jsp"/>  
    
    
	<div class="transfers">
		<div class="mytransfers">
			<h2> Transfers <h2>
		</div>
		<div class="container">
			<form class="transferform">
				<label for="payee"> Payee: </label><br>
				<select name="payee" id="payee">
					<option disabled selected value> -- Choose Payee  -- </option>
					<option value="Payee1">Payee 1 (IBAN:159753123)</option>
					<option value="Payee2">Payee 2 (IBAN:159753148)</option>
					<option value="Payee3">Payee 3 (IBAN:159753178)</option>
					<option value="Payee4">Payee 4 (IBAN:159753023)</option>
				</select><br>
				<label for="account"> Debit Account: </label><br>
				<select name="account" id="account">
					<option disabled selected value> -- Choose Debit Account  -- </option>
					<option value="acc1">Savings Account (IBAN:500753485)</option>
					<option value="acc2">Current Account (IBAN:2009753148)</option>
				</select><br>
				
					<label for="amount"> Amount: </label><br>
				<div class="amount">
					<input type="text" id="amount" name="amount">
					<div class="amtbtn">
						<button type="button" class="amtvalue" id="btn1" value="50"> $50 </button>
						<button type="button" class="amtvalue" id="btn2" value="75"> $75 </button>
						<button type="button" class="amtvalue" id="btn3" value="100"> $100 </button>
						<button type="button" class="amtvalue" id="btn4" value="200"> $200 </button>
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