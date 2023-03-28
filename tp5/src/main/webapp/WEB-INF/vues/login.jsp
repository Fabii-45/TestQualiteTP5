<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="connexion.titre"/></title>
</head>
<body>

<s:form action="login">
    <s:textfield name="login" key="login"/>
    <s:password name="password" key="password"/>
    <s:submit/>
</s:form>

</body>
</html>
