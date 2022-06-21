<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" th:href="@{/css/formatting.css}">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>

<body>
<br/>
<div class="container">

    <form:form action="save" modelAttribute="printRequest">
        CARDPAN : <form:input path="cardPan"/> <br/>
        PERSONNELCODE : <form:input path="personnelCode"/> <br/>
        ISSUEDDATE : <form:input type="date" path="issuedDate" /> <br/>
        BRANCHCODE : <form:input  path="id.branchCode" /> <br/>
        IPADDRESS : <form:input path="id.ipAddress" /> <br/>
        <br/>
        <input type="submit" value="Add Request">
    </form:form>

    </form>
</div>
</body>
</html>