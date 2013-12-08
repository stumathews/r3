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
          <!-- Create a review -->
        <c:url var="CreateReviewPostURL" value="/Review" />
        <form:form modelAttribute="review" >
                <fieldset>
                    <legend>Add a review for ${product.getTitle()}</legend> 
                    <div class="alert alert-warning"><Strong>TODO</strong> Specify the characteristics available for product. Select one to review.</div>
                    
                        <form:label for="text" path="text">Review</form:label>
                        <form:input type="text" path="text" />  
                        <span class="help-block">What is the summary of your review</span>      
                        
                        <form:label type="text" path="highlights">Highlights</form:label>
                        <form:input type="text" path="highlights" />
                        <span class="help-block">What were the highlights?</span>
                        
                        <form:label type="text" path="lowlights">Lowlights</form:label>
                        <form:input type="text" path="lowlights" />
                        <span class="help-block">What were the lowlights?</span>
                            
                        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
                        <input type="hidden" name="_eventId" value="submit" />
                        <input type="submit" value="Next >" />                                     
                </fieldset> 
                
                <br/>
                <c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">  
                  <div class="alert alert-danger">
                      <p class="text-error"><strong>Error: </strong>${message.text}</p>   
                  </div>
                </c:forEach>
                
                </form:form>          
          
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
          ${flowRequestContext.flowScope}
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