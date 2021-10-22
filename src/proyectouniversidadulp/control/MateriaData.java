 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouniversidadulp.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import proyectouniversidadulp.modelo.*;
import proyectouniversidadulp.modelo.Conexion;

public class MateriaData {
   
    private Connection con;

    public MateriaData(Conexion conexion) {
       
     
        try {
            con = conexion.getConexion();
            
        } catch (SQLException ex) {
            System.out.println("Error en la conexion ");
        }
   
    
    }
    
    public void guardarMateria(Materia materia){
        String sql = "INSERT INTO materia (nombre, anio, activo) VALUES (?,?,?)";
        
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isActivo());
            
            
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys(); //recupero el idMateria
            if (rs.next()){
             materia.setIdMateria(rs.getInt(1));
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar "+ex);
        }
        
    
    }
    
    
    public void actualizarMateria(Materia materia){
        String sql = "UPDATE materia SET  nombre=?, anio=?, activo=? WHERE idMateria=?";
        
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isActivo());
                       
            ps.setInt(6,materia.getIdMateria() );
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al modificar "+ex);
        }
        
    
    }
    
    public List<Materia> obtenerMateria(){// obtiene todas
    List<Materia> materias= new ArrayList<>();
        
    Materia materia=null;
    
    String sql="SELECT * FROM materia";
    
        try {
            PreparedStatement ps= con.prepareStatement(sql);
                       
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));                           
                materia.setNombre(rs.getString(2));
                materia.setAnio(rs.getInt(3));
                materia.setActivo(rs.getBoolean(4));
                materias.add(materia);
            }         
            
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar  materia ");
        }    
       return materias;    
       
    }
    
    public Materia buscarMaterias(int idMateria){// busca x id
    Materia materia=null;
    
    String sql="SELECT * FROM materia WHERE idMateria=?";
    
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            
            ResultSet rs =ps.executeQuery();
            if (rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setActivo(rs.getBoolean("activo"));
            }
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar ");
        }    
       return materia;    
    }
    
    public void borrarMateria(int idMateria){
        String sql = "UPDATE materia SET activo = 0 WHERE materia.idMateria=?";
        
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            
            ResultSet rs =ps.executeQuery();
            
    
        } catch (SQLException ex) {
            System.out.println("Error al eliminar ");
        } 
    }

    
}
