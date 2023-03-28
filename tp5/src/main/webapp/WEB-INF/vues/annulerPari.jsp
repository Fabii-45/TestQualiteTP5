<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="annulerpari.titre"/></title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

<s:text name="annulerpari.texte1"/> <s:property value="pari.match.sport"/> :
<s:property value="pari.match.equipe1"/>
<s:text name="annulerpari.texte2"/>
<s:property value="pari.match.equipe2"/>
<s:text name="annulerpari.texte3"/>
<s:property value="pari.match.quand"/>

<p>
    <s:text name="annulerpari.texte4"/> <s:property value="pari.vainqueur"/>
    <s:text name="annulerpari.texte5"/> <s:property value="pari.montant"/>
</p>
<s:form action="annulerPari">
    <s:hidden name="idPari" value="%{pari.idPari}"/>
    <s:if test="hasActionErrors()">
        <div class="errors">
            <s:actionerror/>
        </div>
    </s:if>
    <s:submit/>
</s:form>

<s:a action="goMenu"><s:text name="retouraumenu"/></s:a>

</body>
</html>
