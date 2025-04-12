<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<h1>Modifier un Produit</h1>

<form action="product?action=update" method="POST">
    <input type="text" class="form-control" name="id" value="${product.id}" hidden>

    <label>Nom</label>
    <input type="text" class="form-control" name="designation" value="${product.designation}">
    <br>

    <label>Prix</label>
    <input type="number" step="0.01" class="form-control" name="prix" value="${product.prix}">
    <br>

    <label>Quantité</label>
    <input type="number" class="form-control" name="quantite" value="${product.quantite}">
    <br>

    <label>Catégorie</label>
    <select name="category" class="form-control">
        <c:forEach items="${categories}" var="category">
            <option value="${category.id}" ${category.id == product.category.id ? 'selected' : ''}>${category.name}</option>
        </c:forEach>
    </select>

    <button class="btn btn-info" name="save" type="submit">Mettre à jour</button>
    <button class="btn btn-danger" name="save" type="reset">Retour</button>
</form>
