<%-- Document : AdminNav Created on : Jan 10, 2024, 8:14:42 PM Author :
jpesewang --%> <%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>JSP Page</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body>
        <%@ include file="../../layout/header.jsp" %>
        <div class=" mx-6">
            <div class="sm:hidden">
                <label for="tabs" class="sr-only">Select a tab</label>
                <!-- Use an "onChange" listener to redirect the user to the selected tab URL. -->
                <select
                    id="tabs"
                    name="tabs"
                    class="block w-full rounded-md border-gray-300 focus:border-indigo-500 focus:ring-indigo-500"
                    >
                    <option>My Account</option>
                    <option>Company</option>
                    <option selected>Team Members</option>
                    <option>Billing</option>
                </select>
            </div>
            <div class="hidden sm:block">
                <div class="border-b border-gray-200">
                    <nav class="-mb-px flex space-x-8" aria-label="Tabs">
                        <!-- Current: "border-indigo-500 text-indigo-600", Default: "border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700" -->

                        <a
                            href="/ResortManagement/Admin/Dashboard/ListEmployee.jsp"
                            class="border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 group inline-flex items-center border-b-2 py-4 px-1 text-sm font-medium"
                            >
                            <svg
                                class="text-gray-400 group-hover:text-gray-500 -ml-0.5 mr-2 h-5 w-5"
                                viewBox="0 0 20 20"
                                fill="currentColor"
                                aria-hidden="true"
                                >
                            <path
                                fill-rule="evenodd"
                                d="M4 16.5v-13h-.25a.75.75 0 010-1.5h12.5a.75.75 0 010 1.5H16v13h.25a.75.75 0 010 1.5h-3.5a.75.75 0 01-.75-.75v-2.5a.75.75 0 00-.75-.75h-2.5a.75.75 0 00-.75.75v2.5a.75.75 0 01-.75.75h-3.5a.75.75 0 010-1.5H4zm3-11a.5.5 0 01.5-.5h1a.5.5 0 01.5.5v1a.5.5 0 01-.5.5h-1a.5.5 0 01-.5-.5v-1zM7.5 9a.5.5 0 00-.5.5v1a.5.5 0 00.5.5h1a.5.5 0 00.5-.5v-1a.5.5 0 00-.5-.5h-1zM11 5.5a.5.5 0 01.5-.5h1a.5.5 0 01.5.5v1a.5.5 0 01-.5.5h-1a.5.5 0 01-.5-.5v-1zm.5 3.5a.5.5 0 00-.5.5v1a.5.5 0 00.5.5h1a.5.5 0 00.5-.5v-1a.5.5 0 00-.5-.5h-1z"
                                clip-rule="evenodd"
                                />
                            </svg>
                            <span>

                                <form 
                                    method="POST"
                                    action="../../EmployeeController?action=get">
                                    <button type="submit" value="Submit" >Employee List</button>
                                </form>
                            </span>
                        </a>
                        <a
                            href="/ResortManagement/Admin/Dashboard/CreateEmployee.jsp"
                            class="border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 group inline-flex items-center border-b-2 py-4 px-1 text-sm font-medium"
                            >
                            <!-- Current: "text-indigo-500", Default: "text-gray-400 group-hover:text-gray-500" -->
                            <svg
                                class="text-gray-400 group-hover:text-gray-500 -ml-0.5 mr-2 h-5 w-5"
                                viewBox="0 0 20 20"
                                fill="currentColor"
                                aria-hidden="true"
                                >
                            <path
                                d="M10 8a3 3 0 100-6 3 3 0 000 6zM3.465 14.493a1.23 1.23 0 00.41 1.412A9.957 9.957 0 0010 18c2.31 0 4.438-.784 6.131-2.1.43-.333.604-.903.408-1.41a7.002 7.002 0 00-13.074.003z"
                                />
                            </svg>
                            <span>Create Employee</span>
                        </a>

                        <!--                        <a href="#" class="border-indigo-500 text-indigo-600 group inline-flex items-center border-b-2 py-4 px-1 text-sm font-medium" aria-current="page">
                                        <svg class="text-indigo-500 -ml-0.5 mr-2 h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                                        <path d="M7 8a3 3 0 100-6 3 3 0 000 6zM14.5 9a2.5 2.5 0 100-5 2.5 2.5 0 000 5zM1.615 16.428a1.224 1.224 0 01-.569-1.175 6.002 6.002 0 0111.908 0c.058.467-.172.92-.57 1.174A9.953 9.953 0 017 18a9.953 9.953 0 01-5.385-1.572zM14.5 16h-.106c.07-.297.088-.611.048-.933a7.47 7.47 0 00-1.588-3.755 4.502 4.502 0 015.874 2.636.818.818 0 01-.36.98A7.465 7.465 0 0114.5 16z" />
                                        </svg>
                                        <span>Edit Employee</span>
                                    </a>-->
                    </nav>
                </div>
            </div>
        </div>
    </body>
</html>
