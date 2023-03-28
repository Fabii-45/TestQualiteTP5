<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="menu.titre"/></title>
</head>
<body>

<h2><s:text name="menu.text1"/> <s:property value="utilisateur.login"/></h2>
<s:if test="%{utilisateur.isAdmin}">
    <h3><s:text name="menu.text2"/></h3>
</s:if>

<ul>
    <li><s:a action="goMatchsOuvertAuxParis"><s:text name="menu.text3"/></s:a></li>
    <li><s:a action="goMesParis"><s:text name="menu.text4"/></s:a></li>
    <s:if test="%{utilisateur.isAdmin}">
        <li><s:a action="goAjouterMatch"><s:text name="menu.text5"/></s:a></li>
        <li><s:a action="goMatchs"><s:text name="menu.text6"/></s:a></li>
    </s:if>
    <li><s:a action="logout"><s:text name="menu.text7"/></s:a></li>
</ul>

</body>
</html>
