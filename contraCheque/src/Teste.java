import java.io.File;
import java.net.URL;


public class Teste {

	public static void main(String[] args) {
		File file=null;
		try {
			URL url = Teste.class.getResource("//WebContent//resources//caminhos.txt");
			file = new File("/WebContent//resources//caminhos.txt");
			}
			finally{
				
			}
			System.out.println(file.getAbsolutePath());
	}

}
