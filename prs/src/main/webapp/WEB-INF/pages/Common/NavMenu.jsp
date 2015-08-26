<div class="well sidebar-nav" th:fragment="NavMenu">
    <ul class="nav nav-list">
        <li class="nav-header">Products</li>
            <li><a href="/Product/ShowProductList" th:href="@{/Product/ShowProductList}">Show all products</a></li>
        <li class="nav-header">Users</li>
            <li><a href="/User/ShowUserList" th:href="@{/User/ShowUserList}">Show all users</a></li>
        <li class="nav-header">Recommendations</li>
            <li><a href="/Recommendation/ShowRecommendations" th:href="@{/Recommendation/ShowRecommendations}">Show all recommendations</a></li>
        <li class="nav-header">Reviews</li>
            <li><a href="/Review/ShowAllReviews" th:href="@{/Review/ShowAllReviews}">Show all reviews</a></li>
        <li class="nav-header">Characteristics</li>
            <li><a href="/Characteristic/ShowCharacteristics" th:href="@{/Characteristic/ShowCharacteristics}">Show all characteristics</a></li>        
        <li class="nav-header">Meal Plan</li>
            <li><a href="/Mealplan/ShowProductList" th:href="@{/Product/ShowProductList}">Show all products</a></li>
    </ul>
</div>