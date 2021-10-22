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

/**
 *
 * @author Admin
 */
public class AlumnoData {
    private Connection con;

    public AlumnoData(Conexion conexion) {
  
        try {
            con = conexion.getConexion();
            
        } catch (SQLException ex) {
            System.out.println("Error en la conexion ");
        }
    
    }
    
    public void guardarAlumno(Alumno alumno){
        String sql = "INSERT INTO alumno (legajo, nombre, apellido ,fechNac, activo) VALUES (?,?,?,?,?)";
        
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getLegajo());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
            ps.setDate(4, Date.valueOf(alumno.getFechNac()));
            ps.setBoolean(5, alumno.isActivo());
            
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys(); //recupero el idAlumno
            
            if (rs.next()){
             alumno.setIdAlumno(rs.getInt(1));
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar "+ex);
        }
        
    
    }
    
    
    public void actualizarAlumno(Alumno alumno){
        String sql = "UPDATE alumno SET legajo=?, nombre=?, apellido=? ,fechNac=?, activo=? WHERE idAlumno=?";
        
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getLegajo());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
            ps.setDate(4, Date.valueOf(alumno.getFechNac()));//LocalDate a Date
            ps.setBoolean(5, alumno.isActivo());
            ps.setInt(6,alumno.getIdAlumno() );
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al modificar "+ex);
        }
        
    
    }
    
    public List<Alumno> obtenerAlumnos(){
    List<Alumno> alumnos= new ArrayList<>();
        
    Alumno alumno=null;
    
    String sql="SELECT * FROM alumno";
    
        try {
            PreparedStatement ps= con.prepareStatement(sql);
                       
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setLegajo(rs.getInt(2));
                alumno.setNombre(rs.getString(3));
                alumno.setApellido(rs.getString(4));
                alumno.setFechNac(rs.getDate(5).toLocalDate());  //date a LocalDate
                alumno.setActivo(rs.getBoolean(6));
                alumnos.add(alumno);
            }          
            
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar ALumno ");
        }    
       return alumnos;    
       
    }
    
    public Alumno buscarAlumno(int id){ // se busca a Alumno por id
    Alumno alumno=null;
    
    String sql="SELECT * FROM alumno WHERE idAlumno=?";
    
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs =ps.executeQuery();
            if (rs.next()){
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setLegajo(rs.getInt(2));
                alumno.setNombre(rs.getString(3));
                alumno.setApellido(rs.getString(4));
                alumno.setFechNac(rs.getDate(5).toLocalDate());  //date a LocalDate
                alumno.setActivo(rs.getBoolean(6));
            }
            
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar ");
        }    
       return alumno;    
       
    }
    
    public void borrarAlumno(int id){
        String sql = "UPDATE alumno SET activo = 0 WHERE alumno.idAlumno=?";
        
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs =ps.executeQuery();
            
    
        } catch (SQLException ex) {
            System.out.println("Error al eliminar ");
        } 
    }
   
    
     public void activarAlumno(int id){
    String sql = "UPDATE alumno SET activo=? WHERE idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1,true);
            ps.setInt(2, id);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error al desactivar "+ex);
        }
    }
    

}
