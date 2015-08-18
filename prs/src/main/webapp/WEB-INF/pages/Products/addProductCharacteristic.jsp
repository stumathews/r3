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
      <title>Add Product Characteristic</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row">
          
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div>
        
        <div class="span9">          
            
            <!-- Title: What is the Characteristic? -->
            <!-- Description: Give a description of what this characteristic is all about -->
            <c:url value="/Product/add/characteristic/${productID}" var="AddProductCharacteristicURL"/>
            <form:form modelAttribute="FormProductCharacteristic" cssClass="form-horizontal" action="${AddProductCharacteristicURL}" method="post">
                <fieldset>
                    <legend>Add product aspect</legend> 
                    <p>Add an aspect or characteristic of the product you wish to review. Eg. Colour, ease of use etc. </p>
                    
                        <div class="form-group">
                        <form:label for="title" path="title">Title</form:label>
                        <form:input id="char_title" type="text" path="title" cssClass="input-large" /><form:errors path="title" cssClass="text-error"></form:errors>  
                            <span class="help-block">Name or title for this characteristic eg. Colour</span>
                        </div>
                        <div class="form-group">
                        <form:label for="description" path="description">Description</form:label>
                        <form:input type="text" path="description" /><form:errors path="description" cssClass="text-error"></form:errors>
                            <span id="char_description" class="help-block"> What is specific aspect of the characteristic ? Give a description eg. Its affects the usability, style, robustness etc....</span> 
                        </div>
                                           
                </fieldset> 
                    <br/>
                <input type="submit" value="Add this aspect" class="btn btn-primary" />
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
    <script>
    $(document).ready(function(){
        
        // set the default focus for this page
        $('#char_title').focus();
       
        // update the descriptin of the aspect to describe on title focus loss
        $('#char_title').focusout(function() { 
            message = 'What is the aspect of the '+$('#char_title').val().toLowerCase()+' that you would like to describe? Give a short description.';             
            $('#char_description').text(message);
        });

    });
    
    
    </script>
        

  

</body>

</html>