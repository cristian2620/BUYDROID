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

package ConsultaUsuarios;//paquete en donde se encuentra el formulario
//librerias del formulario
import ConexionBasedeDatos.conexion;
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
 * @author Proyecto BUYDROID
 */
public final class ConsultaUsuarios extends javax.swing.JFrame {
 //Conexión Global de los formularios con la base de datos//
    conexion cc = new conexion();//llama al metodo conexion de la clase conexion//
    java.sql.Connection cn = cc.Conectar();//llama el metodo conectar de la clase conexion//
    /**
     * Creates new form ConsultaUsuarios
     */
    public ConsultaUsuarios() {
        initComponents();//Iniciliza los componentes  
        mostrardatos("");
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
        DefaultTableModel modelo = new DefaultTableModel();//tabla por defecto del formulario
        modelo.addColumn("id_usuario");//agrega una columna en la tabla 
        modelo.addColumn("nombre");//agrega una columna en la tabla 
        modelo.addColumn("apellido"); //agrega una columna en la tabla        
        modelo.addColumn("telefono");//agrega una columna en la tabla 
        modelo.addColumn("direccion");//agrega una columna en la tabla 
        modelo.addColumn("usuario");//agrega una columna en la tabla 
        modelo.addColumn("contraseña");//agrega una columna en la tabla 
        modelo.addColumn("tipo_usuario");//agrega una columna en la tabla 
        jTable1.setModel(modelo);// selecciona la tabla por defecto y agrega las columnas

        String SQL = "";//variable string en donde se almacenara la sentencia SQL
        if (valor.equals("")) { SQL = "SELECT * FROM gestiondeusuarios";}//Hace la consulta en la base de datos segun el valor ingresado 
        else {SQL = "SELECT * FROM gestiondeusuarios WHERE id_usuario='" + valor + "'";}//Hace la consulta en la base de datos segun el valor ingresado y busca por id
        String[] datos = new String[9];//declara un vector en donde se almacenaran los datos
        try {
            Statement st = cn.createStatement();//establece los valores para la sentencia sql 
            ResultSet rs = st.executeQuery(SQL);//ejeucuta los valores de la sentecia sql 
            while (rs.next()) {
                datos[0] = rs.getString(1);//obtiene los datos y los almacena en una posicion de un vector  
                datos[1] = rs.getString(2);//obtiene los datos y los almacena en una posicion de un vector  
                datos[2] = rs.getString(3);//obtiene los datos y los almacena en una posicion de un vector  
                datos[3] = rs.getString(4);//obtiene los datos y los almacena en una posicion de un vector  
                datos[4] = rs.getString(5);//obtiene los datos y los almacena en una posicion de un vector  
                datos[5] = rs.getString(6);//obtiene los datos y los almacena en una posicion de un vector  
                datos[6] = rs.getString(7);//obtiene los datos y los almacena en una posicion de un vector  
                datos[7] = rs.getString(8);//obtiene los datos y los almacena en una posicion de un vector  
                modelo.addRow(datos);//muestra los datos obtenidos y los agrega en una tabla 
            }
            jTable1.setModel(modelo);//selecciona la tabla por defecto del formulario y agrega las columnas
        } catch (SQLException ex) {
            Logger.getLogger(GestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);// en caso de que las sentencias no funcionen se ejecutara esta accion que captura el error 
        }
    }
 //Metodo de enviar los datos de la tabla a la factura    
    void enviar (){
    String id_usuario="",nom="",ape="";//variable en donde almacena los datos de la tabla 
    int fila = jTable1.getSelectedRow();//obtiene la fila seleccionada de la tabla
    try {
        if(fila==-1){//determina si no ha sido seleccionado alguna fila
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun dato");              
        }else {
         id_usuario = (String)jTable1.getValueAt(fila, 0);//obtiene el valor y lo convierte a cada de caraterez y lo almacena 
         nom = (String)jTable1.getValueAt(fila, 1);//obtiene el valor y lo convierte a cada de caraterez y lo almacena 
         ape = (String)jTable1.getValueAt(fila, 2);//obtiene el valor y lo convierte a cada de caraterez y lo almacena 
         
         Facturación.FacturadeVenta.jTextField3.setDisabledTextColor(Color.blue);//busca los campos de texto en donde se capturan los datos y le asigna un color
         Facturación.FacturadeVenta.jTextField3.setText(id_usuario);//seleccciona el texto de el campo de texto 
         Facturación.FacturadeVenta.jTextField4.setDisabledTextColor(Color.blue);//busca los campos de texto en donde se capturan los datos y le asigna un color
         Facturación.FacturadeVenta.jTextField4.setText(nom);//seleccciona el texto de el campo de texto 
         Facturación.FacturadeVenta.jTextField5.setDisabledTextColor(Color.blue);//busca los campos de texto en donde se capturan los datos y le asigna un color
         Facturación.FacturadeVenta.jTextField5.setText(ape);//seleccciona el texto de el campo de texto 
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        jMenuItem1.setText("Enviar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setOpaque(false);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/look.png"))); // NOI18N
        jButton1.setText("Mostrar Todos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.setOpaque(false);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Facturación/sent.png"))); // NOI18N
        jButton2.setText("Enviar a Factura");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        //Evento - boton de enviar los datos a la factura
         mostrardatos("");//llama el metodo de enviar datos a la tabla de factura 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        //Campo de texto de busqueda 
         mostrardatos(jTextField1.getText());//metodo de mostrar datos- valida los datos ingresado en txtbusqueda 
        String valor=" ";//Variable en donde almecena los datos ingresado para la busqueda
        if (valor.equals("id_usuario")) {
            JOptionPane.showMessageDialog(null, "Usuario no registrador");//mensaje de alerta que avisa que el usuario no se encuentra regisrado 
        }
        jTextField1.setText("");//limpia el campo de texto 
        jTextField1.requestFocus();//deja el curso en el campo de texto 
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // Llama el metodo enviar.
        enviar();//llama el metodo de enviar datos a la tabla de factura 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // Llama el metodo enviar.
        enviar();//llama el metodo de enviar datos a la tabla de factura 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
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
