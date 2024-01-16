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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = employeeService.getAllEmployees();

        request.setAttribute("employees", employees);
        request.getRequestDispatcher("Admin/Dashboard/ListEmployee.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmployeeService employeeService = new EmployeeService();
        EmployeeValidator employeeValidator = new EmployeeValidator();

        List<String> validationErrors;
        List<Employee> employees = employeeService.getAllEmployees();
        String action = request.getParameter("action");

        switch (action) {
            case "get":
                request.setAttribute("employees", employees);
                request.getRequestDispatcher("Admin/Dashboard/ListEmployee.jsp").forward(request, response);
                break;

            case "getById":
                String id = request.getParameter("id");
                Employee employee = employeeService.getEmployeeById(id);
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("Admin/Dashboard/UpdateEmployee.jsp").forward(request, response);

                break;

            case "create":
                CreateEmployeeDto createEmployeeDto = new CreateEmployeeDto(request);
                request.setAttribute("formData", createEmployeeDto);
                validationErrors = employeeValidator.validateCreateEmployeeDto(createEmployeeDto);
                if (validationErrors.isEmpty()) {
                    try {
                        employeeService.createEmployee(createEmployeeDto);
                        response.sendRedirect("Admin/Dashboard/CreateSuccess.jsp");
                        return;
                    } catch (ConflictException ex) {
                        request.setAttribute("error", ex.getMessage());
                    }
                } else {
                    request.setAttribute("error", String.join(" - ", validationErrors));
                }
                request.getRequestDispatcher("Admin/Dashboard/CreateEmployee.jsp").forward(request, response);
                break;

            case "update":
                String id2 = request.getParameter("id");
                UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto(request);
                validationErrors = employeeValidator.validateUpdateEmployeeDto(updateEmployeeDto);
                if (validationErrors.isEmpty()) {
                    try {
                        employeeService.updateEmployee(id2, updateEmployeeDto);
                    } catch (ConflictException ex) {
                        request.setAttribute("error", ex.getMessage());
                    }
                } else {
                    request.setAttribute("error", String.join(", ", validationErrors));

                }
                request.getRequestDispatcher("Admin/Dashboard/CreateEmployee.jsp").forward(request, response);
                break;

            default:

                request.getRequestDispatcher("Admin/Dashboard/CreateEmployee.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
