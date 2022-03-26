/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Detail {
    private int id;
    private Student student;
    private Room room;
    private Date date_booked;
    private BigDecimal price;
    private Bed bed;
    private Semester semester;
    private Date date_checkout;

    public Detail() {
    }

    public Detail(int id,Student student, Room room, Date date_booked, BigDecimal price, Bed bed, Semester semester, Date date_checkout) {
        this.id = id;
        this.student = student;
        this.room = room;
      
        this.date_booked = date_booked;
        this.price = price;
        this.bed = bed;
        this.semester = semester;
        this.date_checkout = date_checkout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public Date getDate_booked() {
        return date_booked;
    }

    public void setDate_booked(Date date_booked) {
        this.date_booked = date_booked;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Date getDate_checkout() {
        return date_checkout;
    }

    public void setDate_checkout(Date date_checkout) {
        this.date_checkout = date_checkout;
    }

    
    
}
