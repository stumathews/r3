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
      <title>View Product</title>
</head>
<body>
<div th:replace="Common/TopMenu :: TopMenu"></div>
<div class="container-fluid">
  <div class="row-fluid">
      
    <!-- Column for the navigation menu -->
    <div class="span3">          
        <div th:replace="Common/NavMenu :: NavMenu"></div>          
    </div>
    
    <!-- Small space between nav menu and main page content -->
   <c:url var="/Product/ShowEdit/${product.getIdentifier()}" value="/Product/ShowEdit/${product.getIdentifier()}"/>
   <c:url value="/Product/add/characteristic/${product.getIdentifier()}" var="/Product/add/characteristic/${product.getIdentifier()}"/> 
   <c:url value="/add-review?productId=${product.getIdentifier()}" var="/add-review?productId=${product.getIdentifier()}"/>        
   <c:url value="/add-recommendation?productId=${product.getIdentifier()}" var="/add-recommendation?productId=${product.getIdentifier()}"/>
   
   
    <!-- Column for main page content -->
    <div class="span9">
        <div class="row">  
            <div style="padding-left:10px;">
            <!-- Column for produt picture -->
            <div class="span2">              
                <img class="img-polaroid" th:src="@{/themes/images/product_image.gif}" src="$../..themes/images/product_image.gif"/>                
            </div>
            <!-- Column next to picture for basic product details -->            
            <div class="span9">
                <div class="row-fluid">                    
                    <dl class="dl-horizontal">
                        <dt>Title: </dt><dd th:text="${product.title}">title</dd>
                        <dt>Who made it: </dt>
                        <dd th:text="${product.getWhoMadeIt()}">who</dd>
                        <dt>What it is: </dt>
                        <dd th:text="${product.getWhatIsIt()}">what</dd>
                    </dl>
                    </div>                
                <div class="row-fluid">                       
                    <ul class="inline"> 
                        <li><a href="/Product/ShowEdit/1" th:href="@{'/Product/ShowEdit/'+${product.getIdentifier()}}" >Edit</a><br/></li>
                        <li><a href="/Product/add/characteristic/1" th:href="@{'/Product/add/characteristic/'+${product.getIdentifier()}}" class="btn btn-primary">Add detail</a></li>
                        <li><a href="/add-review?productId=1" th:href="@{'/add-review?productId='+${product.getIdentifier()}}" class="btn btn-primary">Review this</a></li>
                        <li><a href="/add-recommendation?productId=1" th:href="@{'/add-recommendation?productId='+${product.getIdentifier()}}" class="btn btn-primary">Recommend this</a></li>
                    </ul>                     
                </div>                
            </div>
           </div> 
        </div>    
        <br/> <!-- next row -->
        
        <div class="row">    
           <div style="padding-left:10px;">
            <div class="span12">      
              <strong>Characteristics:</strong>
              <p>These are the characteristics that make up this product.<br/>You can review each of them separately or make a full review, choosing multiple to base your review on.</p>                           
              <div class="span1"></div>
              <div class="span11">
                  <div class="tab-content">                      
                      Tab Content
                  </div> <!-- tab-content -->
              </div> 
            </div>
            </div>              
        </div>        
    </div>
  </div>

  <hr/>
  
  ${model}

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
   
    $( document ).ready(function(){        
       $('#characteristicTabs a:first').tab('show');  
       
    });

</script>
  

</body>

</html>