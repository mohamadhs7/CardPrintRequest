<%@ page import="java.awt.print.Printable" %>
<%@ page import="ir.dotin.cardprintrequest.models.PrintRequest" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Show Requests</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" th:href="@{/css/formatting.css}">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>

<body>
<br/>
<div class="container">
    <h3>Requests List</h3>
    <hr>
    <!--  Add New Project Button -->
    <a href="/printrequests/new"
       class="btn btn-primary btn-sm mb-3">
        New Request
    </a>
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>IPAddress</th>
            <th>Branch Code</th>
            <th>CardPan</th>
            <th>Issued Date</th>
            <th>Personnel Code</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${printRequests}" var="listValue">
            <tr>
                <td>${listValue.id.ipAddress}</td>
                <td>${listValue.id.branchCode}</td>
                <td>${listValue.cardPan}</td>
                <td>${listValue.issuedDate}</td>
                <td>${listValue.personnelCode}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>