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
package IniciodeSesión;// paquete en donde se encuentra el formulario
//librerias del formulario
import BienvenidoAdministrador.BienvenidoAdministrador;
import BienvenidoVendedor.BienvenidoVendedor;
import ConexionBasedeDatos.conexion;
import java.awt.event.KeyEvent;
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

public final class IniciodeSesión extends javax.swing.JFrame {
    /**
     * Creates new form IniciodeSesión
     */
    //Conexión Global de los formularios con la base de datos//
    conexion cc = new conexion();//llama al metodo conexion de la clase conexion//
    java.sql.Connection cn = cc.Conectar();//llama el metodo conectar de la clase conexion//
    public IniciodeSesión() {
        Principal();//Llama el metodo de evento del cierre del formulario        
        initComponents();//Iniciliza los componentes        
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
    // Validación de usuario//
    public void acceder(String usuario, String pass) {//Ciclo que determina los datos ingresado en la caja de texto con los datos de la BD
        String cap = "";//variable de captura de los datos 
        String sql = "Select * From gestiondeusuarios Where usuario='" + usuario + "' && contraseña=MD5('" + pass + "')";//compara los datos con los de BD
        try {
            Statement st = cn.createStatement();//conexion con la base de datos 
            ResultSet rs = st.executeQuery(sql);//Ejecuta el comando de consulta 
            while (rs.next()) {
                cap = rs.getString("tipo_usuario");//determina el tipo de usuario
             }
            if (cap.equals("Administrador")) {
                this.setVisible(false);//cierra el formulario de inicio de sesión
                JOptionPane.showMessageDialog(null, "Bienvenido señor Administrador");//mensaje de bienvenido segun el tipo de usuario
                BienvenidoAdministrador ingreso = new BienvenidoAdministrador();//obtiene el formulario 
                ingreso.setVisible(true);//ingresa a la respectiva interfaz segun el tipo de usuario
                ingreso.pack();//ingreso al sistema 
            }
            if (cap.equals("Vendedor")) {
                this.setVisible(false);//cierra el formulario de inicio de sesión
                JOptionPane.showMessageDialog(null, "Bienvenido señor Vendedor");//mensaje de bienvenido segun el tipo de usuario
                BienvenidoVendedor ingresos = new BienvenidoVendedor();//obtiene el formulario 
                ingresos.setVisible(true);//ingresa a la respectiva interfaz segun el tipo de usuario
                ingresos.pack();//ingreso al sistema 
            }
            if ((!cap.equals("Vendedor")) && (!cap.equals("Administrador"))) {//determina si los datos no tiene similitud a los de la BD muestre el siguiente mensaje
                JOptionPane.showMessageDialog(this, "El usuario que ingreso no esta registrado"); //mensaje de aviso de datos mas ingresado o usuario no registrado 
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(IniciodeSesión.class.getName()).log(Level.SEVERE, null, ex);//en caso de no cumplir ninguna de las sentencias anteriores ejecuta este comando 
        }
    }
    public void Principal(){
    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);//Seleciona la operación por defecto del formulario
    addWindowListener(new java.awt.event.WindowAdapter(){//Adaptador del evento del cierre del formulario
    @Override
            public void windowClosing(java.awt.event.WindowEvent evt){
           confirmarcierredesesion();//Llam el evento que confirma la salida del formulario
    }
    });    
    }
    //Confirmacion del cierre de sesión
    public void confirmarcierredesesion(){//Iniciacion de la funcion
    int val = JOptionPane.showConfirmDialog(this,"¿Desea Salir de la Aplicacón?","Advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);//mensaje de confirmacion de cierre de confirmacion
        if(val==JOptionPane.YES_OPTION){//inicializacion del ciclo 
           JOptionPane.showMessageDialog(null,"Hasta Pronto","Gracias",JOptionPane.INFORMATION_MESSAGE);//mensaje del cierre de sesion      
           System.exit(0);//cierra el formulario de interfaz de admin                
        }     
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Iniciar Sesión ");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IniciodeSesión/logo.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IniciodeSesión/Usuario Formulario Logeo.png"))); // NOI18N
        jLabel1.setText("Usuario:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IniciodeSesión/Contraseña Formulario Logeo.png"))); // NOI18N
        jLabel2.setText("Contraseña:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("--Ayuda--");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(48, 48, 48)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IniciodeSesión/Ingresar Formulario Logeo.png"))); // NOI18N
        jButton2.setText("Ingresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IniciodeSesión/Salir Formulario Logeo.png"))); // NOI18N
        jButton3.setText("Cerrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Cerrar Aplicación//
    confirmarcierredesesion();//llama el metodo para confirmar la salida de la aplicación        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // Mensaje de ayuda 
        JOptionPane.showMessageDialog(null, "Los campos de Inicio de Sesión solo permiten caracteres alfanuméricos");//mensaje de aviso, ingreso correcto de datos
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // Evento - salto de textbox con la tecla enter
        char cTeclaPresionada=evt.getKeyChar();//determina si la tecla enter fue precionada
        if(cTeclaPresionada==KeyEvent.VK_ENTER){//ciclo if que determina la tecla precionada
            jTextField1.transferFocus();//salta a otra accion
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped
        // Evento - salto de textbox con la tecla enter
        char cTeclaPresionada=evt.getKeyChar();//determina si la tecla enter fue precionada
        if(cTeclaPresionada==KeyEvent.VK_ENTER){//ciclo if que determina la tecla precionada
            jButton2.doClick();//salta a otra accion
        }
    }//GEN-LAST:event_jPasswordField1KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       //obtiene los datos ingresados en el textbox para validarlos en el metodo de acceder
        String usu = jTextField1.getText();//obtiene los datos ingresados
        String pas = new String(jPasswordField1.getPassword());//obtiene los datos ingresados
        acceder(usu, pas);//valida los datos ingresados en el metodo de acceder
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        //Restricción del campo de texto
        if (!jTextField1.getText().matches("[ 0-9-a-z-A-Z]*"))//Se determina los caracteres que el campo de texto podra recibir
        { JOptionPane.showMessageDialog(null,"SOLO SE PERMITEN CARACTERES ALFANUMÉRICOS","Advertencia",JOptionPane.WARNING_MESSAGE);//Mensaje de aviso en caso de que el usuario haya ingresado mal el caracter
        jTextField1.setText("");//Limpiar la caja de texto 
        jTextField1.requestFocus();//Reinicia el ingreso de los carateres del campo de texto
        }
        
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jPasswordField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyReleased
        //Restricción del campo de texto
        if (!jPasswordField1.getText().matches("[ 0-9-a-z-A-Z]*"))//Se determina los caracteres que el campo de texto podra recibir
        { JOptionPane.showMessageDialog(null,"SOLO SE PERMITEN CARACTERES ALFANUMÉRICOS","Advertencia",JOptionPane.WARNING_MESSAGE);//Mensaje de aviso en caso de que el usuario haya ingresado mal el caracter
        jPasswordField1.setText("");//Limpiar la caja de texto 
        jPasswordField1.requestFocus();//Reinicia el ingreso de los carateres del campo de texto
        }
    }//GEN-LAST:event_jPasswordField1KeyReleased

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IniciodeSesión.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new IniciodeSesión().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
