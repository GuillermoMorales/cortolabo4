/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;
import modelo.Producto;
/**
 *
 * @author LN710Q
 */
public class ProductoDao implements metodos<Producto>{
    private static final Conexion con=Conexion.conectar();
    private static final String SQL_INSERT="INSERT INTO productos(codigo,precio,nombre,cantidad,tipo,disponibilidad) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE productos SET tipo=?, precio=?, disponibilidad=? WHERE codigo=?";
    private static final String SQL_DELETE="DELETE FROM productos WHERE codigo=?";
    private static final String SQL_READ="SELECT * FROM productos WHERE codigo=?";
    private static final String SQL_READALL="SELECT * FROM productos";
    
     @Override
    public boolean create(Producto g) {
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1,g.getCodigo());
            ps.setDouble(2, g.getPrecio());
            ps.setString(3, g.getNombre());
            ps.setInt(4,g.getCantidad());
            ps.setString(5, g.getTipo());
            ps.setBoolean(6, true);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE,null,ex);
            
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
     @Override
    public boolean update(Producto c){
         PreparedStatement ps;
        try{
            System.out.println(c.getCodigo());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1,c.getTipo());
            ps.setDouble(2, c.getPrecio());
            ps.setString(4,c.getCodigo());
            ps.setBoolean(3, true);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Producto read(Object key){
        Producto f = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                f = new Producto (rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getBoolean(7));
            }
            rs.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Producto> readAll(){
        ArrayList<Producto> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Producto(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getBoolean(7)));
            }
            
            rs.close();
        }catch(SQLException ex){
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return all;
    }
    
}
