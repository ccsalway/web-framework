<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table>
<c:forEach items="${result.rows}" var="row">
    <tr>
        <td>${row.id}</td>
        <td>${row.name}</td>
    </tr>
</c:forEach>
</table>
<div>${result.totalRows} results</div>
<div>[1][${result.hasPreviousPage}] &hellip; [${result.currentPage}] &hellip; [${result.hasNextPage}][${result.totalPages}]</div>
</body>
</html>
