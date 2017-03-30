<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	background-color:aliceblue;
	font-family:sans-serif;
	font-size:15px;
}
#menu ul{
background: #8ad385;
width:250px;
padding:0;
list-style-type:none;
}
#menu ul li{
width:auto;
height:40px;
line-height:40px;
border-bottom: 1px solid #e8e8e8;
padding:0 1em;

}
#menu ul li a{
text-decoration:none;
color:#333;
font-weight:bold;
display: block;
}
#menu ul li:hover{
background:#CDEZCD;
}
#menu .sub-menu {
position : absolute;
left: 250px;
top: 0;
display: none;
}
#menu ul li:hover .sub-menu{
display:block;
}
div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
#header{
	background-color: aliceblue;
}
#footer{
	background-color: aliceblue;
}
</style>
</head>
<body>
<div id="header">
<h1>Project Fresher Final</h1>
<h2>My name : An Nguyen</h2>
<h2>Mentor : Do Tien Phong</h2>
</div>
<div id="menu">
<ul>
	
	<li><a href="register">Register</a></li>
	<li><a href="generate">Set Database</a></li>
	<li><a href="inputgenerate">Generate</a></li>

	
</ul>
</div>
<div id="footer">
	<p align="center">Thank for your support</p> 
</div>
</body>
</html>