package abel.servidor.tcp;

public class ParserTrama {
	String trama;
	//char dataTrama[] = new char[1024];
	String dato[] = new String[1024];

	public ParserTrama(String frame){
		trama = frame;
	}
	
	public String[] getParserFrame(){
		
		/*IDENTIFICA SI LA TRAMA ES DEL PROTOCOLO DE MEITRACK*/
		if(trama.startsWith("$$")){
			for(int i=0; i<dato.length; i++)dato[i] = "";
			dato = trama.split(",");
		}
		return dato;
	}
}
