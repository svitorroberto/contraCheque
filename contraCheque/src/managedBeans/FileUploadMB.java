package managedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Caminhos;

import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "fileUploadMB")
@RequestScoped
public class FileUploadMB {
	private String destination= Caminhos.dirDestinoTXT;
	
	
	public String getDestination() {
		return destination;
	}

	public FileUploadMB() {
	}

	public void doUpload(FileUploadEvent event) { 
		FacesMessage msg = new FacesMessage("Êxito! ", event.getFile().getFileName() + " foi selecionado com sucesso");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        TXTHandlerMB th = new TXTHandlerMB();
        th.atualizarTable();
        th.atualizarTable();
        th.atualizarTable();
        String nome = destination+"EXPORTA.TXT"; 
        File f = new File(nome); 
        f.delete();
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Erro ", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
           
        }
	}
	
	public void copyFile(String fileName, InputStream in) {
        try {
           
           
             // write the inputStream to a FileOutputStream
             OutputStream out = new FileOutputStream(new File(destination + fileName));
             
             int read = 0;
             byte[] bytes = new byte[1024];
           
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
           
             in.close();
             out.flush();
             out.close();
           
             System.out.println("TXT enviado com sucesso!");
             } catch (IOException e) {
            	 System.out.println(e.getMessage());
            	 FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Erro ", e.getMessage());
            	 FacesContext.getCurrentInstance().addMessage(null, msg);
             }
 }
}