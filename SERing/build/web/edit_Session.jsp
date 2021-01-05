<%@ page import="com.code.*" %>
<%@ page import="org.json.JSONArray"%>
<% faccade_main fb = new faccade_main();%>


<!DOCTYPE html>
<html>

<head>
    <title>Session</title>
    <link rel="stylesheet" type="text/css" href="css/homepage.css">
</head>

<body>
    <div class="header">
        <a href="index.jsp">
            <h1>Boston Code Camp </h1>
        </a>
        <nav>
            <ul>
                <!--My favorite html.-->
                <!--Back to HomePage.html-->
                <li><a href="index.jsp">Session List</a></li>
                <!--Link to Session HTML-->

            </ul>
        </nav>
        <h3>Black Team</h3>
    </div>
    <div class="Session">
        <h1>Session</h1>
        <form action="processEditSession" method="post">
            <!--from backend, will send arrayjson type to SpeakerID, then js front-end, will get these data, and set to input-->
            <!--input type="text" name="SessionID" id="SessionID" value=${SessionID}-->
            <div style="color:red">${errorMessage}</div>

            <div class="Sessioninput">
                <img src="icon/SessionID.jpg">
                <input type="text" id="SessionName" name="SessionName" value="Session Name" placeholder="Session Name" required>
            </div>
            <input type="hidden" id="id" name="id" value="none">
            <button type="submit" value="login">Confirm</button>
        </form>
    </div>
    <!-- 
    <footer>
        <span>Black Team<br>Copyright &copy; 2019-2020 </span>
    </footer>-->

    <script>
        var array = ${SessionID};
        document.getElementById("SessionName").value = array[0].sessName;
        document.getElementById("id").value = array[0].sessID;
    </script>
</body>

</html>