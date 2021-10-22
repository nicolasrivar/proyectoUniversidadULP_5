/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouniversidadulp;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectouniversidadulp.control.AlumnoData;
import proyectouniversidadulp.control.InscripcionData;
import proyectouniversidadulp.control.MateriaData;
import proyectouniversidadulp.modelo.Alumno;
import proyectouniversidadulp.modelo.Conexion;
import proyectouniversidadulp.modelo.Inscripcion;
import proyectouniversidadulp.modelo.Materia;
import proyectouniversidadulp.vistas.AlumnosMateriasView;

/**
 *
 * @author Admin
 */
public class ProyectoUniversidadULP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion conexion ;
        try {
            conexion = new Conexion();
            /*InscripcionData id=new InscripcionData(conexion);
            AlumnoData ad=new AlumnoData(conexion);
            MateriaData md=new MateriaData(conexion);
            
            Alumno a1=new Alumno(606,"juan","lopez",LocalDate.of(2000,02, 24),true);
            Alumno a2=new Alumno(505,"jose","Sanchez",LocalDate.of(2002,06, 25),true);
            Alumno a3=new Alumno(707,"Luis","Corino",LocalDate.of(2004,04, 14),true);
            Alumno a4=new Alumno(808,"Marcos","Santos",LocalDate.of(1998,11, 12),true);
            Alumno a5=new Alumno(810,"Paula","Gutierrez",LocalDate.of(1976,07, 4),true);
                     
            Materia m1=new Materia("ingles",4,true);
            Materia m2=new Materia("Programacion",1,true);
            Materia m3=new Materia("frances",2,true);
            Materia m4=new Materia("Matematicas",1,true);
            Materia m5=new Materia("Calculo",1,true);

            
            
            ad.guardarAlumno(a1);
            ad.guardarAlumno(a2);
            ad.guardarAlumno(a3);
            ad.guardarAlumno(a4);
            ad.guardarAlumno(a5);
            
            System.out.println(ad.buscarAlumno(1));
            System.out.println(ad.obtenerAlumnos()); 
            ad.borrarAlumno(2);
            
            md.guardarMateria(m1);
            md.guardarMateria(m2);
            md.guardarMateria(m3);
            md.guardarMateria(m4);
            md.guardarMateria(m5);
            
            m1.setAnio(1);
            md.actualizarMateria(m1);
            md.borrarMateria(1);
            System.out.println(md.buscarMaterias(3));
            System.out.println(md.obtenerMateria());
            
            Inscripcion i1=new Inscripcion(a1,m1, 8);
            Inscripcion i2=new Inscripcion(a2,m2, 6);
            Inscripcion i3=new Inscripcion(a3,m3, 5);
            Inscripcion i4=new Inscripcion(a4,m4,6);
            Inscripcion i5=new Inscripcion(a5,m5,8);
            
            id.guardarInscripcion(i1);
            id.guardarInscripcion(i2);
            id.guardarInscripcion(i3);
            id.guardarInscripcion(i4);
            id.guardarInscripcion(i5);
            
            id.borrarInscripcion(6);
            System.out.println(id.obtenerInscripcion(1, 1));
            System.out.println(id.obtenerInscripcionesMateria(4));
            System.out.println(id.obtenerInscripciones());
            System.out.println(id.obtenerMateriasCursadasAlumno(3));
            System.out.println(id.obtenerMateriasNOCursadasAlumno(3));
            id.actualizarNota(2, 7.5);
            id.borrarInscripcion(5);*/
            AlumnosMateriasView a = new AlumnosMateriasView();
            a.setVisible(true);
            a.toFront();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProyectoUniversidadULP.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    
}}
