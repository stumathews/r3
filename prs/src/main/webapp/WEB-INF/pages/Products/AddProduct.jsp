<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-spring3-3.dtd">

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
      <title>Add Product</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>
    
    <div class="container-fluid">
      <div class="row-fluid">
        <!-- column for navigation menu -->
        <div class="span3">          
          <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div>
        <!-- Column for main page content -->
        <div class="span9">            
          <div class="container">            
           
            <!-- Add product form -->
            <c:url value="/Product/Create" var="AddProductURL"/>
            <form:form modelAttribute="product" action="${AddProductURL}" method="post">
                <fieldset>
                    <legend>Add product</legend>
                    <p>Please enter the details for your new product</p>
                        <form:label for="whoMadeIt" path="whoMadeIt">Who made it?</form:label>
                        <form:input type="text" path="whoMadeIt" />  <form:errors path="whoMadeIt" cssClass="text-error"></form:errors>  
                            <span class="help-block">Tell us who made or designed it. Maybe it belongs to someone?</span>
                            
                        <form:label for="whatIsIt" path="whatIsIt">What is it?</form:label>
                        <form:input type="text" path="whatIsIt" /><form:errors path="whatIsIt" cssClass="text-error"></form:errors>  
                            <span class="help-block">What is it basically? A watch, pillow or service?</span>
                        <form:label for="title" path="title">Title</form:label>
                        <form:input type="text" path="title"/> <form:errors path="title" cssClass="text-error"></form:errors>  
                            <span class="help-block">If you had to wrap it up in a title, what would you call it?</span>                                                    
                        <form:hidden path="identifier"/>
                        
                        <c:choose>
                            <c:when test="${empty NewProduct.getIdentifier()}">
                                <c:set var="action" value="create" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="action" value="update" />
                            </c:otherwise>
                        </c:choose>
                        <input type="submit" value="${action} new product" class="btn btn-primary" />
                    
                    
                </fieldset> 
                </form:form>            
        </div>          
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
    <script>
        // $j is now an alias to the jQuery function; creating the new alias is optional.
        var $j = jQuery.noConflict();
        
        // JQuery's ready() method:
        $j( document ).ready(function(){
            main(); // call our main function to get th ball rolling
        });
            
       /* We pass this main() method into ready() because it looks cleaner :-) */     
       function main()
       {
           
       }
    </script>

  

</body>

</html>