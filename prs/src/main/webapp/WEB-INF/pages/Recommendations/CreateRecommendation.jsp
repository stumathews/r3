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
      <title>Create Recommendation</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div><!--/span-->       
        <div class="span9">
                
        <form:form modelAttribute="recommendation">
            <form:label for="title" path="title">Title</form:label>
            <form:input type="text" path="title" />
            <span class="help-block">Please enter a title for this recommendation</span>  
            <form:label for="recommend" path="recommend">Yes/No?</form:label>
            <form:checkbox path="recommend"/>
            <span class="help-block">Would you recommend this product based on your review?</span>
            
             <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
                        <input type="hidden" name="_eventId" value="submit" />
                        <input type="submit" value="Next >" />
             <c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">                       
                  <p class="text-error">${message.text}</p>                      
             </c:forEach>
        </form:form>
            
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