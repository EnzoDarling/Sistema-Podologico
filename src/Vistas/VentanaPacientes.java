/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Conexion.Conexion;

/**
 *
 * @author Enzo
 */
public class VentanaPacientes extends javax.swing.JFrame {
DefaultTableModel model;

    public VentanaPacientes() {
        initComponents();
        setTitle("Pacientes");
        setSize(824, 496);
        setLocationRelativeTo(null);
        setResizable(false);
        cargar("");
    }
    private void cargar(String valor){
    	String [] titulos={"Telefono", "Nombre", "Apellido", "Domicilio"};
    	String [] registros= new String[4];
    	String sql= "SELECT * FROM pacientes WHERE pac_ap LIKE '%"+valor+"%' ORDER BY pac_ap ASC";
    	model= new DefaultTableModel(null, titulos);
    	Conexion cc= new Conexion();
    	Connection cn= cc.conexion();
    	try {
    		Statement st= cn.createStatement();
    		ResultSet rs= st.executeQuery(sql);
    		while(rs.next()){
    			registros[0]=rs.getString("pac_cel");
    			registros[1]=rs.getString("pac_nom");
    			registros[2]=rs.getString("pac_ap");
    			registros[3]=rs.getString("pac_dom");
    			model.addRow(registros);
    		}
    		tablaPacientes.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error: "+e, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
		}
   }
    private void guardar(){
    	Conexion cc= new Conexion();
    	Connection cn= cc.conexion();
    	String sql="";
    	String nom= campoNom.getText();
    	String ape= campoApe.getText();
    	String tel= campoTel.getText();
    	String dom= campoDom.getText();
    	sql= "INSERT INTO pacientes (pac_cel, pac_nom, pac_ap, pac_dom) VALUES (?,?,?,?)";
    	if(tel.equals("") || tel==null || nom.equals("") || nom==null || ape.equals("") || ape==null || dom.equals("") || dom==null){
    		JOptionPane.showMessageDialog(null,"Ingrese datos en los campos obligatorios donde figure un asterisco (*)", "Mensaje de Alerta", JOptionPane.INFORMATION_MESSAGE);
    	}else{
    	try {
			PreparedStatement psd= cn.prepareStatement(sql);
			psd.setString(1, tel);
			psd.setString(2, nom);
			psd.setString(3, ape);
			psd.setString(4, dom);
			int n=psd.executeUpdate();
			if(n>0){
				JOptionPane.showMessageDialog(null, "Se han guardado los datos", "Mensaje de Confirmación", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error:" +e, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
		}
    	}
    }
    private void eliminar(){
    	Conexion cc= new Conexion();
    	Connection cn= cc.conexion();
    	String cel= campoTel.getText();
    	String sql= "DELETE FROM pacientes WHERE pac_cel=?";
    	int resp;
    	resp=JOptionPane.showConfirmDialog(null,"¿Está seguro de eliminar este registro?", "Mensaje de Alerta", JOptionPane.YES_NO_OPTION);
    	if(resp == JOptionPane.YES_OPTION){
    		try {
				PreparedStatement psd= cn.prepareStatement(sql);
				psd.setString(1, cel);
				int x= psd.executeUpdate();
				if(x>0){
					JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");					
				}else{
					JOptionPane.showMessageDialog(null, "No existe registro con ese número de celular","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Error: "+e , "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			}
    	}
    }
    private void modificar(){
    	Conexion cc= new Conexion();
    	Connection cn= cc.conexion();
    	String nom= campoNom.getText();
    	String ape= campoApe.getText();
    	String cel= campoTel.getText();
    	String dom= campoDom.getText();
    	String sql= "UPDATE pacientes SET pac_nom='"+nom+"', pac_ap='"+ape+"', pac_dom='"+dom+"' WHERE pac_cel='"+cel+"'";
    	int resp;
    	resp=JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este registro?","Mensaje de Alerta", JOptionPane.YES_NO_OPTION);
    	if(resp == JOptionPane.YES_OPTION){
    		try {
    			PreparedStatement psd= cn.prepareStatement(sql);
    			int x= psd.executeUpdate();
    			if(x==1){
    				JOptionPane.showMessageDialog(null,"Registro Modificado");    				
    			}else{
    				JOptionPane.showMessageDialog(null,"No existe ningún registro con ese número de celular");
    			}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Error :" +e, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			}
    	}
    }
    private void limpiar(){
    	campoTel.setText("");
    	campoNom.setText("");
    	campoApe.setText("");
    	campoDom.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        campoBuscar = new javax.swing.JTextField();
        labelBuscar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPacientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        labelApellido = new javax.swing.JLabel();
        labelTel = new javax.swing.JLabel();
        labelDomicilio = new javax.swing.JLabel();
        campoNom = new javax.swing.JTextField();
        campoApe = new javax.swing.JTextField();
        campoTel = new javax.swing.JTextField();
        campoDom = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);
        jPanel2.add(campoBuscar);
        campoBuscar.addKeyListener(new java.awt.event.KeyAdapter(){
        	public void keyReleased(java.awt.event.KeyEvent evt){
        		campoBuscarKeyReleased(evt);
        	}
        });
        campoBuscar.setBounds(90, 10, 160, 30);

        labelBuscar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelBuscar.setText("Buscar");
        jPanel2.add(labelBuscar);
        labelBuscar.setBounds(14, 14, 60, 30);

        tablaPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaPacientes.addMouseListener(new java.awt.event.MouseAdapter(){
        	public void mouseClicked(java.awt.event.MouseEvent evt){
        		t_tablaPacientesMouseClicked(evt);
        	}
        });
        jScrollPane1.setViewportView(tablaPacientes);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 50, 510, 404);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(280, 0, 540, 470);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        labelNombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelNombre.setText("* Nombre");
        jPanel1.add(labelNombre);
        labelNombre.setBounds(20, 80, 80, 30);

        labelApellido.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelApellido.setText("* Apellido");
        jPanel1.add(labelApellido);
        labelApellido.setBounds(20, 140, 80, 30);

        labelTel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelTel.setText("* Tel/Cel");
        jPanel1.add(labelTel);
        labelTel.setBounds(20, 20, 80, 30);

        labelDomicilio.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelDomicilio.setText("* Domicilio");
        jPanel1.add(labelDomicilio);
        labelDomicilio.setBounds(20, 200, 80, 30);
        jPanel1.add(campoNom);
        campoNom.setBounds(130, 80, 90, 30);
        jPanel1.add(campoApe);
        campoApe.setBounds(130, 140, 90, 30);
        jPanel1.add(campoTel);
        campoTel.setBounds(130, 20, 90, 30);
        jPanel1.add(campoDom);
        campoDom.setBounds(130, 200, 90, 30);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		btn_guardarActionPerformed(evt);
        	}
        });
        jPanel1.add(btnGuardar);
        btnGuardar.setBounds(10, 280, 100, 40);

        btnBorrar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		btn_borrarActionPerformed(evt);
        	}
        });
        jPanel1.add(btnBorrar);
        btnBorrar.setBounds(130, 280, 110, 40);

        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		btn_modificarActionPerformed(evt);
        	}
        });
        jPanel1.add(btnModificar);
        btnModificar.setBounds(10, 350, 99, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 270, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void btn_guardarActionPerformed(ActionEvent evt) {
		guardar();
		cargar("");
		limpiar();
	}
    protected void btn_borrarActionPerformed(ActionEvent evt){
    	eliminar();
    	cargar("");
    	limpiar();
    }
    protected void btn_modificarActionPerformed(ActionEvent evt){
    	modificar();
    	cargar("");
    	limpiar();
    }
    protected void t_tablaPacientesMouseClicked(java.awt.event.MouseEvent evt){
    	int fila= tablaPacientes.getSelectedRow();
    	if(fila>=0){
    		campoTel.setText(tablaPacientes.getValueAt(fila,0).toString());
    		campoNom.setText(tablaPacientes.getValueAt(fila,1).toString());
    		campoApe.setText(tablaPacientes.getValueAt(fila,2).toString());
    		campoDom.setText(tablaPacientes.getValueAt(fila,3).toString());
    	}
    }
    protected void campoBuscarKeyReleased(KeyEvent evt){
    	cargar(campoBuscar.getText());
    }
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
            java.util.logging.Logger.getLogger(VentanaPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JTextField campoApe;
    private javax.swing.JTextField campoBuscar;
    private javax.swing.JTextField campoDom;
    private javax.swing.JTextField campoNom;
    private javax.swing.JTextField campoTel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelApellido;
    private javax.swing.JLabel labelBuscar;
    private javax.swing.JLabel labelDomicilio;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTel;
    private javax.swing.JTable tablaPacientes;
    // End of variables declaration//GEN-END:variables
}
