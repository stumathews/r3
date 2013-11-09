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
        <table class="table table-hover">
            <th>Title</th>
            <th>Description</th>
            <th>Review</th>  
            <th>Product</th>
            <c:forEach items="${characteristics}" var="characteristic" varStatus="count">
                <c:set value="${characteristic.getProduct()}" var="product" />
                <tr>
                <td>${characteristic.getTitle()}</td>                
                <td>${characteristic.getReview()}</td>
                <td>${characteristic.getDescription()}</td> 
                <c:url var="view_product_url" value="/Product/Show/${product.getIdentifier()}" />
                <td>
                    <a href="${view_product_url}">${product.getTitle()}</a> which is a ${product.getWhatIsIt()} by ${product.getWhoMadeIt()}</td> 
                </tr>
           </c:forEach>
            
        </table>
         
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