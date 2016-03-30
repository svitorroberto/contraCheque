package managedBeans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "inputTextAreaMB")
public class InputTextAreaMB {
	public static String msg;
	
	public void saveArea(String query) {
		msg=query;
	}

}
