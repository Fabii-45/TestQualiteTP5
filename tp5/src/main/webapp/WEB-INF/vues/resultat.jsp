<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="resultat.titre"/></title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

<s:text name="resultat.text1"/> <s:property value="match.sport"/> :
<s:property value="match.equipe1"/>
<s:text name="resultat.text2"/>
<s:property value="match.equipe2"/>
<s:text name="resultat.text3"/>
<s:property value="match.quand"/>

<s:form action="resultat">
    <s:hidden name="idMatch" value="%{match.idMatch}"/>
    <s:select list="resultatsPossibles" name="resultat"/>
    <s:submit/>
</s:form>

<s:a action="goMenu"><s:text name="retouraumenu"/></s:a>/s:a>

</body>
</html>
