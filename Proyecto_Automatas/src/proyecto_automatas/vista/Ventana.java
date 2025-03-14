/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_automatas.vista;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import proyecto_automatas.control.Control;

/**
 *
 * @author Vanessa, Adrián
 */
public class Ventana extends javax.swing.JFrame {
    Control c;

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
         c = new Control(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtSalida = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContenido = new javax.swing.JTextArea();
        jlblArchivo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        mnuOpen = new javax.swing.JMenuItem();
        mnuClean = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenuItem();
        jmnuControl = new javax.swing.JMenu();
        mnuLexico = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenuItem1.setText("jMenuItem1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Principal");
        setResizable(false);

        TxtSalida.setEditable(false);
        TxtSalida.setColumns(20);
        TxtSalida.setRows(5);
        jScrollPane2.setViewportView(TxtSalida);

        txtContenido.setColumns(20);
        txtContenido.setRows(5);
        jScrollPane1.setViewportView(txtContenido);

        jlblArchivo.setText("Archivo : ");

        jMenu4.setText("Archivo");

        mnuOpen.setText("Abrir");
        mnuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpenActionPerformed(evt);
            }
        });
        jMenu4.add(mnuOpen);

        mnuClean.setText("Limpiar");
        mnuClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCleanActionPerformed(evt);
            }
        });
        jMenu4.add(mnuClean);

        jMenuSalir.setText("Salir");
        jMenuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSalirActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuSalir);

        jMenuBar1.add(jMenu4);

        jmnuControl.setText("Control");

        mnuLexico.setText("Lexico");
        mnuLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLexicoActionPerformed(evt);
            }
        });
        jmnuControl.add(mnuLexico);

        jMenuBar1.add(jmnuControl);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                    .addComponent(jlblArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //CÓDIGO PARA ABRIR ARCHIVO CON UN JFILECHOOSER.
    private void mnuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenActionPerformed
        c.abrirArchivo();
    }//GEN-LAST:event_mnuOpenActionPerformed

    private void mnuCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCleanActionPerformed
       
    }//GEN-LAST:event_mnuCleanActionPerformed

    private void mnuLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLexicoActionPerformed


    }//GEN-LAST:event_mnuLexicoActionPerformed

    private void jMenuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuSalirActionPerformed
    public JLabel getLblArchivo() {
        return jlblArchivo;
    }

    public JTextArea getTxtSalida() {
        return TxtSalida;
    }

    public void setTxtSalida(JTextArea TxtSalida) {
        this.TxtSalida = TxtSalida;
    }

    public JTextArea getTxtContenido() {
        return txtContenido;
    }

    public void setTxtContenido(JTextArea txtContenido) {
        this.txtContenido = txtContenido;
    }
    
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TxtSalida;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuSalir;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlblArchivo;
    private javax.swing.JMenu jmnuControl;
    private javax.swing.JMenuItem mnuClean;
    private javax.swing.JMenuItem mnuLexico;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JTextArea txtContenido;
    // End of variables declaration//GEN-END:variables

  
    
}
