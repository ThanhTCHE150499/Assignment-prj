/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class BookBed {
    private Student student;
    private Room room;
    private Date booked_date;
    private Date date_checkout;
    private Bed bed;
    private Semester semester;
    private int status;

    public BookBed() {
    }

    public BookBed(Student student, Room room, Date booked_date, Date date_checkout, Bed bed,Semester semester, int status) {
       
        this.student = student;
        this.room = room;
        this.booked_date = booked_date;
        this.date_checkout = date_checkout;
        this.bed = bed;
        this.semester = semester;
        this.status = status;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getBooked_date() {
        return booked_date;
    }

    public void setBooked_date(Date booked_date) {
        this.booked_date = booked_date;
    }

    public Date getDate_checkout() {
        return date_checkout;
    }

    public void setDate_checkout(Date date_checkout) {
        this.date_checkout = date_checkout;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
