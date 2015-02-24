<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-spring3-3.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="../../themes/bootstrap/css/bootstrap.min.css" th:href="@{/themes/bootstrap/css/bootstrap.min.css}" media="screen"/>
    <link rel="stylesheet" href="../../themes/bootstrap/css/bootstrap-responsive.css" th:href ="@{/themes/bootstrap/css/bootstrap-responsive.css}"/>
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
    <title>Login</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">          
            <div th:replace="Common/NavMenu :: NavMenu"></div>          
        </div><!--/span-->
        <div class="span9">
          <div class="container">      
                <!-- this is so that the interceptors know to use this as login details -->
                
                <!-- Also, as we wont be actually needing the 
                login details(they are dealt with by the interceptors),
                we wont be using a spring form:form with modelAttribute.
                -->
                <h1>R3</h1>
                <form action="j_spring_security_check" method="post">
                <p>Please specify your user credentials to login to R3</p>
                    <legend>Login</legend>                     
                        <label for="username" path="username">Username</label>
                        <input type="text" path="username" name="j_username" />  
                            <span class="help-block">What is your user name?</span>
                        <label for="password" path="password">Password</label>
                        <input type="password" path="password" name="j_password" />
                            <span class="help-block">Specify your password</span>                        
                        <input type="submit" value="login" class="btn btn-primary" /><br/>                             
                </form> 
            
      
            
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
    <script th:src="@{/themes/jquery.js}" src="$../../themes/jquery.js"></script>
    <script th:src="@{/themes/bootstrap/js/bootstrap.min.js}" src="../../themes/bootstrap/js/bootstrap.min.js"></script>

  

</body>

</html>