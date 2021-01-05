package com.code;

import object.SessionInfor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Room;
import object.Session;
import object.Speaker;
import object.TimeSlot;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONArray;

public class faccade_main {

    // Method to write a new speaker from Web App to SQL DB
    private DBContext database = new DBContext();
    private JSONArray json;
    private ResultSet result;

    //Method check if phone email, from speaker already exist in database
    //exID(edit)check another phone & email from another id not current
    public boolean checkDup_speaker(String key, String value, String exceptID) {
        String idDatabase = "";
        boolean exID;
        if (exceptID.equals("none")) {
            exID = false;
        } else {
            exID = true;
        }
        if (key == "email") {
            idDatabase = "email";
        } else if (key == "phone") {
            idDatabase = "phone_number";
            value = trim_phone(value);
        }
        String query = "SELECT * FROM speaker where " + idDatabase + " ='" + value + "'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            result = database.getConnectionStatement(query);
            while (result.next()) {
                if (!exID) {
                    if (result.getString(idDatabase).equals(value)) {
                        database.closeConnection();
                        return false;
                    }
                } else {
                    if (!(result.getString("speaker_id").equals(exceptID))) {
                        database.closeConnection();
                        return false;
                    }
                }
            }
            database.closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean write_speaker(String name, String phone, String email, String doc) {
        // check for duplicates
        phone = trim_phone(phone);
        String id = "";
        String query = "Select * from speaker";

        try {
            // This will check if database is null
            result = database.getConnectionStatement(query);
            if (!result.next()) {
                id = "1";
            } else {
                id = "(SELECT MAX( speaker_id )+1 FROM speaker cust)";
            }
            database.closeConnection();
            String reQuery = "INSERT INTO speaker (speaker_id,name,day_of_contact,phone_number,email) VALUES(" + id + ",'" + name + "','" + doc + "','" + phone + "','" + email + "')";
            int i = database.preStatement(reQuery);
            if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Method to edit an existing Speaker in SQL DB
    public boolean edit_speaker(String name, String phone, String email, String doc, String id) {
        phone = trim_phone(phone);
        // check for duplicates
        String query = "UPDATE speaker set name='" + name + "' , day_of_contact ='" + doc + "' , phone_number ='" + phone + "' , email ='" + email + "' where speaker_id ='" + id + "'";
        String reQuery = "SELECT * FROM speaker where speaker_id='" + id + "'";
        //ResultSet result2;
        boolean check = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            int i = database.preStatement(query);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public JSONArray showInforspeaker(String id) {
        String query = "SELECT * FROM speaker where speaker_id ='" + id + "'";
        ArrayList<Speaker> speaker = new ArrayList<>();
        json = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            result = database.getConnectionStatement(query);
            Speaker spk = new Speaker();
            if (result.next()) {
                spk.name = cap_FirstLetter(result.getString("name"));//result.getString("name");
                spk.doc = result.getString("day_of_contact");
                spk.phone = format_phone(result.getString("phone_number"));
                spk.email = result.getString("email");
                spk.spkID = result.getString("speaker_id");
            }
            speaker.add(spk);
            database.closeConnection();
        } catch (Exception ex) {
        }
        json = new JSONArray(speaker);

        return json;
    }

    // Method to remove an speaker from the database
    public boolean remove_speaker(String speakID) {
        String query = "DELETE FROM speaker where speaker_id='" + speakID + "'";
        try {
            int i = database.preStatement(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public int getId_speaker(String name, String phone, String email, String doc) {
        phone = trim_phone(phone);
        int id = 0;
        try {
            String query = "SELECT * FROM speaker where phone_number ='" + phone + "' and email ='" + email + "' and name ='" + name + "' and day_of_contact ='" + doc + "'";
            result = database.getConnectionStatement(query);
            if (result.next()) {
                id = result.getInt("speaker_id");
                //return id;
            }
            database.closeConnection();
            //return 0;
        } catch (Exception ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    // Method to find a speaker w/o using the speaker_id
    public boolean find_speaker(String name, String email) {
        String query = "SELECT * FROM speaker where name='" + name + "' and email ='" + email + "'";
        try {
            result = database.getConnectionStatement(query);
            if (result.next()) {
                database.closeConnection();
                return true;
            }
            database.closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public JSONArray getSession() {
        ResultSet result2;
        String query = "SELECT * FROM session";
        ArrayList<SessionInfor> sessionInforArr = new ArrayList<>();
        json = null;
        String sa = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            result = database.getConnectionStatement(query);

            while (result.next()) {
                SessionInfor sessionInfor = new SessionInfor();
                sessionInfor.Session = cap_FirstLetter(result.getString("session_name"));//result.getString("session_name");
                sessionInfor.SessionID = result.getInt("session_id");
                sessionInfor.SpeakID = result.getInt("speaker_id");
                sessionInfor.RoomID = result.getInt("room_id");
                sessionInfor.TimeID = result.getInt("time_slot_id");

                //System.out.print(result.getString("session_name") + " | ");
                String querySPK = "SELECT * FROM speaker where speaker_id='" + result.getInt("speaker_id") + "'";
                result2 = database.getConnectionStatement(querySPK);
                while (result2.next()) {
                    sessionInfor.Name = cap_FirstLetter(result2.getString("name"));
                    sessionInfor.Email = result2.getString("email");
                    // System.out.print("Name: " + result2.getString("name") + "| Email: " + result2.getString("email"));
                }
                database.closeConnection();

                String queryROOM = "SELECT * FROM room where room_id='" + result.getInt("room_id") + "'";
                result2 = database.getConnectionStatement(queryROOM);
                while (result2.next()) {
                    sessionInfor.RoomName = result2.getString("room_number");
                    sessionInfor.Seat = result2.getInt("seats");
                    sessionInfor.RoomID = result2.getInt("room_id");
                    //System.out.print("| Room Num: " + result2.getString("room_number") + "| Seat: " + result2.getInt("seats"));
                }
                database.closeConnection();

                String queryTIME = "SELECT * FROM time_slot where time_slot_id='" + result.getInt("time_slot_id") + "'";
                result2 = database.getConnectionStatement(queryTIME);
                while (result2.next()) {
                    //getDuration
                    String Ts = result2.getTime("time_start").toString();
                    String Te = result2.getTime("time_end").toString();
                    Ts = Ts.substring(0, Math.min(Ts.length(), 5));
                    Te = Te.substring(0, Math.min(Te.length(), 5));

                    sessionInfor.Time = Ts + " - " + Te + getDuration(Ts, Te);
                    //System.out.print("| Time: " + result2.getTime("time_start") + " - " + result2.getTime("time_end"));
                }
                database.closeConnection();

                //System.out.println();
                sessionInforArr.add(sessionInfor);
            }
            json = new JSONArray(sessionInforArr);
            database.closeConnection();
        } catch (Exception ex) {

        }

        return json;
    }

    // Method to get all speakers info to front end
    public String[][] get_all_speakers() {
        // Decision on return type between 1D or 2D array of String
        return new String[0][0];
    }

    public JSONArray showInforRoom(String id) {
        String query = "SELECT * FROM room where room_id ='" + id + "'";
        ArrayList<Room> room = new ArrayList<>();
        json = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            result = database.getConnectionStatement(query);
            Room rm = new Room();
            if (result.next()) {
                rm.roomID = result.getString("room_id");
                rm.roomNum = result.getString("room_number");
                rm.roomSeat = result.getString("seats");
            }
            room.add(rm);
            database.closeConnection();
        } catch (Exception ex) {
        }
        json = new JSONArray(room);

        return json;
    }

    // Method to write a new room from Web App to SQL DB
    public boolean write_room(String room_no, String seats) {
        // check for duplicates
        String id = "";
        String query = "Select * from room";

        try {
            // This will check if database is null
            result = database.getConnectionStatement(query);
            if (!result.next()) {
                id = "1";
            } else {
                id = "(SELECT MAX( room_id )+1 FROM room cust)";
            }
            database.closeConnection();
            String reQuery = "INSERT INTO room (room_id,room_number,seats) VALUES(" + id + ",'" + room_no + "','" + seats + "')";

            int i = database.preStatement(reQuery);
            if (i > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Method to edit an existing Room in SQL DB
    public boolean edit_room(String roomNumber, String seats, String id) {
        // check for duplicates
        String query = "UPDATE room set room_number='" + roomNumber + "' , seats ='" + seats + "' where room_id ='" + id + "'";
        String reQuery = "SELECT * FROM room where room_id='" + id + "'";

        boolean check = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            int i = database.preStatement(query);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public int getId_room(String room_no, String seats) {
        int id = 0;
        try {
            String query = "SELECT * FROM room where room_number ='" + room_no + "' and seats ='" + seats + "'";
            result = database.getConnectionStatement(query);
            while (result.next()) {
                id = result.getInt("room_id");
            }
            database.closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    // Method to remove a room from the database
    public boolean remove_room(String roomID) {
        String query = "DELETE FROM room where room_id='" + roomID + "'";
        try {
            int i = database.preStatement(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    // Method to find a room w/o using the room_id
    public boolean find_room(String room_no, String seats) {
        return false;
    }

    // Method to get all rooms info to front end
    public String[][] get_all_rooms() {
        // Decision on return type between 1D or 2D array of String
        return new String[0][0];
    }

    // Method to write a new time slot from Web App to SQL DB
    public boolean write_time_slot(String start, String end) {
        // check for duplicates
        String id = "";
        String query = "Select * from time_slot";

        try {
            // This will check if database is null
            result = database.getConnectionStatement(query);
            if (!result.next()) {
                id = "1";
            } else {
                id = "(SELECT MAX( time_slot_id )+1 FROM time_slot cust)";
            }
            database.closeConnection();

            String reQuery = "INSERT INTO time_slot (time_slot_id,time_start,time_end) VALUES(" + id + ",'" + start + "','" + end + "')";

            int i = database.preStatement(reQuery);
            if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Method to edit an existing time slot in SQL DB
    public boolean edit_time_slot(String time_start, String time_end, String id) {
        // check for duplicates
        String query = "UPDATE time_slot set time_start='" + time_start + "' , time_end ='" + time_end + "' where time_slot_id ='" + id + "'";
        String reQuery = "SELECT * FROM time_slot where time_slot_id='" + id + "'";

        boolean check = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            int i = database.preStatement(query);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public int getId_time_slot(String start, String end) {
        int id = 0;
        try {
            String query = "SELECT * FROM time_slot where time_start ='" + start + ":00" + "' and time_end ='" + end + ":00" + "'";
            result = database.getConnectionStatement(query);
            while (result.next()) {
                id = result.getInt("time_slot_id");
            }
            database.closeConnection();
            //return 0;
        } catch (Exception ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    // Method to remove a time slot from the database
    public boolean remove_time_slot(String timeId) {
        String query = "DELETE FROM time_slot where time_slot_id='" + timeId + "'";
        try {
            int i = database.preStatement(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    // Method to find a time slot w/o using the time_slot_id
    public boolean find_time_slot(LocalTime start, LocalTime end) {
        return false;
    }

    // Method to get all time slots info to front end
    public String[][] get_all_time_slots() {
        // Decision on return type between 1D or 2D array of String
        return new String[0][0];
    }

    public JSONArray showInforTimeSlot(String id) {
        String query = "SELECT * FROM time_slot where time_slot_id ='" + id + "'";
        ArrayList<TimeSlot> timeSlot = new ArrayList<>();
        json = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            result = database.getConnectionStatement(query);
            TimeSlot timeS = new TimeSlot();
            if (result.next()) {
                timeS.timeStart = result.getString("time_start");
                timeS.timeEnd = result.getString("time_end");
                timeS.timeID = result.getString("time_slot_id");
            }
            timeSlot.add(timeS);
            database.closeConnection();
        } catch (Exception ex) {
        }
        json = new JSONArray(timeSlot);
        return json;
    }

    public JSONArray showInforSession(String id) {
        String query = "SELECT * FROM session where session_id ='" + id + "'";
        ArrayList<Session> session = new ArrayList<>();
        json = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            result = database.getConnectionStatement(query);
            Session ss = new Session();
            if (result.next()) {
                ss.sessID = result.getString("session_id");
                ss.sessName = cap_FirstLetter(result.getString("session_name"));//result.getString("session_name");
            }
            session.add(ss);
            database.closeConnection();
        } catch (Exception ex) {
        }
        json = new JSONArray(session);
        return json;
    }

    // Method to write a new session from Web App to SQL DB
    public boolean write_session(String session_name, String spkID, String roomID, String timeID) {
        // check for duplicates
        String id = "";
        String query = "Select * from session";

        try {
            // This will check if database is null
            result = database.getConnectionStatement(query);
            if (!result.next()) {
                id = "1";
            } else {
                id = "(SELECT MAX( session_id )+1 FROM session cust)";
            }
            database.closeConnection();
            String reQuery = "INSERT INTO session (session_id,session_name,time_slot_id,room_id,speaker_id) VALUES(" + id + ",'" + session_name + "','" + timeID + "','" + roomID + "','" + spkID + "')";

            int i = database.preStatement(reQuery);
            if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(faccade_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Method to edit an existing Session in SQL DB
    public boolean edit_session(String session_name, String id) {
        // check for duplicates
        String query = "UPDATE session set session_name='" + session_name + "' where session_id ='" + id + "'";
        String reQuery = "SELECT * FROM session where session_id='" + id + "'";

        boolean check = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            int i = database.preStatement(query);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    // Method to remove a session from the database
    public boolean remove_session(String sessionID) {
        String query = "DELETE FROM session where session_id='" + sessionID + "'";
        try {
            int i = database.preStatement(query);
            if (i > 0) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    // Method to find a session w/o using the session_id
    public boolean find_session(String session_name) {
        return false;
    }

    // Method to get all sessions info to front end
    public String[][] get_all_sessions() {
        // Decision on return type between 1D or 2D array of String
        return new String[0][0];
    }

    public boolean delete_doc_info() {
        return true;
    }

    // check for malicious code?
    public boolean check_valid_entry(String s) {
        return false;
    }

    // check to make sure its a valid integer number
    public boolean check_num(String s) {
        return false;
    }

    //check name
    public boolean check_name(String name) {
        if (name.length() > 32 || name.length() < 2) {
            return false;
        }
        boolean check = name.matches("^[a-zA-Z]{1,}(?: [a-zA-Z]+){0,2}$");
        return check;
    }

    //check phone number
    public boolean check_phone(String phone) {
        phone = trim_phone(phone);
        if (phone.length() > 10) {
            return false;
        }
        if (phone.matches("^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$")) {
            return true;
        } else {
            return false;
        }
    }

    //format phone number (333)-333-3333
    public String format_phone(String phone) {
        phone = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");
        return phone;
    }

    //form phone with no symbol 123456789
    public String trim_phone(String phone) {
        phone = phone.trim()
                .replace(" ", "")
                .replace("-", "")
                .replace("(", "")
                .replace(")", "");
        return phone;
    }

    // Maybe delete - check with Bruce, Raymond
    public boolean check_email(String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        return valid;
    }

    // Check Time input
    // If it occurs some error, please edit time in database, that the second set to 00
    // Example 12:12:00 or 09:10:00
    public String check_time(String _timeStart, String _timeEnd) {
        final String errorMaxTimeSession = "Sorry, we only allow a session with maximum 2 hours. You exceed ";
        final String errorHourEnd = "Invalid TimeEnd. Please check Hours!";
        final String errorMinEnd = "Invalid TimeEnd. Please check Minute!";
        String error = "0";
        String[] timeStart = _timeStart.split(":");
        String[] timeEnd = _timeEnd.split(":");

        //Convert Time String to Integer 
        //Time End - 
        int he = Integer.parseInt(timeEnd[0]); // Hours End
        int me = Integer.parseInt(timeEnd[1]); // Minutes End
        //Time Start
        int hs = Integer.parseInt(timeStart[0]); // Hours Start
        int ms = Integer.parseInt(timeStart[1]); // Minutes Start

        while (true) {
            if (he < hs) {
                //Invalid TimeEnd, check Hours, If Hours End less than Hours Start then Break loop
                error = errorHourEnd;
                break;
            } else if (he == hs) {
                if (me <= ms) {
                    //Invalid TimeEnd, check Minute, If Hours End equal Hour Start, but Minutes End less than Minutes Start then Break loop
                    error = errorMinEnd;
                    break;
                } else {
                    //If no error, set error to 0
                    me = me - ms;
                    he = he - hs;
                    error = "0";
                }
            } else if (he > hs) {
                //If no error, set error to 0
                if (me < ms) {
                    me = (me + 60) - ms;
                    he = (he - 1) - hs;
                    error = "0";
                } else {
                    //If no error, set error to 0
                    me = me - ms;
                    he = he - hs;
                    error = "0";
                }
            }
            //If Total time of Session more than 2 hours
            if (he >= 2) {
                //Check session time, if hours is 2 hours (good) but minute is more than 1, then Break Looop
                //If it exceed in few minute, we will show speaker that they exceed ? minute
                //If it exceed more than hours, then show hours
                if (me >= 1 && he == 2) {
                    error = errorMaxTimeSession + me + " minutes";
                    break;
                } else if (he > 2) {
                    //If session more than 2 hours, then Break Loop
                    error = errorMaxTimeSession + (he - 2) + " hours";
                    break;
                    //out.print("\nSorry, we only allow a session with maximum 2 hours. You exceed " + (he - 2) + " hours");
                }
            }
            break;
        }

        return error;
    }

    public String getDuration(String _timeStart, String _timeEnd) {
        String[] timeStart = _timeStart.split(":");
        String[] timeEnd = _timeEnd.split(":");
        String _tHour = "";
        String _tMin = "";
        //Convert Time String to Integer 
        //Time End - 
        int he = Integer.parseInt(timeEnd[0]); // Hours End
        int me = Integer.parseInt(timeEnd[1]); // Minutes End
        //Time Start
        int hs = Integer.parseInt(timeStart[0]); // Hours Start
        int ms = Integer.parseInt(timeStart[1]); // Minutes Start
        String duration = "";

        if (me < ms) {
            me = (me + 60) - ms;
            he = (he - 1) - hs;
        } else {
            me = me - ms;
            he = he - hs;
        }

        if (me != 0) {
            _tMin = String.valueOf(me) + (me == 1 ? " minute" : " minutes");
        }
        if (he != 0) {
            _tHour = String.valueOf(he) + (he == 1 ? " hour " : " hours ");
        }

        return " ( Duration: " + _tHour + _tMin + (me == 0 ? ")" : " )");
    }

    public String cap_FirstLetter(String _name) {
        String[] name = _name.split(" ");
        _name = "";
        for (int i = 0; i < name.length; i++) {
            name[i] = Character.toUpperCase(name[i].charAt(0)) + name[i].substring(1);
            _name += name[i] + (i == name.length - 1 ? "" : " ");
        }
        return _name;
    }
    
    public boolean is_Number(String number){
        try{
            int num = Integer.parseInt(number);
            if (num >= 1 && num <= 120){
                return true;
            }
        }catch(NumberFormatException ex){
            System.out.println(ex);
        }
        return false;
    }
}
