package model;

import java.text.NumberFormat;
import java.util.Locale;

public class Provento2 {
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
		if(vencimentos>0){
			String d2 = String.format("%.2f", vencimentos);
			StringBuffer sb = new StringBuffer(d2);
			if(vencimentos>999){
				sb.insert(sb.length() - 6, ".");				
			}
			this.vencimentos = sb.toString();
		}else
			this.vencimentos ="0";
		
		
		
		
	}
	public String getDescontos() {
		return descontos;
	}
	public void setDescontos(Double descontos) {
		if(descontos>0){
			String d2 = String.format("%.2f", descontos);
			StringBuffer sb = new StringBuffer(d2);
			if(descontos>999){
				sb.insert(sb.length() - 6, ".");				
			}
			this.descontos = sb.toString();
		}else
			this.descontos ="0";
	}
	
	

}
