/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
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
 * @author user
 */
public class vistainscripcion extends javax.swing.JInternalFrame {
    private int idOperacion;
    private DefaultTableModel modelo;
    private ArrayList <Inscripcion> listaInscripcion;
    private ArrayList <Materia> listaMaterias;
    private InscripcionData inscripcionData;
    private MateriaData materiaData;
    private AlumnoData alumnoData;
    private ArrayList <Alumno> listaAlumnos;
    private Conexion conexion;
    private ArrayList <Materia> listaMateriasno;
    Inscripcion i= new Inscripcion();
    ////////////////////////////////////////////
    public vistainscripcion() {
        initComponents();
        
       buttonGroup3.add(jrbInscripto);
       buttonGroup3.add(trbNoInscripto);
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
    } 
    catch (ClassNotFoundException ex) {
        Logger.getLogger(vistainscripcion.class.getName()).log(Level.SEVERE, null, ex);
    }
    desactivarBotones();
    }
     public void cargarAlumnos(){// CARGA EL COMBO BOX CON LOS ALUMNOS 
        for(Alumno b : listaAlumnos){
            jCoAlumno.addItem(b);
        }
    }
     public void armaCabeceraTabla(){ //ARMO LA TABLA 
    ArrayList<Object> columns =new ArrayList <Object>();
        columns.add("Id");
        columns.add("Nombre");
        columns.add("Nota");
        for(Object it:columns){
            modelo.addColumn(it);
        }
        jTable1.setModel(modelo);
    }
      public void borrarFilasTabla(){
        int a = modelo.getRowCount()-1;
        for(int i = a;i>=0;i--){
            modelo.removeRow(i);
        }
    }
public void cargarDatos(){
     borrarFilasTabla();
    
    Alumno a =(Alumno) jCoAlumno.getSelectedItem();
      if (a!=null){
          if (jrbInscripto.isSelected()){// SI EL SELECTOR ESTA EN INSCRIPTO CARGO LA TABLA CON LAS MATERIAS A LAS QUE ESTA INSCRIPTO LA NOTA Y EL ID DE LA INSCRIPCION
            for(Materia m:inscripcionData.obtenerMateriasCursadasAlumno(a.getIdAlumno())){
              modelo.addRow(new Object[]{inscripcionData.obtenerInscripcion( a.getIdAlumno(), m.getIdMateria()).getIdInsc(),m.getNombre(),inscripcionData.obtenerInscripcion( a.getIdAlumno(), m.getIdMateria()).getNota()});
            }  
          }
          else if(trbNoInscripto.isSelected()){// SI ESTA SELECCIONADO EL NO INSCRIPTO CARGA LA TABLA CON EL ID Y EL NOMBRE DE LAS MATERIAS A LAS QUE NO ESTA INSCRIPTO EL ALUMNO SELECCIONADO
              for(Materia m:inscripcionData.obtenerMateriasNOCursadasAlumno(a.getIdAlumno())){
              modelo.addRow(new Object[]{m.getIdMateria(),m.getNombre()});
            }   
          }
    
}
}
public void desactivarBotones(){
    jbAnular.setEnabled(false);
    jbInscribir.setEnabled(false);
    // DESACTICO LOS BOTONES
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCoAlumno = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jrbInscripto = new javax.swing.JRadioButton();
        trbNoInscripto = new javax.swing.JRadioButton();
        jbInscribir = new javax.swing.JButton();
        jbAnular = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jLabel1.setText("Formulario Inscripcion");

        jCoAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCoAlumnoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        jLabel2.setText("Alumno");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id", "Nombre", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jrbInscripto.setBackground(new java.awt.Color(0, 255, 255));
        jrbInscripto.setText("Inscripto");

        trbNoInscripto.setBackground(new java.awt.Color(0, 255, 255));
        trbNoInscripto.setText("No Inscripto");

        jbInscribir.setText("Inscribir ");
        jbInscribir.setEnabled(false);
        jbInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInscribirActionPerformed(evt);
            }
        });

        jbAnular.setText("Anular Inscripcion");
        jbAnular.setEnabled(false);
        jbAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnularActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir ");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jbInscribir)
                .addGap(62, 62, 62)
                .addComponent(jbAnular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jbSalir)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbInscripto)
                    .addComponent(trbNoInscripto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(31, 31, 31)
                .addComponent(jCoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(jrbInscripto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(trbNoInscripto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalir)
                    .addComponent(jbInscribir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbAnular))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCoAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCoAlumnoActionPerformed
      cargarDatos();
    }//GEN-LAST:event_jCoAlumnoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
     idOperacion=0;
     int fila= jTable1.getSelectedRow();
        
      if (jrbInscripto.isSelected()){// SI ESTA SELECTO EL INSCRIPTO ACTIVA EL BOTON INAVILITAR INSCRIPCION Y LE DA A IDoPERACION EL VALOR DEL ID DE LA INSCRIPCION
      jbAnular.setEnabled(true);
      if (fila!=-1){
          int id =(int) jTable1.getValueAt(fila, 0);
          idOperacion=id;
      }
      
     }
     else if (trbNoInscripto.isSelected()){// SI ESTA EN NO INSCRIPTO SE ACTIVA EL BOTON DE INSCRIBIR Y A IDoPERACION SE LE DA EL EL VALOR DEL ID DE LA MATERIA 
          jbInscribir.setEnabled(true);
          if (fila!=-1){
          int id =(int) jTable1.getValueAt(fila, 0);
           idOperacion=id;
      }
     }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jbAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnularActionPerformed
      inscripcionData.borrarInscripcion(idOperacion);//SI PRECIONAN EL BOTON ANULAR INSCRIPCION ESTA SE BORRA
      JOptionPane.showMessageDialog(this, "se a borrado la inscripcion");
    }//GEN-LAST:event_jbAnularActionPerformed

    private void jbInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInscribirActionPerformed
    i.setAlumno((Alumno) jCoAlumno.getSelectedItem());// SI PRECIONAN EL BOTON INSCRIBIRCARGA LOS DATO EN UNA INSCRIPCION Y LA GUARDA EN LA BASE DE DATOS 
    i.setMateria(materiaData.buscarMaterias(idOperacion));
    inscripcionData.guardarInscripcion(i);
    JOptionPane.showMessageDialog(this, "inscripto el alumno en"+i.getMateria().getNombre());
    }//GEN-LAST:event_jbInscribirActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
      
    }//GEN-LAST:event_jbSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<Alumno> jCoAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbAnular;
    private javax.swing.JButton jbInscribir;
    private javax.swing.JButton jbSalir;
    private javax.swing.JRadioButton jrbInscripto;
    private javax.swing.JRadioButton trbNoInscripto;
    // End of variables declaration//GEN-END:variables
}
