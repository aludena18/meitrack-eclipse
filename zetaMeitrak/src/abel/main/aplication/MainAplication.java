package abel.main.aplication;

import abel.servidor.tcp.ServidorTcp;

public class MainAplication {
	
	static final int SERVER_PORT = 2020;

	public static void main(String[] args) {
		
		/*SE INICIA EL HILO PARA EL SERVIDOR TCO ESCUCHA*/
		ServidorTcp hiloTCP = new ServidorTcp(SERVER_PORT);
		hiloTCP.start();
		
		
	}

}
