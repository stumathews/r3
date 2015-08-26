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
      <title th:text="#{Home.title}">Home</title>
</head>
<body>
    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">          
            <div th:replace="Common/NavMenu :: NavMenu"></div>        
        </div><!--/span-->
        <div class="span9">
          
            <h1 th:text="#{home}">Home</h1>           
            
            Content Goes Here
            
        
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