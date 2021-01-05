<!DOCTYPE html>
<html>

<head>
    <title>Time Slot</title>
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
    <div class="TimeSlice">
        <h1>Time Slot</h1>
        <h3>Choose a time for your meeting:</h3>
        <div style="color:red">${errorMessage}</div>
        <form action="processEditTime" method="post">
            <div class="StartTime">
                <label>Time Start:</label>
                <input type="time" id="timeStart" name="timeStart" value="<%=request.getAttribute("timeStartValue")%>"
                    min="09:00" max="18:00" required>
            </div>
            <div class="StartTime">
                <label>Time End:</label>
                <input type="time" id="timeEnd" name="timeEnd" value="<%=request.getAttribute("timeEndValue")%>"
                    min="09:00" max="18:00" required>
            </div>
            <input type="hidden" id="id" name="id" value="<%=request.getAttribute("idTime")%>">
            <button type="submitBtn">Done</button>
        </form>
    </div>
    <!--footer 
    <footer>
        <span>Black Team<br>Copyright &copy; 2019-2020 </span>
    </footer>-->
    <script>
        var array = ${ timeID }
        document.getElementById("timeStart").value = array[0].timeStart;
        document.getElementById("timeEnd").value = array[0].timeEnd;
        document.getElementById("id").value = array[0].timeID;

    </script>
</body>

</html>