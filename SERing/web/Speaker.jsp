<!DOCTYPE html>
<html>
    <head>
        <title>Speaker</title>
        <link rel="stylesheet" type="text/css" href ="css/homepage.css">
    </head>
    <body>
        <!--Header File
        -->
        <div class="header">
            <a href = "index.jsp"><h1>Boston Code Camp  </h1></a>
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
            <h1>Speaker</h1>
            <div style="color:red">${errorMessage}</div>
            <form action="addingSessionSPK" method="post">
                <div class = "Speakerinput">
                    <img src = "icon/userID.png">
                    <input type = "text" id = "Name" name="Name" value="<%=(request.getAttribute("NameValue") != null) ? request.getAttribute("NameValue") : ""%>" placeholder="Full Name" required=true>           
                </div>
                
                <div class="Speakerinput">
                    <img src="icon/cellphone.png">
                    <input type = "text" id = "PhoneNumber" name="PhoneNumber" value="<%=(request.getAttribute("PhoneNumberValue") != null) ? request.getAttribute("PhoneNumberValue") : ""%>" placeholder="Phone Number" required=true>           

                </div>
                <div class="Speakerinput">
                    <img src="icon/emailICON.png">
                    <input type = "text" id = "Email" name="Email" value="<%=(request.getAttribute("EmailValue") != null) ? request.getAttribute("EmailValue") : ""%>" placeholder="Email" required=true>           
                </div>
                <div class = "Speakerinput">
                    <label>Date Of Contact</label><br>
                    <img src = "icon/userID.png">
                    <input type = "date" id = "doc" name="doc" value="<%=(request.getAttribute("docValue") != null) ? request.getAttribute("docValue") : ""%>" placeholder="Day Of Contact" required=true>           

                </div>

                <input type="hidden" id="SessionName" name="SessionName" value="<%=request.getAttribute("SessionNameValue")%>">
                <input type="hidden" id="roomNumber" name="roomNumber" value="<%=request.getAttribute("roomNumberValue")%>">
                <input type="hidden" id="roomSeat" name="roomSeat" value="<%=request.getAttribute("roomSeatValue")%>">
                <input type="hidden" id="timeStart" name="timeStart" value="<%=request.getAttribute("timeStartValue")%>">
                <input type="hidden" id="timeStart" name="timeEnd" value="<%=request.getAttribute("timeEndValue")%>">

                <button id="Next">Done</button>
            </form>
        </div>
        <script >

            /*  document.getElementById("Next").onclick = function () {
             location.href = "Session.jsp";
             };*/
        </script>
    </body>
</html>