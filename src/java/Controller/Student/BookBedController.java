/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Student;

import Controller.authentication.BaseRequireAuthenController;
import Dal.BedDBContext;
import Dal.BookBedRequestDBContext;
import Dal.DomDBContext;
import Dal.RoomDBContext;
import Dal.SemesterDBContext;
import Dal.StudentDBContext;
import Model.Account;
import Model.Bed;
import Model.BookBed;
import Model.Dom;
import Model.Room;
import Model.Semester;
import Model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class BookBedController extends BaseRequireAuthenController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("acc");
        StudentDBContext stuDB = new StudentDBContext();
        Student student = stuDB.getStudentbyUsername(acc.getUsername());
        BookBedRequestDBContext bookDB = new BookBedRequestDBContext();
        ArrayList<BookBed> books = bookDB.bookbedsbyStudentID(student.getId());
        int domid = (request.getParameter("domid") == null) ? 1 : Integer.parseInt(request.getParameter("domid"));
        DomDBContext domDB = new DomDBContext();
        ArrayList<Dom> doms = domDB.getDoms();
        RoomDBContext roomDB = new RoomDBContext();
        ArrayList<Room> rooms = roomDB.getRoombyDomID(domid);
        SemesterDBContext semesterDB = new SemesterDBContext();
        ArrayList<Semester> semesters = semesterDB.getSemesters();
        request.setAttribute("semesters", semesters);
        request.setAttribute("doms", doms);
        request.setAttribute("rooms", rooms);
        request.setAttribute("domid", domid);
        request.setAttribute("books", books);
        request.getRequestDispatcher("view/BookBed.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("acc");
        StudentDBContext stuDB = new StudentDBContext();
        Student student = stuDB.getStudentbyUsername(acc.getUsername());
        BigDecimal money = student.getMoney();
        BookBed bookbed = new BookBed();
        String roomcode = request.getParameter("roomcode");
        int bednumber = Integer.parseInt(request.getParameter("bednumber"));
        BedDBContext bedDB = new BedDBContext();
        Bed bed1 = bedDB.getBedbyRoomandNumber(roomcode, bednumber);
        if (money.compareTo(bed1.getPrice()) < 0) {
            response.sendRedirect("home");
        } else {
            BigDecimal newmoney = money.subtract(bed1.getPrice());
            student.setMoney(newmoney);
            stuDB.updateStudentMoney(student);
            Calendar calendar = Calendar.getInstance();
            Date booked_date = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 30);
            Date checkout_date = calendar.getTime();
            int semesterid = Integer.parseInt(request.getParameter("semesterid"));
            Semester semester = new Semester();
            semester.setId(semesterid);
            Room room = new Room();
            room.setRoom_code(roomcode);
            Bed bed = new Bed();
            bed.setNumber(bednumber);
            bookbed.setRoom(room);
            bookbed.setBed(bed);
            bookbed.setBooked_date(new java.sql.Date(booked_date.getTime()));
            bookbed.setDate_checkout(new java.sql.Date(checkout_date.getTime()));
            bookbed.setStudent(student);
            bookbed.setStatus(0);
            bookbed.setSemester(semester);
            BookBedRequestDBContext bookDB = new BookBedRequestDBContext();
            bookDB.insert(bookbed);
            response.sendRedirect("home");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
