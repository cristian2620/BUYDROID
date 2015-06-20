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
package Facturación;
//librerias del formulario

import ConexionBasedeDatos.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Famili AnDraDe
 */
public final class FacturadeVenta extends javax.swing.JFrame {

    private JFrame contenedor;

    /**
     * Creacion del formulario FacturadeVenta
     */
    //Conexión Global de los formularios con la base de datos//
    conexion cc = new conexion();//llama al metodo conexion de la clase conexion//
    java.sql.Connection cn = cc.Conectar();//llama el metodo conectar de la clase conexion//

    public FacturadeVenta(JFrame contenedor) {
        this.contenedor = contenedor;
        //Inicia los componentes//

        initComponents();
        jTextField2.setText(fechaactual());
        //Para que el formulario aparezca centrado
        setLocationRelativeTo(null);
        //Para qe el formulario no se puede expandir//
        setResizable(false);
        //Poner un Icono a la ventana//
        setIconImage(new ImageIcon(getClass().getResource("/BienvenidoAdministrador/icono.png")).getImage());
        //Genera los números de los usuarios registrados en la base de datos//
        Generarnumeracion();
        //Poner un fondo a el Formulario//
        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon uno = new ImageIcon(this.getClass().getResource("/BienvenidoAdministrador/madera.jpg"));
        JLabel fondo = new JLabel();
        fondo.setIcon(uno);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, uno.getIconWidth(), u­no.getIconHeight());
    }

    public void cerrar() {
        int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea salir ?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);//mensaje de confirmación para cerrar el fomulario
        if (valor == JOptionPane.YES_OPTION) { //Si en el mensaje el usuario le dio que si seguira con el siclo.      
            BienvenidoAdministrador.BienvenidoAdministrador salir = new BienvenidoAdministrador.BienvenidoAdministrador();//llamar el formulario de interfaz de bienvenida usuario para salir del anterior formulario
            salir.setVisible(true);//muestra el formulario del interfaz admin
            this.setVisible(false);//cierra el formulario de ayuda
        }//Fin cel ciclo
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

    void buscar(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
       modelo.addColumn("id_producto");
        modelo.addColumn("tipo");
        modelo.addColumn("gama");
        modelo.addColumn("referencia");
        modelo.addColumn("valor");
        modelo.addColumn("cantidad");
        modelo.addColumn("subtotal");
        jTable1.setModel(modelo);

        String SQL = "";

        SQL = "SELECT factura_has_producto.factura_id_factura, factura_has_producto.producto_id_producto,"
                + " factura.fecha, factura.id_usuario, factura.id_cliente, cliente.nombre, "
                + "cliente.apellido, cliente.telefono, cliente.direccion, producto.tipo, producto.gama,"
                + " producto.referencia, producto.valor, gestiondeusuarios.nombre, gestiondeusuarios.apellido,factura_has_producto.cantidad"
                + " FROM factura_has_producto"
                + " INNER JOIN factura ON factura_has_producto.factura_id_factura = factura.id_factura"
                + " INNER JOIN cliente ON factura.id_cliente = cliente.id"
                + " INNER JOIN producto ON factura_has_producto.producto_id_producto = producto.id_producto"
                + " INNER JOIN gestiondeusuarios ON factura.id_usuario = gestiondeusuarios.id_usuario"
                + "  WHERE factura_has_producto.factura_id_factura =  '" + valor + "'";

        String[] datos = new String[7];
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            boolean hayDatos = false;

            while (rs.next()) {
                hayDatos = true;
                
                jTextField2.setText(rs.getString(3));
                jTextField1.setText(rs.getString(1));
                jTextField3.setText(rs.getString(4));
                jTextField4.setText(rs.getString(14));
                jTextField5.setText(rs.getString(15));
                jTextField6.setText(rs.getString(5));
                jTextField7.setText(rs.getString(6));
                jTextField8.setText(rs.getString(7));
                jTextField9.setText(rs.getString(8));
                jTextField10.setText(rs.getString(9));

                datos[0] = rs.getString(2);
                datos[1] = rs.getString(10);
                datos[2] = rs.getString(11);
                datos[3] = rs.getString(12);
                datos[4] = rs.getString(13);
                datos[5] = rs.getString(16);

                modelo.addRow(datos);
                calcular();
                jTextField11.setText("");
                
            

            }

            if (!hayDatos) {
                JOptionPane.showMessageDialog(this, "No hay datos");
                limpiar();
                fechaactual();
                Generarnumeracion();
            } else {
                jTable1.setModel(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturadeVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void factura() {

        String InsertarSQL = "INSERT INTO factura (fecha,id_usuario,id_factura,id_cliente) VALUES (?,?,?,?)";

        String fecha = jTextField2.getText();
        String id_usuario = jTextField3.getText();
        //String cantidad=jTable1.getValueAt(i, 5).toString();
        //String iva=jTextField20.getText();
        //String subtotal=jTextField19.getText();
        //String total=jTextField21.getText();
        String id_factura = jTextField1.getText();
        String id_cliente = jTextField6.getText();
        //String producto=jTable1.getValueAt(i, 0).toString();

        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
            pst.setString(1, fecha);
            pst.setString(2, id_usuario);
            pst.setString(3, id_factura);
            pst.setString(4, id_cliente);

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Los datos se guardaron correctamente");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Facturación.FacturadeVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void factura_has_produto() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String InsertarSQL1 = "INSERT INTO factura_has_producto (factura_id_factura,producto_id_producto,cantidad) VALUES (?,?,?)";
            String cantidad = jTable1.getValueAt(i, 5).toString();

            String factura_id_factura = jTextField1.getText();
            String producto_id_producto = jTable1.getValueAt(i, 0).toString();
            try {
                PreparedStatement pst = cn.prepareStatement(InsertarSQL1);
                pst.setString(1, factura_id_factura);
                pst.setString(2, producto_id_producto);
                pst.setString(3, cantidad);

                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Los datos se guardaron correctamente");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Facturación.FacturadeVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 


    void descontarstock(String codi, String can) {
        int des = Integer.parseInt(can);
        String cap = "";
        int desfinal;
        String consul = "SELECT * FROM producto WHERE  id_producto='" + codi + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consul);
            while (rs.next()) {
                cap = rs.getString(6);
            }

        } catch (Exception e) {
        }
        desfinal = Integer.parseInt(cap) - des;
        String modi = "UPDATE producto SET existencia='" + desfinal + "' WHERE id_producto = '" + codi + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }

    void Generarnumeracion() {
        String SQL = "select max(id_factura) from factura";
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
            Logger.getLogger(FacturadeVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }

    public void confirmareliminacion(){
        int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de elimar el Cliente", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (valor == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "El Cliente se ha eliminado correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            
            String id = "";
            id =jTextField1.getText().toString();
            try {
                 
                PreparedStatement pst = cn.prepareStatement("DELETE FROM factura WHERE id_factura='" + id + "'");
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "eliminacion correcta");
                
                limpiar();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(this, "No se elimino la factura seleccionada");
            }
       
    
        }
    }

    public void limpiar() {//metodo de limpiar el formulario
        jTextField3.setText("");//limpia el textbox
        jTextField4.setText("");//limpia el textbox
        jTextField5.setText("");//limpia el textbox
        jTextField6.setText("");//limpia el textbox
        jTextField7.setText("");//limpia el textbox
        jTextField8.setText("");//limpia el textbox
        jTextField9.setText("");//limpia el textbox
        jTextField10.setText("");//limpia el textbox
        jTextField19.setText("");//limpia el textbox
        jTextField20.setText("");//limpia el textbox
        jTextField21.setText("");//limpia el textbox
        jTextField11.setText("");//limpia el textbox

        //jTextField2.requestFocus();//enfoca 
    }//

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField21 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Factura de Venta");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Núm. Factura:");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Fecha:");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Vendedor"));
        jPanel2.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Apellido:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Identificación:");

        jTextField3.setEditable(false);

        jTextField4.setEditable(false);

        jTextField5.setEditable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Facturación/bp.png"))); // NOI18N
        jButton1.setText("Buscar Vendedor ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                        .addComponent(jTextField4)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Cliente"));
        jPanel3.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Identificación:");

        jTextField6.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Nombre:");

        jTextField7.setEditable(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Facturación/bp.png"))); // NOI18N
        jButton2.setText("Buscar Cliente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Apellido:");

        jTextField8.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("Teléfono:");

        jTextField9.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setText("Dirección:");

        jTextField10.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                .addComponent(jTextField8))
                            .addComponent(jButton2))))
                .addGap(83, 83, 83))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        jPanel4.setOpaque(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BienvenidoAdministrador/cp.png"))); // NOI18N
        jButton3.setText("Buscar Producto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_producto", "tipo", "gama", "referencia", "valor", "existencia", "subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField21.setEditable(false);

        jLabel23.setText("Total:");

        jLabel22.setText("IVA:");

        jTextField20.setEditable(false);

        jTextField19.setEditable(false);
        jTextField19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setText("Sub. Total:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField19)
                            .addComponent(jTextField20)
                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/exit.png"))); // NOI18N
        jButton8.setText("Cerrar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/repor.png"))); // NOI18N
        jButton7.setText("Impirmir");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/del.png"))); // NOI18N
        jButton6.setText("Eliminar Producto");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistrodeProductos/save.png"))); // NOI18N
        jButton4.setText("Registrar Venta");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ConsultaUsuarios.ConsultaUsuarios vend = new ConsultaUsuarios.ConsultaUsuarios();

        vend.toFront();
        vend.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ConsultaCliente.ConsultaCliente cli = new ConsultaCliente.ConsultaCliente();

        cli.toFront();
        cli.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        ConsultadeProducto.Consultab pro = new ConsultadeProducto.Consultab();

        pro.toFront();
        pro.setVisible(true);


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        int fila = jTable1.getSelectedRow();
        if (fila >= 0) {
            modelo.removeRow(fila);
            ConsultadeProducto.Consultab pro = new ConsultadeProducto.Consultab();
            pro.calcular();
        } else {
            JOptionPane.showMessageDialog(null, "No Selecciono ninguna fila");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if ((jTextField6.getText().equals("")) || (jTextField19.getText().equals(""))) {
            JOptionPane.showMessageDialog(this, "No ingreso cliente,productos o realice operacion");
        } else {
            String capcod = "", capcan = "";
            for (int i = 0; i < FacturadeVenta.jTable1.getRowCount(); i++) {
                capcod = FacturadeVenta.jTable1.getValueAt(i, 0).toString();
                capcan = FacturadeVenta.jTable1.getValueAt(i, 5).toString();
                descontarstock(capcod, capcan);

            }
            factura();
            factura_has_produto();

            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int a = jTable1.getRowCount() - 1;
            int i;
            for (i = a; i >= 0; i--) {
                modelo.removeRow(i);
            }

            Generarnumeracion();
            limpiar();
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.contenedor.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        cerrar();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
        buscar(jTextField11.getText());
    }//GEN-LAST:event_jTextField11ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    public static javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField20;
    public static javax.swing.JTextField jTextField21;
    public static javax.swing.JTextField jTextField3;
    public static javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField5;
    public static javax.swing.JTextField jTextField6;
    public static javax.swing.JTextField jTextField7;
    public static javax.swing.JTextField jTextField8;
    public static javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
