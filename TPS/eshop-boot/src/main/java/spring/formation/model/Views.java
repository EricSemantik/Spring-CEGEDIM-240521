package spring.formation.model;

public interface Views {
	public static interface ViewBasic {}
	
	public static interface ViewAdresse extends ViewBasic {}
	
	public static interface ViewPersonne extends ViewBasic {}
	
	public static interface ViewClient extends ViewPersonne{}
	
	public static interface ViewFournisseur extends ViewPersonne {}
	
	public static interface ViewFournisseurDetail extends ViewFournisseur {}
}
