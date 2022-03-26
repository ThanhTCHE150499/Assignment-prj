/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Student;

import Controller.authentication.BaseRequireAuthenController;
import Dal.AccountDBContext;
import Dal.StudentDBContext;
import Model.Account;
import Model.Room;
import Model.Student;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class UpdateController extends BaseRequireAuthenController{

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
        String username = request.getParameter("username");
        StudentDBContext stuDB = new StudentDBContext();
        Student student = stuDB.getStudentbyUsername(username);
        request.setAttribute("student", student);
        request.getRequestDispatcher("view/Update.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        boolean gender = request.getParameter("gender").equals("male");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String address = request.getParameter("address");
        Account sessionAcc = (Account) request.getSession().getAttribute("acc");
        String username = sessionAcc.getUsername();
        String password = request.getParameter("password");
        Student student = new Student();
        student.setName(name);
        student.setGender(gender);
        student.setDob(dob);
        student.setAddress(address);
        student.setUsername(username);
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        StudentDBContext stuDB = new StudentDBContext();
        stuDB.updateStudent(student);
        AccountDBContext accDB = new AccountDBContext();
        accDB.updateAccount(account);
        response.sendRedirect("home");
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
