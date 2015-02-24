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
      <title>About</title>
</head>
<body>

    <div th:replace="Common/TopMenu :: TopMenu"></div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"></div>
        </div><!--/span-->
        
        <div class="span9">
          <div class="container">
            <h1>About</h1>
            <p>Recommendations, reputations and reviews is an independent set of technologies that aim to help the people find relevant information
                about the things they concerned about.
                Finally, people can define free-form characteristics about products, people and companies so as to really define what is important to people.
                Inevitably, other's might find these aspects also useful.</p>
            <ol>
                <li>Make it easier to find things that you want, according to your criteria</li>
                <li>Study the aspects and characteristic of products so you can make an informed buying decision</li>
                <li>Review other's opinions on the characteristics of products that matter most to you</li>
                <li>See how people react to the reputation of people, products and companies</li>
            </ol>
            <p>We hope you like using R3 and it helps you. You can contact us at:</p>
            <br/>
            <br/>
            <address>
                Recommendations, Reputations and reviews.
                23 Montague Road, First floor, rear flat
                P: (075) 95254673
            </address>                
            <address>
                Stuart Mathews
            </address>
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

  

</body>

</html>