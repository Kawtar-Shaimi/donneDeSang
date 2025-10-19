<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Association Donneur - Receveur</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<jsp:include page="navbar.jsp"/>

<h2 class="mb-4 text-center">🔗 Page de Matching Donneur / Receveur</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/match" method="post" class="border p-4 rounded bg-light">

    <div class="mb-3">
        <label class="form-label"><b>Choisir un Receveur :</b></label>
        <select name="receveurId" class="form-select" required>
            <option value="">-- Sélectionner un receveur --</option>
            <c:forEach var="r" items="${receveurs}">
                <option value="${r.id}">
                        ${r.nom} ${r.prenom} | ${r.groupeSanguin} | Besoin : ${r.besoinPoches - r.pocheRecues} poches
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label class="form-label"><b>Choisir un Donneur :</b></label>
        <select name="donneurId" class="form-select" required>
            <option value="">-- Sélectionner un donneur --</option>
            <c:forEach var="d" items="${donneurs}">
                <option value="${d.id}">
                        ${d.nom} ${d.prenom} | ${d.groupeSanguin} | Statut : ${d.statut}
                </option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-primary w-100">Associer</button>
</form>

</body>
</html>