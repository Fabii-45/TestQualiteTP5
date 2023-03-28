<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="confirmerresultat.titre"/></title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

<s:text name="confirmerresultat.texte1"/> <s:property value="match.sport"/> :
<s:property value="match.equipe1"/>
<s:text name="annulerpari.texte2"/>
<s:property value="match.equipe2"/>
<s:text name="annulerpari.texte3"/>
<s:property value="match.quand"/>

<p>
    <s:text name="confirmerresultat.texte4"/> <s:property value="match.resultat.get"/>
</p>

<s:a action="goMenu"><s:text name="retouraumenu"/></s:a>

</body>
</html>
