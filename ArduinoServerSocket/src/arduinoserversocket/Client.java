
package arduinoserversocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
    private final Socket socket;
    private final Postgresql postgre;
    public Client(Socket socket, Postgresql postgre) {
        this.socket = socket;
        this.postgre = postgre;
    }
    @Override
    public void run() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String jsonIn, jsonOut, retorno;
            while (true) {
                jsonIn = (String)input.readObject();
                if (jsonIn == "encerrar") break;
                System.out.println(jsonIn);
                retorno=postgre.executaSQL(jsonIn);
                jsonOut = retorno;
                output.writeObject(jsonOut);
            }
        } catch (Exception e) {
            System.out.println("Erro client: " + e.getMessage());
        }
        encerrar(); 
    } 
    public void encerrar(){
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Finalizar Socket: "+ ex);
        }
    }
}






