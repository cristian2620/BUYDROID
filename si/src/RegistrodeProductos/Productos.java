/*
 * Conexión de netbeans con la base de datos
 *
 * version 2.0
 *
 * Fecha: 23/04/2015
 *
 * Copyright Elaborado por :
                            Angello Stivén Andrade Chacón
                            Cristian Andres Hurtado Gil
                            Nicolás David Ruiz Naji
 */
package RegistrodeProductos;//paquete en donde se encuentra la clase java
//librerias para que las programación funcione correctamente
import ConexionBasedeDatos.conexion;
import GestiondeUsuarios.GestionUsuarios;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public final class Productos extends javax.swing.JFrame {
    private JFrame contenedor;
    //Conexión Global de los formularios con la base de datos//
    conexion cc = new conexion();//llama al metodo conexion de la clase conexion//
    java.sql.Connection cn = cc.Conectar();//llama el metodo conectar de la clase conexion//
    //función para llamar los componentes del formulario   
    public Productos(JFrame contenedor) {
        this.contenedor=contenedor;
        //Inicia los componentes//
        initComponents();
        //Para que el formulario aparezca centrado
        setLocationRelativeTo(null);
        //Para qe el formulario no se puede expandir//
        setResizable(false);
        //mostrar datos de la tabla en el formulario//
        mostrardatos("");
        //comienza la autonumeracion//
        Generarnumeracion();
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
    //Metodo limpiar: es para limpiar el formulario completamente//
    public void limpiar() {
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextArea1.setText("");
        txtnomimagen.setText("");
        lblfoto.setText("");
        lblfoto.setIcon(new ImageIcon());
        jTextField1.requestFocus();
    }
    //Metodo mostrar datos: es para mostrar o actualizar los datos de la tabla en caso de que se haga una operacion o transacion en la tabla
    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("ID Producto");
        modelo.addColumn("Tipo");
        modelo.addColumn("Gama");        
        modelo.addColumn("Referencia");
        modelo.addColumn("Valor");
        modelo.addColumn("Existencia");
        modelo.addColumn("Marca");
        modelo.addColumn("Detalle");     
        jTable1.setModel(modelo);

        String SQL = "";
        if (valor.equals("")) {
        SQL = "SELECT * FROM producto";
        } 
        else {
            SQL = "SELECT * FROM producto WHERE id_producto='" + valor + "'";
        }
        String[] datos = new String[10];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(GestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Metodo Generación: es para que muestre el siguiente id de la base de datos 
    void Generarnumeracion() {
        String SQL = "select max(id_producto) from producto";
        int c = 0;
        int b = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getInt(1);
            }
            if (c == 0) {
                jTextField1.setText("1");
            } else {
                jTextField1.setText("" + (c + 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Metodo Confirmar eliminacion: es para confirmar la eliminación de un producto de la base de datos
    public void confirmareliminacion(){
        int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de elimar el producto", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (valor == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "El usuario se ha eliminado correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            int fila = jTable1.getSelectedRow();
            String id = "";
            id = jTable1.getValueAt(fila, 0).toString();
            try {
                PreparedStatement pst = cn.prepareStatement("DELETE FROM producto WHERE id_producto='" + id + "'");
                pst.executeUpdate();
                mostrardatos("");
                limpiar();
            } catch (SQLException ex) {
                limpiar();
            }
        }
    }
    //Metodo Confirmar modificacion: es para confirmar la actulización del producto 
     public void confirmacionmodificar() {
        int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea modificar el producto?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (valor == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Usuario modificado", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            try {
                PreparedStatement pst = cn.prepareStatement("UPDATE producto SET tipo='" + jComboBox1.getSelectedItem().toString() + "',gama ='"+jTextField2.getText()+"'"
                        + ",referencia ='"+jTextField3.getText()+"',valor ='"+jTextField4.getText()+"',existencia ='"+jTextField5.getText()+"',marca='" + jTextField6.getText() 
                        +"',detalle='"+jTextArea1.getText()+ "'WHERE id_producto='" + jTextField1.getText() + "'");
                pst.executeUpdate();
                mostrardatos("");
                limpiar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        lblfoto = new javax.swing.JLabel();
        txtnomimagen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jMenuItem1.setText("Modificar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Eliminar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Productos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ID Producto:");

        jTextField1.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tipo:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Accesorio", "Celular" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Gama:");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Referencia:");

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Valor:");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Existencia:");

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Detalles:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagen del Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        jPanel2.setOpaque(false);

        lblfoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblfoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblfoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblfotoMouseClicked(evt);
            }
        });

        txtnomimagen.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnomimagen)
                    .addComponent(lblfoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnomimagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Marca:");

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 203, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5)
                            .addComponent(jTextField4)
                            .addComponent(jTextField3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(jTextField6))))
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/save.png"))); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/Mod.png"))); // NOI18N
        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/del.png"))); // NOI18N
        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/exit.png"))); // NOI18N
        jButton4.setText("Cerrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/look.png"))); // NOI18N
        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(57, 57, 57)
                        .addComponent(jButton2)
                        .addGap(68, 68, 68)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //boton cerrar//
         int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea salir ?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (valor == JOptionPane.YES_OPTION) {            
            BienvenidoAdministrador.BienvenidoAdministrador  salir = new BienvenidoAdministrador.BienvenidoAdministrador ();
            salir.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Botón de registro
        int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de registrar el producto", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);//Ventana emergente para confirmar el registro del producto
        if (valor == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "El producto se ha registrado correctamente", "Confirmacion de registro", JOptionPane.INFORMATION_MESSAGE);
            try {
                // Creamos una variable para poder obtener la cantidad de bytes de una imagen
                FileInputStream  archivofoto;
                PreparedStatement pst = cn.prepareStatement("INSERT INTO producto(id_producto,tipo,gama,referencia,valor,existencia,marca,detalle,rutaimagen,imagen_producto)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?)");
                //Obtiene los datos del ingreso del producto y lo refleja en la tabla del jframe
                pst.setString(1, jTextField1.getText());
                pst.setString(2, jComboBox1.getSelectedItem().toString());
                pst.setString(3, jTextField2.getText());
                pst.setString(4, jTextField3.getText());
                pst.setString(5, jTextField4.getText());
                pst.setString(6, jTextField5.getText());
                pst.setString(7, jTextField6.getText());
                pst.setString(8, jTextArea1.getText());
                pst.setString(9, txtnomimagen.getText());// pero para ello necesitamos conocer la ruta de la imagen
                archivofoto = new FileInputStream(txtnomimagen.getText());
                //SetBinaryStream permite almacenar cantidad de bytes en la base de datos
                pst.setBinaryStream(10, archivofoto);                          
                Generarnumeracion();//reiniciar la generacion de numeros del id     
                int n = pst.executeUpdate();// ejecuta la sentencia sql 
                mostrardatos("");// realiza la actualizacion de los resgistro en la tabla del jframe
                if (n > 0){}//determina si el valor fue mayor a 0
                limpiar();//limpia el formulario
                Generarnumeracion();//genera los numeros el id
            } catch (SQLException ex) {//exepcion en caso de tener algun problema con el codigo SQL
                JOptionPane.showMessageDialog(null, ex);
            } catch (FileNotFoundException ex) {//execepcion en caso de que el programa reviente
                Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            limpiar();//limpia el formulario en caso de que todo salga mal
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void lblfotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblfotoMouseClicked
        // 
         //Creamos nuestra variable archivo en la cual podremos usar todos los metodos de la clase jFileChooser
        JFileChooser archivo = new JFileChooser();
        //Si deseamos crear filtros para la selecion de archivos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG)", "jpg","jpeg");
        //Si deseas que se muestre primero los filtros usa la linea q esta abajo de esta.
        //archivo.setFileFilter(filtro);
        // Agregamos el Filtro pero cuidado se mostrara despues de todos los archivos
        archivo.addChoosableFileFilter(filtro);
        // Colocamos titulo a nuestra ventana de Seleccion
        archivo.setDialogTitle("Abrir Archivo");
        //Si deseamos que muestre una carpeta predetermina usa la siguiente linea
        File ruta = new File("E:\\IMAGENES PRODUCTOS BUYDROID");
        //Le implementamos a nuestro ventana de seleccion
         archivo.setCurrentDirectory(ruta);
         //Abrimos nuestra Ventana de Selccion
        int ventana = archivo.showOpenDialog(null);
        //hacemos comparacion en caso de aprete el boton abrir
        if(ventana == JFileChooser.APPROVE_OPTION)
        {
            //Obtenemos la ruta de nuestra imagen seleccionada
            File file = archivo.getSelectedFile();
            //Lo imprimimos en una caja de texto para ver su ruta
            txtnomimagen.setText(String.valueOf(file));
            //de cierto modo necesitamos tener la imagen para ello debemos conocer la ruta de dicha imagen
            Image foto= getToolkit().getImage(txtnomimagen.getText());
            //Le damos dimension a nuestro label que tendra la imagen
            foto= foto.getScaledInstance(lblfoto.getWidth(), lblfoto.getHeight(), Image.SCALE_DEFAULT);
            //Imprimimos la imagen en el label
            lblfoto.setIcon(new ImageIcon(foto));
        }
        
    }//GEN-LAST:event_lblfotoMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //LLamar los datos de la tabla a los textbox para que sean moficados
        int fila = jTable1.getSelectedRow();
        if (fila >= 0) {
            jTextField1.setText(jTable1.getValueAt(fila, 0).toString());
            jComboBox1.setSelectedItem(jTable1.getValueAt(fila, 1).toString());
            jTextField2.setText(jTable1.getValueAt(fila, 2).toString());
            jTextField3.setText(jTable1.getValueAt(fila, 3).toString());
            jTextField4.setText(jTable1.getValueAt(fila, 4).toString());
            jTextField5.setText(jTable1.getValueAt(fila, 5).toString());
            jTextField6.setText(jTable1.getValueAt(fila, 6).toString());
            jTextArea1.setText(jTable1.getValueAt(fila, 7).toString());
        } else {
            JOptionPane.showMessageDialog(null, "No se selecciono fila");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // botón de Actualizar
        confirmacionmodificar();//Llamar el metodo de confirmar la modificación
        mostrardatos("");//Muestra los datos actualizados
        Generarnumeracion();//Genera los números del id 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //Opcion de elimar directamente un producto
        confirmareliminacion();//llamar el metodo de eliminacion
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Boton Eliminar
         confirmareliminacion();//llama el metodo de confirmar eliminacion
         mostrardatos("");//llama el metodo de mostrar los datos
         Generarnumeracion();//llama el metodo de generear la númeracion del id
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //botón de buscar 
        mostrardatos(txtbuscar.getText());//buscar en la tabla segun el valor ingresado en textbox de busqueda
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
        //Restricción del campo de texto
        
        
    }//GEN-LAST:event_jTextArea1KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        //Restricción del campo de texto
        if (!jTextField6.getText().matches("[ 0-9-a-z-A-Z]*"))//Se determina los caracteres que el campo de texto podra recibir
        { JOptionPane.showMessageDialog(null,"SOLO SE PERMITEN CARACTERES ALFANUMÉRICOS","Advertencia",JOptionPane.WARNING_MESSAGE);//Mensaje de aviso en caso de que el usuario haya ingresado mal el caracter
        jTextField6.setText("");//Limpiar la caja de texto 
        jTextField6.requestFocus();//Reinicia el ingreso de los carateres del campo de texto
        } 
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        //Restricción del campo de texto
        if (!jTextField5.getText().matches("[ 0-9--]*"))//Se determina los caracteres que el campo de texto podra recibir
        { JOptionPane.showMessageDialog(null,"SOLO SE PERMITEN CARACTERES ALFANUMÉRICOS","Advertencia",JOptionPane.WARNING_MESSAGE);//Mensaje de aviso en caso de que el usuario haya ingresado mal el caracter
        jTextField5.setText("");//Limpiar la caja de texto 
        jTextField5.requestFocus();//Reinicia el ingreso de los carateres del campo de texto
        } 
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        //Restricción del campo de texto
        if (!jTextField4.getText().matches("[ 0-9--]*"))//Se determina los caracteres que el campo de texto podra recibir
        { JOptionPane.showMessageDialog(null,"SOLO SE PERMITEN CARACTERES ALFANUMÉRICOS","Advertencia",JOptionPane.WARNING_MESSAGE);//Mensaje de aviso en caso de que el usuario haya ingresado mal el caracter
        jTextField4.setText("");//Limpiar la caja de texto 
        jTextField4.requestFocus();//Reinicia el ingreso de los carateres del campo de texto
        } 
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        //Restricción del campo de texto
        
        if (!jTextField3.getText().matches("[ 0-9-a-z-A-Z]*"))//Se determina los caracteres que el campo de texto podra recibir
        { JOptionPane.showMessageDialog(null,"SOLO SE PERMITEN CARACTERES ALFANUMÉRICOS","Advertencia",JOptionPane.WARNING_MESSAGE);//Mensaje de aviso en caso de que el usuario haya ingresado mal el caracter
        jTextField3.setText("");//Limpiar la caja de texto 
        jTextField3.requestFocus();//Reinicia el ingreso de los carateres del campo de texto
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        //Restricción del campo de texto
        if (!jTextField2.getText().matches("[ 0-9-a-z-A-Z-8-]*"))//Se determina los caracteres que el campo de texto podra recibir
        { JOptionPane.showMessageDialog(null,"SOLO SE PERMITEN CARACTERES ALFANUMÉRICOS","Advertencia",JOptionPane.WARNING_MESSAGE);//Mensaje de aviso en caso de que el usuario haya ingresado mal el caracter
        jTextField2.setText("");//Limpiar la caja de texto 
        jTextField2.requestFocus();//Reinicia el ingreso de los carateres del campo de texto
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        //Restricción del campo de texto
        if (!txtbuscar.getText().matches("[ 0-9-a-z-A-Z]*"))//Se determina los caracteres que el campo de texto podra recibir
        { JOptionPane.showMessageDialog(null,"SOLO SE PERMITEN CARACTERES ALFANUMÉRICOS","Advertencia",JOptionPane.WARNING_MESSAGE);//Mensaje de aviso en caso de que el usuario haya ingresado mal el caracter
        txtbuscar.setText("");//Limpiar la caja de texto 
        txtbuscar.requestFocus();//Reinicia el ingreso de los carateres del campo de texto
        }
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
         this.contenedor.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lblfoto;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtnomimagen;
    // End of variables declaration//GEN-END:variables
}
