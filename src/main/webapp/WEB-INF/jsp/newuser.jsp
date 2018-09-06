
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
<f:form action="newuser.htm" method="post" modelAttribute="user">  
Email:<f:input type="text" path="email"/><f:errors path="email"/><br/>  
Password:<f:input type="password" path="password"/><f:errors path="password"/><br/> 
Date Of Birth:<f:input type="text" path="dob"/><f:errors path="dob"/><br/>
First Name:<f:input type="text" path="fname"/><f:errors path="fname"/><br/>
Last Name:<f:input type="text" path="lname"/><f:errors path="lname"/><br/>
<f:select path="ids">
  <option value="Aadhar">Aadhar</option>
  <option value="Passport">Passport</option>
  <option value="Pan">Pan</option>
</f:select>
ID Number:<f:input type="number" path="idnumber"/>
State:<f:input type="text" path="state"/><f:errors path="state"/><br/>
Pincode:<f:input type="number" path="pincode"/><f:errors path="pincode"/><br/>  
Submit:<input type="submit" value="login"/>  

<h4><%
if (request.getParameter("msg") != null) {
        out.println(request.getParameter("msg"));
        }%></h4>
</f:form>
</body>
</html>