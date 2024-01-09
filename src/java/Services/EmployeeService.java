package Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.DTOs.EmployeeDto.CreateEmployeeDto;
import Domain.Enums.PositionType;
import Domain.Enums.QualificationType;
import Domain.Exceptions.ConflictException;
import Domain.Models.Employee;
import Repositories.Common.RepositoryBase;
import Repositories.Entities.EmployeeEntity;

public class EmployeeService extends RepositoryBase<EmployeeEntity> {

    @Override
    protected String getTableName() {
        return "Employees";
    }

    @Override
    protected EmployeeEntity createEntityFromResultSet(ResultSet rs) throws SQLException {
        return new EmployeeEntity(rs);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        List<EmployeeEntity> entities = super.getAll();

        for (EmployeeEntity entity : entities)
            employees.add(mapEntityToEmployee(entity));

        return employees;
    }

    public Employee getEmployeeById(String id) {
        EmployeeEntity entity = super.getById(id);

        return mapEntityToEmployee(entity);
    }

    public void createEmployee(CreateEmployeeDto dto) throws ConflictException {
        EmployeeEntity entity = super.getById(dto.getId());

        if (entity != null) {
            throw new ConflictException("User ID already exists.");
        }

        String query = String.format(
                "INSERT INTO %s (Id,FullName,BirthDate,Gender,IdNumber,PhoneNumber,Email,Qualification,Position,Salary) VALUES (?,?,?,?,?,?,?,?,?,?);",
                getTableName());

        List<Object> params = new ArrayList<>();
        params.add(dto.getId());
        params.add(dto.getFullName());
        params.add(dto.getBirthDate());
        params.add(dto.getGender());
        params.add(dto.getIdNumber());
        params.add(dto.getPhoneNumber());
        params.add(dto.getEmail());
        params.add(dto.getQualification().getIndex());
        params.add(dto.getPosition().getIndex());
        params.add(dto.getSalary());

        super.executeNonQuery(query, params);
    }

    private Employee mapEntityToEmployee(EmployeeEntity entity) {
        Employee employee = new Employee();
        employee.setId(entity.getId());
        employee.setFullName(entity.getFullName());
        employee.setBirthDate(entity.getBirthDate());
        employee.setGender(entity.getGender());
        employee.setIdNumber(entity.getIdNumber());
        employee.setPhoneNumber(entity.getPhoneNumber());
        employee.setEmail(entity.getEmail());
        employee.setQualification(QualificationType.fromIndex(entity.getQualification()));
        employee.setPosition(PositionType.fromIndex(entity.getPosition()));
        employee.setSalary(entity.getSalary());

        return employee;
    }
}
