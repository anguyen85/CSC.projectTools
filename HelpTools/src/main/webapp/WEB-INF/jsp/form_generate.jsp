<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate tools</title>
	<style>
input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
input[type=button] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
</style>

</head>
<body>
<h3>Choose Your Database</h3>

<div>
  <form:form action="generate" method="post" modelAttribute="infoForm" commandName="infoForm">
    <label>Host Name</label>
   <form:input path="host" />

    <label>Username</label>
      <form:input path="user" />
  
    <label>Password</label>
     <form:input path="pass" />
     <input type="submit" value="Set Database" >
 <!-- <h3>Generate</h3>
    
     <label>Input</label>
    <input type="text" id="input" name="input">

    <label>Output</label>
    <input type="text" id="output" name="output">

    
     
    <input type="submit" value="Generate"> -->
  
  </form:form>
   
</div>
</body>
</html>