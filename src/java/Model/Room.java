/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
public class Room {
    private String room_code;
    private Dom dom;
    private int sumbed;

    public Room() {
    }

    public Room(String room_code, Dom dom, int sumbed) {
        this.room_code = room_code;
        this.dom = dom;
        this.sumbed = sumbed;
    }

    public String getRoom_code() {
        return room_code;
    }

    public void setRoom_code(String room_code) {
        this.room_code = room_code;
    }

    public Dom getDom() {
        return dom;
    }

    public void setDom(Dom dom) {
        this.dom = dom;
    }

    public int getSumbed() {
        return sumbed;
    }

    public void setSumbed(int sumbed) {
        this.sumbed = sumbed;
    }

    
    
}
