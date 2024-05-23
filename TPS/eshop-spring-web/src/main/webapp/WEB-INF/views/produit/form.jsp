<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition d'un produit</title>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap-icons.css"/>">
</head>
<body>

	<header>
		<ul class="nav nav-pills m-5">
			<li class="nav-item"><a class="nav-link" href="<c:url value="/"/>">Accueil</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/commentaire"/>">Commentaire</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/fournisseur"/>">Fournisseur</a></li>
			<li class="nav-item"><a class="nav-link active" href="<c:url value="/produit"/>">Produit</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/client"/>">Client</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/adresse"/>">Adresse</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/commande"/>">Commande</a></li>
		</ul>
	</header>
	<div class="container-fluid">
		<div class="card mt-3">
			<form action="<c:url value="/produit/save"/>" method="post">
				<input type="hidden" name="id" value="${produit.id}" />
				<div class="card-header bg-primary text-white display-6">Edition d'un produit</div>
				<div class="card-body">
					<div class="form-group">
						<label for="libelle">Libellé:</label> <input type="text"
							class="form-control" id="libelle" name="libelle"
							value="${produit.libelle}" />
					</div>
					<div class="form-group">
						<label for="prixAchat">Prix d'achat:</label> <input type="number" step="0.01"
							class="form-control" id="prixAchat" name="prixAchat"
							value="${produit.prixAchat}" />
					</div>
					<div class="form-group">
						<label for="prixVente">Prix de vente:</label> <input type="number" step="0.01"
							class="form-control" id="prixVente" name="prixVente"
							value="${produit.prixAchat}" />
					</div>
					<div class="form-group">
						<label for="reference">Référence:</label> <input type="text"
							class="form-control" id="reference" name="reference"
							value="${produit.reference}" />
					</div>
					<div class="form-group">
						<label for="modele">Modèle:</label> <input type="text"
							class="form-control" id="modele" name="modele"
							value="${produit.modele}" />
					</div>
					<div class="form-group">
						<label for="stock">Stock:</label> <input type="number"
							class="form-control" id="stock" name="stock"
							value="${produit.stock}" />
					</div>

				</div>
				<div class="card-footer">
					<div class="btn-group btn-group-lg float-right">
						<button type="submit" class="btn btn-success">
							<i class="bi bi-check-square-fill"></i>
						</button>
						<a class="btn btn-warning" href="<c:url value="/produit/cancel"/>"><i
							class="bi bi-backspace-fill"></i></a>
					</div>
				</div>
			</form>
		</div>
	</div>


	<script src="<c:url value="/js/bootstrap.bundle.js"/>"></script>
</body>
</html>