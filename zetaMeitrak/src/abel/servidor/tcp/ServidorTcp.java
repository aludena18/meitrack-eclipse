package abel.servidor.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import abel.database.sql.InsertData;

public class ServidorTcp extends Thread{
	ServerSocket servidor = null;
	String[] datoParseado = new String[1024];
	
	
	public ServidorTcp(int port){
		int puerto = port;
		try {
			/*SE CREA EL SOCKET PARA EL SERVIDOR*/
			servidor = new ServerSocket(puerto);
			System.out.println("SERVIDOR TCP PARA MEITRACK INICIADO");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			try {
				/*PARA RECIBIR MENSAJES DE UN CLIENTE*/
				Socket cliente = servidor.accept();
				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				String msjRx = entrada.readLine().trim();
				
				ParserTrama parserTrama = new ParserTrama(msjRx);
				datoParseado = parserTrama.getParserFrame();
				
				
				System.out.println("RECIBIDO : " + msjRx);
				for (int i=0; i<datoParseado.length; i++) System.out.println("PARSEO : " + datoParseado[i]);
				
				/*INSERTAR DATOS EN LA BASE DE DATOS*/
				InsertData insertar = new InsertData();
				insertar.inertInto(datoParseado);
				
				/*PARA RESPONDER CON UN MENSAJE AL CLIENTE*/
				DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
				salida.writeUTF(msjRx);
				salida.close();
				
				/*CERRAR LA CONEXION*/
				cliente.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
