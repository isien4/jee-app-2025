<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<h1> Liste des Produits</h1>

<form method="get" action="product">
    <input type="text" name="keyword" placeholder="Recherche par mot-clé" />
    <input class="btn btn-primary" type="submit" value="Rechercher" />
</form>


<a class="btn btn-success" href="product?action=add">Ajouter un Produit</a>
<table class="table table-bordered">
    <tr>
        <td>Nom</td>
        <td>Prix</td>
        <td>Quantité</td>
        <td>Catégorie</td>
        <td>Actions</td>
    </tr>
    <c:forEach items="${listProduct}" var="product">
        <tr>
            <td>${product.designation}</td>
            <td>${product.prix}</td>
            <td>${product.quantite}</td>
            <td>${product.category.name}</td>
            <td>
                <a class="btn btn-danger" href="product?action=delete&id=${product.id}">Supprimer</a>
                <a class="btn btn-primary" href="product?action=edit&id=${product.id}">Modifier</a>
            </td>
        </tr>
    </c:forEach>
</table>
