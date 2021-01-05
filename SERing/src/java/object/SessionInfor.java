/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author HaoPhan
 */
public class SessionInfor {

    public String Session;
    public String Name;
    public String Email;
    public String RoomName;
    public String Time;
    public int Seat;
    public int SpeakID;
    public int TimeID;
    public int RoomID;
    public int SessionID;

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public void setSeat(int Seat) {
        this.Seat = Seat;
    }

    public int getSeat() {
        return Seat;
    }

    public String getSession() {
        return Session;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getRoomName() {
        return RoomName;
    }

    public int getSpeakID() {
        return SpeakID;
    }

    public int getTimeID() {
        return TimeID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public int getSessionID() {
        return SessionID;
    }

    public void setSession(String Session) {
        this.Session = Session;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public void setSpeakID(int SpeakID) {
        this.SpeakID = SpeakID;
    }

    public void setTimeID(int TimeID) {
        this.TimeID = TimeID;
    }

    public void setRoomID(int RoomID) {
        this.RoomID = RoomID;
    }

    public void setSessionID(int SessionID) {
        this.SessionID = SessionID;
    }

}
