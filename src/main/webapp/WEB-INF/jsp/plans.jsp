
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
<h1>${msg}</h1>
<h1>${name} : ${sim}</h1>
<h1><input type = "submit" value="Logout" onclick="window.close()"></h1>

	<h1>Plans</h1>

	<form action="plans.htm" method="post">
		<table style="width: 100%">
			<tr>
				<th>Select</th>
				<th>Plan Name</th>
				<th>Description</th>
				<th>Amount</th>
			</tr>
			<tr>
				<td><center><input type ="radio" name="plan" value="1" /></center></td>
				<td>Silver Plan</td>
				<td>Unlimited Calls, 10 GB 4G data per day, Validity for 30 days</td>
				<td> 149</td>
			</tr>
			<tr>
				<td><center><input type="radio"  name="plan" value="2" /></center></td>
				<td>Gold Plan</td>
				<td>Unlimited Calls, 10 GB 4G data per day, Validity for 45 days</td>
				<td>199</td>
			</tr>
			<tr>
				<td><center><input type="radio" name="plan" value="3" /></center></td>
				<td>Platinum Plan</td>
				<td>Unlimited Calls, 10 GB 4G data per day, Validity for 90 days</td>
				<td> 499</td>
			</tr>
		</table>

		<input type="submit" value="submit" />
	<form>
	 <%
	    String username = null;
        if(username==null)  {
    %>  <div> 
    <br/>
    <br/>
    <br/>
   
       <label>Validate your SimCard</label> 
           <br/>      
       <a href ="activation.htm">Go for Validation</a>
     </div> 
       
    <%        } else {    %>
       <div><br/><input type="checkbox" name = ""/>Buy A Simcard</div>
    <%
        }
    %>
    </form>
</body>
</html>
