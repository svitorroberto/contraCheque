package model;

import java.text.NumberFormat;
import java.util.Locale;

public class Provento {
	private int codigo;
	private String descricao;
	private String aliquota;
	private String vencimentos;
	private String descontos;
	Locale ptBR = new Locale("pt", "BR");
	NumberFormat nf = NumberFormat.getNumberInstance(ptBR);
			
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public String getAliquota() {
		return aliquota;
	}
	public void setAliquota(Double aliquota) {
		if(aliquota>0)
			this.aliquota = nf.format(aliquota);
		else
			this.aliquota ="";
	}
	public String getVencimentos() {
		return vencimentos;
	}
	public void setVencimentos(Double vencimentos) {
		this.vencimentos = nf.format(vencimentos);
	}
	public String getDescontos() {
		return descontos;
	}
	public void setDescontos(Double descontos) {
		this.descontos = nf.format(descontos);
	}
	@Override
	public String toString() {
		return codigo +"	"+descricao+"	"+aliquota+"	"+vencimentos+"	"+descontos;
	}
	
}
