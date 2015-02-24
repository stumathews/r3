<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-spring3-3.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
      <link rel="stylesheet" href="../../themes/bootstrap/css/bootstrap.min.css" th:href="@{/themes/bootstrap/css/bootstrap.min.css}" media="screen"  />
      <link rel="stylesheet" href="../../themes/bootstrap/css/bootstrap-responsive.css" th:href ="@{/themes/bootstrap/css/bootstrap-responsive.css}"/>
      <style>
        body {
          padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
      </style>
      <title>Add User</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
          
        <!-- Column to hold navigation menu -->
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"></div>
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

      <hr/>

      <footer>
        <p>© R3 2013</p>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${themeURLBase}/jquery.js"></script>
    <script src="${themeURLBase}/bootstrap/js/bootstrap.min.js"></script>

  

</body>

</html>