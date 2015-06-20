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
package ConsultadeProducto;//paquete en donde se encuentra el formulario 
//librerias del formulario
import ConexionBasedeDatos.conexion;
import GestiondeUsuarios.GestionUsuarios;
import java.awt.Image;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PROYECTO BUYDROID
 */
public final class Consulta extends javax.swing.JFrame {
    /**
     * Creacion del formulario BienvenidoAdministrador
     */ 
    private JFrame contenedor;//variable global para devolverse a la interfaz 
    //Conexión Global de los formularios con la base de datos//
    conexion cc = new conexion();//llama al metodo conexion de la clase conexion//
    java.sql.Connection cn = cc.Conectar();//llama el metodo conectar de la clase conexion//

    public Consulta(JFrame contenedor) {
        initComponents();//Iniciliza los componentes  
        this.contenedor=contenedor;//variable global para devolverse a la interfaz 
        try{
        mostrardatos("");}
        catch(SQLException | IOException e){}              
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

    void mostrardatos(String valor) throws SQLException, IOException{
DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Identificación");//agrega una columna en la tabla 
        modelo.addColumn("Tipo");//agrega una columna en la tabla 
        modelo.addColumn("Gama");//agrega una columna en la tabla     
        modelo.addColumn("Referencia");//agrega una columna en la tabla 
        modelo.addColumn("Valor");//agrega una columna en la tabla 
        modelo.addColumn("Existencia");//agrega una columna en la tabla 
        modelo.addColumn("Marca");//agrega una columna en la tabla 
        jTable2.setModel(modelo);// selecciona la tabla por defecto y agrega las columnas

        String sql1,SQL = "";//variable string en donde se almacenara la sentencia SQL
        if (valor.equals("")) { SQL = "SELECT id_producto,tipo,gama,referencia,valor,existencia,marca,detalle FROM producto";} //Hace la consulta en la base de datos segun el valor ingresado 
        else {
            SQL = "SELECT id_producto,tipo,gama,referencia,valor,existencia,marca,detalle FROM producto WHERE id_producto='" + valor + "'||referencia='"+valor+"'||marca='"+valor+"'";//Hace la consulta en la base de datos segun el valor ingresado 
            sql1="SELECT imagen_producto FROM producto WHERE id_producto='" + valor +"'  limit 1"; //Hace la consulta en la base de datos segun el valor ingresado para obtener su imagen 
            abrirImagen(sql1);//llama el metdo de obtencion de la imagen 
        }
        String[] datos = new String[11];//declara un vector en donde se almacenaran los datos
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
                //jTextArea1.setText(rs.getString(8)); //obtiene los datos y los almacena en una posicion de un vector  
                modelo.addRow(datos);//muestra los datos obtenidos y los agrega en una tabla                   
            } 
            jTable2.setModel(modelo);//selecciona la tabla por defecto del formulario y agrega las columnas
        } catch (SQLException ex) {
            Logger.getLogger(GestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirImagen(String sql) {
        try{
        Image rpta;  
        //En este ejemplo solo recupero la primera Imagen 
         Statement st = cn.createStatement();//establece los valores para la sentencia sql 
         ResultSet results = st.executeQuery(sql);//ejeucuta los valores de la sentecia sql 
         Blob imagen=null; //variable de la imagen nula
        
        while(results.next())
        imagen = results.getBlob("imagen_producto");//resultado en la imagen
       // System.out.println("Cargando la imagen"+imagen);
        rpta= javax.imageio.ImageIO.read(imagen.getBinaryStream()).getScaledInstance( jLabel9.getWidth(),jLabel9.getHeight(), Image.SCALE_DEFAULT);//obtine la imagen y la escala al label 
        
        JLabel picLabel = new JLabel(new ImageIcon(rpta));//agrega la imagen al label
        jLabel9.setIcon(picLabel.getIcon());//selecciona el icono 
        }
        catch(SQLException | IOException | NullPointerException e){
        System.out.println(e.getMessage());// en caso de que las sentencias no funcionen se ejecutara esta accion que captura el error 
        }      
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTextField7 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jMenuItem1.setText("Llamar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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
        jScrollPane3.setViewportView(jTable2);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagen Producto"));
        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Descripción Producto:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/exit.png"))); // NOI18N
        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(211, 211, 211))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {mostrardatos(jTextField7.getText());} //muestra los datos segun la busqueda
        catch(SQLException | IOException e){System.out.println(e+"error");}// en caso de que las sentencias no funcionen se ejecutara esta accion que captura el error 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //boton cerrar//
         int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea salir ?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);//mensaje de confirmación para cerrar el fomulario
        if (valor == JOptionPane.YES_OPTION) { //Si en el mensaje el usuario le dio que si seguira con el siclo.      
            BienvenidoAdministrador.BienvenidoAdministrador  salir = new BienvenidoAdministrador.BienvenidoAdministrador ();//llamar el formulario de interfaz de bienvenida usuario para salir del anterior formulario
            salir.setVisible(true);//muestra el formulario del interfaz admin
            this.setVisible(false);//cierra el formulario de ayuda
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.contenedor.setVisible(true);//Acccion al cerrar la el formulario
    }//GEN-LAST:event_formWindowClosed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        try {
            // TODO add your handling code here:
            mostrardatos(jTextField7.getText());//muestra los datos segun la busqueda
                    } catch (SQLException | IOException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);// en caso de que las sentencias no funcionen se ejecutara esta accion que captura el error 
        }
    }//GEN-LAST:event_jTextField7KeyReleased



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
