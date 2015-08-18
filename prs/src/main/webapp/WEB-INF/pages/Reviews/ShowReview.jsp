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
      <title>Show Review</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div><!--/span-->       
        <div class="span9">
          <!-- CONTENT GOES HERE -->
          
          <p>Thank you, ${reviewer.getUsername()} for your review on ${product.getTitle()}</p>
          <h4>Summary of your review</h4>
          
          <c:url var="ViewProductURL" value="/Product/Show/${product.getIdentifier()}"/>
          <strong>Product: </strong><a href="${ViewProductURL}">${product.getTitle()}</a><br/>
          <c:forEach items="${review.getCharacteristicReviews()}" var="characteristicReview" varStatus="count">
              
              <strong>Title: </strong> ${characteristicReview.getCharacteristic().getTitle()}<br/>
              <strong>Description: </strong><br/> ${characteristicReview.getCharacteristic().getDescription()}<br/>
              <strong>Review: </strong><br/> ${characteristicReview.getReview_text()}<br/>
          </c:forEach>
          
              <br/>
         <c:url value="/add-review?productId=${product.getIdentifier()}" var="AddReviewURL"/>
         <a href="${AddReviewURL}" class="btn btn-primary">Review as another aspect of ${product.getTitle()}</a>
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