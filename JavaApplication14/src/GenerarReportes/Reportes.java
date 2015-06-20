/*
 * Conexión de netbeans con la base de datos
 *
 * version 2.0
 *
 * Fecha: 29/04/2015
 *
 * Copyright Elaborado por :
                            Angello Stivén Andrade Chacón
                            Cristian Andres Hurtado Gil
                            Nicolás David Ruiz Naji
 */
package GenerarReportes;
//librerias del formulario
import ConexionBasedeDatos.conexion;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.view.JasperViewer;




public class Reportes extends javax.swing.JFrame {
    private JFrame contenedor;
    /**
     * Creacion del formulario de Reportes
     */
    //Conexión Global de los formularios con la base de datos//
    conexion cc = new conexion();//llama al metodo conexion de la clase conexion//
    java.sql.Connection cn = cc.Conectar();//llama el metodo conectar de la clase conexion//
    
    public Reportes(JFrame contenedor) {
        this.contenedor=contenedor;//Devuelve al formulario principal al darle en la opcion de cerrar ventana.
        //Inicia los componentes//
        initComponents();//
        //Para que el formulario aparezca centrado
        setLocationRelativeTo(null);
        //Para qe el formulario no se puede expandir//
        setResizable(false);
         //Poner un Icono a la ventana//
        setIconImage(new ImageIcon(getClass().getResource("/BienvenidoAdministrador/icono.png")).getImage());
        //Poner un fondo a el Formulario//
        ((JPanel)getContentPane()).setOpaque(false); 
        ImageIcon uno=new ImageIcon(this.getClass().getResource("/BienvenidoAdministrador/madera.jpg")); 
        JLabel fondo= new JLabel(); 
        fondo.setIcon(uno); 
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0,0,uno.getIconWidth(),u­no.getIconHeight());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generación de Reportes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Generación deReportes"));
        jPanel1.setOpaque(false);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/exit.png"))); // NOI18N
        jButton5.setText("Cerrar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Productos.png"))); // NOI18N
        jButton3.setText("Reporte de Productos");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Clientes.png"))); // NOI18N
        jButton1.setText("Reporte de Clientes");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try {
        String u = System.getProperty("user.dir")+"/src/GenerarReportes/clientes.jrxml";
        JasperReport reporteJasper = JasperCompileManager.compileReport(u);
        JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, null,this.cn);
        JasperViewer.viewReport(mostrarReporte);
        } 
        catch (Exception e) {
        System.out.print(e);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //boton cerrar//
         int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea salir ?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);//mensaje de confirmación para cerrar el fomulario
        if (valor == JOptionPane.YES_OPTION) { //Si en el mensaje el usuario le dio que si seguira con el siclo.      
            BienvenidoAdministrador.BienvenidoAdministrador  salir = new BienvenidoAdministrador.BienvenidoAdministrador ();//llamar el formulario de interfaz de bienvenida usuario para salir del anterior formulario
            salir.setVisible(true);//muestra el formulario del interfaz admin
            this.setVisible(false);//cierra el formulario de ayuda
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
         this.contenedor.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
        String u = System.getProperty("user.dir")+"/src/GenerarReportes/productos.jrxml";
        JasperReport reporteJasper = JasperCompileManager.compileReport(u);
        JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, null,this.cn);
        JasperViewer.viewReport(mostrarReporte);
        } 
        catch (Exception e) {
        System.out.print(e);
    }
    }//GEN-LAST:event_jButton3ActionPerformed
    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
