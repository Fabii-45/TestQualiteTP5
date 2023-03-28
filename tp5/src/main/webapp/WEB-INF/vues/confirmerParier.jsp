<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="confirmerparier.titre"/></title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

<s:text name="confirmerannulerpari.texte1"/> <s:property value="match.sport"/> :
<s:property value="match.equipe1"/>
<s:text name="confirmerannulerpari.text3"/>
<s:property value="match.equipe2"/>
<s:text name="confirmerajoutermatch.texte3"/>
<s:property value="match.quand"/>

<p>
    <s:text name="affichermatchs.resultat"/> <s:property value="pari.vainqueur"/>
    <s:text name="confirmerannulerpari.text7"/> <s:property value="pari.montant"/>
</p>

<p><s:text name="confirmerparier.confirmer"/></p>

<s:a action="goMenu"><s:text name="retouraumenu"/></s:a>

</body>
</html>
