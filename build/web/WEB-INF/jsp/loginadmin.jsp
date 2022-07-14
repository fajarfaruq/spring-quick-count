<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="captcha" uri="/WEB-INF/tlds/captcha.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Admin</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/resetlogin.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/structure.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/simpla.jquery.configuration.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/facebox.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.wysiwyg.js"></script>        
    </head>
    <body>
        ${alert}
        <form action="${pageContext.request.contextPath}/masuk" method="post" class="box login">
            <fieldset class="boxBody">
                <label>Username</label>
                <input type="text" tabindex="1" title="Username Harus Diisi" placeholder="Isikan Username" name="Username" required >
                <label>Password</label>
                <input type="password" tabindex="2" title="Password Harus Diisi" placeholder="Isikan Password" name="Password" required >
                 <!--               <br/>
                <//captcha:captcha themeName="white" privatekey="6LevyN4SAAAAAEg-yZzHL9NekkIWJ-Ks1LmQ_j2N" publickey="6LevyN4SAAAAAAbQmrOVMGveMufrg_bbryRsZfpu"/>-->
            </fieldset>
            <footer>
                <input type="submit" class="btnLogin" value="Masuk" tabindex="4">
            </footer>
        </form>
        <footer id="main">
            <a href="#">Login Admin Real Count dan Rekapitulasi Pilgub Jatim 2013 Dapil Kab.Nganjuk</a> | <a href="#">by F2m</a>
        </footer>
    </body>
</html>