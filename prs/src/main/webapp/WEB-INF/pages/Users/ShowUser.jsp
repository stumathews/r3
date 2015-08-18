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
      <title>Show User</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div><!--/span-->       
        <div class="span9">          
          <!-- SPLIT PANEL 1-->
          <div class="span3">
            <!-- User picture -->
            <img class="img-polaroid" src="${themeURLBase}/images/product_image.gif"/>
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
            <div id="reviews">
            <dl class="dl-horizontal">                
                    <!-- Choose between traditional page data for load lazy loading ajax -->
                    <c:choose>
                        <c:when test="${useAjax == true}">
                            <!-- <span id="ajax-loader" class="pagination-centered">
                                <img src="${themeURLBase}/images/ajax-loader.gif"/>
                             </span> -->
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${reviews}" var="review" varStatus="count">
                            <c:set var="product" value="${review.getProduct()}" />
                            <dt>Product:</dt><dd>${product.getTitle()}</dd>
                            <c:set var="aspects" value="${review.getCharacteristicReviews()}"/>
                            <c:forEach items="${aspects}" var="aspect" >
                              <c:set var="characteristic" value="${aspect.getCharacteristic()}"/>
                              <dt>Aspect:</dt><dd>${characteristic.getTitle()}</dd>
                              <dt>Review:</dt><dd>${aspect.getReview_text()}</dd>
                            </c:forEach>
                          </c:forEach>
                        </c:otherwise>                        
                    </c:choose>
                 </dl>   
                </div>
            
            </div>           
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
   
    $( document ).ready(function(){        
         if (request) {
              request.abort();  // abort any pending request
         }
       
       /* Determine if we should run or not */
        
       var request = $.ajax({
              dataType: "json",
              url : "/PRS/Review/${user.getUsername()}/json",
              type : "GET"              
            });
            
        // This is jQuery 1.8+
        // callback handler that will be called on success
        request.done(function(data) {
          
          if($("#ajax-loader").length > 0 ) // do ajax if page designed that way
          {
             $("#ajax-loader").remove();         
          
            /* We're expecting a list of user's reviews */
            for ( var i = 0; i < data.length; i++) 
            {
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