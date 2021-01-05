/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import com.code.faccade_main;

/**
 *
 * @author HaoPhan
 */
public class Speaker {
    
    public String name;
    public String doc;
    public String phone;
    public String email;
    public String spkID;

    public void setName(String name) {
        this.name = name;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSpkID(String spkID) {
        this.spkID = spkID;
    }

    public String getName() {
        return name;
    }

    public String getDoc() {
        return doc;
    }

    public String getPhone() {
        faccade_main fb = new faccade_main();
        //return fb.format_phone(phone);
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getSpkID() {
        return spkID;
    }

}
