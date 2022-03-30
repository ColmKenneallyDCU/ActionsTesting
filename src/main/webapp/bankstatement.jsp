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
					<option value="acc1">Savings Account (IBAN:500753485)</option>
					<option value="acc2">Current Account (IBAN:2009753148)</option>
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
			<th>Debit</th>
			<th>Credit</th>
			</tr>
			<tr>
			<td>10/03/2022</td>
			<td>10:02:30</td>
			<td>From 15975346823 to 147852369</td>
			<td></td>
			<td>1000</td>
			</tr>
			<tr>
			<td>08/03/2022</td>
			<td>18:06:48</td>
			<td>Gas Bill Payment</td>
			<td>50</td>
			<td></td>
			</tr>
			<tr>
			<td>07/03/2022</td>
			<td>09:09:09</td>
			<td>EuroSpar Tap and Pay</td>
			<td>10</td>
			<td></td>
			</tr>
			<tr>
			<td>06/03/2022</td>
			<td>20:01:58</td>
			<td>ATM Cash Withdrawal</td>
			<td>20</td>
			<td></td>
			</tr>
			<tr>
			<td>05/03/2022</td>
			<td>06:05:15</td>
			<td>Tax Refund</td>
			<td></td>
			<td>500</td>
			</tr>
			</thead>
			</table>
		</div>
	</div>
	
	<!-- Footer -->
    <jsp:include page="components/footer.jsp"/>  
    
	
</body>
</html>