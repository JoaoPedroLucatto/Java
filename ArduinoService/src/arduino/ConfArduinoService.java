package arduino;

import java.io.FileInputStream;
import java.util.Properties;
import com.fazecast.jSerialComm.SerialPort;
import java.io.FileOutputStream;
import org.json.JSONObject;

public class ConfArduinoService {
    private String ip;
    private int port;
    private String serial;
    private int intervalo;
    private String cnpj;
    
    private String arquivo = "C:\\Firedesk\\Conf\\Conf.ini";
    private Properties properties = new Properties();
    private SerialPort[] ports = SerialPort.getCommPorts();
    
    public String getIp() {
        return ip;
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
            ip = properties.getProperty("IP");
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
            JSONObject jsonrecon = new JSONObject(jsonIn);
            if(jsonrecon.getString("mensagem").equals("edit")){
                properties.setProperty("Intervalo", Integer.toString(jsonrecon.getInt("intervalo")));
                properties.setProperty("TempMin", Integer.toString(jsonrecon.getInt("tempmin")));
                properties.setProperty("TempMax", Integer.toString(jsonrecon.getInt("tempmax")));
                properties.setProperty("UmidadeMin", Integer.toString(jsonrecon.getInt("umidambmin")));
                properties.setProperty("UmidadeMax", Integer.toString(jsonrecon.getInt("umidambmax")));
                properties.store(new FileOutputStream(arquivo),"ARDUINO");
                properties.clone();
            }
            else{
                return;
            }
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
