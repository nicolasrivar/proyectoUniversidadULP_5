/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouniversidadulp.modelo;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class Alumno {
    private int idAlumno;
    private int legajo;         
    private String nombre;
    private String apellido;
    private LocalDate fechNac;
    private boolean activo;

    public Alumno(int legajo, String nombre, String apellido, LocalDate fechNac, boolean activo) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechNac = fechNac;
        this.activo = activo;
    }

    public Alumno(int idAlumno, int legajo, String nombre, String apellido, LocalDate fechNac, boolean activo) {
        this.idAlumno = idAlumno;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechNac = fechNac;
        this.activo = activo;
    }

    public Alumno() {
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechNac() {
        return fechNac;
    }

    public void setFechNac(LocalDate fechNac) {
        this.fechNac = fechNac;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Alumno{" + "idAlumno=" + idAlumno + ", legajo=" + legajo + ", nombre=" + nombre + ", apellido=" + apellido + ", fechNac=" + fechNac + ", activo=" + activo + '}';
    }
    
    
    
}
