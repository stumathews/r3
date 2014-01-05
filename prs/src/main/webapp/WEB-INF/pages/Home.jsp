<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <div class="span3">          
            <jsp:include page="/WEB-INF/pages/Common/NavMenu.jsp"/>         
        </div><!--/span-->
        <div class="span9">
          
            <h1>Home</h1>            
            <img class="centred"  src="${themeURLBase}/images/components.png"/>            
            <div>
                <h3>Overview</h1>
                <dl>
                    <dt>Recommendation management</dt>
                    <dd>The recommendation service is an system that manages product recommendations</dd>
                    <dt>Existing product catalogue integration</dt>
                    <dd>This would mainly be for a Service Provider like amazon that have a user base and a product catalogue
                        <ul>
                            <li>External Catalogue item lookup service</li>
                            <li>Link existing catalogue item's identifiers to existing matched content characteristics</li>
                        </ul>
                    </dd>
                    <dt>Custom Characteristics</dt>
                    <dd>The system primarily provides functionality for a user to define a product's characteristics and then provide feedback on those aspects of the product
                        <ul>
                            <li>Construct characteristics with custom meaning</li>
                            <li>Review characteristics</li>
                        </ul>
                    </dd>
                    <dt>Peer insight visibility</dt>
                    <dd>This would be done while sharing this aspect with other users to use as part of their subsequent recommendations.
                        <ul>
                            <li>View other's characteristics on product</li>
                            <li>Agree/Disagree/Reuse of other's characteristics on product</li>                            
                        </ul>
                    </dd>                    
                    <dt>Personalisation</dt>
                    <dd>These are pertinent to the individual user that used the product and thus enables him/her to comment on that aspect. This is becauses it was a useful consideration for him/her.
                        <ul>
                            <li>Users will be able to add custom aspects to products</li>
                            <li>Users's profile provides context to habits/interests</li>
                        </ul>
                    </dd>
                    <dt>Existing peer insights can be reused.</dt>
                    <dd>Other users who also wish to recommend that same product might also choose this aspect to review.
                        <ul>
                            <li>View other characteristics on product</li>
                        </ul>
                    </dd>
                    <dt>Build up information</dt>
                    <dd>They may agree on existing reviews on this aspect and as such builds a truer, more accurate representation of the quality of the product
                        <ul>
                            <li>View other's characteristics on product</li>
                            <li>Reuse other's characteristics on product</li>
                            <li>See what aspect of product others are interested in</li>
                        </ul>
                    </dd>
                    <dt>Aggregation</dt>
                    <dd>Most importantly, this is based on what has become the most useful characteristics/aspects for users when considering the product.
                        <ul>                            
                            <li>Product characteristic value system establishes usefulness</li>                        
                        </ul>
                    </dd>
                    <dt>Evolving models</dt>
                    <dd>This builds an evolving interpretation of the product in question
                        <ul>
                            <li>Ability for constant additions/changes to product characteristics</li>
                            <li>Product and product characteristic value fluctuations</li>
                            <li>Opinion based on current up to date information</li>
                            <li>Value systems adapt to change</li>                                
                        </ul>
                    </dd>
                    <dt>Fast track product evaluation</dt>
                    <dd>The goal is ultimately providing a useful information on a product, for a user at product evaluation phase
                        <ul>
                            <li>Quick lookup of most important considerations for user via characteristics</li>
                            <li>Most apparent/general/loudest characteristics in foreground.</li>
                            <li>Access to variety of information about product.</li>
                            <li>Access to existing recommendations on product via reviews</li>
                        </ul>
                    </dd>
                </dl>
           
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

  

</body>

</html>