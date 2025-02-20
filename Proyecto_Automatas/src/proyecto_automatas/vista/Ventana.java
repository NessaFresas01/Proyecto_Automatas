/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_automatas.vista;

/**
 *
 * @author Vanessa, Adrián
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaCodigo = new javax.swing.JTextArea();
        jScrollPaneMensaje = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmnuArchivo = new javax.swing.JMenu();
        jmnuAbrir = new javax.swing.JMenu();
        jmnuLimpiar = new javax.swing.JMenu();
        jmnuSalir = new javax.swing.JMenu();
        jmnuControl = new javax.swing.JMenu();
        jmnuLexico = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Principal");
        setResizable(false);

        jTextAreaCodigo.setColumns(20);
        jTextAreaCodigo.setRows(5);
        jScrollPane1.setViewportView(jTextAreaCodigo);

        jmnuArchivo.setText("Archivo");

        jmnuAbrir.setText("Abrir");
        jmnuArchivo.add(jmnuAbrir);

        jmnuLimpiar.setText("Limpiar");
        jmnuArchivo.add(jmnuLimpiar);

        jmnuSalir.setText("Salir");
        jmnuArchivo.add(jmnuSalir);

        jMenuBar1.add(jmnuArchivo);

        jmnuControl.setText("Control");

        jmnuLexico.setText("Lexico");
        jmnuControl.add(jmnuLexico);

        jMenuBar1.add(jmnuControl);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .addComponent(jScrollPaneMensaje))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneMensaje;
    private javax.swing.JTextArea jTextAreaCodigo;
    private javax.swing.JMenu jmnuAbrir;
    private javax.swing.JMenu jmnuArchivo;
    private javax.swing.JMenu jmnuControl;
    private javax.swing.JMenu jmnuLexico;
    private javax.swing.JMenu jmnuLimpiar;
    private javax.swing.JMenu jmnuSalir;
    // End of variables declaration//GEN-END:variables
}
