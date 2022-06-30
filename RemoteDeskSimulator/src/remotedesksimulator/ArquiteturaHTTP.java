package remotedesksimulator;

import javax.swing.JLabel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class ArquiteturaHTTP implements Runnable{
   private JSONObject jsonObject = new JSONObject();
   private ArquivoConexao conf;
   private OutputStream output;
   private OutputStreamWriter outputw;
   private BufferedReader bufferead;
   private StringBuilder response;
   private HttpURLConnection con;
   private long tempoInicio;
   private long limitExecucao;
   private JLabel enviolabel;
   private JLabel retornolabel;
   private static int countEnvio;
   private static int countRetorno;
   
    public ArquiteturaHTTP(ArquivoConexao conf, long limitExecucao, JLabel enviolabel, JLabel retornolabel){
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
    
    public void run(){
        conectaUrl();
        tempoInicio = System.currentTimeMillis();
        while(true){
            if((System.currentTimeMillis() - tempoInicio) > limitExecucao){
                System.out.println("PAROU!!!!!");
                break;
            }
            String jsonEnvio=geradorJson();
            System.out.println("JsonEnvio: "+ jsonEnvio);
            String jsonOut=requisicaoHTTP(jsonEnvio);
            incEnvio();
            if(jsonOut!=null){
                incRetorno();
            }
            System.out.println("JsonRetorno: "+jsonOut);
        }
        enviolabel.setText(Integer.toString(countEnvio));
        retornolabel.setText(Integer.toString(countRetorno));
        //timeoutlabel.setText(Integer.toString((countEnvio - countRetorno)));
    }
    
    public String geradorJson(){
        String jsonenvio = null;
        int temp = 2;
        int umid = 3;
       try {
           jsonObject.put("umidade", umid);
           jsonObject.put("cnpj", conf.getCnpj());
           jsonObject.put("temperatura", temp);
           jsonenvio = jsonObject.toString();
       } catch (JSONException ex) {
           System.out.println("Erro GeradodorJSON: "+ex);
       }
       return jsonenvio;
    }
    
    public void conectaUrl(){
       try {
           URL url = new URL(conf.getUrl());
           
           con = (HttpURLConnection)url.openConnection();
           con.setRequestMethod("POST");
           con.setDoInput(true);
           con.setDoOutput(true);
           con.connect();
           
       } catch (Exception ex) {
           System.out.println("Erro ao conectar na URL:" +ex);
       }
    }
    
    public String requisicaoHTTP(String json){
        json = "json="+json;
       try {
           
           output = con.getOutputStream();
           outputw = new OutputStreamWriter(output, "UTF-8");
           outputw.write(json.toString());
           outputw.flush();
           outputw.close();
           
           bufferead = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
           response = new StringBuilder();
           String reponseLine =null;
           while((reponseLine = bufferead.readLine())!=null){
               response.append(reponseLine.trim());
           }
           
       } catch (Exception ex) {
           System.out.println("ERRO HTTP: "+ex);
       }
       return response.toString();
    }
}
