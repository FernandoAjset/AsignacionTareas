package ui;

import domain.Usuario;
import java.sql.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author briza
 */
public class MENU extends javax.swing.JFrame {

    Usuario user;

    public MENU() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public MENU(Usuario user) {
        initComponents();
        this.user = user;
        setLocationRelativeTo(null);
        switch (user.getId_tipo()) {
            case 1 -> reasignar_tarea.setVisible(false);
            case 3 -> reasignar_tarea.setVisible(false);
        }
        nombreuser.setText("Usuario: " + user.getUsuario().toUpperCase());
        tipouser.setText("Tipo usuario: " + user.getNombre_tipo());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        asignar_tarea = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        reasignar_tarea = new javax.swing.JButton();
        consultar_tareas = new javax.swing.JButton();
        terminar_tarea = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        nombreuser = new javax.swing.JLabel();
        tipouser = new javax.swing.JLabel();
        logout = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(717, 269));
        setMinimumSize(new java.awt.Dimension(717, 269));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        asignar_tarea.setText("ASIGNAR TAREA");
        asignar_tarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asignar_tareaActionPerformed(evt);
            }
        });
        getContentPane().add(asignar_tarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 127, 32));

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 1, 36)); // NOI18N
        jLabel2.setText("MENU DE CONTROL DE TAREAS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 24, 679, 51));

        jLabel5.setText("Elija la operacion de que desee realizar:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 86, 281, 22));

        reasignar_tarea.setText("REASIGNAR TAREA");
        reasignar_tarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reasignar_tareaActionPerformed(evt);
            }
        });
        getContentPane().add(reasignar_tarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, -1, 32));

        consultar_tareas.setText("REPORTES");
        consultar_tareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar_tareasActionPerformed(evt);
            }
        });
        getContentPane().add(consultar_tareas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 127, 32));

        terminar_tarea.setText("TERMINAR TAREA");
        terminar_tarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminar_tareaActionPerformed(evt);
            }
        });
        getContentPane().add(terminar_tarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 140, 32));

        jLabel6.setText("SESION ACTIVA");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 165, -1));
        getContentPane().add(nombreuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 124, 21));
        getContentPane().add(tipouser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 165, 21));

        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, 80, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void asignar_tareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignar_tareaActionPerformed
        ASIGNAR asig = new ASIGNAR(user);
        asig.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_asignar_tareaActionPerformed

    private void reasignar_tareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reasignar_tareaActionPerformed
        REASIGNAR asig = new REASIGNAR(user);
        asig.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_reasignar_tareaActionPerformed

    private void consultar_tareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultar_tareasActionPerformed
        CONSULTAS con = new CONSULTAS(user);
        con.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_consultar_tareasActionPerformed

    private void terminar_tareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminar_tareaActionPerformed
        TERMINAR t = new TERMINAR(user);
        t.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_terminar_tareaActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        Inicio nuevo = new Inicio();
        this.setVisible(false);
        nuevo.setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

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
            java.util.logging.Logger.getLogger(MENU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MENU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MENU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MENU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MENU().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton asignar_tarea;
    private javax.swing.JButton consultar_tareas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton logout;
    private javax.swing.JLabel nombreuser;
    private javax.swing.JButton reasignar_tarea;
    private javax.swing.JButton terminar_tarea;
    private javax.swing.JLabel tipouser;
    // End of variables declaration//GEN-END:variables
}
