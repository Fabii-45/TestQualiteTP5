<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="affichermatchsouverts.titre"/></title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>


    <s:form action="goParier">

        <s:select list="%{matchs}" name="idMatch" listKey="idMatch" listValue="%{'(' + sport + ') '+ equipe1 + ' vs '+ equipe2}"/>

    </s:form>

    <s:a action="goMenu"><s:text name="retouraumenu"/></s:a>


</body>
</html>
