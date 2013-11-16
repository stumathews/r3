<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
        <!-- column for navigation menu -->
        <div class="span3">          
          <jsp:include page="/WEB-INF/pages/Common/NavMenu.jsp"/>
        </div>
        <!-- Column for main page content -->
        <div class="span9">            
          <div class="container">            
           
            <!-- Add product form -->
            <c:url value="/Product/create" var="AddProductURL"/>
            <form:form modelAttribute="NewProduct" action="${AddProductURL}" method="post">
                <fieldset>
                    <legend>Add product</legend>
                    <div class="span5">
                        <form:label for="whoMadeIt" path="whoMadeIt">Who made it?</form:label>
                        <form:input type="text" path="whoMadeIt" />  <form:errors path="whoMadeIt" cssClass="text-error"></form:errors>  
                            <span class="help-block">Tell us who made or designed it. Maybe it belongs to someone?</span>
                            
                        <form:label for="whatIsIt" path="whatIsIt">What is it?</form:label>
                        <form:input type="text" path="whatIsIt" /><form:errors path="whatIsIt" cssClass="text-error"></form:errors>  
                            <span class="help-block">What is it basically? A watch, pillow or service?</span>
                        <form:label for="title" path="title">Title</form:label>
                        <form:input type="text" path="title"/> <form:errors path="title" cssClass="text-error"></form:errors>  
                            <span class="help-block">If you had to wrap it up in a title, what would you call it?</span>                            
                        <input type="submit" value="add" class="btn" />
                    </div>
                    
                </fieldset> 
                </form:form>            
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
    <script src="http://code.jquery.com/jquery.js"></script>
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