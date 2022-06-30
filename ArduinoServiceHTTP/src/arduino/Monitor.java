package arduino;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class Monitor implements Runnable {
    private ArduinoSerial arduino;
    private ConfArduinoService conf;
    private OutputStream output;
    private OutputStreamWriter outputw;
    private BufferedReader bufferead;
    private StringBuilder response;
    private HttpURLConnection con;
    private JSONObject jsonObject;
    
    public Monitor(ConfArduinoService conf) {
        this.conf = conf;
    }
    
    public void run() {
        try {
            conectaArduino();
            conectaUrl();
            while (true) {
                String json = leSensores();
                JSONObject jsonObject = new JSONObject(json);
                String cnpj=conf.getCnpj();
                jsonObject.put("cnpj", cnpj);
                String jsonenvio=jsonObject.toString();
                String jsonIn = (String)requisicaoHTTP(jsonenvio);
                conf.atualizaConfiguracoes(jsonIn);
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
    
    private void conectaUrl(){
        try {
            URL url = new URL(conf.getUrl());
            
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);            
            con.connect();
            
        } catch (Exception ex) {
            System.out.println("Erro ao Conectar na URL: "+ex);
        }
           
    }
    private String requisicaoHTTP(String json) {
        json="json="+json;
        try {
            
            output = con.getOutputStream();
            outputw = new OutputStreamWriter(output, "UTF-8");
            outputw.write(json.toString());
            outputw.flush();
            outputw.close();
            System.out.println("Envio: "+json);
            
            bufferead = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));  
            response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = bufferead.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Response: "+response.toString());
            return response.toString();
                     
        } catch (Exception ex) {
            System.out.println("Erro envio HTTP: " +ex);
            return null;
        }
    }
}
