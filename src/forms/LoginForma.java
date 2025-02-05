/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;
import controller.*;
import domain.Korisnik;
import javax.swing.JOptionPane;
/**
 *
 * @author Anđela
 */
public class LoginForma extends javax.swing.JFrame {
    private Controller controller = Controller.getController();
    java.util.List<Korisnik> listaKorisnika = controller.getListaKorisnika();
    private final int brojPokusaja = 3;
    private int brojac = 0;
    /**
     * Creates new form LoginForma
     */
    public LoginForma() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jButtonPrijava = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prijava na sistem");

        jLabel1.setText("Email adresa:");

        jLabel2.setText("Password:");

        jButtonPrijava.setText("Prijava");
        jButtonPrijava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrijavaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPrijava))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                            .addComponent(jPasswordField))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jButtonPrijava)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrijavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrijavaActionPerformed
        // TODO add your handling code here:
        
        brojac++;
        int preostaliBrojPokusaja = brojPokusaja - brojac;
        
        
            String errorMessage = "";
            if(jTextFieldEmail.getText() == null || jTextFieldEmail.getText().isEmpty()){
                errorMessage += "Niste uneli email adresu \n";
            }
            if(String.valueOf(jPasswordField.getPassword()) == null || String.valueOf(jPasswordField.getPassword()).isEmpty()){
                errorMessage += "Niste uneli lozinku \n";
            }
            if(!errorMessage.isEmpty()){
                errorMessage += "Imate jos " + preostaliBrojPokusaja + " pokusaja \n";
                if(preostaliBrojPokusaja == 0){
                        JOptionPane.showMessageDialog(this, errorMessage, "Greska", JOptionPane.ERROR_MESSAGE);
                        this.dispose();
                        return;
                    }
                JOptionPane.showMessageDialog(this, errorMessage, "Greska", JOptionPane.ERROR_MESSAGE);
                
            }else{
                errorMessage = "";
                String pass = String.valueOf(jPasswordField.getPassword());
                String email = jTextFieldEmail.getText();
                if(proveriKorisnika(email, pass) == null){
                    errorMessage += "Korisnik sa ovim podacima nije pronadjen u sistemu \n";
                    errorMessage += "Imate jos " + preostaliBrojPokusaja + " pokusaja \n";
                    if(preostaliBrojPokusaja == 0){
                        JOptionPane.showMessageDialog(this, errorMessage, "Greska", JOptionPane.ERROR_MESSAGE);
                        this.dispose();
                        return;
                    }
                    JOptionPane.showMessageDialog(this, errorMessage, "Greska", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    Korisnik ulogovani = proveriKorisnika(email, pass);
                    JOptionPane.showMessageDialog(this, "Uspesna prijava na sistem", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new MainForma(ulogovani).setVisible(true);
                    return;
                }
            }
       
    }//GEN-LAST:event_jButtonPrijavaActionPerformed
    
    private Korisnik proveriKorisnika(String email, String pass){
        for(Korisnik k : listaKorisnika){
                if(pass.equals(k.getPassword()) && email.equals(k.getEmail())){
                    return k;
                }
            }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPrijava;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldEmail;
    // End of variables declaration//GEN-END:variables
}
