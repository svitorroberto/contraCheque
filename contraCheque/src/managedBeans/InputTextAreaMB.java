package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Mensagem;

@ManagedBean(name = "inputTextAreaMB")
public class InputTextAreaMB {
	Mensagem m = new Mensagem();
	private String msg;
	
	//GRAVAR OBJETO NA SESSÃO
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			
	
	public void salvarMensagem(){
		m.setMsg(msg);
		session.setAttribute("MENSAGEM", m);
	}
	
	
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}



	public void saveArea(String query) {
		msg=query;
	}

}
