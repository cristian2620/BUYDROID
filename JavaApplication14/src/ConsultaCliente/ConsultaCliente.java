/*
 * Conexión de netbeans con la base de datos
 *
 * version 5.5
 *
 * Fecha: 18/05/2015
 *
 * Copyright Elaborado por :
                            Angello Stivén Andrade Chacón
                            Cristian Andres Hurtado Gil
                            Nicolás David Ruiz Naji
 */
package ConsultaCliente;//paquete en donde se encuentra el formulario
//librerias del formulario
import ConexionBasedeDatos.conexion;
import static Facturación.FacturadeVenta.fechaactual;
import static Facturación.FacturadeVenta.jTable1;
import GestiondeUsuarios.GestionUsuarios;
import java.awt.Color;
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
 * @author Proyeco BUYDROID
 */
public class ConsultaCliente extends javax.swing.JFrame {
 //Conexión Global de los formularios con la base de datos//
    conexion cc = new conexion();//llama al metodo conexion de la clase conexion//
    java.sql.Connection cn = cc.Conectar();//llama el metodo conectar de la clase conexion//
    public ConsultaCliente() {
        initComponents();//Iniciliza los componentes  
        mostrardatos("");//Muestra los datos en la tabla      
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
     void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel(); //tabla por defecto del formulario
        modelo.addColumn("Identificación");//agrega una columna en la tabla 
        modelo.addColumn("Nombre");//agrega una columna en la tabla 
        modelo.addColumn("Apellido"); //agrega una columna en la tabla        
        modelo.addColumn("Teléfono");//agrega una columna en la tabla 
        modelo.addColumn("Dirección");  //agrega una columna en la tabla            
        jTable1.setModel(modelo);// selecciona la tabla por defecto y agrega las columnas

        String SQL = "";//variable string en donde se almacenara la sentencia SQL
        if (valor.equals("")) { SQL = "SELECT * FROM cliente"; } //Hace la consulta en la base de datos segun el valor ingresado 
        else { SQL = "SELECT * FROM cliente WHERE id='" + valor + "'"; }//Hace la consulta en la base de datos segun el valor ingresado y busca por id
        String[] datos = new String[6];//declara un vector en donde se almacenaran los datos
        try {
            Statement st = cn.createStatement();//establece los valores para la sentencia sql 
            ResultSet rs = st.executeQuery(SQL);//ejeucuta los valores de la sentecia sql 
            boolean hayDatos = false;
            while (rs.next()) {
                hayDatos = true;
                datos[0] = rs.getString(1);//obtiene los datos y los almacena en una posicion de un vector 
                datos[1] = rs.getString(2);//obtiene los datos y los almacena en una posicion de un vector 
                datos[2] = rs.getString(3);//obtiene los datos y los almacena en una posicion de un vector 
                datos[3] = rs.getString(4);//obtiene los datos y los almacena en una posicion de un vector 
                datos[4] = rs.getString(5);  //obtiene los datos y los almacena en una posicion de un vector                              
                modelo.addRow(datos);//muestra los datos obtenidos y los agrega en una tabla 
            }
            if (!hayDatos) {
                JOptionPane.showMessageDialog(this, "No hay datos");
                
                ;
                
            } else {
                jTable1.setModel(modelo);//selecciona la tabla por defecto del formulario y agrega las columnas
            }
            
                
            
                
            
           
        } catch (SQLException ex) {
            Logger.getLogger(GestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);// en caso de que las sentencias no funcionen se ejecutara esta accion que captura el error 
        }
    }
//Metodo de enviar los datos de la tabla a la factura 
    void enviar() {
        String identificacion = "", nom = "", ape = "", tel = "", dir = "";//variable en donde almacena los datos de la tabla 
        int fila = jTable1.getSelectedRow();//obtiene la fila seleccionada de la tabla
        try {
            if (fila == -1) {//determina si no ha sido seleccionado alguna fila
                JOptionPane.showMessageDialog(null, "No ha seleccionado ningun dato");//mensaje de alerta en caso de haber sido seleccionado alguna fila 
            } else {
                identificacion = (String) jTable1.getValueAt(fila, 0);//obtiene el valor y lo convierte a cada de caraterez y lo almacena 
                nom = (String) jTable1.getValueAt(fila, 1);//obtiene el valor y lo convierte a cada de caraterez y lo almacena 
                ape = (String) jTable1.getValueAt(fila, 2);//obtiene el valor y lo convierte a cada de caraterez y lo almacena 
                tel = (String) jTable1.getValueAt(fila, 3);//obtiene el valor y lo convierte a cada de caraterez y lo almacena 
                dir = (String) jTable1.getValueAt(fila, 4);//obtiene el valor y lo convierte a cada de caraterez y lo almacena 

                Facturación.FacturadeVenta.jTextField6.setDisabledTextColor(Color.blue);//busca los campos de texto en donde se capturan los datos y le asigna un color
                Facturación.FacturadeVenta.jTextField6.setText(identificacion);//seleccciona el texto de el campo de texto 
                Facturación.FacturadeVenta.jTextField7.setDisabledTextColor(Color.blue);//busca los campos de texto en donde se capturan los datos y le asigna un color
                Facturación.FacturadeVenta.jTextField7.setText(nom);//seleccciona el texto de el campo de texto 
                Facturación.FacturadeVenta.jTextField8.setDisabledTextColor(Color.blue);//busca los campos de texto en donde se capturan los datos y le asigna un color
                Facturación.FacturadeVenta.jTextField8.setText(ape);//seleccciona el texto de el campo de texto 
                Facturación.FacturadeVenta.jTextField9.setDisabledTextColor(Color.blue);//busca los campos de texto en donde se capturan los datos y le asigna un color
                Facturación.FacturadeVenta.jTextField9.setText(tel);//seleccciona el texto de el campo de texto 
                Facturación.FacturadeVenta.jTextField10.setDisabledTextColor(Color.blue);//busca los campos de texto en donde se capturan los datos y le asigna un color
                Facturación.FacturadeVenta.jTextField10.setText(dir);//seleccciona el texto de el campo de texto 
                this.dispose();//cierra la ventana
            }
        } catch (Exception e) {
            System.out.println("Error en la obtención de los datos"+e);//mensaje de error en caso de que las sentencia no funcionen correctamente
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
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jMenuItem1.setText("Enviar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(466, 184));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/look.png"))); // NOI18N
        jButton1.setText("Mostrar Todos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Facturación/sent.png"))); // NOI18N
        jButton2.setText("Enviar a Factura");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setOpaque(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //evento - boton enviar datos 
        enviar();//llama el metodo de enviar datos a la tabla de factura 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //evento - jmenu item de la tabla - enviar datos a la tabla de factura 
        enviar();//llama el metodo de enviar datos a la tabla de factura 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mostrardatos(jTextField1.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     * 
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
