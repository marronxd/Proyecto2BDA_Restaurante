/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import java.awt.BorderLayout;

/**
 *
 * @author luiscarlosbeltran
 */
public class PnlComandasMesero extends javax.swing.JPanel {

    private CoordinadorFrames coordinadorF;

    private final int xSize = 736;
    private final int ySize = 433;

    public PnlComandasMesero(CoordinadorFrames coordinadorF) {
        this.coordinadorF = coordinadorF;
        initComponents();

        //panel buscador
        PnlBuscadorComandas bs = new PnlBuscadorComandas(this.coordinadorF);
        bs.setSize(xSize, ySize);
        bs.setLocation(0, 0);

        content.removeAll();
        content.add(bs, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        content = new javax.swing.JPanel();        

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setMaximumSize(new java.awt.Dimension(800, 400));
        setPreferredSize(new java.awt.Dimension(800, 478));
        setLayout(new java.awt.BorderLayout());

        

        content.setForeground(new java.awt.Color(255, 252, 249));
        content.setLayout(new java.awt.BorderLayout());


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGap(16, 16, 16))
        );

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                ).addGap(35, 35, 35))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }





    private javax.swing.JPanel content;
    private javax.swing.JPanel jPanel1;

}
