<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <c:url value="/themes" var="themeURLBase"/>	
    <link rel="stylesheet" href="${themeURLBase}/<spring:theme code='stylesheet'/>" type="text/css" />
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
      
    <!-- Column for the navigation menu -->
    <div class="span3">          
        <jsp:include page="/WEB-INF/pages/Common/NavMenu.jsp"/>          
    </div>
    
    <!-- Small space between nav menu and main page content -->
   <c:url var="EditURL" value="/Product/ShowEdit/${product.getIdentifier()}"/>
   <c:url value="/Product/add/characteristic/${product.getIdentifier()}" var="AddProductCharacteristicURL"/> 
   <c:url value="/add-review?productId=${product.getIdentifier()}" var="AddReviewURL"/>        
   <c:url value="/add-recommendation?productId=${product.getIdentifier()}" var="AddRecommendationURL"/>
   
    <!-- Column for main page content -->
    <div class="span9">
        <div class="row">  
            <!-- Column for produt picture -->
            <div class="span2">
                <img class="img-polaroid" src="http://lorempixel.com/149/207"/>                
            </div>
            <!-- Column next to picture for basic product details -->            
            <div class="span9">
                <div class="row-fluid">                    
                    <dl class="dl-horizontal">
                        <dt>Title: </dt><dd>${product.getTitle()}<dd/>
                        <dt>Who made it: </dt><dd>${product.getWhoMadeIt()}<dd/>
                        <dt>What it is: </dt><dd>${product.getWhatIsIt()}<dd/>   
                    </dl>
                    </div>                
                <div class="row-fluid">                       
                    <ul class="inline"> 
                        <li><a href="${EditURL}">Edit</a><br/></li>
                        <li><a href="${AddProductCharacteristicURL}" class="btn btn-primary">Add new product aspect</a></li>
                        <li><a href="${AddReviewURL}" class="btn btn-primary">Review this product</a></li>
                        <li><a href="${AddRecommendationURL}" class="btn btn-primary">Recommend this product</a></li>
                    </ul>                     
                </div>
                
            </div>
        </div>
            
        <br/> <!-- next row -->
        
        <div class="row">    
            <strong>Aspects:</strong><br/><br/>
            <div class="span12">      
                
                <!-- Construct the characteristics for the product -->
                <ul class="nav nav-tabs" id="characteristicTabs">
                    <!-- Loop through all the characteristics. 
                    Later well need to loop through only the most valuable ones -->
                    <c:forEach items="${productCharacteristics}" var="currentCharacteristic" varStatus="counter">
                        <c:set var="name" value="${currentCharacteristic.getTitle()}" />
                        <li><a href="#${fn:replace(name,' ','_')}" data-toggle="tab" >${name} <span class="badge">${counter.index + 3}</span></a></li>                        
                    </c:forEach>
                        <li><a href="${AddProductCharacteristicURL}">+ add</a></li>
                </ul>
                <!-- We put the aspects' description here for users to see :-) -->
                <div class="span1"></div>
                <div class="span11">
                    <div class="tab-content">                        
                        <c:forEach items="${productCharacteristics}" var="currentCharacteristic">
                                <c:set var="description" value="${currentCharacteristic.getDescription()}"/>
                                <c:set var="name" value="${currentCharacteristic.getTitle()}" />                                             
                                <!-- <div class="tab-pane active" id="{name}">...</div> -->
                                <div class="tab-pane" id="${fn:replace(name,' ','_')}"> 
                                    <p>
                                        <strong>Description: </strong>${description}
                                    </p>
                                    <br/>
                                    <c:forEach begin="1" end="3" varStatus="status">
                                    <div class="row-fluid">
                                        <div class="span2">                                            
                                            <img src="http://lorempixel.com/80/8${status.index}/"/>
                                            <div><strong>User name</strong></div>                                            
                                        </div>
                                        <div class="span9">                                            
                                            <p>${currentCharacteristic.getReview()}</p>                                            
                                            <!-- Show pictures detailing the characteristic of the product -->
                                            <br/><br/><br/>
                                            <p><strong>User images:</strong> Images detailing this aspect</p>

                                            <c:forEach begin="1" end="3">
                                                <!--<img class="img-polaroid" src="http://img825.imageshack.us/img825/4719/filedm.jpg"/>-->
                                                <img src="http://placehold.it/100x50" alt="..." class="img-thumbnail">
                                            </c:forEach>   
                                            <br/><br/>
                                            <a href="#">Change this review</a><br><br/>
                                            <a id="thumbs-up-btn" class="btn btn-success" href="#">
                                                <i class="icon-thumbs-up icon-white icon-align-left">                    
                                                </i> I agree with this review
                                            </a>                                           
                                        </div>  
                                            
                                    </div>
                                    <br/><br/>
                                    </c:forEach>
                                    
                                </div>                            
                        </c:forEach>

                    </div> 
                </div>               
            </div>                     
        </div>
        <div class="row"> 
            <div class="span8">
              
            </div>
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
   
    $( document ).ready(function(){        
       $('#characteristicTabs a:first').tab('show');  
       
    });

</script>
  

</body>

</html>