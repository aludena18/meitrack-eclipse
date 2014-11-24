package abel.test.aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteVentanaTCP extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblServerIp;
	private JLabel lblServerPort;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteVentanaTCP frame = new ClienteVentanaTCP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClienteVentanaTCP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setBounds(10, 57, 299, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("192.168.0.100");
		textField_1.setBounds(80, 8, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField("2020");
		textField_2.setBounds(80, 29, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		final JLabel lblConsola = new JLabel("Consola");
		lblConsola.setBounds(10, 88, 299, 14);
		contentPane.add(lblConsola);
		
		lblServerIp = new JLabel("Server IP :");
		lblServerIp.setBounds(10, 11, 60, 14);
		contentPane.add(lblServerIp);
		
		lblServerPort = new JLabel("Server Port:");
		lblServerPort.setBounds(10, 32, 60, 14);
		contentPane.add(lblServerPort);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String SERVER_IP = textField_1.getText().trim();
				int SERVER_PORT = Integer.parseInt(textField_2.getText().trim());
				
				try {
					/*CREA EL SOCKET PARA LA CONEXION CON EL SERVIDOR*/
					Socket servidor = new Socket(SERVER_IP, SERVER_PORT);

					/*ENVIAR MENSAJES AL SERVIDOR*/
					PrintWriter salida = new PrintWriter(servidor.getOutputStream(),true);
					String msjTx = textField.getText().trim();
					salida.println(msjTx);
					System.out.println("ENVIADO : " + msjTx);
					
					/*RECIBE MENSAJE DEL SERVIDOR*/
					BufferedReader entrada = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
					String msjRx = entrada.readLine();
					msjRx = msjRx.trim();
					
					/*CERRANDO SOCKET*/
					servidor.close();
					
					textField.setText("");
					lblConsola.setText(msjRx);
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnEnviar.setBounds(335, 56, 89, 23);
		contentPane.add(btnEnviar);
		
	}
}
