package remotedesksimulator;

import javax.swing.JOptionPane;

public class RemoteDeskSimulator extends javax.swing.JFrame {
    private ArquivoConexao conf = new ArquivoConexao();
    private int numeroThreads;
    private Thread[] t;
    
    public RemoteDeskSimulator() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Arquitetura1jRadioButton = new javax.swing.JRadioButton();
        Arquitetura2jRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NumeroThreadsjTextField = new javax.swing.JTextField();
        TempoSjTextField = new javax.swing.JTextField();
        IniciarjButton = new javax.swing.JButton();
        CancelarjButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ResultadoEnviojLabel = new javax.swing.JLabel();
        RetornoEnviojLabel = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RemoteDesk | Simulator");
        setResizable(false);

        Arquitetura1jRadioButton.setSelected(true);
        Arquitetura1jRadioButton.setText("Arquitetura1 - Socket");
        Arquitetura1jRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Arquitetura1jRadioButtonActionPerformed(evt);
            }
        });

        Arquitetura2jRadioButton.setText("Arquitetura2 - HTTP");
        Arquitetura2jRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Arquitetura2jRadioButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Número de Threads: ");

        jLabel2.setText("Tempo(s):");

        TempoSjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TempoSjTextFieldActionPerformed(evt);
            }
        });

        IniciarjButton.setText("Iniciar");
        IniciarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarjButtonActionPerformed(evt);
            }
        });

        CancelarjButton.setText("Cancelar");
        CancelarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarjButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Enviados:");

        jLabel4.setText("Retorno:");

        jMenu3.setText("Edit");

        jMenuItem1.setText("Conexão");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(CancelarjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(IniciarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TempoSjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(NumeroThreadsjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Arquitetura1jRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(Arquitetura2jRadioButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ResultadoEnviojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RetornoEnviojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Arquitetura1jRadioButton)
                    .addComponent(Arquitetura2jRadioButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NumeroThreadsjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TempoSjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IniciarjButton)
                    .addComponent(CancelarjButton))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ResultadoEnviojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RetornoEnviojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IniciarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarjButtonActionPerformed
        controller();
    }//GEN-LAST:event_IniciarjButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new ArquivoConexao().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void TempoSjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TempoSjTextFieldActionPerformed
        
    }//GEN-LAST:event_TempoSjTextFieldActionPerformed
   
    private void Arquitetura2jRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Arquitetura2jRadioButtonActionPerformed
        if(Arquitetura2jRadioButton.isSelected()){
            Arquitetura1jRadioButton.setSelected(false);
        }
    }//GEN-LAST:event_Arquitetura2jRadioButtonActionPerformed

    private void Arquitetura1jRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Arquitetura1jRadioButtonActionPerformed
        if(Arquitetura1jRadioButton.isSelected()){
            Arquitetura2jRadioButton.setSelected(false);
        }
    }//GEN-LAST:event_Arquitetura1jRadioButtonActionPerformed

    private void CancelarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarjButtonActionPerformed
        finalizar();
    }//GEN-LAST:event_CancelarjButtonActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RemoteDeskSimulator().setVisible(true);        
            }
        });
    }
  
    public void controller(){
        if(NumeroThreadsjTextField.getText().length()>0 && TempoSjTextField.getText().length()>0){
            System.out.println("Entrou");
            numeroThreads = Integer.parseInt(NumeroThreadsjTextField.getText());
            long limitExecucao = Integer.parseInt(TempoSjTextField.getText()) *1000;
            if(Arquitetura1jRadioButton.isSelected()){
                ArquiteturaSocket.inicializar();
                Thread[] t = new Thread[numeroThreads];
                for(int i = 0; i < numeroThreads; i++){
                   t[i] = new Thread(new ArquiteturaSocket(conf, limitExecucao, ResultadoEnviojLabel, RetornoEnviojLabel));
                   t[i].start();
                }
                for(int j = 0; j < numeroThreads; j++){
                    try {
                        t[j].join();
                    } catch (InterruptedException ex) {
                        System.out.println("Erro Threads: "+ex);
                    }
                }
            }
            else if(Arquitetura2jRadioButton.isSelected()){
                ArquiteturaHTTP.inicializar();
                 t = new Thread[numeroThreads];
                for(int i = 0; i < numeroThreads; i++){
                   t[i] = new Thread(new ArquiteturaHTTP(conf, limitExecucao, ResultadoEnviojLabel, RetornoEnviojLabel));
                   t[i].start();
                }
                for(int j = 0; j < numeroThreads; j++){
                    try {
                        t[j].join();
                    } catch (InterruptedException ex) {
                        System.out.println("Erro Threads: "+ex);
                    }
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Preencha os Campos!" , "Alerta!", 2);
        }
    }
    public void finalizar(){
        for(int i=0; i<numeroThreads; i++){
            t[i].stop();
        }
        System.exit(0);
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Arquitetura1jRadioButton;
    private javax.swing.JRadioButton Arquitetura2jRadioButton;
    private javax.swing.JButton CancelarjButton;
    private javax.swing.JButton IniciarjButton;
    private javax.swing.JTextField NumeroThreadsjTextField;
    private javax.swing.JLabel ResultadoEnviojLabel;
    private javax.swing.JLabel RetornoEnviojLabel;
    private javax.swing.JTextField TempoSjTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables

}
