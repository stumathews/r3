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
    <c:import url="/Navigation/TopMenu"></c:import>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <c:import url="/Navigation/NavMenu"></c:import> 
        </div><!--/span-->       
        <div class="span9">
          <!-- Create a review -->
        <c:url var="CreateReviewPostURL" value="/Review" />
        <form:form modelAttribute="NewReview" action="${CreateReviewPostURL}" method="post">
                <fieldset>
                    <legend>Add a review</legend>                    
                        <form:label for="text" path="text">Review</form:label>
                        <form:input type="text" path="text" />  
                        <span class="help-block">What is the summary of your review</span>      
                        
                        <form:label type="text" path="highlights">Highlights</form:label>
                        <form:input type="text" path="highlights" />
                        <span class="help-block">What were the highlights?</span>
                        
                        <form:label type="text" path="lowlights">Lowlights</form:label>
                        <form:input type="text" path="lowlights" />
                        <span class="help-block">What were the lowlights?</span>
                            
                        <input type="submit" value="add" class="btn" />                                        
                </fieldset> 
                </form:form>          
          
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