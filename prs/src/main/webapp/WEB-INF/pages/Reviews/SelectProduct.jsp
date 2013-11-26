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
          <!-- CONTENT GOES HERE -->
          
        <h2>Please select a product to base your review on</h2>       
        <form:form commandName="product"> 
            <table>
                <th>Product Title</th>
                <th>Product Who made it</th>
                <th>Product What is it</th>
                <th>Identifier</th>
            <c:forEach items="${products}" var="prod" varStatus="counter">
                <tr>
                    <td>
                        <form:radiobutton path="identifier" value="${prod.getIdentifier()}"/>
                    </td>                                          
                    <td>${prod.getWhoMadeIt()}<td>                         
                    <td>${prod.getWhatIsIt()}<td>                         
                    <td>${prod.getIdentifier()}</td>
                </tr>
            </c:forEach>                
            </table>
            <br/>
            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
            <input type="hidden" name="_eventId" value="submit" />
            <input type="submit" value="Next >" />
            
            <c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">                       
                  <p class="text-error">${message.text}</p>                      
            </c:forEach>
          </form:form>          
       
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
          ${flowRequestContext.flowScope}
          <br/>
          ${flowRequestContext.messageContext}
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