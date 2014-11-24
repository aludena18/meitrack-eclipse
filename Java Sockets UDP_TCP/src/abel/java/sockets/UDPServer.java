package abel.java.sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
class UDPServer
{
	public static void main(String args[]) throws Exception {
		
		/*CREA LAS VARIABLES*/
		DatagramPacket receivePacket;
		byte[] receive = new byte[1024];
		byte[] send = new byte[1024];
		
		System.out.println("Servidor UDP iniciado");
		
		/*CREA EL SOCKET UDP*/
		DatagramSocket serverSocket = new DatagramSocket(2015);
		
		while(true)
		{
			/*PARA RECIBIR MENSAJES DESDE UN CLIENTE*/
			receivePacket = new DatagramPacket(receive, receive.length);
			serverSocket.receive(receivePacket);
			String sentence = new String( receivePacket.getData());
			
			System.out.println("RECEIVED: " + sentence);
			
			/*PARA RESPONDER AL CLIENTE CON UN MENSAJE*/
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			send = sentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(send, send.length, IPAddress, port);
			serverSocket.send(sendPacket);
			
			System.out.println(IPAddress + " : " + port);
		}
		
	}
}
