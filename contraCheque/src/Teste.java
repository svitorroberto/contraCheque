public class Teste {

	public static void main(String[] args) {
		
		String d = "0025780000"; 
		Double s = Double.parseDouble(d)/100;
		String d2 = String.format("%.2f", s);
		StringBuffer sb = new StringBuffer(d2);
		sb.insert(sb.length() - 6, ".");
		
		System.out.println(sb);
	}

}
