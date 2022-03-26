/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin;

import Controller.authentication.BaseRequireAuthenController;
import Dal.RequestDBContext;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ViewRequestController extends BaseRequireAuthenController {

    int requestperpage = 3;

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
        RequestDBContext requestDB = new RequestDBContext();
        String q = request.getParameter("search");
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.length() == 0) {
            raw_page = "1";
        }
        int pageid = Integer.parseInt(raw_page);
        int sumofpage = 0;
        if (q != null) {
            int totalsearchrequest = Integer.parseInt(request.getParameter("totalsearchrequest"));
            int remainpage = (totalsearchrequest % requestperpage > 0) ? 1 : 0;
            sumofpage = totalsearchrequest / requestperpage + remainpage;
            int offset = (pageid - 1) * requestperpage;
            int fetch = requestperpage;
            ArrayList<Request> search = requestDB.search(q, offset, fetch);
            request.setAttribute("totalsearchrequest", totalsearchrequest);
            request.setAttribute("requests", search);

            request.setAttribute("q", q);
        }
        System.out.println(q);
        request.setAttribute("pageid", pageid);
        request.setAttribute("totalpage", sumofpage);
        request.getRequestDispatcher("view/ViewRequest.jsp").forward(request, response);
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
        String q = request.getParameter("search");
        int pageid = 1;
        RequestDBContext requestDB = new RequestDBContext();
        int totalrequest = requestDB.totalSearch(q);
        int remainpage = (totalrequest % requestperpage > 0) ? 1 : 0;
        int sumofpage = totalrequest / requestperpage + remainpage;
        int offset = (pageid - 1) * requestperpage;
        int fetch = requestperpage;
        ArrayList<Request> search = requestDB.search(q, offset, fetch);
        request.setAttribute("totalsearchrequest", totalrequest);
        request.setAttribute("requests", search);
        request.setAttribute("pageid", pageid);
        request.setAttribute("totalpage", sumofpage);
        request.setAttribute("q", q);
        request.getRequestDispatcher("view/ViewRequest.jsp").forward(request, response);
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
