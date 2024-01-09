package Api.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Api.Validators.EmployeeValidator;
import Domain.DTOs.EmployeeDto.CreateEmployeeDto;
import Domain.DTOs.EmployeeDto.UpdateEmployeeDto;
import Domain.Exceptions.ConflictException;
import Domain.Models.Employee;
import Services.EmployeeService;

public class EmployeeController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeService employeeService = new EmployeeService();

        List<Employee> employees = employeeService.getAllEmployees();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmployeeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeController at " + request.getContextPath() + "</h1>");
            out.println("Employees: " + employees.get(2).getQualification());
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        EmployeeService employeeService = new EmployeeService();
        EmployeeValidator employeeValidator = new EmployeeValidator();

        List<String> validationErrors;

        switch (action) {
            case "create":
                CreateEmployeeDto createEmployeeDto = new CreateEmployeeDto(request);
                validationErrors = employeeValidator.validateCreateEmployeeDto(createEmployeeDto);
                if (validationErrors.size() == 0) {
                    try {
                        employeeService.createEmployee(createEmployeeDto);
                    } catch (ConflictException ex) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Servlet EmployeeController</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println(ex.getMessage());
                            out.println("</body>");
                            out.println("</html>");
                        }
                    }
                } else {
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet EmployeeController</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Validation failed: " + String.join(", ", validationErrors) + "</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
                break;
            case "update":
                String id = request.getParameter("id");
                UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto(request);
                validationErrors = employeeValidator.validateUpdateEmployeeDto(updateEmployeeDto);

                if (validationErrors.size() == 0) {
                    try {
                        employeeService.updateEmployee(id, updateEmployeeDto);
                    } catch (ConflictException ex) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Servlet EmployeeController</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println(ex.getMessage());
                            out.println("</body>");
                            out.println("</html>");
                        }
                    }
                } else {
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet EmployeeController</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Validation failed: " + String.join(", ", validationErrors) + "</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
                break;
            default:
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet EmployeeController</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Invalid action: " + action + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }

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
