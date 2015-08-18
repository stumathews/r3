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
      <title>Add Product</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>
    
    <div class="container-fluid">
      <div class="row-fluid">        
        <div class="span3">          
          <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div>        
        <div class="span9">            
          <div class="container"> 
            <form th:object="${product}" th:action="@{/Product/Create}" action="/Product/Create" method="post">
                <fieldset>
                    <legend>Add product</legend>
                    <p>Please enter the details for your new product</p>
                        <label>Who made it?</label>
                        <input type="text" id="whoMadeIt" name="whoMadeIt" th:value="*{whoMadeIt}" th:errorclass="text-error" />
                        <span class="help-block">Tell us who made or designed it. Maybe it belongs to someone?</span>
                            
                        <label>What is it?</label>
                        <input type="text" name="whatIsIt" id="whatIsIt" th:value="*{whatIsIt}" th:errorclass="text-error"/>
                        <span class="help-block">What is it basically? A watch, pillow or service?</span>
                        
                        <label>Title</label>
                        <input type="text" name="title" id="title" th:value="*{title}" th:errorclass="text-error"/>
                        <span class="help-block">If you had to wrap it up in a title, what would you call it?</span>                       
                        <input type="submit" value="Create/Update product" class="btn btn-primary" />
                    
                    
                </fieldset> 
                </form>            
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