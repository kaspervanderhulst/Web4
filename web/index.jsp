<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors }">
                    <li>${error }</li>
                </c:forEach>
            </ul>
        </div>
    </c:if> <c:choose>
    <c:when test="${user!=null}">
        <p>Welcome ${user.getFirstName()}!</p>
        <form method="post" action="Controller?action=LogOut">
            <p>
                <input type="submit" id="logoutbutton" value="Log Out">
            </p>
        </form>
    </c:when>
    <c:otherwise>
        <form method="post" action="Controller?action=LogIn">
            <p>
                <label for="email">Your email </label>
                <input type="text" id="email" name="email" value="jan@ucll.be">
            </p>
            <p>
                <label for="password">Your password</label>
                <input type="password" id="password" name="password" value="t">
            </p>
            <p>
                <input type="submit" id="loginbutton" value="Log in">
            </p>
        </form>
    </c:otherwise>
</c:choose>

    <section id="topics">

        <form id="1">
            <h4>What do you think about this assignment?</h4>
            <div id="comments1"></div>
            <label for="name1">Name:</label><input value="${user.getFirstName()}" type="text" id="name1" required>
            <label for="comment1">Comment:</label><input id="comment1" type="text" required>
            <label for="number1">Rating</label><input id="number1" type="number" min="0" max="10" required>
            <button type="submit">Add Comment</button>
        </form>

        <form id="2">
            <h4>How is everything going during the lockdown?</h4>
            <div id="comments2"></div>
            <label for="name2">Name:</label><input value="${user.getFirstName()}" type="text" id="name2" required>
            <label for="comment2">Comment:</label><input id="comment2" type="text" required>
            <label for="number2">Rating</label><input id="number2" type="number" min="0" max="10" required>
            <button type="submit">Add Comment</button>
        </form>

        <form id="3">
            <h4>Have you finished all your assignments?</h4>
            <div id="comments3"></div>
            <label for="name3">Name:</label><input  value="${user.getFirstName()}" type="text" id="name3" required>
            <label for="comment3">Comment:</label><input id="comment3" type="text" required>
            <label for="number3">Rating</label><input id="number3" type="number" min="0" max="10" required>
            <button type="submit">Add Comment</button>
        </form>

        <form id="4">
            <h4>What is your favourite series?</h4>
            <div id="comments4"></div>
            <label for="name4">Name:</label><input value="${user.getFirstName()}" type="text" id="name4" required>
            <label for="comment4">Comment:</label><input id="comment4" type="text" required>
            <label for="number4">Rating</label><input id="number4" type="number" min="0" max="10" required>
            <button type="submit">Add Comment</button>
        </form>

        <form id="5">
            <h4>Do you have any ideas for topics?</h4>
            <div id="comments5"></div>
            <label for="name5">Name:</label><input value="${user.getFirstName()}" type="text" id="name5" required>
            <label for="comment5">Comment:</label><input id="comment5" type="text" required>
            <label for="number5">Rating</label><input id="number5" type="number" min="0" max="10" required>
            <button type="submit">Add Comment</button>
        </form>
    </section>
</main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script src="js/comments.js"></script>
</body>
</html>