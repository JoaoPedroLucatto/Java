package remotedesksimulator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JLabel;
import org.json.*;

public class ArquiteturaSocket implements Runnable{
    private ArquivoConexao conf;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;
    private JSONObject jsonObject = new JSONObject();
    private int temp;
    private int umid;
    private static int countEnvio;
    private static int countRetorno;
    private long tempoInicio;
    private long limitExecucao;
    private JLabel enviolabel;
    private JLabel retornolabel;
    public ArquiteturaSocket(ArquivoConexao conf, long limitExecucao, JLabel enviolabel, JLabel retornolabel){
        this.conf = conf;
        this.limitExecucao = limitExecucao;
        this.enviolabel = enviolabel;
        this.retornolabel = retornolabel;
    }
    public static void inicializar() {
      countEnvio = 0;
      countRetorno = 0;
    }
    private static synchronized void incEnvio() {
      countEnvio++;
    }
    private static synchronized void incRetorno() {
      countRetorno++;
    }
    @Override
    public void run(){
        conectaServidor();
        tempoInicio = System.currentTimeMillis();
        while(true){
            if((System.currentTimeMillis() - tempoInicio) > limitExecucao){
                System.out.println("PAROU!!!");
                break;
            }
            String jsonEnvio=geradorJson();
            System.out.println("JsonEnvio: "+ jsonEnvio);
            String jsonOut=enviaDados(jsonEnvio);
            incEnvio();
            if(jsonOut!=null){
               incRetorno();
            }
            System.out.println("JsonRetorno: "+jsonOut);
        }
        enviaDados("encerrar");
        encerrar();
        enviolabel.setText(Integer.toString(countEnvio));
        retornolabel.setText(Integer.toString(countRetorno));
    }
    public void conectaServidor(){
        try {
            System.out.println("O IP:" + conf.getIp());
            socket = new Socket(conf.getIp(), Integer.parseInt(conf.getPorta()));
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (Exception ex) {
            System.out.println("Erro Socket: "+ex);
        }
    }
    public void encerrar(){
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Erro ao encerra o socket: "+ex);
        }
    }  
    public String enviaDados(String json){
        String jsonIn = null;
        try {
            output.writeObject(json);
            jsonIn=(String)input.readObject();
        } catch (Exception ex) {
            System.out.println("Erro ao envio json: "+ex);
        }
        return jsonIn;
    }
    public String geradorJson(){
        String jsonenvio = null;
        int umid = 3;
        int temp = 2;
        try {
            jsonObject.put("umidade", umid);
            jsonObject.put("cnpj", conf.getCnpj());
            jsonObject.put("temperatura", temp);
            jsonenvio = jsonObject.toString();
            System.out.println("JSON CRIADO: "+jsonenvio);
        } catch (JSONException ex) {
            System.out.println("Erro no JSON: "+ex);
        }
        return jsonenvio;
    }   
}
