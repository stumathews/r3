<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
    <div class="container-fluid">
        <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        </button>
        <a class="brand" href="#">${productName}</a>
        <div class="nav-collapse collapse">
        <c:if test="${isAuthenticated}">
            <p class="navbar-text pull-right">
            <c:url value="/j_spring_security_logout" var="logoutURL"></c:url>            
            <c:url value="/User/ShowDetail/${logged_in_user}" var="show_user_details"></c:url>
            Hi <a href="${show_user_details}" class="navbar-link">${logged_in_user}</a>
            <a href="${logoutURL}" class="navbar-link"> logout</a>
            </p>
        </c:if>
        <ul class="nav">
            <!-- See if the current url is one of the links passed in. Highlight it.
            Also Turn each passed in relative URL to an absolute url -->
            <c:forEach items="${links}" var="link">
                <c:url value="/" var="url" />  
                <c:set var="each_url" value="${url}${link.value}"></c:set> 
                <c:choose>                                       
                    <c:when test="${each_url eq current_url}">
                        <li class="active"><a href="${each_url}">${link.key}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${each_url}">${link.key}</a></li>
                    </c:otherwise>
                </c:choose>            
               
            </c:forEach>   
            
            
        </ul>
      
        </div><!--/.nav-collapse -->
    </div>
    </div>
</div>