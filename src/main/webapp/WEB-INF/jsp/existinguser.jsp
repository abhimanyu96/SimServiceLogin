
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>

<f:form action="existinguser.htm" method="post" modelAttribute="loginDetails">  
Email:<f:input type="text" path="email"/><br/>  
Password:<f:input type="password" path="password"/><br/>  
<input type="submit" value="login" name="submit"/>  
</f:form>
<h4>${msg}</h4>

</body>
</html>  




