/*
 * Descripcion: Ventana para menu principal
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 26 de Junio de 2022
 */

package JustGaming;

/**
 *
 * @author los67
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
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

        bg = new javax.swing.JPanel();
        btnVentas = new javax.swing.JButton();
        lblVentas = new javax.swing.JLabel();
        btnInsumos = new javax.swing.JButton();
        lblInsumos = new javax.swing.JLabel();
        btnClientes = new javax.swing.JButton();
        lblClientes = new javax.swing.JLabel();
        btnProveedores = new javax.swing.JButton();
        btnEmpleados = new javax.swing.JButton();
        btnServicios = new javax.swing.JButton();
        lblServicios = new javax.swing.JLabel();
        lblEmpleados = new javax.swing.JLabel();
        lblProveedores = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");

        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JustGaming/img/shopping-cart.png"))); // NOI18N
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        lblVentas.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblVentas.setLabelFor(btnVentas);
        lblVentas.setText("Ventas");
        lblVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnInsumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JustGaming/img/insumos.png"))); // NOI18N
        btnInsumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsumosActionPerformed(evt);
            }
        });

        lblInsumos.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblInsumos.setLabelFor(btnVentas);
        lblInsumos.setText("Insumos");
        lblInsumos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JustGaming/img/clients.jpg"))); // NOI18N
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        lblClientes.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblClientes.setLabelFor(btnVentas);
        lblClientes.setText("Clientes");
        lblClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JustGaming/img/supplier.jpg"))); // NOI18N
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });

        btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JustGaming/img/employees.png"))); // NOI18N
        btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosActionPerformed(evt);
            }
        });

        btnServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JustGaming/img/services.png"))); // NOI18N
        btnServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServiciosActionPerformed(evt);
            }
        });

        lblServicios.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblServicios.setLabelFor(btnVentas);
        lblServicios.setText("Servicios");
        lblServicios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblEmpleados.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblEmpleados.setLabelFor(btnVentas);
        lblEmpleados.setText("Empleados");
        lblEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblProveedores.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblProveedores.setLabelFor(btnVentas);
        lblProveedores.setText("Compras");
        lblProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setText("Cerrar Sesión");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVentas)
                            .addComponent(btnProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProveedores))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(btnEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEmpleados))
                                .addGap(18, 18, 18)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(btnServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblServicios)
                                    .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblClientes)))
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(btnInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblInsumos))))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton1)
                .addGap(8, 8, 8)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblClientes))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btnInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblInsumos))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblVentas)))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btnServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblServicios))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btnEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblEmpleados))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btnProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblProveedores)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnInsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsumosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsumosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEmpleadosActionPerformed

    private void btnServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiciosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnServiciosActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnEmpleados;
    private javax.swing.JButton btnInsumos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnServicios;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblEmpleados;
    private javax.swing.JLabel lblInsumos;
    private javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblServicios;
    private javax.swing.JLabel lblVentas;
    // End of variables declaration//GEN-END:variables
}
