/*
 * Descripcion: Ventana para consultar el detalle de ventas
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 26 de Junio de 2022
 */

package Vista;

/**
 *
 * @author los67
 */
public class DetallesVentas extends javax.swing.JFrame {

    /** Creates new form DetallesVentas */
    public DetallesVentas() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txtCliente = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblDetallesVenta = new javax.swing.JLabel();
        lblIdVenta = new javax.swing.JLabel();
        txtIdVenta = new javax.swing.JTextField();
        lblEmpleado = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        btnMostrar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDetallesVenta = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Detalles Venta");

        txtCliente.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        lblFecha.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblFecha.setText("Fecha:");

        txtFecha.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        lblDetallesVenta.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblDetallesVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDetallesVenta.setText("Detalles Ventas");

        lblIdVenta.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblIdVenta.setText("Id Venta:");

        txtIdVenta.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        lblEmpleado.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblEmpleado.setText("Empleado:");

        txtEmpleado.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        txtEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoActionPerformed(evt);
            }
        });

        lblCliente.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblCliente.setText("Cliente:");

        btnMostrar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnMostrar.setText("Mostrar");

        btnRegresar.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btnRegresar.setText("Regresar");

        tblDetallesVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tblDetallesVenta);

        txtTotal.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        lblTotal.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblTotal.setText("Total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmpleado, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFecha, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblIdVenta, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTotal, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                            .addComponent(txtCliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpleado)
                            .addComponent(txtIdVenta, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDetallesVenta)
                            .addComponent(btnRegresar)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                            .addComponent(btnMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 18, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addGap(18, 18, 18)
                .addComponent(lblDetallesVenta)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdVenta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmpleado))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnMostrar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoActionPerformed

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
            java.util.logging.Logger.getLogger(DetallesVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetallesVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetallesVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetallesVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetallesVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDetallesVenta;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIdVenta;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblDetallesVenta;
    public javax.swing.JTextField txtCliente;
    public javax.swing.JTextField txtEmpleado;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtIdVenta;
    public javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

}
