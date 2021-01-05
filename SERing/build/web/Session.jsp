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
    <div class="SessionAdd">
        <h1>Session</h1>
        <div style="color:red">${errorMessage}</div>
        <form action="addingSession" method="post">
            <div class="SessionAddinput">
                <img src="icon/SessionID.jpg">
                <input type="text" id="SessionName" name="SessionName" placeholder="Session Name"
                    value="<%=(request.getAttribute("SessionNameValue") != null) ? request.getAttribute("SessionNameValue") : ""%>"
                    required=true>
            </div>

            <div class="SessionAddinput">
                <img src="icon/userID.png">
                <input type="text" id="roomNumber" name="roomNumber"
                    value="<%=(request.getAttribute("roomNumberValue") != null) ? request.getAttribute("roomNumberValue") : ""%>"
                    placeholder="Room Number" required=true>
            </div>

            <div class="SessionAddinput">
                <img src="icon/RoomID.png">
                <input type="text" id="roomSeat" name="roomSeat"
                    value="<%=(request.getAttribute("roomSeatValue") != null) ? request.getAttribute("roomSeatValue") : ""%>"
                    placeholder="Seats" required=true>
            </div>


            <!-- <h3>Choose a time for your meeting:</h3> -->
            <div class="StartTime">
                <label>Time Start:</label>
                <input type="time" id="timeStart" name="timeStart"
                    value="<%=(request.getAttribute("timeStartValue") != null) ? request.getAttribute("timeStartValue") : ""%>"
                    min="09:00" max="18:00" required>
            </div>
            <div class="StartTime">
                <label>Time End:</label>
                <input type="time" id="timeEnd" name="timeEnd"
                    value="<%=(request.getAttribute("timeEndValue") != null) ? request.getAttribute("timeEndValue") : ""%>"
                    min="09:00" max="18:00" required>
            </div>


            <button id="submitBtn1" name="submitBtn1">Next</button>
        </form>
    </div>

    <!--footer 
    <footer>
            <span>Black Team<br>Copyright &copy; 2019-2020 </span>
        </footer>
    -->
    <script>



        /*
        document.getElementById("submitBtn1").onclick = function () {

            var name = document.getElementById("FullName").value;
            var roomnumber = document.getElementById("RoomNumber").value;
            var session = document.getElementById("SessionName").value;
            var regName = /^[a-zA-Z]+ [a-zA-Z]+$/;

            if (regName.test(name) || name == "") {
                alert('Valid name given.');

            } else if (regName.test(roomnumber) || roomnumber == "") {
                alert('Valid Room Name given.');
            } else if (regName.test(session) || session == "") {
                alert('Valid Session given.');
            }
            else {
                var obj = { Name: name, Session: session, RoomNumber: roomnumber, Seats: 70 }
                localStorage.setItem("Adding", JSON.stringify(obj));
                location.href = "Speaker.jsp";
            }

            */


        };




    </script>
</body>

</html>