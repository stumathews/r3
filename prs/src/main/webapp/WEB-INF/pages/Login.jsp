<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div class="span3">          
            <jsp:include page="/WEB-INF/pages/Common/NavMenu.jsp"/>          
        </div><!--/span-->
        <div class="span9">
          <div class="container">      
                <!-- this is so that the interceptors know to use this as login details -->
                <c:url value="j_spring_security_check" var="loginURL"/>
                
                <!-- Also, as we wont be actually needing the 
                login details(they are dealt with by the interceptors),
                we wont be using a spring form:form with modelAttribute.
                -->
                <form action="${loginURL}" method="post">
                <fieldset>
                    <legend>Login</legend>                     
                        <label for="username" path="username">Username</label>
                        <input type="text" path="username" name="j_username" />  
                            <span class="help-block">What is your user name?</span>
                        <label for="password" path="password">Password</label>
                        <input type="password" path="password" name="j_password" />
                            <span class="help-block">Specify your password</span>                        
                        <input type="submit" value="login" class="btn" />                                       
                </fieldset>
                
                <c:if test="${error}"> <!-- note c:if test="$exp" checks to see if $exp is true -->
                <p class="text-error">                    
                    <c:out value="${errorMsg}"/>
                </p>
                </c:if>               
                </form> 
            </p>
      
            
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