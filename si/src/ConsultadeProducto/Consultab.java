/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsultadeProducto;

import ConexionBasedeDatos.conexion;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cristian
 */
public final class Consultab extends javax.swing.JFrame {

    DefaultTableModel tabla;
    //Conexión Global de los formularios con la base de datos//
    conexion cc = new conexion();//llama al metodo conexion de la clase conexion//
    java.sql.Connection cn = cc.Conectar();//llama el metodo conectar de la clase conexion//

    /**
     * Creates new form consultab
     */
    public Consultab() {
        initComponents();
        cargarlistaproductos("");
        setLocationRelativeTo(null);//Posiciona el formulario en el centro de la pantalla        
        setResizable(false);//Cancela la maximización del formulario           
        setIconImage(new ImageIcon(getClass().getResource("/BienvenidoAdministrador/icono.png")).getImage());//Selecciona y pone un icono al formulario
        ((JPanel)getContentPane()).setOpaque(false); //Verifica si hay un panel y quita su opacidad
        ImageIcon uno=new ImageIcon(this.getClass().getResource("/BienvenidoAdministrador/madera.jpg")); //Ruta en donde se encuentra la imagen
        JLabel fondo= new JLabel(); //Varible en donde almacena la imagen
        fondo.setIcon(uno); //Establece de fondo la imagen que se enruto anteriormente
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);//Obtiene el tamaño del panel 
        fondo.setBounds(0,0,uno.getIconWidth(),u­no.getIconHeight());//Acopla la imagen al formulario    
    }

    public void calcular() {
        String valor;
        String existencia;
        double igv = 0;
        double total = 0;
        double subtotal = 0;
        double precio;
        int cantidad;
        double imp = 0.0;

        /*can=Integer.parseInt(cant);
         imp=pre*can;
         dato[4]=Float.toString(imp);*/

        for (int i = 0; i < Facturación.FacturadeVenta.jTable1.getRowCount(); i++) {
            valor = Facturación.FacturadeVenta.jTable1.getValueAt(i, 4).toString();
            existencia = Facturación.FacturadeVenta.jTable1.getValueAt(i, 5).toString();
            precio = Double.parseDouble(valor);
            cantidad = Integer.parseInt(existencia);
            imp = precio * cantidad;
            subtotal = subtotal + imp;
            igv = subtotal * 0.16;
            total = subtotal + igv;
            // txtcod.setText(""+Math.rint(c*100)/100)
            Facturación.FacturadeVenta.jTable1.setValueAt(Math.rint(imp * 100) / 100, i, 6);

        }
        Facturación.FacturadeVenta.jTextField19.setText("" + Math.round(subtotal));
        Facturación.FacturadeVenta.jTextField20.setText("" + Math.rint(igv * 100) / 100);
        Facturación.FacturadeVenta.jTextField21.setText("" + Math.round(total * 100) / 100);


    }

    String comparar(String cod) {
        String existencia = "";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM producto WHERE id_producto='" + cod + "'");
            while (rs.next()) {
                existencia = rs.getString(6);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultadeProducto.Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existencia;
    }

    void cargarlistaproductos(String dato) {
        String[] Titulo = {"id_producto", "tipo", "gama", "referencia", "valor", "existencia"};
        tabla = new DefaultTableModel(null, Titulo);
        String[] Registro = new String[6];
        String mostrar = "SELECT * FROM producto WHERE CONCAT (id_producto,'',tipo,'',referencia,'',gama) LIKE '%" + dato + "%'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            boolean hayDatos = false;
            while (rs.next()) {
                hayDatos = true;
                
                Registro[0] = rs.getString("id_producto");
                Registro[1] = rs.getString("tipo");
                Registro[2] = rs.getString("gama");
                Registro[3] = rs.getString("referencia");
                Registro[4] = rs.getString("valor");
                Registro[5] = rs.getString("existencia");

                tabla.addRow(Registro);
            }
            if (!hayDatos) {
                JOptionPane.showMessageDialog(this, "No hay datos");
                jTextField7.setText("");
                
            } else {
               jTable2.setModel(tabla);
               
            }
           
        } catch (SQLException ex) {
            //Logger.getLogger(ConsultadeProducto.Consultab.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No hay datos");
        }
    }
    
    public void enviar(){
        // TODO add your handling code here:
        try {

            DefaultTableModel tabladet = (DefaultTableModel) Facturación.FacturadeVenta.jTable1.getModel();
            String[] dato = new String[6];

            int fila = jTable2.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No  ha seleccionado ningun registro");
            } else {
                String identificacion = jTable2.getValueAt(fila, 0).toString();
                String tipo = jTable2.getValueAt(fila, 1).toString();
                String gama = jTable2.getValueAt(fila, 2).toString();
                String referencia = jTable2.getValueAt(fila, 3).toString();
                String valor = jTable2.getValueAt(fila, 4).toString();

                int c = 0;
                int j = 0;
                String existencia = JOptionPane.showInputDialog("ingrese cantidad");
                if ((existencia.equals("")) || (existencia.equals("0"))) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0");
                } else {

                    int canting = Integer.parseInt(existencia);
                    int comp = Integer.parseInt(comparar(identificacion));
                    if (canting>comp) {
                        JOptionPane.showMessageDialog(this, "No cuenta con la existencia suficiente");
                    } 
                    else {
                        JOptionPane.showMessageDialog(this, "Producto agregado correctamente");
                        for (int i = 0; i < Facturación.FacturadeVenta.jTable1.getRowCount(); i++) {
                            Object com = Facturación.FacturadeVenta.jTable1.getValueAt(i, 0);
                            if (identificacion.equals(com)) {
                                j = i;
                                Facturación.FacturadeVenta.jTable1.setValueAt(existencia, i, 5);
                                c = c + 1;

                            }

                        }
                        if (c == 0) {

                            dato[0] = identificacion;
                            dato[1] = tipo;
                            dato[2] = gama;
                            dato[3] = referencia;
                            dato[4] = valor;
                            dato[5] = existencia;

                            tabladet.addRow(dato);

                            Facturación.FacturadeVenta.jTable1.setModel(tabladet);
                            calcular();
                            //fac.calcular();

                        }
                    }
                    //fac.calcular();
                }

            }

        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El producto no se a agregado" + e.getMessage());
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTextField7 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        jMenuItem1.setText("Enviar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Producto");

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/look.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setComponentPopupMenu(jPopupMenu1);
        jScrollPane3.setViewportView(jTable2);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Facturación/sent.png"))); // NOI18N
        jButton2.setText("Envia a Factura");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cargarlistaproductos(jTextField7.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        // TODO add your handling code here:
        cargarlistaproductos(jTextField7.getText());
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        enviar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        enviar();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
