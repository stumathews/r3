<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <c:url value="/themes" var="themeURLBase"/>	
    <!-- <link rel="stylesheet" href="${themeURLBase}/<spring:theme code='stylesheet'/>" type="text/css" /> -->
    <link rel="stylesheet" href="${themeURLBase}/bootstrap/css/bootstrap.min.css" media="screen">
    <link rel="stylesheet" href="${themeURLBase}/bootstrap/css/bootstrap-responsive.css">
        <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
        </style>
</head>
<body>

    <jsp:include page="/WEB-INF/pages/Common/TopMenu.jsp" />

    <div class="container-fluid">
      <div class="row-fluid">
          
        <!-- Column to hold navigation menu -->
        <div class="span3">
          <jsp:include page="/WEB-INF/pages/Common/NavMenu.jsp"/>
        </div>
        
        <!-- column to hold main page content -->
        <div class="span9">
          <div class="container">
            
        <!-- Allow the user to input user details -->
	<c:url value="/User/create" var="loginUrl"/>
	
	<form:form modelAttribute="user" action="${loginUrl}" method="post">
            <fieldset> 
                <legend>Add user</legend>
		<form:label for="username" path="username">User name</form:label>
		<form:input type="text" path="username"/><form:errors path="username" cssClass="text-error"></form:errors>  
                    <span class="help-block">What will the users name be?</span>
		<form:label for="password" path="password" >Password</form:label>
		<form:input type="password" path="password"/><form:errors path="password" cssClass="text-error"></form:errors>  
                    <span class="help-block">Put in their password here.</span>
                    <input type="submit" name="submit" type="submit" value="OK" class="btn">
            </fieldset>  
	</form:form>
                
        </div>
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>© R3 2013</p>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="${themeURLBase}/bootstrap/js/bootstrap.min.js"></script>

  

</body>

</html>