/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouniversidadulp.vistas;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
public class CargarNotas extends javax.swing.JInternalFrame {
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
    public CargarNotas() {
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
            cargarAlumnos();
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
        cbAlumnos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        jLabel2.setText("ALUMNO:");

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

        cbAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlumnosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("CARGAR NOTAS");

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel2)
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jButtonGuardar)
                        .addGap(74, 74, 74)
                        .addComponent(jButtonCancelar)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonCancelar))
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlumnosActionPerformed
        cargarDatos();
    }//GEN-LAST:event_cbAlumnosActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
       // String nota = jNota.getText();
        int i = tAlumnos.getSelectedRow();
        String id =tAlumnos.getValueAt(i, 0).toString();
        String nota = tAlumnos.getValueAt(i, 2).toString();
        
 
        inscripcionData.actualizarNota(Integer.parseInt(id), Double.parseDouble(nota));
        JOptionPane.showMessageDialog(this, "se ha cambiado la nota a "+ nota);
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    public void cargarAlumnos(){
        for(Alumno i: listaAlumnos){
            cbAlumnos.addItem(i);
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
        
        Alumno mat =(Alumno)cbAlumnos.getSelectedItem();
        for(Inscripcion m: listaInscripcion){
            if (m.getAlumno().getIdAlumno() == mat.getIdAlumno()){
                modelo.addRow(new Object []{m.getMateria().getIdMateria(), m.getMateria().getNombre(), m.getNota()});
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Alumno> cbAlumnos;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tAlumnos;
    // End of variables declaration//GEN-END:variables
}