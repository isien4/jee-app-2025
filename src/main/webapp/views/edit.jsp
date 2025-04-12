<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<h1>Modifier un Produit</h1>

<form action="produit?action=update" method="POST">
    <input type="text" class="form-control" name="id" value="${produit.id}" hidden>

    <label>Nom</label>
    <input type="text" class="form-control" name="designation" value="${produit.designation}">
    <br>

    <label>Prix</label>
    <input type="number" step="0.01" class="form-control" name="prix" value="${produit.prix}">
    <br>

    <label>Quantité</label>
    <input type="number" class="form-control" name="quantite" value="${produit.quantite}">
    <br>

    <label>Catégorie</label>
    <select name="categorie" class="form-control">
        <c:forEach items="${categories}" var="categorie">
            <option value="${categorie.id}" ${categorie.id == produit.categorie.id ? 'selected' : ''}>${categorie.nom}</option>
        </c:forEach>
    </select>

    <button class="btn btn-info" name="save" type="submit">Modifier</button>
    <button class="btn btn-danger" name="save" type="reset">Retour</button>
</form>
