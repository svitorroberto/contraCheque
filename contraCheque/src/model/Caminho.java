package model;

public class Caminho {
	
	private String nome;
	private String url;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Caminho(String nome, String url){
		this.nome = nome;
		this.url = url;
	}
	
	@Override
	public String toString() {
		
		return "\n"+nome+" - "+url;
	}
	
}
