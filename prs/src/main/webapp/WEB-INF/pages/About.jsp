<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <c:import url="/Navigation/TopMenu"></c:import>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <c:import url="/Navigation/NavMenu"></c:import> 
        </div><!--/span-->
        
        <div class="span9">
          <div class="container">
            <h1>About</h1>
            <p>Recommendations, reputations and reviews is an independent set of technologies that aim to help the people find relevant information<br/>
                about the things they concerned about.<br/>
                This is done by using what people are willing to share about their experiences and expertise.<br/> All opinions, advice, and information is aggregated by all those who contribute.</br>
                Finally, people can define free-form characteristics about products, people and companies so as to really define what is important to people.</br>
                Inevitably, other's might find these aspects also useful.
                 
            <ol>
                <li>Make it easier to find things that you want, according to your criteria</li>
                <li>Study the aspects and characteristic of products so you can make an informed buying decision</li>
                <li>Review other's opinions on the characteristics of products that matter most to you</li>
                <li>See how people react to the reputation of people, products and companies</li>
            </ol>
          </p>            
            <p>We hope you like using R3 and it helps you. You can contact us at:</p>
            <br/>
            <br/>
            <address>
                <strong>Recommendations, Reputations and reviews.</strong><br/>
                23 Montague Road, First floor, rear flat</br>
                <abbr title="Phone">P:</abbr> (075) 95254673
            </address>                
            <address>
                <strong>Stuart Mathews</strong></br>
                <a href="mailto:stumathews@gmail.com">stumathews@gmail.com</a>
            </address>
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

  

</body>

</html>