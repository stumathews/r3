<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <c:import url="/Navigation/TopMenu"></c:import>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <c:import url="/Navigation/NavMenu"></c:import> 
        </div><!--/span-->       
        <div class="span9">
          
        
            
            <!-- Iterate through reviews and show them in a table -->
            <table class="table table-hover">
            <th>#</th>
            <th>Review</th>
            <th>Highlights</th>
            <th>Lowlights</th>
            <th>product</th>
            <c:forEach items="${reviews}" var="review" varStatus="counter">  
                <c:set var="highlights" value="${review.getHighlights()}" />
                <c:set var="lowlights" value="${review.getLowlights()}" />
                <tr>                    
                    <td>${counter.count}</td><td>${review.getText()}</td>    
                    <td>${highlights}</td>
                    <td>${lowlights}</td>
                    <td>${product.getTitle()}</td>
                </tr>
            </c:forEach>
            </table>
            <c:url value="/add-review" var="CreateReviewURL" />
            <a class="btn btn-primary" href="${CreateReviewURL}">Create new review</a>
            
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