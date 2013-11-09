<%-- 
    Document   : SocialAuthPage
    Created on : Jun 11, 2013, 7:25:00 PM
    Author     : Stuart Mathews <stuart@stuartmathews.com>
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
  <head>
    <title>SocialAuth Demo</title>
    <script>
      function validate(obj){
        var val = obj.id.value;
        if(trimString(val).length <= 0){
          alert("Please enter OpenID URL");
          return false;
        }else{
          return true;
        }
      }
      function trimString(tempStr)
      {
         return tempStr.replace(/^\s*|\s*$/g,"");
      }
    </script>
  </head>
  <body>
     <c:url value="/themes" var="themeURLBase"/>
    <c:url value="/" var="socialAuthURL"/>
    <table cellpadding="10" cellspacing="10" align="center">
      
      <tr><td colspan="8"><p align="center">Please click on any icon to authenticate.</p></td></tr>
      <tr>
        <td>
          <a href="${socialAuthURL}socialauth?id=facebook">
            <img src="${themeURLBase}/images/facebook_icon.png" alt="Facebook" title="Facebook" border="0"/>
          </a>
        </td>
        <td>
          <a href="${socialAuthURL}socialauth?id=twitter">
            <img src="${themeURLBase}/images/twitter_icon.png" alt="Twitter" title="Twitter" border="0"/>
          </a>
        </td>
        <td>
          <a href="${socialAuthURL}socialauth?id=google">
            <img src="${themeURLBase}/images/gmail-icon.jpg" alt="Gmail" title="Gmail" border="0"/>
          </a>
        </td>
        <td>
          <a href="${socialAuthURL}socialauth?id=yahoo">
            <img src="${themeURLBase}/images/yahoomail_icon.jpg" alt="YahooMail" title="YahooMail" border="0"/>
          </a>
        </td>
        <td>
          <a href="${socialAuthURL}socialauth?id=hotmail">
            <img src="${themeURLBase}/images/hotmail.jpeg" alt="HotMail" title="HotMail" border="0"/>
          </a>
        </td>
        <td>
          <a href="${socialAuthURL}socialauth?id=linkedin">
            <img src="${themeURLBase}/images/linkedin.gif" alt="Linked In" title="Linked In" border="0"/>
          </a>
        </td>
        <td>
          <a href="${socialAuthURL}socialauth?id=foursquare">
            <img src="${themeURLBase}/images/foursquare.jpeg" alt="FourSquare" title="FourSquare" border="0"/>
          </a>
        </td>
        <td>
          <a href="${socialAuthURL}socialauth?id=myspace">
            <img src="${themeURLBase}/images/myspace.jpeg" alt="MySpace" title="MySpace" border="0"/>
          </a>
        </td>
      </tr>
      <tr>
        <td colspan="8" align="center">
          <form action="${socialAuthURL}socialauth" onsubmit="return validate(this);">
            or enter OpenID url: <input type="text" value="" name="id"/>
            <input type="submit" value="Submit"/> 
          </form>
        </td>
      </tr>
    </table>
  </body>
</html>
