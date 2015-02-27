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
    <div th:replace="Common/TopMenu :: TopMenu"/>
    <div class="container-fluid">
      <div class="row-fluid">        
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"/>
        </div>
        <div class="span9">
        <div class="container">
          <form th:object="${user}" action="/User/create" th:action="@{/User/create}" method="post">
                  <fieldset> 
                      <legend>Add user</legend>
                      
                      <label>User name</label>
                      <input type="text" name="username" id="username" th:value="*{username}" th:errorclass="text-error"/>                      
                      <span class="help-block">What will the users name be?</span>
                      
                      <label>Password</label>
                      <input type="password" name="password" id="password" th:value="*{password}" th:errorclass="text-error"/>                        
                      <span class="help-block">Put in their password here.</span>
                      <input type="submit" value="OK" class="btn"/>
                  </fieldset>  
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
    <script src="${themeURLBase}/jquery.js"></script>
    <script src="${themeURLBase}/bootstrap/js/bootstrap.min.js"></script>

  

</body>

</html>