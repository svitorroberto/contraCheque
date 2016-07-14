package model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Funcionario {
	
	private int linha;
	

	private String nome;
	private String cpf;
	private int matricula;
	private String referencia;
	
	private int cbo;
	private String divisao;
	private String cargo;
	private int lotacao;
	private String situacao;
	
	private int codBanco;
	private int agencia;
	private String conta;
	
	private ArrayList<Provento2> proventos = new ArrayList<Provento2>();
	
	private String salContInss;
	private String baseCalcFgts;
	private String valorFgts;
	private String baseCalcIrrf;
	private int depIrrf;
	private String totalProventos;
	private String totalDescontos;
	private String totalLiquido;
	Locale ptBR = new Locale("pt", "BR");
	NumberFormat nf = NumberFormat.getNumberInstance(ptBR);
	
	
	public Funcionario() {
		nome = null;
		cpf = null;
		matricula = 0;
		referencia = null;
		
		cbo = 0;
		divisao = null;
		cargo = null;
		lotacao = 0;
		situacao = null;
		
		codBanco = 0;
		agencia = 0;
		conta = null;
		
		
		salContInss = "";
		baseCalcFgts = "";
		valorFgts = "";
		baseCalcIrrf = "";
		depIrrf = 0;
		totalProventos = "";
		totalDescontos = "";
		totalLiquido = "";
	}
	
	

	public String getDivisao() {
		return divisao;
	}

	public void setDivisao(String divisao) {
		this.divisao = divisao;
	}

	public int getLinha() {
		return linha;
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getCbo() {
		return cbo;
	}

	public void setCbo(int cbo) {
		this.cbo = cbo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(int i) {
		this.codBanco = i;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public ArrayList<Provento2> getProventos() {
		return proventos;
	}

	public String getSalContInss() {
		return salContInss;
	}

	public void setSalContInss(double salContInss) {
		if(salContInss>0){
			String d2 = String.format("%.2f", salContInss);
			StringBuffer sb = new StringBuffer(d2);
			if(salContInss>999){
				sb.insert(sb.length() - 6, ".");				
			}
			this.salContInss = sb.toString();
		}else
			this.salContInss ="0";
	}

	public String getBaseCalcFgts() {
		return baseCalcFgts;
	}

	public void setBaseCalcFgts(double baseCalcFgts) {
		if(baseCalcFgts>0){
			String d2 = String.format("%.2f", baseCalcFgts);
			StringBuffer sb = new StringBuffer(d2);
			if(baseCalcFgts>999){
				sb.insert(sb.length() - 6, ".");				
			}
			this.baseCalcFgts = sb.toString();
		}else
			this.baseCalcFgts ="0";
	}

	public String getValorFgts() {
		return valorFgts;
	}

	public void setValorFgts(double valorFgts) {
		if(valorFgts>0){
			String d2 = String.format("%.2f", valorFgts);
			StringBuffer sb = new StringBuffer(d2);
			if(valorFgts>999){
				sb.insert(sb.length() - 6, ".");				
			}
			this.valorFgts = sb.toString();
		}else
			this.valorFgts ="0";
	}

	public String getBaseCalcIrrf() {
		return baseCalcIrrf;
	}

	public void setBaseCalcIrrf(double baseCalcIrrf) {
		if(baseCalcIrrf>0){
			String d2 = String.format("%.2f", baseCalcIrrf);
			StringBuffer sb = new StringBuffer(d2);
			if(baseCalcIrrf>999){
				sb.insert(sb.length() - 6, ".");				
			}
			this.baseCalcIrrf = sb.toString();
		}else
			this.baseCalcIrrf ="0";
	}

	

	public int getDepIrrf() {
		return depIrrf;
	}

	public void setDepIrrf(int depIrrf) {
		this.depIrrf = depIrrf;
	}

	public String getTotalProventos() {
		return totalProventos;
	}

	public void setTotalProventos(double totalProventos) {
		if(totalProventos>0){
			String d2 = String.format("%.2f", totalProventos);
			StringBuffer sb = new StringBuffer(d2);
			if(totalProventos>999){
				sb.insert(sb.length() - 6, ".");				
			}
			this.totalProventos = sb.toString();
		}else
			this.totalProventos ="0";
	}

	public String getTotalDescontos() {
		return totalDescontos;
	}

	public void setTotalDescontos(double totalDescontos) {
		if(totalDescontos>0){
			String d2 = String.format("%.2f", totalDescontos);
			StringBuffer sb = new StringBuffer(d2);
			if(totalDescontos>999){
				sb.insert(sb.length() - 6, ".");				
			}
			this.totalDescontos = sb.toString();
		}else
			this.totalDescontos ="0";
	}

	public String getTotalLiquido() {
		return totalLiquido;
	}

	public void setTotalLiquido(double totalLiquido) {
		if(totalLiquido>0){
			String d2 = String.format("%.2f", totalLiquido);
			StringBuffer sb = new StringBuffer(d2);
			if(totalLiquido>999){
				sb.insert(sb.length() - 6, ".");				
			}
			this.totalLiquido = sb.toString();
		}else
			this.totalLiquido ="0";
	}

	public void setProventos(ArrayList<Provento2> proventos) {
		this.proventos = proventos;
	}
	
	
	@Override
	public String toString() {
		String p ="";
		for(Provento2 c : proventos) {  
			  p += c.toString()+"\n";
			}
		
		return "CENTRAIS DE ABASTECIMENTO DE GOIAS				01.098.797/0001-74\n"+
				getNome()+"	"+getCpf()+"	"+getMatricula()+"	"+getReferencia()+"\n"+
				getCbo()+"	"+getCargo()+"	"+getLotacao()+"	"+getSituacao()+"\n"+
				getCodBanco()+"	"+getAgencia()+"	"+getConta()+"\n"+
				p+"\n"+
				getSalContInss()+"	"+getBaseCalcFgts()+"	"+getValorFgts()+"	"+getBaseCalcIrrf()+"	"+getDepIrrf()+"\n"+
				getTotalProventos()+"	"+getTotalDescontos()+"	"+getTotalLiquido()+"\n"+
				getMensagem();
		
	}
	
}
