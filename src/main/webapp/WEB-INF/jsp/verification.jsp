<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<f:form action="activation.htm" method="post" modelAttribute="sim">
<h1>${message}</h1>
	Enter Sim Number:<f:input type="text" path="simNumber"/><f:errors path="simNumber"/><br/>
	Enter Service Number<f:input type="text" path="serviceNumber"/><f:errors path="serviceNumber"/><br/>
	<input type="submit" value="submit"/> 
</f:form>


</body>
</html>