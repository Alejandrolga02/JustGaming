/*
Descripción: Creación de la pestaña donde se ingresan los datos de los insumos
Nombre: Alejandro Ivan Lizarraga Rojas
Fecha: 26-junio-2022
*/
package Vista;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;

public class Insumos extends javax.swing.JFrame {
    Container contenedor = this.getContentPane();

    public Insumos() {
        initComponents();
        setIconImage(getIconImage());
    }
    
    // Logo
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/logo.png"));

        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIngresar = new javax.swing.JButton();
        lblCosto = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInsumos = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblCantidad = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        lblIdInsumo = new javax.swing.JLabel();
        txtIdInsumo = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnElimina = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Insumos");
        setResizable(false);

        btnIngresar.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        lblCosto.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblCosto.setText("Costo:");

        lblNombre.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblNombre.setText("Nombre:");

        tblInsumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblInsumos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblInsumos);

        btnLimpiar.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btnLimpiar.setText("Limpiar");

        btnRegresar.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btnRegresar.setText("Regresar");

        lblCantidad.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblCantidad.setText("Cantidad:");

        txtCosto.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        txtNombre.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        txtCantidad.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        lblTitulo.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblTitulo.setText("Insumos");

        lblIdInsumo.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblIdInsumo.setText("Id Insumo:");

        txtIdInsumo.setEditable(false);
        txtIdInsumo.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        btnActualizar.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btnActualizar.setText("Modificar");

        btnElimina.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btnElimina.setText("Eliminar");

        btnConsultar.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIdInsumo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCosto, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNombre)
                                    .addComponent(txtCosto)
                                    .addComponent(txtIdInsumo)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnElimina, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegresar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIdInsumo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantidad))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCosto)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTitulo)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnActualizar)
                    .addComponent(btnElimina)
                    .addComponent(btnIngresar)
                    .addComponent(btnConsultar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarActionPerformed

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
            java.util.logging.Logger.getLogger(Insumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Insumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Insumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Insumos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnConsultar;
    public javax.swing.JButton btnElimina;
    public javax.swing.JButton btnIngresar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblIdInsumo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    public javax.swing.JTable tblInsumos;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtCosto;
    public javax.swing.JTextField txtIdInsumo;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
