<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="confirmerannulerpari.titre"/></title>
</head>
<body>
<h2><s:property value="utilisateur.login"/></h2>

<s:text name="confirmerannulerpari.texte1"/> <s:property value="pari.match.sport"/> :
<s:property value="pari.match.equipe1"/>
<s:text name="confirmerannulerpari.text3"/>
<s:property value="pari.match.equipe2"/>
<s:text name="confirmerannulerpari.text5"/>
<s:property value="pari.match.quand"/>

<p>
    <s:text name="confirmerannulerpari.text6"/> <s:property value="pari.vainqueur"/>
    <s:text name="confirmerannulerpari.text7"/> <s:property value="pari.montant"/>
</p>

<p>
    <s:text name="confirmerannulerpari.text4"/>
</p>

<s:a action="goMenu"><s:text name="retouraumenu"/></s:a>

</body>
</html>
