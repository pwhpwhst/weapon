<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<h2>Hello World!</h2>

	<form action="login">
		<s:property value="Result" />
		<br /> 姓名<input type='text' name="Name"> <br /> 年龄<input
			type='text' name=Age> <input type="submit" value="提交">
	</form>
</body>
</html>
