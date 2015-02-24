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
          
            
            <!-- Table of users -->
            <table class="table table-hover">
		<th>Number</th>
		<th>User name</th>
		<th>Action</th>
	
                <!-- Create the table of users -->
		<c:forEach items="${users}" var="user" varStatus="counter">			
                    <tr>
                        <td><c:out value="${counter.count}"></c:out></td>
                        <c:url value="/User/${user.getUsername()}" var="viewUserURL" />
                        <td><a href="${viewUserURL}"><c:out value="${user.getUsername()}"></c:out></a></td>
                        <c:url value="/User/Delete/${user.getUsername()}" var="deleteURL"/>
                        <td><a href="${deleteURL}"> Delete </a></td>
                    </tr>
		</c:forEach>
            </table>
            <c:url value="/User/ShowAddUser" var="createUserURL"/>
            <a class="btn btn-primary" href="${createUserURL}">Create new user</a>
         
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