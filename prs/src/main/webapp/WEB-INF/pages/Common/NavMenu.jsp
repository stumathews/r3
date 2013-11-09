<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="well sidebar-nav">
    <ul class="nav nav-list"> 
        <c:forEach items="${stacks}" var="stack">
        <li class="nav-header">${stack.key}</li>  
            <c:forEach items="${stack.value}" var="link">
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
        </c:forEach>
    </ul>
</div><!--/.well -->