/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tictactoe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author luca
 */
public class PvAI extends javax.swing.JFrame implements ActionListener {
    ImageIcon icona = new ImageIcon(getClass().getResource("logo.png"));
    public javax.swing.JButton button[][] = new javax.swing.JButton[3][3];
    private String currentPlayer = "X";
    private int vittorieGiocatore1 = 0;
    private int vittorieGiocatore2 = 0;
    private Component frame;
    /**
     * Creates new form PvAI
     */
   

    public PvAI() {
        initComponents();
        setSize(540, 690);
        inizializzaBottoni();
        setIconImage(icona.getImage());
    }

    private void inizializzaBottoni() {
        
        GridLayout layout = new GridLayout(3,3);
        layout.setHgap(25);
        layout.setVgap(25);
        
        int i = 0;
        int j = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                button[i][j] = new JButton();
                button[i][j].setText(null);
                button[i][j].setFont(new java.awt.Font("Segoe UI", 1, 36));
                button[i][j].setOpaque(false);
                button[i][j].setContentAreaFilled(false);
                button[i][j].setBackground(new Color(0,0,0,50));
                button[i][j].setFocusPainted(false);
                button[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
                button[i][j].setBorderPainted(true);
                button[i][j].addActionListener(this);
            }
        }

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                jPanel2.add(button[i][j]);
            }
        }
    }

    public boolean equalsX(String a, String b, String c) {
        if (a == "X" && b == "X" && c == "X") {
            return true;
        }
        return false;
    }

    public boolean equalsO(String a, String b, String c) {
        if (a == "O" && b == "O" && c == "O") {
            return true;
        }
        return false;
    }

    public void resetGame() {
        int i = 0;
        int j = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                button[i][j].setText(null);
                button[i][j].setEnabled(true);
            }
        }
    }

    public int minimax(boolean isMaximizing) {
        int i = 0;
        int j = 0;
        if (staVincendo() == 1 || staVincendo() == -1 || staVincendo() == 0) {
            int temp = staVincendo();
            return temp;
        }
        if (isMaximizing == true) {
            int maxScore = -10;
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (button[i][j].getText() == null) {
                        button[i][j].setText("X");
                        int score = minimax(false);
                        button[i][j].setText(null);
                        maxScore = Math.max(score, maxScore);
                    }
                }
            }
            return maxScore;
        } else {
            int minScore = 10;
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (button[i][j].getText() == null) {
                        button[i][j].setText("O");
                        int score = minimax(true);
                        button[i][j].setText(null);
                        minScore = Math.min(score, minScore);
                    }
                }
            }
            return minScore;

        }
    }

    public void cpuMove() {
        int i = 0;
        int j = 0;
        int bestScore = 10;
        int besti = 0;
        int bestj = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (button[i][j].getText() == null) {
                    button[i][j].setText("O");
                    int score = minimax(true);
                    button[i][j].setText(null);
                    if (score < bestScore) {
                        bestScore = score;
                        besti = i;
                        bestj = j;
                    }
                }
            }
        }
        button[besti][bestj].setText("O");
        button[besti][bestj].setForeground(Color.BLUE);
        button[besti][bestj].setEnabled(false);
        Registrazione registrazione = new Registrazione();
        String giocatore1 = registrazione.nome1;
        if (staVincendo() == 1) {
            vittorieGiocatore1++;
            if (JOptionPane.showConfirmDialog(frame, "Player " + " : " + giocatore1 + " vince! Vuoi giocare di nuovo?\nVittorie" + giocatore1 + ": " + vittorieGiocatore1 + "\nVittorie AI: " + vittorieGiocatore2, "Tic Tac Toe",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                resetGame();
            } else {
                this.dispose();
            }
            return;
        } else if (staVincendo() == -1) {
            vittorieGiocatore2++;
            if (JOptionPane.showConfirmDialog(frame, "Player AI vince! Vuoi giocare di nuovo?\nVittorie" + giocatore1 + ": "+  vittorieGiocatore1 + "\nVittorie AI: " + vittorieGiocatore2, "Tic Tac Toe",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                resetGame();
            } else {
                this.dispose();
            }
            return;
        } else if (staVincendo() == 0) {
            vittorieGiocatore1 = vittorieGiocatore1;
            vittorieGiocatore2 = vittorieGiocatore2;
            if (JOptionPane.showConfirmDialog(frame, "Pareggio! Vuoi giocare di nuovo?\nVittorie" + giocatore1 + ": " + vittorieGiocatore1 + "\nVittorie AI: " + vittorieGiocatore2, "Tic Tac Toe",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                resetGame();
            } else {
                this.dispose();
            }

        }

    }

    public int staVincendo() {
        int i = 0;
        int j = 0;
        for (i = 0; i < 3; i++) {
            if (equalsX(button[i][0].getText(), button[i][1].getText(), button[i][2].getText())) {
                return 1;
            }
            if (equalsO(button[i][0].getText(), button[i][1].getText(), button[i][2].getText())) {
                return -1;
            }
        }

        for (j = 0; j < 3; j++) {
            if (equalsX(button[0][j].getText(), button[1][j].getText(), button[2][j].getText())) {
                return 1;
            }
            if (equalsO(button[0][j].getText(), button[1][j].getText(), button[2][j].getText())) {
                return -1;
            }
        }

        if (equalsX(button[0][0].getText(), button[1][1].getText(), button[2][2].getText())) {
            return 1;
        }
        if (equalsO(button[0][0].getText(), button[1][1].getText(), button[2][2].getText())) {
            return -1;
        }

        if (equalsX(button[0][2].getText(), button[1][1].getText(), button[2][0].getText())) {
            return 1;
        }
        if (equalsO(button[0][2].getText(), button[1][1].getText(), button[2][0].getText())) {
            return -1;
        }

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (button[i][j].getText() == null) {
                    return -2;
                }
            }
        }

        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = 0;
        int j = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (e.getSource() == button[i][j]) {
                    button[i][j].setText(currentPlayer);
                    button[i][j].setForeground(Color.RED);
                    button[i][j].setEnabled(false);
                    if (staVincendo() == 1) {
                        if (JOptionPane.showConfirmDialog(frame, "Player X vince! Vuoi giocare di nuovo?", "Tic Tac Toe",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                            resetGame();
                        } else {
                            System.exit(0);
                        }
                        return;
                    } else if (staVincendo() == -1) {
                        if (JOptionPane.showConfirmDialog(frame, "Player O vince! Vuoi giocare di nuovo?", "Tic Tac Toe",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                            resetGame();
                        } else {
                            System.exit(0);
                        }
                        return;
                    } else if (staVincendo() == 0) {
                        if (JOptionPane.showConfirmDialog(frame, "Pareggio! Vuoi giocare di nuovo?", "Tic Tac Toe",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                            resetGame();
                        } else {
                            System.exit(0);
                        }
                        return;
                    }

                    cpuMove();

                }
            }
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

        jButton2.setBackground(new java.awt.Color(80, 80, 80));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tictactoe/home.gif"))); // NOI18N
        jButton2.setToolTipText("");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
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
                        .addGap(0, 58, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(PvAI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PvAI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PvAI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PvAI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PvAI().setVisible(true);
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
