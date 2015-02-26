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
    <title>Show All Products</title>
</head>
<body>
    <div th:replace="Common/TopMenu :: TopMenu"/>
    <div class="container-fluid">
      <div class="row-fluid">                  
        <div class="span3">
          <div th:replace="Common/NavMenu :: NavMenu"/>
        </div>        
        <div class="span9">                        
        <table class="table table-hover">
          <thead>
            <tr>
              <th>Image</th>
              <th>Product Title</th>
              <th>What is it?</th>
              <th>Who made it?</th>                
              <th>Action</th> 
            </tr>
          </thead>
          <tr th:each="product : ${products}">
            <td class="span1">
                <a href="/Product/Show/1" th:href="@{'/Product/Show/'+${product.identifier}}">
                  <img src="@{../../themes/images/product_image.gif}" th:src="@{/themes/images/product_image.gif}" class="img-polaroid"/>
                </a>
            </td>
            <td>
                <a href="/Product/Show/1" th:href="@{/Product/Show/${product.identifier}}" th:text="${product.title}"></a>
            </td>
            <td th:text="${product.whatIsIt}"></td>
            <td th:text="${product.whoMadeIt}"></td>                       

            <td>
                <ul class="inline">
                <li><a href="/Product/Delete/1" th:href="@{'/Product/Delete/'+${product.identifier}}">Delete</a> </li>   
                <li><a href="/Product/ShowEdit/1" th:href="@{'/Product/ShowEdit/'+${product.identifier}}">Edit</a> </li> 
                </ul>
            </td>				
          </tr>		
        </table>
	
        <a class="btn btn-primary" href="${AddProductsURL}">Add new product</a>
        </div><!--/span-->
      </div><!--/row-->
      
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