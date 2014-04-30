<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
          
          
        
           <c:choose>
            <c:when test="${empty characteristics.getItems()}">
               <c:url var="ViewProductURL" value="/Product/Show/${product.getIdentifier()}"/>
                This product has not characteristics to review: <a href="${ViewProductURL}">${product.getTitle()}</a><br/>     
            </c:when>
            <c:otherwise>
                <!-- Select characterteristics that you'd like to review. Show all the product's characteristics.          -->
            <p>Select a characteristic of <a href="${ViewProductURL}">${product.getTitle()}</a> that you want to review.</p>
            <!-- Get the products characteristics -->
          
                <!-- Ask user to select the characteristics to base review on -->
                <form:form modelAttribute="selectedCharacteristics">
                    <table class="table table-hover">
                    <th></th>
                    <th>Title</th>
                    <th>description</th>                  
                             
                      <ul>                        
                        <form:checkboxes itemLabel="title" path="items" items="${characteristics.getItems()}"/>
                      </ul>
                              
                    </table>                   
                    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
                    <input type="hidden" name="_eventId" value="submit" />
                    <input type="submit" value="Next >" />

                    <c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">                       
                        <p class="text-error">${message.text}</p>                      
                    </c:forEach>
                </form:form>
            </c:otherwise>
          </c:choose>
                
                  
              
            
          
          
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