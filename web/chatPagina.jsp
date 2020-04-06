<%--
  Created by IntelliJ IDEA.
  User: kasper
  Date: 05/03/2020
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script src="js/status.js"></script>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<main>
    <section>
        <h4>Status: </h4>
        <div id="status"><p id="pStatus">Online</p></div>
    </section>
    <section>
        <h4>Change status</h4>
        <p><input type="text" id="input"><input type="button" value="changeStatus" onclick="changeStatus();"></p>
    </section>

    <section>
        <table id="friends">
            <tr>
                <td>Nr.</td>
                <td>Name</td>
                <td>Status</td>
            </tr>
        </table>
    </section>


    <section>
        <h4>Add friend</h4>
        <p><input type="text" id="nameInput"><input type="button" value="Add friend" onclick="addFriend();"></p>
    </section>


</main>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
</body>
</html>
