package arduino;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class Monitor implements Runnable {
    private ArduinoSerial arduino;
    private ConfArduinoService conf;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    public Monitor(ConfArduinoService conf) {
        this.conf = conf;
    }
    public void run() {
        try {
            conectaArduino();
            conectaServidor();
            while (true) {
                String json = leSensores();
                JSONObject jsonObject = new JSONObject(json);
                String cnpj=conf.getCnpj();
                jsonObject.put("cnpj", cnpj);
                String jsonenvio=jsonObject.toString();
                String jsonIn = (String)enviaDados(jsonenvio);
                conf.atualizaConfiguracoes(jsonIn);
                //reconfigurar(jsonIn);
                Thread.sleep(conf.getIntervalo() * 1000);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void conectaArduino() {
        try {
            arduino = new ArduinoSerial(conf.getSerial());
            arduino.initialize();
            Thread.sleep(2000);
            System.out.println("Arduino inicializado");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void conectaServidor() {
        try {
            Socket socket = new Socket(conf.getIp(), conf.getPort());
            System.out.println("Conectado ao servidor WebSocket API");
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private String leSensores() {
        try {
            arduino.send("R");
            String json = null;
            while (json == null) {
                json = arduino.read();
                Thread.sleep(50);
            }
            return json;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
    
    private String enviaDados(String json) {
        try {
            output.writeObject(json);
            String jsonIn = (String)input.readObject();
            System.out.println(jsonIn);
            return jsonIn;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
    
    private void reconfigurar() {       
    }
}
