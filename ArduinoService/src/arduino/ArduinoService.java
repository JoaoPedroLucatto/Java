package arduino;

public class ArduinoService  {
    public static void main(String[] args) {
        ConfArduinoService conf = new ConfArduinoService();
        Thread t = new Thread(new Monitor(conf));
        t.start();
    }
}
