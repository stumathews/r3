<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="well sidebar-nav">
    <ul class="nav nav-list">
        <c:url value="/" var="url" />
        <li class="nav-header">Products</li>
            <li><a href="${url}Product/ShowProductList">Show all products</a></li>
        <li class="nav-header">Users</li>
            <li><a href="${url}User/ShowUserList">Show all users</a></li>
        <li class="nav-header">Recommendations</li>
            <li><a href="${url}Recommendation/ShowRecommendations">Show all recommendations</a></li>
        <li class="nav-header">Reviews</li>
            <li><a href="${url}Review/ShowAllReviews">Show all reviews</a></li>
        <li class="nav-header">Characteristics</li>
       <li><a href="${url}Characteristic/ShowCharacteristics">Show all characteristics</a></li>        
    </ul>
</div><!--/.well -->