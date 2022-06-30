package arduino;

import java.io.FileInputStream;
import java.util.Properties;
import com.fazecast.jSerialComm.SerialPort;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.StringWriter;

public class ConfArduinoService {
    private String ip;
    private int port;
    private String serial;
    private int intervalo;
    private String cnpj;
    private String url;
    
    private String arquivo = "C:\\Firedesk\\Conf\\Conf.ini";
    private Properties properties = new Properties();
    private SerialPort[] ports = SerialPort.getCommPorts();
    
    public String getUrl(){
        return url;
    }
    
    public int getPort() {
        return port;
    }
    
    public String getSerial() {
        return serial;
    }
    
    public int getIntervalo() {
        return intervalo;
    }
    public String getCnpj(){
        return cnpj;
    }
    
    public ConfArduinoService() {
        try {
            properties.load(new FileInputStream(arquivo));
            localizaPortaSerial();
            url = properties.getProperty("URL");
            port = Integer.parseInt(properties.getProperty("Port"));
            cnpj=properties.getProperty("cnpj");
            serial =  properties.getProperty("Serial");
            intervalo = Integer.parseInt(properties.getProperty("Intervalo"));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void atualizaConfiguracoes(String jsonIn) {
        try{
            JSONObject jsonrecon = new JSONObject(new JSONObject(jsonIn).getString("message"));
                properties.setProperty("Intervalo", Integer.toString(jsonrecon.getInt("interval")));
                properties.setProperty("TempMin", Integer.toString(jsonrecon.getInt("tempmin")));
                properties.setProperty("TempMax", Integer.toString(jsonrecon.getInt("tempmax")));
                properties.setProperty("UmidadeMin", Integer.toString(jsonrecon.getInt("umidmin")));
                properties.setProperty("UmidadeMax", Integer.toString(jsonrecon.getInt("umidmax")));
                properties.store(new FileOutputStream(arquivo),"ARDUINO");
                properties.clone();
            
        } catch (Exception ex) {
            System.err.println("Metodo AtualizaConfigurações: "+ex);
        }
    }
    public void localizaPortaSerial(){ 
        String portserial=null;
        for(SerialPort port : ports){
          portserial =port.getSystemPortName();
        }
        properties.setProperty("Serial",portserial);
    }
}
