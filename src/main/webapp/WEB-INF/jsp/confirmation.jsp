<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>var doc = new jsPDF();

$('#cmd').click(function () {   
    doc.fromHTML($('#content').html(), 15, 15, {
        'width': 170,
            'elementHandlers': specialElementHandlers
    });
    doc.save('sample-file.pdf');
});</script>

</head>
<body>
<div class="container" id = "content">
<h1>${name} : ${sim}</h1>

<table class = "table-bordered" style={width:100%}>
			<thead>
			<tr>
				<th>Service</th>
				<th>Amount</th>
				
			</tr>
			</thead>
			<tbody>
			<tr>
				
				<td>${plandesc}</td>
				<td>${planamount}</td>
			</tr>
			</tbody>
		</table>
<br/>
<br/>
<p><strong>New sim: ${simNumber}</strong></p>
<p><strong>${msg}</strong></p>

<h4>Shipping Address</h4><br/>
<label>State: ${state}</label><br/>
<label>Pincode: ${pincode} </label><br/>


<a href="editAddress.htm"><div class="btn btn-primary">Edit Address</div></a><br/><br/><br/>

<a href="confirm.htm"><button id="cmd" class="btn btn-success">Confirm</button></a>
</div>
</body>
</html>