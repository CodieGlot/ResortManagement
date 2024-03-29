package Api.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Api.Validators.CustomerValidator;
import Domain.DTOs.CustomerDto.CreateCustomerDto;
import Domain.DTOs.CustomerDto.UpdateCustomerDto;
import Domain.Exceptions.ConflictException;
import Domain.Models.Customer;
import Services.CustomerService;

public class CustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerService customerService = new CustomerService();

        List<Customer> customers = customerService.getAllCustomers();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerController at " + request.getContextPath() + "</h1>");
            out.println("Customers: " + customers.get(2).getCustomerType());
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        CustomerService customerService = new CustomerService();
        CustomerValidator customerValidator = new CustomerValidator();

        List<String> validationErrors;

        switch (action) {
            case "create":
                CreateCustomerDto createCustomerDto = new CreateCustomerDto(request);
                validationErrors = customerValidator.validateCreateCustomerDto(createCustomerDto);
                if (validationErrors.isEmpty()) {
                    try {
                        customerService.createCustomer(createCustomerDto);
                    } catch (ConflictException ex) {
                        handleConflictException(response, ex.getMessage());
                    }
                } else {
                    handleValidationErrors(request, response, validationErrors);
                }
                break;
                
            case "update":
                String customerId = request.getParameter("id");
                UpdateCustomerDto updateCustomerDto = new UpdateCustomerDto(request);
                validationErrors = customerValidator.validateUpdateCustomerDto(updateCustomerDto);

                if (validationErrors.isEmpty()) {
                    try {
                        customerService.updateCustomer(customerId, updateCustomerDto);
                    } catch (ConflictException ex) {
                        handleConflictException(response, ex.getMessage());
                    }
                } else {
                    handleValidationErrors(request, response, validationErrors);
                }
                break;
            default:
                handleInvalidAction(response, action);
        }
    }

    private void handleConflictException(HttpServletResponse response, String message) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(message);
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void handleValidationErrors(HttpServletRequest request, HttpServletResponse response, List<String> validationErrors)
            throws IOException, ServletException {
//        Hanle error log
        request.setAttribute("error", String.join(", ", validationErrors));
        request.getRequestDispatcher("/ResortManagement/Admin/Dashboard/CreateEmployee.jsp").forward(request, response);
        
        
//        try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CustomerController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Validation failed: " + String.join(", ", validationErrors) + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    private void handleInvalidAction(HttpServletResponse response, String action) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Invalid action: " + action + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
