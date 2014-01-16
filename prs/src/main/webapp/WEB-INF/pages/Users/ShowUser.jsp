<%-- 
    Document   : ShowUser
    Created on : 09-Jan-2014, 07:57:16
    Author     : Stuart
--%>

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
          <!-- SPLIT PANEL 1-->
          <div class="span3">
            <!-- User picture -->
            <img class="img-polaroid" src="http://lorempixel.com/149/207"/> 
          </div>
          
          <div class="span6">
            <div class="row-fluid">  
              <!-- User details -->
              User details<hr/>
                    <dl class="dl-horizontal">
                        <dt>Username: </dt><dd>${user.getUsername()}<dd/>
                        <dt>Password: </dt><dd>${user.getPassword()}</dd>                        
                    </dl>
            </div> 
            <div class="row-fluid">   
              <!-- User Reviews -->
              User Reviews:<hr/>
            <dl class="dl-horizontal">
                <div id="reviews">
                    <div id="ajax-loader" class="pagination-centered">
                        <img src="${themeURLBase}/images/ajax-loader.gif"/>
                    </div>   
                </div>
            </dl>
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
    <script src="${themeURLBase}/jquery.js"></script>
    <script src="${themeURLBase}/bootstrap/js/bootstrap.min.js"></script>
    <script>
   
    $( document ).ready(function(){        
         if (request) {
              request.abort();  // abort any pending request
         }
       
        
       var request = $.ajax({
              dataType: "json",
              url : "/PRS/Review/${user.getUsername()}/json",
              type : "GET"              
            });
            
        // This is jQuery 1.8+
        // callback handler that will be called on success
        request.done(function(data) {
          
          $("#ajax-loader").remove();
          
          /* We're expecting a list of user's reviews */
          for ( var i = 0; i < data.length; i++) {
            var review = data[i];
            var product = review.product;
            var reviewer = review.reviewer;            
            var characteristicReviews = review.characteristicReviews;
            var newDiv = "<dt>Product:</dt>"+
                         "<dd>"+product.title+"</dd>";
            $(newDiv).appendTo('#reviews');
            
            /* Lets detail each of the characteristics the user has reviewed in each review */    
            characteristicReviews.forEach(function(entry){
                var ch_review = entry;
                var review_text = ch_review.review_text;
                var characteristic = ch_review.characteristic;
                var title = ch_review.title;
                var description = ch_review.description;
                
                /* Inject it into the page */
                var newDiv = "<dt>Aspect:</dt>"+
                             "<dd>"+characteristic.title+ "</dd>"+
                             "<dt>Review:</dt>"+
                             "<dd>"+review_text+"</dd>"    
                    $(newDiv).appendTo('#reviews');

            })                 
        }

            
          

        });
            
        // callback handler that will be called on failure
        request.fail(function(jqXHR, textStatus, errorThrown) {
          // log the error to the console
          alert("The following error occured: " + textStatus, errorThrown);
        });
        
        // callback handler that will be called regardless if the request failed or succeeded
            request.always(function() {
              //$inputs.prop("disabled", false);  // re-enable the inputs
            });

            event.preventDefault();   // prevent default posting of form
       
    });

    </script>
  

</body>

</html>