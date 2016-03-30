package ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import main.PDFFactory;
import model.Caminhos;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class ConexaoFTP {

	
	
	public static void EnviarArquivos() throws IOException{
		FTPClient ftp = new FTPClient();
		ftp.connect("ftp.contracheque.ceasa.go.gov.br");
		System.out.println("Conectou");
		ftp.login("ceasa", "_MRnhC3u");
		System.out.println("Autenticou");
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		File diretorioFonte = new File(Caminhos.dirCriacaoPDF+PDFFactory.sufixo);
		
		File[] pdfs = diretorioFonte.listFiles();
		int contador = 0;
		int num = pdfs.length;
		
		for (int i = 0; i < num; i++) {
			// PEGA OS NUMEROS DO CPF DO PDF E VERIFICA SE EXISTE ALGUMA PASTA COM ESSE NOME
			// SE NÃO HOUVER, ENTÃO CRIA A PASTA
			String[] nomeArquivo = pdfs[i].getName().split("_");
			File dir = new File(Caminhos.dirDestinoPDFs + nomeArquivo[0]);
			String dire = Caminhos.dirDestinoPDFs + nomeArquivo[0];
			if (!dir.isDirectory())
				ftp.makeDirectory(dire);
			ftp.changeWorkingDirectory(dire);
			// GRAVA O ARQUIVO NA DEVIDA PASTA
			InputStream in = new FileInputStream(pdfs[i]);
			
			if (ftp.storeFile(pdfs[i].getName(), in)){
				System.out.println("Arquivo " + pdfs[i].getName()+ " armazenado com sucesso!");
				contador+=1;
				FacesMessage msg = new FacesMessage(contador+" de "+num+"Enviado.	", pdfs[i].getName());  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
				
				
			}
			else{
				System.out.println("Erro ao armazenar o arquivo "+ pdfs[i].getName());
			}
		}
		
		if(contador==num)
			System.out.println("Todos os arquivos copiados");
		else
			System.out.println("Erro! "+(num-contador)+" arquivo(s) falharam!");
		ftp.disconnect();
	}
	
	
	
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
//		FTPClient ftp = new FTPClient();
//		
//		try {
//			ftp.connect("ftp.contracheque.ceasa.go.gov.br");
//			System.out.println("Conectou");
//			ftp.login("ceasa", "_MRnhC3u");
//			System.out.println("Autenticou");
//			ftp.setFileType(FTP.BINARY_FILE_TYPE);
//			String caminho = "Z:\\Contracheque\\Contracheque\\contracheque_NOVEMBRO-2015\\";
//			String arquivo = "03547784123_NOVEMBRO-2015.pdf";
//			File myFile = new File(caminho + arquivo);
//			InputStream is = new FileInputStream( myFile.getAbsolutePath() );
//			System.out.println(myFile.getAbsolutePath());
//			if(ftp.deleteFile("03547784123_NOVEMBRO-2015.pdf")){
//				System.out.println("Deletou");
//			}
//			else{
//				System.out.println("Não deletou");
//			}
//			ftp.changeWorkingDirectory("//contraCheque//03547784123");
//			System.out.println();
//			ftp.storeFile(myFile.getName(), is);
//			System.out.println("Gravou");
//			
//		}catch(Exception e){
//			ftp.disconnect();
//            System.out.println("Conexão recusada");
//            System.exit(0);
//     }
		
}
	}
