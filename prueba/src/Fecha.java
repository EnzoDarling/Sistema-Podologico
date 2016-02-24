
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.ParseException;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.JTextField;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Enzo
 */
public class Fecha extends javax.swing.JFrame {
DefaultTableModel model;
SimpleDateFormat Formato= new SimpleDateFormat("dd-MM-yyyy");
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date dcFechas){
    if(dcFechas!=null){
        java.sql.Date sqlDate= new java.sql.Date(dcFechas.getTime());
        return sqlDate;
    }
        return null;
    }
    public java.util.Date StringADate(String fecha){
        SimpleDateFormat formato_del_Texto= new SimpleDateFormat("dd-MM-yyyy");
        Date fechaE= null;
        try {
            fechaE= formato_del_Texto.parse(fecha);
            return fechaE;
        } catch (Exception e) {
        }
    return null;    
    }
    public Fecha() {
        initComponents();
        setSize(471, 499);
        cargar("");
    }
    private void cargar(String valor){
        Conector cc= new Conector();
        Connection cn= cc.conexion();                        
        String [] titulos={"Codigo","Fecha"};
        String [] registros;
        registros = new String[2];
        String sql="SELECT * FROM turnos WHERE fecha LIKE '%"+valor+"%'";
        model= new DefaultTableModel(null,titulos);       
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()) {                
                registros[0]=rs.getString(1);
                registros[1]=rs.getString(2);
                model.addRow(registros);
            }
            tablaturnos.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:"+e, "Messaje Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void eliminar(){
    Conector cc= new Conector();
    Connection cn= cc.conexion();    
    Integer cod= Integer.parseInt(labelCodigo.getText());
    String sql="";
    sql="DELETE FROM turnos WHERE id_turnos=?";
    int resp;
    resp=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar este registro?","Mensaje de Alerta", JOptionPane.YES_NO_OPTION);
    if(resp==YES_OPTION){
        try {
            PreparedStatement psd=cn.prepareStatement(sql);
            psd.setInt(1,cod);
            int x= psd.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente", "Mensaje de Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e);
        }
    }    
    }
    private void modificar(){
    
    }
    private void guardar(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
        Conector cc= new Conector();
        Connection cn= cc.conexion();
        //int año=dcFechas.getCalendar().get(Calendar.YEAR);
        //int dia=dcFechas.getCalendar().get(Calendar.DAY_OF_MONTH);
        //int mes=dcFechas.getCalendar().get(Calendar.MARCH);
        //String fecha=año+"-"+mes+"-"+dia;        
        String sql="";
        sql="INSERT INTO turnos (fecha) VALUES (?)";
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            psd.setString(1, ((JTextField)dcFechas.getDateEditor().getUiComponent()).getText());
            int n=psd.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null,"Guardado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:" +e, "Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dcFechas = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaturnos = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnObtener = new javax.swing.JButton();
        btnSetFecha = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        dcFechas.setDateFormatString("dd-MM-yyyy");
        getContentPane().add(dcFechas);
        dcFechas.setBounds(120, 90, 140, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Fecha");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 100, 60, 15);

        tablaturnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaturnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaturnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaturnos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 160, 360, 190);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(30, 410, 80, 30);

        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnModificar.setText("Modificar");
        getContentPane().add(btnModificar);
        btnModificar.setBounds(140, 410, 90, 30);

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(260, 410, 80, 30);

        btnObtener.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnObtener.setText("Obtener Fecha");
        btnObtener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObtenerActionPerformed(evt);
            }
        });
        getContentPane().add(btnObtener);
        btnObtener.setBounds(23, 360, 120, 30);

        btnSetFecha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSetFecha.setText("Setear Fecha");
        btnSetFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetFechaActionPerformed(evt);
            }
        });
        getContentPane().add(btnSetFecha);
        btnSetFecha.setBounds(170, 360, 110, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Codigo");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 20, 60, 20);

        labelCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(labelCodigo);
        labelCodigo.setBounds(120, 20, 140, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
        cargar("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnObtenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObtenerActionPerformed
        
    }//GEN-LAST:event_btnObtenerActionPerformed

    private void btnSetFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetFechaActionPerformed
        
    }//GEN-LAST:event_btnSetFechaActionPerformed

    private void tablaturnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaturnosMouseClicked
        int fila=tablaturnos.getSelectedRow();
        if(fila>=0){            
             labelCodigo.setText(tablaturnos.getValueAt(fila,0).toString());            
             SimpleDateFormat formatoDelTexto= new SimpleDateFormat("dd-MM-yyyy");
             String fecha= tablaturnos.getValueAt(tablaturnos.getSelectedRow(),1).toString().trim();
             Date dato= null;
             try {
                 dato= formatoDelTexto.parse(fecha);
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null,"Error: "+e, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             }
            dcFechas.setDate(dato);
        }
    }//GEN-LAST:event_tablaturnosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
      eliminar();
      cargar("");
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fecha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fecha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fecha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fecha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fecha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnObtener;
    private javax.swing.JButton btnSetFecha;
    private com.toedter.calendar.JDateChooser dcFechas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JTable tablaturnos;
    // End of variables declaration//GEN-END:variables
}
