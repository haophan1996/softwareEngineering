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
public class TimeSlot {

    public String timeEnd;
    public String timeStart;
    public String timeID;

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeID(String timeID) {
        this.timeID = timeID;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeID() {
        return timeID;
    }

}
