/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tictactoe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author luca
 */
public class PvPxl extends javax.swing.JFrame implements ActionListener{
    ImageIcon icona = new ImageIcon(getClass().getResource("logo.png"));
    private String currentPlayer = "X";
    private int vittorieGiocatore1 = 0;
    private int vittorieGiocatore2 = 0;
    private Component frame;
    private JButton[][] caselle = new JButton[5][5];
    private boolean turnoGiocatore1 = true;
    
    public PvPxl() {
        initComponents();
        setSize(550, 690);
        inizializzaBottoni();
        setIconImage(icona.getImage()); 
    }
    
    public void inizializzaBottoni(){
        int i = 0;
        int j = 0;
        GridLayout layout = new GridLayout(5,5);
        
        jPanel2.setLayout(layout);

        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                caselle[i][j] = new JButton();
                caselle[i][j].setPreferredSize(new Dimension(50, 20));
                caselle[i][j].setFont(new Font("Segoe UI", 1, 36));
                caselle[i][j].setBackground(new java.awt.Color(80,80,80));
                caselle[i][j].setOpaque(false);
                caselle[i][j].setContentAreaFilled(false);
                caselle[i][j].setBackground(new Color(0,0,0,50));
                caselle[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
                caselle[i][j].setFocusPainted(false);
                caselle[i][j].setBorderPainted(true);
                caselle[i][j].addActionListener(this);
                jPanel2.add(caselle[i][j]);
            }
        }
        setVisible(true);
    }
    
    private boolean controllaVittoria() {
        int i = 0;
        int j = 0;
        String[][] tabellone = new String[5][5]; 

        for (i = 0; i < 5; i++) {
            for (j= 0; j < 5; j++) {
                tabellone[i][j] = caselle[i][j].getText();
            }
        }

        for (i = 0; i < 5; i++) {
            if (tabellone[i][0].equals(tabellone[i][1]) && tabellone[i][1].equals(tabellone[i][2])
                    && tabellone[i][2].equals(tabellone[i][3]) && tabellone[i][3].equals(tabellone[i][4])
                    && !tabellone[i][0].equals("")) // Righe
                return true;
            if (tabellone[0][i].equals(tabellone[1][i]) && tabellone[1][i].equals(tabellone[2][i])
                    && tabellone[2][i].equals(tabellone[3][i]) && tabellone[3][i].equals(tabellone[4][i])
                    && !tabellone[0][i].equals("")) // Colonne
                return true;
        }

        if (tabellone[0][0].equals(tabellone[1][1]) && tabellone[1][1].equals(tabellone[2][2])
                && tabellone[2][2].equals(tabellone[3][3]) && tabellone[3][3].equals(tabellone[4][4])
                && !tabellone[0][0].equals("")) // Diagonale 1
            return true;
        if (tabellone[0][4].equals(tabellone[1][3]) && tabellone[1][3].equals(tabellone[2][2])
                && tabellone[2][2].equals(tabellone[3][1]) && tabellone[3][1].equals(tabellone[4][0])
                && !tabellone[0][4].equals("")) // Diagonale 2
            return true;

        return false;
    }
      
    private boolean controllaPareggio() {
        int i = 0;
        int j = 0;
        for (i = 0; i < 5; i++) { 
            for (j = 0; j < 5; j++) { 
                if (caselle[i][j].getText().equals(""))
                    return false;
            }
        }
        return true;
    }   

    private void resettaTabellone() {
        int i = 0;
        int j = 0;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                caselle[i][j].setText("");
            }
        }
        turnoGiocatore1 = true;
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton bottoneCliccato = (JButton) e.getSource();
        if (!bottoneCliccato.getText().equals(""))
            return;

        if (turnoGiocatore1){
            bottoneCliccato.setText("X");
            Color red = new Color (255, 0, 0);
            bottoneCliccato.setForeground(red);
            
        }
        else{
            bottoneCliccato.setText("O");
            Color blue = new Color (0, 0, 255);
            bottoneCliccato.setForeground(blue);
        }
        if (controllaVittoria()) {
            String vincitore = turnoGiocatore1 ? "1" : "2";
            if (turnoGiocatore1) {
                vittorieGiocatore1++; 
            } else {
                vittorieGiocatore2++; 
            }
            int opzione = JOptionPane.showConfirmDialog(frame, 
                            "Player " + vincitore + " vince! Vuoi giocare di nuovo?\n"
                            + "Giocatore 1: " + vittorieGiocatore1 + " vittorie\n"
                            + "Giocatore 2: " + vittorieGiocatore2 + " vittorie", 
                            "Tic Tac Toe", JOptionPane.YES_NO_OPTION);
            if (opzione == JOptionPane.YES_OPTION) {
                resettaTabellone();
            } else {
                this.dispose();
            }
        } else if (controllaPareggio()) {
            int opzione = JOptionPane.showConfirmDialog(frame, 
                            "Pareggio! Vuoi giocare di nuovo?\n"
                            + "Giocatore 1: " + vittorieGiocatore1 + " vittorie\n"
                            + "Giocatore 2: " + vittorieGiocatore2 + " vittorie", 
                            "Tic Tac Toe", JOptionPane.YES_NO_OPTION);
            if (opzione == JOptionPane.YES_OPTION) {
                resettaTabellone();
            } else {
                this.dispose();
            }
        } else {
            turnoGiocatore1 = !turnoGiocatore1;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new java.awt.GridLayout(3, 3, 2, 2));

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tictactoe/home.png"))); // NOI18N
        jButton2.setToolTipText("");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tictactoe/titolo.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 59, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PvPxl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PvPxl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PvPxl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PvPxl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PvPxl().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
