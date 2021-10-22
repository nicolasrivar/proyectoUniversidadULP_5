/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouniversidadulp.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectouniversidadulp.modelo.*;
import proyectouniversidadulp.modelo.Conexion;

public class InscripcionData {
    private Connection con;
    Conexion conexion;
    
    

    public InscripcionData(Conexion conexion) {
        try {
            this.conexion = new Conexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        try {
            con = conexion.getConexion();
            
        } catch (SQLException ex) {
            System.out.println("Error en la conexion ");
        }
    
    }
    public void guardarInscripcion(Inscripcion ins){
        String sql= "INSERT INTO  inscripcion (idAlumno,idMateria, nota) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
       ps.setInt(1,ins.getAlumno().getIdAlumno());
       ps.setInt(2, ins.getMateria().getIdMateria());
       ps.setDouble(3, ins.getNota());
       ps.executeUpdate();
       ResultSet rs= ps.getGeneratedKeys(); //recupero el idAlumno
            
            if (rs.next()){
            ins.setIdInsc(rs.getInt(1));
            }
            ps.close();
        
        } catch (SQLException ex) {
           System.out.println("error al insertar inscripcion");
        }
    }  
    public List<Inscripcion> obtenerInscripcionesMateria(int id){
        List<Inscripcion> lista =new ArrayList<>();
        String sql="SELECT * FROM inscripcion,alumno,materia WHERE inscripcion.idAlumno=alumno.idAlumno and inscripcion.idMateria=materia.idMateria and materia.idMateria = ?";
        
        Inscripcion ins;
        Alumno alumno;
        Materia materia;
        
       
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ins =new Inscripcion();
                alumno=buscarAlumno(rs.getInt(2));
                ins.setAlumno(alumno);
                materia=buscarMateria(rs.getInt(3));
                ins.setMateria(materia);
                ins.setNota(rs.getDouble(rs.getInt(4)));
                lista.add(ins);
                        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
       return lista;
    } 
    
    public Alumno buscarAlumno(int id){
        AlumnoData ad=new AlumnoData(conexion);
        return ad.buscarAlumno(id);
        
    }
    public Materia buscarMateria(int id){
        MateriaData md=new MateriaData(conexion);
        return md.buscarMaterias(id);
    }
    
    public void borrarInscripcion(int idInsc){
       String sql="DELETE FROM inscripcion WHERE inscripcion.idInsc = ?";

      PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idInsc);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
             System.out.println("Error al borrar "+ex);
        }
        }
    
    public void actualizarNota(int idInsc, double nota){
        String sql = "UPDATE universidad.inscripcion SET nota = ? WHERE idInsc = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setDouble(1, nota);
            ps.setInt(2, idInsc);
            
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al agregar nota "+ex);
        }
    }
     public List<Alumno> obtenerAlumnosMateria(int idMateria){
        List<Alumno> lista =new ArrayList<>();
        String sql;
        Alumno alumno;
        sql="SELECT alumno.idAlumno, legajo, nombre, apellido, fechNac, activo FROM inscripcion,alumno WHERE inscripcion.idAlumno=alumno.idAlumno and inscripcion.idMateria = ?";
       PreparedStatement ps ;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                alumno =new Alumno();
               alumno.setIdAlumno(rs.getInt("idAlumno"));
               alumno.setLegajo(rs.getInt("legajo"));
               alumno.setNombre(rs.getString("nombre"));
               alumno.setApellido(rs.getString("apellido"));
               alumno.setFechNac(rs.getDate("fechNac").toLocalDate());
               alumno.setActivo(rs.getBoolean("activo"));
               lista.add(alumno);
               
                        
                 }
            ps.close();
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"error en obtener alumno por materia");
        }
       return lista;
    } 
    
    
    public List<Materia> obtenerMateriasCursadasAlumno(int idAlumno){
        List<Materia> ma=new ArrayList<>();
        Materia materia;
        String sql;
        sql="SELECT materia.idMateria, materia.Nombre, anio, materia.activo FROM `inscripcion`,`alumno`,`materia` WHERE inscripcion.idAlumno=alumno.idAlumno and inscripcion.idMateria=materia.idMateria and materia.activo=1 and inscripcion.idAlumno = ?";
     PreparedStatement ps ;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt(1));                           
                materia.setNombre(rs.getString(2));
                materia.setAnio(rs.getInt(3));
                materia.setActivo(rs.getBoolean(4));
                
                ma.add(materia);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showInternalMessageDialog(null, "Error en buscar materia");

        }
        return ma;
    }
    
    public List<Materia> obtenerMateriasNOCursadasAlumno(int idAlumno){
        String sql;
        sql="SELECT * FROM materia\n" +
"WHERE idMateria not in(SELECT materia.idMateria FROM `inscripcion`,`alumno`,`materia` WHERE inscripcion.idAlumno=alumno.idAlumno and inscripcion.idMateria=materia.idMateria and materia.activo=1 and inscripcion.idAlumno = ?);" ;
 List<Materia> ma=new ArrayList<>();
        Materia materia;
     PreparedStatement ps ;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt(1));                           
                materia.setNombre(rs.getString(2));
                materia.setAnio(rs.getInt(3));
                materia.setActivo(rs.getBoolean(4));
                
                ma.add(materia);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showInternalMessageDialog(null, "Error en buscar materia");

        }
        return ma;
    }
 
//apartir de aca//
//    public List<Inscripcion> obtenerInscripciones(){
//         List<Inscripcion> listaa =new ArrayList<>();
//        String sql="SELECT inscripcion.idInsc, inscripcion.idAlumno, inscripcion.idMateria, inscripcion.nota FROM `inscripcion`,materia WHERE inscripcion.idMateria=materia.idMateria and materia.activo= ? ";
//        Inscripcion in=new Inscripcion();
//        Materia ma=new Materia();
//        Alumno al=new Alumno();
//         PreparedStatement ps ;
//        try {
//            ps= con.prepareStatement(sql);
//            ps.setBoolean(1, true);
//            ResultSet rs=ps.executeQuery();
//            while(rs.next()){
//            ma=this.buscarMateria(rs.getInt("inscripcion.idMateria"));
//            al=this.buscarAlumno(rs.getInt("inscripcion.idAlumno"));
//            in.setAlumno(al);
//            in.setMateria(ma);
//            in.setIdInsc(rs.getInt("inscripcion.idInsc"));
//            in.setNota(rs.getDouble("inscripcion.nota"));
//            listaa.add(in);
//            }
//            ps.close();
//         
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,"error al buscar inscripcioneses"+ ex);
//      
//        }
//         
//       return listaa;
//    }
//    
    
    public List<Inscripcion> obtenerInscripciones(){
         List<Inscripcion> listaa =new ArrayList<>();
        String sql="SELECT * FROM inscripcion";
        
        AlumnoData ad = new AlumnoData(conexion);
        MateriaData md = new MateriaData(conexion);
         PreparedStatement ps ;
        try {
            ps= con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
            Inscripcion in=new Inscripcion();
            Materia ma=new Materia();
            Alumno al=new Alumno();
            ma=this.buscarMateria(rs.getInt("inscripcion.idMateria"));
            al=this.buscarAlumno(rs.getInt("inscripcion.idAlumno"));
            in.setAlumno(al);
            in.setMateria(ma);
            in.setIdInsc(rs.getInt("inscripcion.idInsc"));
            in.setNota(rs.getDouble("inscripcion.nota"));
            listaa.add(in);
            }
            ps.close();
         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error al buscar inscripcioneses"+ ex);
      
        }
         
       return listaa;
    }
    
    
    public Inscripcion obtenerInscripcion(int idAlumno, int idMateria){
        String sql="SELECT * FROM universidad.inscripcion WHERE idAlumno=? AND idMateria = ?";
        Inscripcion in=new Inscripcion();
        Materia ma=new Materia();
        Alumno al=new Alumno();
        
         PreparedStatement ps ;
        try {
            ps= con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            ma=this.buscarMateria(rs.getInt("inscripcion.idMateria"));
            al=this.buscarAlumno(rs.getInt("inscripcion.idAlumno"));
            in.setAlumno(al);
            in.setMateria(ma);
            in.setIdInsc(rs.getInt("inscripcion.idInsc"));
            in.setNota(rs.getDouble("inscripcion.nota"));
            }
            ps.close();
         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error al buscar inscripcion"+ ex);
      
        }
         
       return in;
    }
    
    }  