<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-spring4-2.dtd">

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

    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">          
            <div th:replace="Common/NavMenu :: NavMenu"></div>          
        </div><!--/span-->
        <div class="span9">
          <div class="container">
                <h1>R3</h1>
                <form name='f' th:action='@{/login}' method='POST'>
                <table>
                    <tr><td>User:</td><td>
                    <input type='text' name='username' value='' /></td></tr>
                    <tr><td>Password:</td>
                    <td><input type='password' name='password'/></td></tr>
                    <tr><td colspan='2'>
                    <input name="submit" type="submit" value="Login"/></td></tr>
                </table>
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