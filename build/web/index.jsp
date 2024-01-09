<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="vi">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>Resort Management</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
  </head>

  <body>
    <a href="EmployeeController">Employee</a>
    <form
      name="CreateEmployee"
      method="POST"
      action="EmployeeController?action=create"
    >
      Id: <input type="text" name="Id" /> <br />
      FullName: <input type="text" name="FullName" /> <br />
      Gender: <input type="text" name="Gender" /> <br />
      BirthDate: <input type="date" name="BirthDate" /> <br />

      IdNumber: <input type="text" name="IdNumber" /> <br />
      PhoneNumber: <input type="text" name="PhoneNumber" /> <br />
      Email: <input type="text" name="Email" /> <br />
      Qualification: <input type="text" name="Qualification" /> <br />
      Position: <input type="text" name="Position" /> <br />
      Salary: <input type="text" name="Salary" /> <br />
      <input type="submit" value="Submit" />
    </form>
  </body>
</html>
