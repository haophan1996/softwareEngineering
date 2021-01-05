<!DOCTYPE html>
<html>
<head>
    <title>Time Slot</title>
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
    <div class="TimeSlice">
        <h1>Time Slot</h1>
        <h3>Choose a time for your meeting:</h3> 
        <div class="StartTime">   
            <label>Time Start:</label> 
            <input type="time" id="timeStart" name="timeStart"
            min="09:00" max="6:00" required>
        </div>
        <div class="StartTime"> 
            <label>Time End:</label> 
            <input type="time" id="timeEnd" name="timeEnd"
            min="09:00" max="6:00" required>
        </div>    
        <button id="submitBtn">Submit</button>
    </div>
        <!--footer -->
    <footer>
        <span>Black Team<br>Copyright &copy; 2019-2020 </span>
    </footer>
<script ></script>
</body>
</html>