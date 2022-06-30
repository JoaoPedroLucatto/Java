
package arduinoserversocket;

import java.net.ServerSocket;
import java.net.Socket;

public class ArduinoServerSocket {
    public static void main(String[] args) {
        System.out.println("Servidor executando na porta 9000...");
        try {
            Postgresql postgre= new Postgresql();
            ServerSocket server = new ServerSocket(9000);
            
            while (true) {
                Socket socket = server.accept();
                Thread t = new Thread(new Client(socket, postgre));
                t.start();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}





