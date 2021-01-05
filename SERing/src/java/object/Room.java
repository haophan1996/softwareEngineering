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
public class Room {
    public String roomNum;
    public String roomID;
    public String roomSeat;

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setRoomSeat(String roomSeat) {
        this.roomSeat = roomSeat;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomSeat() {
        return roomSeat;
    }
    
}
