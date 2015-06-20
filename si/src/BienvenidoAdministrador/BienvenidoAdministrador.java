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
package BienvenidoAdministrador;//paquete en donde se encuentra el formulario Jframe
//librerias del formulario
import ConexionBasedeDatos.conexion;
import IniciodeSesión.IniciodeSesión;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;

public final class BienvenidoAdministrador extends javax.swing.JFrame {
    /**
     * Creacion del formulario BienvenidoAdministrador
     */ 
    //Conexión Global de los formularios con la base de datos//
    conexion cc = new conexion();//llama al metodo conexion de la clase conexion//
    java.sql.Connection cn = cc.Conectar();//llama el metodo conectar de la clase conexion//
    public BienvenidoAdministrador() {        
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
    //Evento del cierre del formulario 
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
    int valor = JOptionPane.showConfirmDialog(this,"¿Desea Cerrar Sesión?","Advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);//mensaje de confirmacion de cierre de confirmacion
        if(valor==JOptionPane.YES_OPTION){//inicializacion del ciclo ()
            JOptionPane.showMessageDialog(null,"Hasta Pronto","Gracias",JOptionPane.INFORMATION_MESSAGE);//mensaje del cierre de sesion
            IniciodeSesión  salir = new IniciodeSesión ();//llama el formulario de logeo
            salir.setVisible(true);//muestra el formurio de logeo            
            this.dispose();//cierra el formulario de interfaz de admin                
        }     
    }
     public Date sumarRestarDiasFecha(Date fecha, int dias){
	
 	
      Calendar calendar = Calendar.getInstance();	
      calendar.setTime(fecha); // Configuramos la fecha que se recibe	
      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

	
      return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	
 
	
 }
    //llama el formulario de consulta de producto 
    public void consultadeproducto(){//metodo
    //llamar el formulario a la interfaz//               
       ConsultadeProducto.Consulta consul = new ConsultadeProducto.Consulta(this);//Obtiene el formulario
       consul.setVisible(true);//muestra el formulario
       this.setVisible(false);//Cierra la interfaz de administrador      
    }
    //llamae el formulario de facturación
    public void facturacion(){//metodo
    //llamar el formulario a la interfaz//
       Facturación.FacturadeVenta fac = new Facturación.FacturadeVenta(this);//Obtiene el formulario
       fac.setVisible(true);//muestra el formulario
       this.setVisible(false);//Cierra la interfaz de administrador  
    }
    //llama el formuario de reportes 
    public void reportes(){//metodo
    //llamar el formulario a la interfaz//
       GenerarReportes.Reportes salir = new GenerarReportes.Reportes(this);//Obtiene el formulario
       salir.setVisible(true);//muestra el formulario
       this.setVisible(false);//Cierra la interfaz de administrador  
    }
    //llama el formuario de gestion de usuarios
    public void usuarios(){//metodo
    //llamar el formulario a la interfaz//
       GestiondeUsuarios.GestionUsuarios  salir = new GestiondeUsuarios.GestionUsuarios(this);//Obtiene el formulario
       salir.setVisible(true);//muestra el formulario
       this.setVisible(false);//Cierra la interfaz de administrador  
    } 
    //llama el formuario de gstion de clientes
    public void cliente(){//metodo
    //llamar el formulario a la interfaz//
       GestiondeClientes.GestiondeClientes cli = new GestiondeClientes.GestiondeClientes(this);//Obtiene el formulario
       cli.setVisible(true);//muestra el formulario
       this.setVisible(false);//Cierra la interfaz de administrador 
    }
    //llama el formuario de ayuda al usuario
    public void ayuda(){//metodo
    //llamar el formulario a la interfaz//       
       AyudaUsuario.Ayuda ayu = new AyudaUsuario.Ayuda (this);//Obtiene el formulario
       ayu.setVisible(true);//muestra el formulario
       this.setVisible(false);//Cierra la interfaz de administrador
    }
    //llama el formuario de registto de productos
    public void productos(){//metodo
        //llamar el formulario a la interfaz//
       RegistrodeProductos.Productos pro = new RegistrodeProductos.Productos(this);//Obtiene el formulario
       pro.setVisible(true);//muestra el formulario
       this.setVisible(false);//Cierra la interfaz de administrador
    }
    //llama el formuario de 
    public void garantia(){//metodo
        //llamar el formulario a la interfaz//
       ValidacionProducto.ValidcacionProducto vp = new ValidacionProducto.ValidcacionProducto(this);//Obtiene el formulario
       vp.setVisible(true);//muestra el formulario
       this.setVisible(false);//Cierra la interfaz de administrador
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Bienvenido Administrador");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Consulta.png"))); // NOI18N
        jButton1.setText("Consulta");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Reportes2.png"))); // NOI18N
        jButton2.setText("Reportes");
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Usuarios.png"))); // NOI18N
        jButton3.setText("Gestión de Usuario");
        jButton3.setContentAreaFilled(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/factur.png"))); // NOI18N
        jButton4.setText("Facturación");
        jButton4.setContentAreaFilled(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Garan.png"))); // NOI18N
        jButton5.setText("Validación de Garantia");
        jButton5.setContentAreaFilled(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/hhelp.png"))); // NOI18N
        jButton6.setText("Ayuda");
        jButton6.setContentAreaFilled(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Cerrar Sesión_1.png"))); // NOI18N
        jButton7.setText("Cerrar Sesión");
        jButton7.setContentAreaFilled(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Productos.png"))); // NOI18N
        jButton8.setText("Ventas");
        jButton8.setContentAreaFilled(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Clientes.png"))); // NOI18N
        jButton9.setText("Gestión Clientes");
        jButton9.setContentAreaFilled(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/VEn.png"))); // NOI18N
        jButton10.setText("Productos");
        jButton10.setContentAreaFilled(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Ini.png"))); // NOI18N
        jMenu1.setText("Inicio");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/cer.png"))); // NOI18N
        jMenuItem1.setText("Cerrar Sesión");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/apa.png"))); // NOI18N
        jMenuItem2.setText("Salir de la Aplicación");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Rep.png"))); // NOI18N
        jMenu2.setText("Reportes");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/fa.png"))); // NOI18N
        jMenu3.setText("Facturación");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/pro.png"))); // NOI18N
        jMenu4.setText("Productos");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/cp.png"))); // NOI18N
        jMenuItem3.setText("Consulta de Productos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/rp.png"))); // NOI18N
        jMenuItem4.setText("Registro de Producto");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/Val.png"))); // NOI18N
        jMenuItem5.setText("Validación de Garantia");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/usu.png"))); // NOI18N
        jMenu5.setText("Usuarios");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/cli.png"))); // NOI18N
        jMenu6.setText("Cliente");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5)
                    .addComponent(jButton10))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        //llamar el formulario a la interfaz//
        //Evento -  contador de click's
        if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
         reportes();//llama el metodo que mostrara el formulario
         }      
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        //Evento -  contador de click's
        if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
         facturacion();//llama el metodo que mostrara el formulario
         }
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        //Evento -  contador de click's
             if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
             consultadeproducto();//llama el metodo que mostrara el formulario
} 
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        //Evento -  contador de click's
         if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
             facturacion();//llama el metodo que mostrara el formulario
    } 
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
       //Evento -  contador de click's
         if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
            usuarios();//llama el metodo que mostrara el formulario
    }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        //Evento -  contador de click's
         if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
             cliente();//llama el metodo que mostrara el formulario
    }
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
       //Evento -  contador de click's
         if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
            ayuda();//llama el metodo que mostrara el formulario
    }
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
       //Evento -  contador de click's
         if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
         productos();//llama el metodo que mostrara el formulario
         }
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        //Evento -  contador de click's
        if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
         confirmarcierredesesion();//llama el metodo que mostrara el formulario
         }
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
       //Evento -  contador de click's
        if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
         reportes();//llama el metodo que mostrara el formulario
         }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        //Evento -  contador de click's
        if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
         usuarios();//llama el metodo que mostrara el formulario
         }
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
       //Evento -  contador de click's
        if(evt.getClickCount()==2){   //ciclo que obtiene el número de click para abrir el formulario
         cliente();//llama el metodo que mostrara el formulario
         }
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Evento - archivo - cerrar sesión
        confirmarcierredesesion();//llama el metodo que mostrara el formulario
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //Evento - archivo - salir aplicación
        System.exit(0);//Sale del aplicación directamente
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //Evento - item de producto
        consultadeproducto();//llama el metodo que mostrara el formulario
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        //Evento - item de produtos
        garantia();//llama el metodo que mostrara el formulario
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        //Evento - item de productos
        productos();//llama el metodo que mostrara el formulario
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        //Evento -  contador de click's
        if(evt.getClickCount()==2){  //ciclo que obtiene el número de click para abrir el formulario
         garantia();//llama el metodo que mostrara el formulario
         }
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
         //Evento -  contador de click's
        if(evt.getClickCount()==2){//ciclo que obtiene el número de click para abrir el formulario
        try {// try - cacth /
        JasperReport reporteJasper = JasperCompileManager.compileReport("clientes.jrxml");//compila el reporte
        JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, null,this.cn);//visualiza el reporte 
        JasperViewer.viewReport(mostrarReporte);//muestra el reporte
        } 
        catch (Exception e) {
        JOptionPane.showMessageDialog(null,"El archivo que solicita a no se a podido encontrar"+e);//mesaje de error 
    }
    }
    }//GEN-LAST:event_jButton8MouseClicked

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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
}
