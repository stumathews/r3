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
      <title>Show All Reviews</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div><!--/span-->       
        <div class="span9">
          
            <!-- Iterate through reviews and show them in a table -->
            <table class="table table-hover">
            <th>#</th>
            <th>Review</th>
            <th>Highlights</th>
            <th>Lowlights</th>            
            <c:forEach items="${reviews}" var="review" varStatus="counter">  
                <c:set var="highlights" value="${review.getHighlights()}" />
                <c:set var="lowlights" value="${review.getLowlights()}" />
                <tr>                    
                    <td>${counter.count}</td><td>${review.getText()}</td>    
                    <td>${highlights}</td>
                    <td>${lowlights}</td>                    
                </tr>
            </c:forEach>
            </table>
            <c:url value="/add-review" var="CreateReviewURL" />
            <a class="btn btn-primary" href="${CreateReviewURL}">Create new review</a>
            
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