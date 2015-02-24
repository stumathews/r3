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
      <title>Create Characteristic Review</title>
</head>
<body>    
    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div><!--/span-->       
        <div class="span9">
          <!-- Create a review -->
          <c:url var="CreateReviewPostURL" value="/Review" />    
          <div class="pull-right">
              ${product.getTitle()}><br/>
              Review count: ${review.getCharacteristicReviews().size()}
          </div>
        <form:form modelAttribute="currentCharacteristicReview" >
                <fieldset>
                    <legend>Add a review of ${selectedCharacteristic.getTitle()}</legend> 
                    <div class="alert alert-info">
                        <Strong>Info</strong> Please add your review of ${product.getTitle()}'s ${selectedCharacteristic.getTitle()}
                    </div>
                    <strong>Characteristic: </strong>${selectedCharacteristic.getTitle()}
                    <br/>
                    <strong>Characteristic Description: </strong>${selectedCharacteristic.getDescription()}   
                    <br/><br/>
                    
                       <form:label for="review_text" path="review_text">Review</form:label>                        
                        <form:textarea path="review_text" rows="6"/>
                                                
                        <br/>
                         <input type="submit" name="_eventId_finish"  class=Button value="Finish"/> 
                         <!--<input type="submit" name="_eventId_more" class=Button value="Add review on another characteristic"/>-->
                </fieldset> 
                
                <br/>
                <c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">  
                  <div class="alert alert-danger">
                      <p class="text-error"><strong>Error: </strong>${message.text}</p>   
                  </div>
                </c:forEach>
                
                </form:form>          
          
        </div><!--/span-->
      </div><!--/row-->

      <hr/>

      <footer>
          ${flowRequestContext.flowScope}
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