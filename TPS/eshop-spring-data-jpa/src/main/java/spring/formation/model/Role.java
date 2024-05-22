package spring.formation.model;

public enum Role {
	CLIENT, FOURNISSEUR, ADMIN;
	
	public String authority() {
		return "ROLE_" + name();
	}
}
