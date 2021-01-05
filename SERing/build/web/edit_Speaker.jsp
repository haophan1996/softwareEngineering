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
        <h1>Speaker</h1>
        <form action="processEditSpeaker" method="post">
            <!--from backend, will send arrayjson type to SpeakerID, then js front-end, will get these data, and set to input-->
            <!--input type="hidden" name="SpeakerID" id="SpeakerID" value=${SpeakerID}-->

            <div style="color:red">${errorMessage}</div>

            <div class="Speakerinput">
                <img src="icon/userID.png">
                <input type="text" id="Name" name="Name" value="<%=(request.getAttribute("NameValue") != null) ? request.getAttribute("NameValue") : ""%>" placeholder="Full Name">
            </div>
            <div class="Speakerinput">
                <img src="icon/userID.png">
                <input type="date" id="doc" name="doc" value="<%=(request.getAttribute("docValue") != null) ? request.getAttribute("docValue") : ""%>" placeholder="Day Of Contact">

            </div>
            <div class="Speakerinput">
                <img src="icon/cellphone.png">
                <input type="text" id="PhoneNumber" name="PhoneNumber" value="<%=(request.getAttribute("PhoneNumberValue") != null) ? request.getAttribute("PhoneNumberValue") : ""%>" value="Phone Number" placeholder="Phome Number">

            </div>
            <div class="Speakerinput">
                <img src="icon/emailICON.png">
                <input type="text" id="Email" name="Email" value="<%=(request.getAttribute("EmailValue") != null) ? request.getAttribute("EmailValue") : ""%>" value="Email" placeholder="Email">

            </div>
            <input type="hidden" id="id" name="id" value="<%=(request.getAttribute("idValue") != null) ? request.getAttribute("idValue") : ""%>">
            <button type="submit" value="login">Confirm</button>
        </form>
    </div>
    <script>

       // var data = document.getElementById("SpeakerID").value;
        var array = ${SpeakerID}
        document.getElementById("Name").value = array[0].name;
        document.getElementById("doc").value = array[0].doc;
        document.getElementById("PhoneNumber").value = array[0].phone;
        document.getElementById("Email").value = array[0].email;
        document.getElementById("id").value = array[0].spkID;


    </script>
</body>

</html>