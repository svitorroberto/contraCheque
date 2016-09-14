package managedBeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ftp.ConexaoFTP;
import main.PDFFactory;
import model.Caminhos;
import model.Funcionario;
import model.Mensagem;
import model.Provento2;

@ManagedBean(name = "arquivosMB")
public class ArquivosMB {
	

	static ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	static Funcionario f;
	static Provento2 p;
	public Funcionario f2;
	static String msg;
	static Mensagem m;

	//RECUPERA OBJETO GRAVADO NA SESSÃO
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
	
		
	public void recuperaMensagem(){
		m = (Mensagem) session.getAttribute("MENSAGEM");		
	}
		
	public static void gerarPDF(){

			FileUploadMB fmb = new FileUploadMB();
			String nome = fmb.getDestination()+"EXPORTA.TXT";
			
			try {
				FileReader arq = new FileReader(nome);
				BufferedReader lerArq = new BufferedReader(arq);
				String linha = lerArq.readLine();
				
				while (linha != null) {
					System.out.printf("%s\n", linha);
					
					int num = Integer.parseInt(linha.substring(0, 1)); // todo primeiro número é uma linha do documento
					if(num==1){
						
						f= new Funcionario();
						f.setMensagem(m.getMsg());
						f.setLotacao(Integer.parseInt(linha.substring(1, 6)));
						f.setDivisao(linha.substring(6, 46));
						f.setCargo(linha.substring(51, 91));
						f.setMatricula(Integer.parseInt(linha.substring(91, 98)));
						f.setNome(linha.substring(98, 138));
						f.setCbo(Integer.parseInt(linha.substring(138, 144)));
						f.setReferencia(linha.substring(144, 167));
						f.setCpf(linha.substring(167, 178));
						f.setCodBanco(Integer.parseInt(linha.substring(178, 182)));
						f.setAgencia(Integer.parseInt(linha.substring(182, 187)));
						f.setConta(linha.substring(187, 207));
						f.setSituacao(linha.substring(207, 208));
							switch (Integer.parseInt(f.getSituacao())){
							case 1: f.setSituacao("Celetista");
								break;
							case 2: f.setSituacao("Estatutário");
								break;
							case 3: f.setSituacao("Contrato Temporário");
								break;
							case 4: f.setSituacao("Nomeado em Comissão");
								break;
							case 5: f.setSituacao("Outros");
								break;
							}
								
								
					}
					if(num==2){
						p = new Provento2();
						p.setCodigo(Integer.parseInt(linha.substring(1, 5)));
						p.setDescricao(linha.substring(5, 45));
						p.setAliquota((Double.parseDouble(linha.substring(45, 52))/100));
						p.setVencimentos((Double.parseDouble(linha.substring(56, 67))/100));
						p.setDescontos(0.0);
						f.getProventos().add(p);
						
					}
					if(num==3){
						//System.out.println(linha.substring(52, 59));
						p = new Provento2();
						p.setCodigo(Integer.parseInt(linha.substring(1, 5)));
						p.setDescricao(linha.substring(5, 45));
						p.setAliquota((Double.parseDouble(linha.substring(45, 52))/100));
						p.setVencimentos((Double.parseDouble(linha.substring(52, 59))/100));
						p.setDescontos((Double.parseDouble(linha.substring(59, 67))/100));
						f.getProventos().add(p);
						
					}
					if(num==4){ // CASO OCORRA ERRO EM SAL CONT INSS VERIFICAR SUBSTRING 0-16 (salário)
						f.setSalContInss((Double.parseDouble(linha.substring(16,31))/100));		//OK
						f.setBaseCalcFgts((Double.parseDouble(linha.substring(31,46))/100));	//OK
						f.setValorFgts((Double.parseDouble(linha.substring(46,61))/100));		//OK
						f.setBaseCalcIrrf((Double.parseDouble(linha.substring(61,76))/100));	//OK
						f.setDepIrrf((Integer.parseInt(linha.substring(76,80))/100));			//OK
						
					}
					if(num==5){
						f.setTotalProventos((Double.parseDouble(linha.substring(1,16))/100));
						f.setTotalDescontos((Double.parseDouble(linha.substring(16,31))/100));
						f.setTotalLiquido((Double.parseDouble(linha.substring(31,46))/100));
						System.out.println(f.getTotalProventos()+" "+f.getTotalDescontos()+" "+f.getTotalLiquido());
						funcionarios.add(f);
						System.out.println(f.getNome()+" adicionado");
						
					}
					
					linha = lerArq.readLine(); // lê da segunda até a última linha
					
			}
					
					
		
				arq.close();
		//		f.setMensagem(InputTextAreaMB.msg);
				msg=null;
				System.out.println("\n\n\n");
				
				
			} catch (IOException e) {
				System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro na abertura do arquivo: %s", e.getMessage());  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		
		// SEGUNDA PARTE
			
			
			System.out.println(funcionarios.size());
			PDFFactory pf = new PDFFactory();
			try {
				for(Funcionario c : funcionarios) {
					// PASSA O OBJETO FUNCIONARIO COMO PARAMETRO PARA CRIAR O PDF
					pf.fabricarPdf(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro: %s", e.getMessage());  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
			FacesMessage msg = new FacesMessage("Todos","PDFs gerados com sucesso em"+Caminhos.dirCriacaoPDF);  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		    
		
	}
	
	public static void gerarEPublicarPDF(){
		
		//		Caminhos.lerTXT();
				FileUploadMB fmb = new FileUploadMB();
				String nome = fmb.getDestination()+"EXPORTA.TXT";
				//
				
				try {
					FileReader arq = new FileReader(nome);
					BufferedReader lerArq = new BufferedReader(arq);
					String linha = lerArq.readLine();
					
					while (linha != null) {
						System.out.printf("%s\n", linha);
						
						int num = Integer.parseInt(linha.substring(0, 1)); // todo primeiro número é uma linha do documento
						if(num==1){
							
							f= new Funcionario();
							f.setLotacao(Integer.parseInt(linha.substring(1, 6)));
							f.setDivisao(linha.substring(6, 46));
							f.setCargo(linha.substring(51, 91));
							f.setMatricula(Integer.parseInt(linha.substring(91, 98)));
							f.setNome(linha.substring(98, 138));
							f.setCbo(Integer.parseInt(linha.substring(138, 144)));
							f.setReferencia(linha.substring(144, 167));
							f.setCpf(linha.substring(167, 178));
							f.setCodBanco(Integer.parseInt(linha.substring(178, 182)));
							f.setAgencia(Integer.parseInt(linha.substring(182, 187)));
							f.setConta(linha.substring(187, 207));
							f.setSituacao(linha.substring(207, 208));
								switch (Integer.parseInt(f.getSituacao())){
								case 1: f.setSituacao("Celetista");
									break;
								case 2: f.setSituacao("Estatutário");
									break;
								case 3: f.setSituacao("Contrato Temporário");
									break;
								case 4: f.setSituacao("Nomeado em Comissão");
									break;
								case 5: f.setSituacao("Outros");
									break;
								}
									
									
						}
						if(num==2){
							p = new Provento2();
							p.setCodigo(Integer.parseInt(linha.substring(1, 5)));
							p.setDescricao(linha.substring(5, 45));
							p.setAliquota((Double.parseDouble(linha.substring(45, 52))/100));
							p.setVencimentos((Double.parseDouble(linha.substring(56, 67))/100));
							p.setDescontos(0.0);
							f.getProventos().add(p);
							
						}
						if(num==3){
							//System.out.println(linha.substring(52, 59));
							p = new Provento2();
							p.setCodigo(Integer.parseInt(linha.substring(1, 5)));
							p.setDescricao(linha.substring(5, 45));
							p.setAliquota((Double.parseDouble(linha.substring(45, 52))/100));
							p.setVencimentos((Double.parseDouble(linha.substring(52, 59))/100));
							p.setDescontos((Double.parseDouble(linha.substring(59, 67))/100));
							f.getProventos().add(p);
							
						}
						if(num==4){ // CASO OCORRA ERRO EM SAL CONT INSS VERIFICAR SUBSTRING 0-16 (salário)
							f.setSalContInss((Double.parseDouble(linha.substring(16,31))/100));		//OK
							f.setBaseCalcFgts((Double.parseDouble(linha.substring(31,46))/100));	//OK
							f.setValorFgts((Double.parseDouble(linha.substring(46,61))/100));		//OK
							f.setBaseCalcIrrf((Double.parseDouble(linha.substring(61,76))/100));	//OK
							f.setDepIrrf((Integer.parseInt(linha.substring(76,80))/100));			//OK
							
						}
						if(num==5){
							f.setTotalProventos((Double.parseDouble(linha.substring(1,16))/100));
							f.setTotalDescontos((Double.parseDouble(linha.substring(16,31))/100));
							f.setTotalLiquido((Double.parseDouble(linha.substring(31,46))/100));
							System.out.println(f.getTotalProventos()+" "+f.getTotalDescontos()+" "+f.getTotalLiquido());
							funcionarios.add(f);
							System.out.println(f.getNome()+" adicionado");
							
						}
						
						linha = lerArq.readLine(); // lê da segunda até a última linha
						
				}
						
						
			
					arq.close();
					msg=null;
					System.out.println("\n\n\n");
					
					
				} catch (IOException e) {
					System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
					FacesMessage msg = new FacesMessage("Erro na abertura do arquivo: %s", e.getMessage()+"");  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			
			// SEGUNDA PARTE
				
				
				System.out.println(funcionarios.size());
				PDFFactory pf = new PDFFactory();
				try {
					for(Funcionario c : funcionarios) {
						pf.fabricarPdf(c);
					}
				} catch (Exception e) {
					e.printStackTrace();
					FacesMessage msg = new FacesMessage("Erro: %s", e.getMessage());  
			        FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
				FacesMessage msg = new FacesMessage("Todos","PDFs gerados com sucesso em "+Caminhos.dirCriacaoPDF);  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		        
		        //ENVIAR ARQUIVOS PARA O FTP
		        
		        try {
					ConexaoFTP.EnviarArquivos();
					FacesMessage msg2 = new FacesMessage("todos","PDFs Enviados com sucesso para "+Caminhos.dirDestinoPDFs);  
			        FacesContext.getCurrentInstance().addMessage(null, msg2);
					
				} catch (IOException e) {
					FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,""+e.getMessage(),"");  
			        FacesContext.getCurrentInstance().addMessage(null, msg2);
					e.printStackTrace();
				}
	}
	
	public static int qtdPdf(){
        File diretorio = new File(Caminhos.dirCriacaoPDF+PDFFactory.sufixo);
        
        File[] pdfs = diretorio.listFiles();
        
        return pdfs.length;

	}
	
	public void salvarMensagem(){
		session.setAttribute("MENSAGEM", m);
		FacesMessage msg2 = new FacesMessage("Pronto","Mensagem salva! "+m.getMsg());  
        FacesContext.getCurrentInstance().addMessage(null, msg2);		
	}

	
	public Funcionario getF2() {
		return f2;
	}

	public void setF2(Funcionario f2) {
		this.f2 = f2;
		msg = f2.getMensagem();
	}
	

}
