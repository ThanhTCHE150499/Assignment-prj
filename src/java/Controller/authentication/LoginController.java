/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.authentication;

import Dal.AccountDBContext;
import Dal.GroupDBContext;
import Model.Account;
import Model.Feature;
import Model.Group;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

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
        GroupDBContext groupDB = new GroupDBContext();
        
        ArrayList<Group> groups = groupDB.getGroups();
        Cookie[] cookies = request.getCookies();
        AccountDBContext accDB = new AccountDBContext();
        Account acc = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                System.out.println(c.getName());
                if (c.getName().equals("username")) {
                    acc = accDB.getAccsByUsername(c.getValue());
                    ArrayList<Group> userGroup = accDB.getGroupbyUsername(acc.getUsername());
                    acc.setGroups(userGroup);
                    request.getSession().setAttribute("acc", acc);
                }
                if (c.getName().equals("groupnumber")) {
                    acc.setGroupnumber(Integer.parseInt(c.getValue()));
                }
            }
        }
        request.setAttribute("groups", groups);
        HttpSession session = request.getSession();
        acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            ArrayList<Feature> Userfeature = groupDB.getFeaturesbyUsername(acc.getUsername());
            acc.setFeatures(Userfeature);
            for (Group group : acc.getGroups()) {

                if (acc.getGroupnumber() == group.getGid()) {
                    if (acc.getGroupnumber() == 2) {
                        request.getRequestDispatcher("home").forward(request, response);

                    }
                    if (acc.getGroupnumber() == 1) {
                        request.getRequestDispatcher("adminhome").forward(request, response);
                    }
                    return;
                }
            }
            request.getRequestDispatcher("view/Login.jsp").forward(request, response);
        } else {

            request.getRequestDispatcher("view/Login.jsp").forward(request, response);
        }
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
        String remember = request.getParameter("remember");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int gid = Integer.parseInt(request.getParameter("gid"));
        HttpSession session = request.getSession();
        AccountDBContext accDB = new AccountDBContext();

        ArrayList<Group> Usergroup = accDB.getGroupbyUsername(username);
        Account acc = accDB.getAccsByUsername(username);
        GroupDBContext groupDB = new GroupDBContext();
//        for (Feature feature : Userfeature) {
//            System.out.println(feature.getUrl());
//        }
        if (remember != null) {
            Cookie cookie = new Cookie("username", username);
            Cookie cookie1 = new Cookie("groupnumber", gid + "");
            cookie.setMaxAge(3600 * 24 * 7);
            cookie1.setMaxAge(3600 * 24 * 7);
            response.addCookie(cookie);
            response.addCookie(cookie1);
        }
        if (acc == null) {
            response.sendRedirect("view/Login.jsp");
            return;
        } else {
            acc.setGroupnumber(gid);
            acc.setGroups(Usergroup);
            if (!password.equals(acc.getPassword())) {
                response.sendRedirect("view/Login.jsp");
                return;
            } else {
                session.setAttribute("acc", acc);
            }
        }
        doGet(request, response);
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
