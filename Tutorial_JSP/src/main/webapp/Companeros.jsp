
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
    table {
    	font-family:sans-serif;
    	size:20px;
        border-collapse: collapse;
        width: 100%;
    }
    
    th, td {
        text-align: left;
        padding: 8px;
    }
    
    th {
        background-color: #A3DE83;
        color: white;
    }
    
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
</style>
<table border=1>
        <thead>
            <tr>
                <th>Carnet</th>
                <th>Nombre</th>
                <th>Apellido</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.carnet}" /></td>
                    <td><c:out value="${user.nombre}" /></td>
                    <td><c:out value="${user.apellido}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</html>