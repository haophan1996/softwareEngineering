<%@ page import="com.code.*" %>
<%@ page import="org.json.JSONArray"%>
<% faccade_main fb = new faccade_main();%>


<!DOCTYPE html>

<html>

    <head>
        <title>Boston Code Camp</title>
        <link rel="stylesheet" type="text/css" href="css/homepage.css">

    </head>

    <body>
        <!--Header File-->
        <div class="header">
            <a href="index.jsp">
                <h1>Boston Code Camp</h1>
            </a>
            <nav>
                <ul>
                    <!--My favorite html.-->
                    <!--Back to HomePage.html-->
                    <li><a href="index.jsp">Session List</a></li>
                    <!--Link to Session HTML-->
                    <li><a href="Session.jsp">Adding New</a></li>
                </ul>
            </nav>
            <h3>Black Team</h3>
        </div>

        <!--Information project, course, project-->
        <div class="panelheader">
            <h1>Boston Code Camp Project</h1>
            <h2>Course: Software Engineering (COMP - 4960 - 02)
                <br>
                Professor: Ron "Doc" Krawitz
            </h2>
        </div>
        <div class="panelbody">
            <div class="wrapper">
                <!--<div class="row">    
                    Will do for loop here to read from database.
                    <label>Session:</label> 
                    <input type = "text" id = "SessionName1" size = "100" value = "Session 1" readonly>  <br> 
    
                    <label>Speaker:</label> 
                    <input type = "text" id = "SpeakerName1" size = "100" value = "A" readonly>
                    <label>Email:</label>
                    <input type = "text" id = "SpakerEmail1" value = "ASDASD@Gmail.com "  readonly>  <br>
    
                    <label>RoomName:</label> 
                    <input type = "text" id = "RoomName" size = "100" value = "Clock" readonly>
                    <label>Seat:</label>
    
                    <input type = "text" id = "Seat" value = "70"  readonly>    <br>
    
                    <label>TimeSlice:</label> 
                    <input type = "text" id = "TimeSlice1" size = "100" value =  "11:30 am - 2:00 pm" readonly>  <br>
    
                    <label>Topic:</label> 
                    <input type = "text" id = "Topic"  size = "100" value = " What is A Machine Learning" readonly>  <br>
    
                   
                    <button id = "Delete"> Delete</button>
                    <div class="dropdown">
                        <div class="dropbtn">Edit
                          <i class="fa fa-caret-down"></i>
                        </div>
                        <div class="dropdown-content">
                            <a href="Session.jsp" >Session</a>
                            <a href="Speaker.jsp">Speaker</a>
                            <a href="TimeSlot.jsp">TimeSlot</a>
                        </div>
                        
                    </div> 
                </div> -->

            </div>

        </div>
        <!--footer
        <footer>
            <span>Black Team<br>Copyright &copy; 2019-2020 </span>
        </footer>-->

        <script>

            // Read from db then add to array like this

            var array = <%=fb.getSession()%>;
            for (var i = 0; i < array.length; i++) {
            var myDiv = document.createElement("div");
            //Set its unique ID.
            myDiv.className = 'wrapper';
            //Add your content to the DIV
            myDiv.innerHTML = `
                    <div class="row" >
                    <form action="editInformation" method="post">
                       
                    <label>Session:</label> 
                    <input type = "text" id = "SessionName" size = "100" value = "Session 3" readonly>  <br> 

                    <label>Speaker:</label> 
                    <input type = "text" id = "SpeakerName" size = "100" value = "C" readonly>

                    <label>Gmail:</label>
                    <input type = "text" id = "SpeakerEmail" value = "CSDCSD@Gmail.com "  readonly>  <br>

                    <label>RoomName:</label> 
                    <input type = "text" id = "RoomName" size = "100" value = "Door" readonly>
                    <label>Seat:</label>
                    <input type = "text" id = "Seat" value = "70"  readonly>    <br>

                    <label>TimeSlice:</label> 
                    <input type = "text" id = "TimeSlice" size = "100" value = "1:00pm - 2:30 pm" readonly>  <br>

                    <label>Topic:</label> 
                    <input type = "text" id = "Topic"  size = "100" value = "Computer Science" readonly>  <br>

                 
                    <input type = "hidden" name= "sessionID" id = "sessionID" size = "100" value = "id" >  
                    <input type = "hidden" name= "roomID" id = "roomID" size = "100" value = "id" > 
                    <input type = "hidden" name= "speakID" id = "speakID" size = "100" value = "id" > 
                    <input type = "hidden" name= "timeID" id = "timeID" size = "100" value = "id" >    

                    <button type="submit" name="Delete" id="Delete" style='background-color: red;'value="Delete" onclick="return confirm('Are you sure you want to delete?')">Delete</button>
                    <div class="dropdown">
                        <div class="dropbtn" id = "Edit">Edit
                          <i class="fa fa-caret-down"></i>
                        </div>
                    
                        <div class="dropdown-content">
                        
                            <button type="submit" name="edit_Session" value="edit_Session">Session</button>
                            <button type="submit" name="edit_Speaker" value="edit_Speaker">Speaker</button>
                            <button type="submit" name="edit_TimeSlot" value="edit_TimeSlot">TimeSlot</button>
                            <button type="submit" name="edit_Room" value="edit_Room">Room</button>
                        </div>
                        </form>
                    
                    </div> 
                    
                `;
            //Finally, append the element to the HTML body
            document.body.appendChild(myDiv);
            document.getElementById("SessionName").id = "SessionName" + i.toString();
            document.getElementById("SessionName" + i.toString()).value = array[i].session;
            document.getElementById("SpeakerName").id = "SpeakerName" + i.toString();
            document.getElementById("SpeakerName" + i.toString()).value = array[i].name;
            document.getElementById("SpeakerEmail").id = "SpeakerEmail" + i.toString();
            document.getElementById("SpeakerEmail" + i.toString()).value = array[i].email;
            document.getElementById("RoomName").id = "RoomName" + i.toString();
            document.getElementById("RoomName" + i.toString()).value = array[i].roomName;
            document.getElementById("TimeSlice").id = "TimeSlice" + i.toString();
            document.getElementById("TimeSlice" + i.toString()).value = array[i].time;
            document.getElementById("Seat").id = "Seat" + i.toString();
            document.getElementById("Seat" + i.toString()).value = array[i].seat;
            document.getElementById("sessionID").id = "sessionID" + i.toString();
            document.getElementById("sessionID" + i.toString()).value = array[i].sessionID;
            document.getElementById("roomID").id = "roomID" + i.toString();
            document.getElementById("roomID" + i.toString()).value = array[i].roomID;
            document.getElementById("speakID").id = "speakID" + i.toString();
            document.getElementById("speakID" + i.toString()).value = array[i].speakID;
            document.getElementById("timeID").id = "timeID" + i.toString();
            document.getElementById("timeID" + i.toString()).value = array[i].timeID;
            document.getElementById("Edit").id = "Edit" + i.toString();
            document.getElementById("Delete").id = "Delete" + i.toString();

            }
        </script>
        
    </body>

</html>