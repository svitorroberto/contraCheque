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
	
	private ArrayList<Provento> proventos = new ArrayList<Provento>();
	
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

	public ArrayList<Provento> getProventos() {
		return proventos;
	}

	public String getSalContInss() {
		return salContInss;
	}

	public void setSalContInss(double salContInss) {
		this.salContInss = nf.format(salContInss);
	}

	public String getBaseCalcFgts() {
		return baseCalcFgts;
	}

	public void setBaseCalcFgts(double baseCalcFgts) {
		this.baseCalcFgts = nf.format(baseCalcFgts);
	}

	public String getValorFgts() {
		return valorFgts;
	}

	public void setValorFgts(double valorFgts) {
		this.valorFgts = nf.format(valorFgts);
	}

	public String getBaseCalcIrrf() {
		return baseCalcIrrf;
	}

	public void setBaseCalcIrrf(double baseCalcIrrf) {
		this.baseCalcIrrf = nf.format(baseCalcIrrf);
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
		this.totalProventos = nf.format(totalProventos);
	}

	public String getTotalDescontos() {
		return totalDescontos;
	}

	public void setTotalDescontos(double totalDescontos) {
		this.totalDescontos = nf.format(totalDescontos);
	}

	public String getTotalLiquido() {
		return totalLiquido;
	}

	public void setTotalLiquido(double totalLiquido) {
		this.totalLiquido = nf.format(totalLiquido);
	}

	public void setProventos(ArrayList<Provento> proventos) {
		this.proventos = proventos;
	}
	
	
	@Override
	public String toString() {
		String p ="";
		for(Provento c : proventos) {  
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
