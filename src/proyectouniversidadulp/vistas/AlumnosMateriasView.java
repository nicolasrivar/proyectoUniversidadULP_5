/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouniversidadulp.vistas;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import proyectouniversidadulp.control.AlumnoData;
import proyectouniversidadulp.control.InscripcionData;
import proyectouniversidadulp.control.MateriaData;
import proyectouniversidadulp.modelo.Alumno;
import proyectouniversidadulp.modelo.Conexion;
import proyectouniversidadulp.modelo.Inscripcion;
import proyectouniversidadulp.modelo.Materia;

/**
 *
 * @author Nicolas
 */
public class AlumnosMateriasView extends javax.swing.JInternalFrame {
    private DefaultTableModel modelo;
    private ArrayList <Inscripcion> listaInscripcion;
    private ArrayList <Materia> listaMaterias;
    private InscripcionData inscripcionData;
    private MateriaData materiaData;
    private AlumnoData alumnoData;
    private ArrayList <Alumno> listaAlumnos;
    private Conexion conexion;

    /**
     * Creates new form AlumnosMateriasView
     */
    public AlumnosMateriasView() {
        initComponents();
        try {
            conexion = new Conexion();
            modelo = new DefaultTableModel();
            inscripcionData = new InscripcionData(conexion);
            materiaData = new MateriaData(conexion);
            alumnoData = new AlumnoData(conexion);
            listaInscripcion = (ArrayList)inscripcionData.obtenerInscripciones();
            listaMaterias = (ArrayList)materiaData.obtenerMateria();
            listaAlumnos = (ArrayList)alumnoData.obtenerAlumnos();
            cargarMaterias();
            armaCabeceraTabla();
            cargarDatos();
            
            
        } catch (ClassNotFoundException ex) {
            System.out.println("error" + ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tAlumnos = new javax.swing.JTable();
        cbMaterias = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("MATERIA:");

        tAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tAlumnos);

        cbMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMateriasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("LISTADO DE ALUMNOS POR MATERIA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(91, 91, 91)
                            .addComponent(jLabel2)
                            .addGap(49, 49, 49)
                            .addComponent(cbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMateriasActionPerformed
        cargarDatos();
    }//GEN-LAST:event_cbMateriasActionPerformed

    public void cargarMaterias(){
        for(Materia i: listaMaterias){
            cbMaterias.addItem(i);
        }
    }
    
    public void armaCabeceraTabla(){
    
        ArrayList<Object> columns =new ArrayList <Object>();
        columns.add("Id");
        columns.add("Nombre");
        columns.add("Nota");
        for(Object it:columns){
            modelo.addColumn(it);
        }
        tAlumnos.setModel(modelo);
    }
    
    public void borrarFilasTabla(){
        int a = modelo.getRowCount()-1;
        for(int i = a;i>=0;i--){
            modelo.removeRow(i);
        }
    }
    
    public void cargarDatos(){
        borrarFilasTabla();
        
        Materia mat =(Materia)cbMaterias.getSelectedItem();
        for(Inscripcion m: listaInscripcion){
            if (m.getMateria().getIdMateria() == mat.getIdMateria()){
                modelo.addRow(new Object []{m.getAlumno().getIdAlumno(), m.getAlumno().getNombre(), m.getNota()});
            }
            
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Materia> cbMaterias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tAlumnos;
    // End of variables declaration//GEN-END:variables
}
