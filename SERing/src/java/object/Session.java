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
public class Session {

    public String sessID;
    public String sessName;

    public void setSessID(String sessID) {
        this.sessID = sessID;
    }

    public void setSessName(String sessName) {
        this.sessName = sessName;
    }

    public String getSessID() {
        return sessID;
    }

    public String getSessName() {
        return sessName;
    }

}
