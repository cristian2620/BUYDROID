/*
 * Conexión de netbeans con la base de datos
 *
 * version 1.0
 *
 * Fecha: 16/04/2015
 *
 * Copyright Elaborado por :
                            Angello Stivén Andrade Chacón
                            Cristian Andres Hurtado Gil
                            Nicolás David Ruiz Naji
 */
package ConexionBasedeDatos;//paquete en donde se encuentra la clase java
//librerias de la clase java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
//Conexion de netbeans con MYSQL//
public class conexion {//clase conexion
    public String db = "buydroid";//nombre de la base de datos
    public String url = "jdbc:mysql://localhost/"+db; //carga del controlador de netbeans
    public String user = "root"; //usuario de la base de datos
    public String pass = ""; //clave del usuario de la base de datos
    
//Conectar el driver MYSQL con netbeans//
public Connection Conectar(){//clase conectar
    Connection link = null;//link de la conexion a la base de datos
       try{
           Class.forName("org.gjt.mm.mysql.Driver");//carga el driver de mysql
           link = DriverManager.getConnection(this.url, this.user, this.pass);//link de acceso
       }
       catch(ClassNotFoundException | SQLException ex){
           JOptionPane.showMessageDialog(null, ex);//execpcion en caso de que algo salga mal
       }
       return link;      
   }  
//Confirmación de conexion exitosa o no exitosa con la base de datos//
   public static void main(String[] args) {
        conexion cc = new conexion();//libreria y variable global para los otros formularios
        java.sql.Connection cn= cc.Conectar();//libreria y variable global para los otros formularios
        if(cn!=null){
                JOptionPane.showMessageDialog(null, "Conectado");//Confirma la conexion con la base de datos
                try{
                    cn.close();//cierra la conexion
                }catch(SQLException ex){
                System.out.println("Error al desconectar "+ex);//confirma error en caso de no conectarse a una base de datos
                } }}

} 

