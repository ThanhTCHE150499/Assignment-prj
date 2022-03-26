/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Student {
    private String id;
    private String name;
    private boolean gender;
    private Date dob;
    private String address;
    private BigDecimal money;
    private String username;
    private Room room_code;
    ArrayList<Detail> student_details = new ArrayList<>();

    public ArrayList<Detail> getStudent_details() {
        return student_details;
    }

    public void setStudent_details(ArrayList<Detail> student_details) {
        this.student_details = student_details;
    }
    public Student() {
    }

    public Student(String id, String name, boolean gender, Date dob, String address, BigDecimal money, String username, Room room_code) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.money = money;
        this.username = username;
        this.room_code = room_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Room getRoom_code() {
        return room_code;
    }

    public void setRoom_code(Room room_code) {
        this.room_code = room_code;
    }
    
}
