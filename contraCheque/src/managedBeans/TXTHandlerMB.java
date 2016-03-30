package managedBeans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Caminho;
import model.Caminhos;

@ManagedBean(name = "TXTHandlerMB")
@ViewScoped
public class TXTHandlerMB {
	
	private int option;
	private String caminho;
	private static File file = new File("C://Program Files (x86)//Apache Software Foundation//Tomcat 8.0//caminhos.txt");
//	private static File file = new File("D:\\teste\\caminhos.txt");
	public ArrayList<Caminho> caminhos = new ArrayList<Caminho>();
	
	
	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}
	
	public ArrayList<Caminho> getCaminhos() {
		return caminhos;
	}
	
	public void lerTXT(){ // lê o txt e guarda as Strings em variáveis
		
		System.out.printf("\nConteúdo do arquivo texto:\n");
		try {
			FileReader arq = new FileReader(file);
			BufferedReader lerArq = new BufferedReader(arq);
			
			Caminhos.dirCriacaoPDF = lerArq.readLine();// PDF SÃO CRIADOS AQUI 
			Caminhos.dirLogo = lerArq.readLine(); //OK
			Caminhos.dirDestinoTXT = lerArq.readLine();// MANAGED BEANS TRANSFERE PRA CÁ 
			Caminhos.dirDestinoPDFs = lerArq.readLine();// PASTA DO FTP ONDE FICAM AS SUBPASTAS DOS PDFs ##MODIFICÁVEL##
			
			caminhos.clear();
			caminhos.add(new Caminho("Criação dos PDFs->                   ", Caminhos.dirCriacaoPDF));
			caminhos.add(new Caminho("Diretório da Logo->                  ", Caminhos.dirLogo));
			caminhos.add(new Caminho("Destino do TXT->                     ", Caminhos.dirDestinoTXT));
			caminhos.add(new Caminho("Diretório de envio dos PDFs no FTP-> ", Caminhos.dirDestinoPDFs));
			
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",	e.getMessage());
		}
		
		
		System.out.println(Caminhos.dirCriacaoPDF);
		System.out.println(Caminhos.dirLogo);
		System.out.println(Caminhos.dirDestinoTXT);
		System.out.println(Caminhos.dirDestinoPDFs);
		System.out.println(caminhos.toString());
			
		}
		
		public void escreverTXT(){ //  Pega a opção selecionada e grava no txt as novas informações 
				BufferedWriter writer;
				lerTXT();
				try {
					writer = new BufferedWriter(new FileWriter(file));
					/*if(getOption() == 1){
						Caminhos.dirTransfPDF = getCaminho();
					}*/
					if(getOption() == 1){
						Caminhos.dirDestinoPDFs = getCaminho();
					}
					else{
						System.out.println("Nenhuma opção selecionada");
					}
					
				writer.write(Caminhos.dirCriacaoPDF);
				writer.newLine();
				writer.write(Caminhos.dirLogo);
				writer.newLine();
				writer.write(Caminhos.dirDestinoTXT);
				writer.newLine();
				writer.write(Caminhos.dirDestinoPDFs);
				
				writer.flush();
				writer.close();
				
				FacesMessage msg = new FacesMessage("Êxito!", "Caminho foi alterado com sucesso ("+getCaminho()+")");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		        
				} catch (IOException e) {
					e.printStackTrace();
					FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Erro ", e.getMessage());
		            FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
			}
	
	public ArrayList<Caminho> atualizarTable(){
		lerTXT();
		return caminhos;
		
	}
	public void atualizarTable2(){
		lerTXT();
		
		if(caminhos.isEmpty()){
			FacesMessage msg = new FacesMessage("Clique novamente", "!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
			FacesMessage msg = new FacesMessage("Siga para o passo 2", "!");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
}