<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-spring4-2.dtd">

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
      <title>Show All Users</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
            <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div><!--/span-->
        <div class="span9">
        <table class="table table-hover">
          <thead>
            <tr>
            <th>Number</th>
            <th>User name</th>
            <th>Action</th>
           </tr>
        </thead>	
        <tr th:each="user : ${users}">
            <td>
              <div th:text="${userStat.count}">Index</div>
            </td>            
            <td>
              <a th:href="@{/Users/${user.Username}}" href="/Users/username" th:text="${user.Username}">
                Username
              </a>
            </td>
            <td>
              <a href="/User/Delete/username" th:href="@{/User/Delete/${user.Username}}">Delete</a>
            </td>
        </tr>		
        </table>
        <a class="btn btn-primary" href="/User/ShowAddUser" th:href="@{/User/ShowAddUser}">Create new user</a>         
        </div>
      </div>

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