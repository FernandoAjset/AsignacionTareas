package ui;
import domain.Usuario;
import domain.UsuarioDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author briza
 */
public class REASIGNAR extends javax.swing.JFrame {

    Usuario usuario;
    int idSelec = 0;
    int idTareaSelec = 0;
    String descripcion = "";
    private Connection conexion;

    public REASIGNAR() {
        initComponents();
    }

    public REASIGNAR(Usuario user) {
        initComponents();
        this.usuario = user;
        cargarSubs();
        cargarTareas();
        setLocationRelativeTo(null);
        nombreuser.setText("Usuario: " + user.getUsuario().toUpperCase());
        tipouser.setText("Tipo usuario: " + user.getNombre_tipo());
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaTareas = new javax.swing.JTable();
        tareaSelec = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaUsuarios = new javax.swing.JTable();
        usuarioNuevo = new javax.swing.JLabel();
        Confirmar = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tipouser = new javax.swing.JLabel();
        nombreuser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(743, 576));
        setMinimumSize(new java.awt.Dimension(743, 576));
        setPreferredSize(new java.awt.Dimension(743, 576));
        setSize(new java.awt.Dimension(743, 576));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 1, 36)); // NOI18N
        jLabel2.setText("REASIGNACION DE TAREAS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 679, 51));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Seleccione el usuario a quien reasignará la tarea");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 310, -1));

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
        jLabel4.setText("Seleccione tarea para reasignar (solo puede reasignar tareas en estado ACTIVA)");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 530, -1));

        TablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Usuario"
            }
        ));
        TablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaUsuarios);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 530, 120));

        usuarioNuevo.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        usuarioNuevo.setForeground(new java.awt.Color(51, 51, 255));
        getContentPane().add(usuarioNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 350, 20));

        Confirmar.setText("Reasignar Tarea");
        Confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(Confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, 160, -1));

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        getContentPane().add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 80, 30));

        jLabel6.setText("SESION ACTIVA");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 165, -1));
        getContentPane().add(tipouser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 165, 21));
        getContentPane().add(nombreuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 124, 21));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public final void cargarSubs() {
        DefaultTableModel modelo = (DefaultTableModel) TablaUsuarios.getModel();
        modelo.setRowCount(0);
        UsuarioDAO user = new UsuarioDAO();
        user.verSubs(usuario, modelo);
    }

    public final void cargarTareas() {
        DefaultTableModel modelo = (DefaultTableModel) TablaTareas.getModel();
        UsuarioDAO user = new UsuarioDAO();
        user.verTareasDepartamento(usuario.getId_departamento(), 0, modelo);
    }

    public void reAsignar() {
        UsuarioDAO user = new UsuarioDAO();
        if (user.reAsigarTarea(idTareaSelec, idSelec)) {
            JOptionPane.showConfirmDialog(null, "Tarea reasignada Exitosamente", "Ejecución correcta", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            cargarTareas();
            cargarSubs();
            idTareaSelec = 0;
            idSelec = 0;
            tareaSelec.setText("");
            usuarioNuevo.setText("");
        }
    }

    private void TablaTareasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaTareasMouseClicked
        int seleccionar = TablaTareas.rowAtPoint(evt.getPoint());
        String aux = String.valueOf(TablaTareas.getValueAt(seleccionar, 0));
        idTareaSelec = Integer.parseInt(aux);
        tareaSelec.setText("Tarea seleccionada: " + String.valueOf(TablaTareas.getValueAt(seleccionar, 1)));
    }//GEN-LAST:event_TablaTareasMouseClicked

    private void TablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaUsuariosMouseClicked
        int seleccionar = TablaUsuarios.rowAtPoint(evt.getPoint());
        String aux = String.valueOf(TablaUsuarios.getValueAt(seleccionar, 0));
        idSelec = Integer.parseInt(aux);
        usuarioNuevo.setText("Usuario seleccionado: " + String.valueOf(TablaUsuarios.getValueAt(seleccionar, 2)));
    }//GEN-LAST:event_TablaUsuariosMouseClicked

    private void ConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmarActionPerformed

        if (idTareaSelec == 0) {
            JOptionPane.showConfirmDialog(null, "Seleccione una tarea", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        } else if (idSelec == 0) {
            JOptionPane.showConfirmDialog(null, "Seleccione un usuario", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
        } else {
            reAsignar();
        }
    }//GEN-LAST:event_ConfirmarActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        MENU m = new MENU(usuario);
        m.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_volverActionPerformed

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
            java.util.logging.Logger.getLogger(REASIGNAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(REASIGNAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(REASIGNAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(REASIGNAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new REASIGNAR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirmar;
    private javax.swing.JTable TablaTareas;
    private javax.swing.JTable TablaUsuarios;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel nombreuser;
    private javax.swing.JLabel tareaSelec;
    private javax.swing.JLabel tipouser;
    private javax.swing.JLabel usuarioNuevo;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
