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
        <a class="brand" href="#">R3</a>
        <div class="nav-collapse collapse">       
            <p class="navbar-text pull-right">
            <c:url value="/j_spring_security_logout" var="logoutURL"></c:url>             
            Hi user
            <a href="${logoutURL}" class="navbar-link"> logout</a>
            </p>       
            
            <ul class="nav">
                    <c:url value="/" var="url" />  
                    <li><a href="${url}">Home</a></li>
                    <li><a href="${url}About">About</a></li>
                    <li><a href="${url}Welcome">Welcome</a></li>
            </ul>
      
        </div><!--/.nav-collapse -->
    </div>
    </div>
</div>