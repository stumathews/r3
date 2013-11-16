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
   
    
    <!-- Column for main page content -->
    <div class="span9">
        <div class="row">  
            <!-- Column for produt picture -->
            <div class="span2">
                <img class="img-polaroid" src="http://img825.imageshack.us/img825/4719/filedm.jpg"/>                
            </div>
            <!-- Column next to picture for basic product details -->
            <div class="span6">                    
                <strong>Title: </strong>${product.getTitle()}<br/>
                <strong>Who made it: </strong>${product.getWhoMadeIt()}<br/>
                <strong>What it is: </strong>${product.getWhatIsIt()}<br/>                                       
            </div>
        </div>
            
        <br/> <!-- next row -->
        
        <div class="row">                
            <div class="span8">      
                
                <!-- Construct the characteristics for the product -->
                <ul class="nav nav-tabs" id="characteristicTabs">
                    <!-- Loop through all the characteristics. 
                    Later well need to loop through only the most valuable ones -->
                    <c:forEach items="${productCharacteristics}" var="currentCharacteristic">
                        <c:set var="name" value="${currentCharacteristic.getName()}" />
                        <li><a href="#${fn:replace(name,' ','_')}" data-toggle="tab">${name}</a></li>                        
                    </c:forEach>
                </ul>
                <!-- We put the aspects' description here for users to see :-) -->
                <div class="tab-content">
                    <c:forEach items="${productCharacteristics}" var="currentCharacteristic">
                        <c:set var="description" value="${currentCharacteristic.getDescription()}"/>
                        <c:set var="name" value="${currentCharacteristic.getName()}" />                                             
                        <!-- <div class="tab-pane active" id="{name}">...</div> -->
                        <div class="tab-pane" id="${fn:replace(name,' ','_')}">
                            </p><strong>Description: </strong>${description}</p>
                            <p>${currentCharacteristic.getReview()}<a href="#">change</a></p>
                            <p></p>
                        </div>                                
                    </c:forEach>
                </div> 

                <c:url value="/Product/add/characteristic/${product.getIdentifier()}" var="AddProductCharacteristicURL"/>                   
                <a href="${AddProductCharacteristicURL}" class="btn btn-primary">Add new product aspect</a>
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