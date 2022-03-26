/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.authentication;

import Dal.AccountDBContext;
import Dal.DomDBContext;
import Dal.GroupDBContext;
import Dal.RoomDBContext;
import Dal.StudentDBContext;
import Dal.StudentDetailDBContext;
import Model.Account;
import Model.Bed;
import Model.Detail;
import Model.Dom;
import Model.Group;
import Model.Room;
import Model.Semester;
import Model.Student;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        request.getRequestDispatcher("view/Register.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DomDBContext domDB = new DomDBContext();
        RoomDBContext roomDB = new RoomDBContext();
        StudentDetailDBContext sdDB = new StudentDetailDBContext();
        Detail detail = new Detail();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Boolean gender = request.getParameter("gender").equals("male");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setGender(gender);
        student.setDob(dob);
        student.setUsername(username);
        student.setAddress(address);
        student.setMoney(BigDecimal.ZERO);
        Dom dom = domDB.getDombyID(1);
        Room room = roomDB.getRoombyRoomCode("None");
        student.setRoom_code(room);
        Bed bed = new Bed(room, 0, BigDecimal.ZERO, "");
        Semester semester = new Semester(1, 0, 0);
        Date date = new Date(0);
        detail.setStudent(student);
        detail.setBed(bed);
        detail.setPrice(bed.getPrice());
        detail.setRoom(room);
        detail.setSemester(semester);
        detail.setDate_booked(date);
        detail.setDate_checkout(date);
        System.out.println(student.getUsername());
        AccountDBContext accDB = new AccountDBContext();
        Account account = new Account(username, password, 2);
        StudentDBContext studb = new StudentDBContext();
        Group group = new Group(2, "Normal");
        account.getGroups().add(group);
        GroupDBContext groupDB = new GroupDBContext();
        
        accDB.addAccount(account);
        studb.addStudent(student);
        sdDB.addStudentDetail(detail);
        response.sendRedirect("login");
        
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
