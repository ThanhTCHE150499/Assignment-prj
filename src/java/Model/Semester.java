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
public class Semester {
    private int id;
    private int numbersemester;
    private int year;

    public Semester() {
    }

    public Semester(int id, int numbersemester, int year) {
        this.id = id;
        this.numbersemester = numbersemester;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumbersemester() {
        return numbersemester;
    }

    public void setNumbersemester(int numbersemester) {
        this.numbersemester = numbersemester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
}
