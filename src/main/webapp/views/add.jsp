<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<h1>Ajouter un Produit</h1>

<form action="produit?action=save" method="POST">

  <label>Nom</label>
  <input type="text" class="form-control" name="designation">
  <br>

  <label>Prix</label>
  <input type="number" step="0.01" class="form-control" name="prix">
  <br>

  <label>Quantité</label>
  <input type="number" class="form-control" name="quantite">
  <br>

  <label>Catégorie</label>
  <select name="categorie" class="form-control">
    <c:forEach items="${categories}" var="categorie">
      <option value="${categorie.id}">${categorie.nom}</option>
    </c:forEach>
  </select>

  <button class="btn btn-info" name="save" type="submit">Enregistrer</button>
  <button class="btn btn-danger" name="save" type="reset">Retour</button>
</form>
