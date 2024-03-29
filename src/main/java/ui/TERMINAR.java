package ui;
import domain.Usuario;
import domain.UsuarioDAO;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author briza
 */
public class TERMINAR extends javax.swing.JFrame {

    Usuario usuario;
    int idTareaSelec = 0;
    private Connection conexion;

    public TERMINAR() {
        initComponents();
    }

    public TERMINAR(Usuario user) {
        initComponents();
        this.usuario = user;
        nombreuser.setText("Usuario: " + user.getUsuario().toUpperCase());
        tipouser.setText("Tipo usuario: " + user.getNombre_tipo());
        cargarTareas();
        setLocationRelativeTo(null);
    }
    //private Connection conexion;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaTareas = new javax.swing.JTable();
        tareaSelec = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Confirmar = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        nombreuser = new javax.swing.JLabel();
        tipouser = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(743, 420));
        setMinimumSize(new java.awt.Dimension(743, 420));
        setPreferredSize(new java.awt.Dimension(743, 420));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FINALIZAR TAREAS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 540, 51));

        TablaTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Tarea", "Detalle de Tarea", "Usuario asignado", "Fecha Asignacion", "Estado"
            }
        ));
        TablaTareas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaTareasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaTareas);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 530, 160));

        tareaSelec.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        tareaSelec.setForeground(new java.awt.Color(51, 51, 255));
        getContentPane().add(tareaSelec, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 530, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("Seleccione tarea que desea terminar");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 310, -1));

        Confirmar.setText("Terminar Tarea");
        Confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(Confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 160, -1));

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        getContentPane().add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, 80, 30));
        getContentPane().add(nombreuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 124, 21));
        getContentPane().add(tipouser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 165, 21));

        jLabel6.setText("SESION ACTIVA");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 165, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaTareasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaTareasMouseClicked
        int seleccionar = TablaTareas.rowAtPoint(evt.getPoint());
        String aux = String.valueOf(TablaTareas.getValueAt(seleccionar, 0));
        idTareaSelec = Integer.parseInt(aux);
        tareaSelec.setText("Tarea seleccionada: " + String.valueOf(TablaTareas.getValueAt(seleccionar, 1)));
    }//GEN-LAST:event_TablaTareasMouseClicked

    private void ConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmarActionPerformed

        if (idTareaSelec == 0) {
            JOptionPane.showConfirmDialog(null, "Seleccione una tarea", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        } else {
            UsuarioDAO user = new UsuarioDAO();
            if (user.terminarTarea(idTareaSelec)) {
                JOptionPane.showConfirmDialog(null, "Tarea terminada Exitosamente", "Ejecución correcta", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
                cargarTareas();
                idTareaSelec = 0;
                tareaSelec.setText("");
            }
        }
    }//GEN-LAST:event_ConfirmarActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        MENU m = new MENU(usuario);
        m.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_volverActionPerformed

    public final void cargarTareas() {
        DefaultTableModel modelo = (DefaultTableModel) TablaTareas.getModel();
        modelo.setRowCount(0);
        UsuarioDAO user = new UsuarioDAO();
        user.misTareas(0, usuario.getId_usuario(), modelo);
    }

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
            java.util.logging.Logger.getLogger(TERMINAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TERMINAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TERMINAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TERMINAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TERMINAR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirmar;
    private javax.swing.JTable TablaTareas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nombreuser;
    private javax.swing.JLabel tareaSelec;
    private javax.swing.JLabel tipouser;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
