<%@ page import="com.code.*" %>
<%@ page import="org.json.JSONArray"%>
<% faccade_main fb = new faccade_main();%>

<!DOCTYPE html>
<html>

<head>
    <title>Speaker</title>
    <link rel="stylesheet" type="text/css" href="css/homepage.css">
</head>

<body>
    <!--Header File
    -->
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
    <div class="Speaker">
        <h1>Room</h1>
        <form action="processEditRoom" method="post">
            <!--from backend, will send arrayjson type to SpeakerID, then js front-end, will get these data, and set to input-->
            <!--input type="hidden" name="RoomID" id="RoomID" value=${RoomID}-->

            <div style="color:red">${errorMessage}</div>

            <div class="Speakerinput">
                <img src="icon/userID.png">
                <input type="text" id="roomNum" name="roomNum" value="<%=(request.getAttribute("RoomValue") != null) ? request.getAttribute("RoomValue") : ""%>" placeholder="Room Number" required=true>
            </div>

             
            <div class="Speakerinput">
                <img src="icon/cellphone.png">
                <input type="text" id="Seat" name="Seat" value="<%=(request.getAttribute("SeatValue") != null) ? request.getAttribute("SeatValue") : ""%>" placeholder="Room Seat" required=true>

            </div>
            
            <input type="hidden" id="id" name="id" value="<%=(request.getAttribute("id") != null) ? request.getAttribute("id") : ""%>">
            <button type="submit" value="login">Confirm</button>
        </form>
    </div>
    <script>

        //var data = document.getElementById("RoomID").value;
        var array = ${RoomID};
        document.getElementById("roomNum").value = array[0].roomNum;
        document.getElementById("Seat").value = array[0].roomSeat;
        document.getElementById("id").value = array[0].roomID;

    </script>
</body>

</html>