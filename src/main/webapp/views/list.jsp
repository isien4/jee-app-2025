<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<h1> Liste des Produits</h1>

<form method="get" action="produit">
    <input type="text" name="keyword" placeholder="Recherche par mot-clé" />
    <input class="btn btn-primary" type="submit" value="Rechercher" />
</form>


<a class="btn btn-success" href="produit?action=add">Ajouter un Produit</a>
<table class="table table-bordered">
    <tr>
        <td>Nom</td>
        <td>Prix</td>
        <td>Quantité</td>
        <td>Catégorie</td>
        <td>Actions</td>
    </tr>
    <c:forEach items="${listProduct}" var="produit">
        <tr>
            <td>${produit.designation}</td>
            <td>${produit.prix}</td>
            <td>${produit.quantite}</td>
            <td>${produit.categorie.nom}</td>
            <td>
                <a class="btn btn-danger" href="produit?action=delete&id=${produit.id}">Supprimer</a>
                <a class="btn btn-primary" href="produit?action=edit&id=${produit.id}">Modifier</a>
            </td>
        </tr>
    </c:forEach>
</table>
