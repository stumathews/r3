<%-- 
    Document   : ShowUser
    Created on : 09-Jan-2014, 07:57:16
    Author     : Stuart
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <jsp:include page="/WEB-INF/pages/Common/TopMenu.jsp" />

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <jsp:include page="/WEB-INF/pages/Common/NavMenu.jsp"/>
        </div><!--/span-->       
        <div class="span9">          
          <!-- SPLIT PANEL 1-->
          <div class="span3">
            <!-- User picture -->
            <img class="img-polaroid" src="http://lorempixel.com/149/207"/> 
          </div>
          
          <div class="span6">
            <div class="row-fluid">  
              <!-- User details -->
              User details<hr/>
                    <dl class="dl-horizontal">
                        <dt>Username: </dt><dd>${user.getUsername()}<dd/>
                        <dt>Password: </dt><dd>${user.getPassword()}</dd>                        
                    </dl>
            </div> 
            <div class="row-fluid">   
              <!-- User Reviews -->
              User Reviews:<hr/>
                    <dl class="dl-horizontal">
                      <c:forEach items="${reviews}" var="review" varStatus="count">
                        <c:set var="product" value="${review.getProduct()}" />
                        <dt>Product:</dt><dd>${product.getTitle()}</dd>
                        <c:set var="aspects" value="${review.getCharacteristicReviews()}"/>
                        <c:forEach items="${aspects}" var="aspect" >
                          <c:set var="characteristic" value="${aspect.getCharacteristic()}"/>
                          <dt>Aspect:</dt><dd>${characteristic.getTitle()}</dd>
                          <dt>Review:</dt><dd>${aspect.getReview_text()}</dd>
                        </c:forEach>
                      </c:forEach>                        
                    </dl>
            </div>           
          </div>
          
          
          
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
    <script src="${themeURLBase}/jquery.js"></script>
    <script src="${themeURLBase}/bootstrap/js/bootstrap.min.js"></script>

  

</body>

</html>