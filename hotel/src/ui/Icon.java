/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import lk.chathurawidanage.veffects.Bounce;

/**
 *
 * @author Chathura
 */
public class Icon extends javax.swing.JPanel {

    private boolean mouseOver = false;
    private String title;

    /**
     * Creates new form Icon
     */
    public Icon(String title, ImageIcon icon) {
        initComponents();
        titileLbl.setText(title);
        iconLbl.setIcon(icon);
        this.setOpaque(false);

        this.title = title;
        //this.setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (mouseOver) {

            g.setColor(new Color(0f, 0f, 0f, 0.3f));
        } else {
            g.setColor(new Color(0f, 0f, 0f, 0.1f));
        }
        g.fillRoundRect(0, 0, 135, 135, 20, 20);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconLbl = new javax.swing.JLabel();
        titileLbl = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        iconLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons/supplier.png"))); // NOI18N

        titileLbl.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        titileLbl.setForeground(new java.awt.Color(255, 255, 255));
        titileLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titileLbl.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(titileLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titileLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        mouseOver = true;
        this.repaint();
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        mouseOver = false;
        this.repaint();
    }//GEN-LAST:event_formMouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        MainWindow.getInstance().swapIconPanels();

        MainWindow.getInstance().setSubIconPanelTitle(title);
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconLbl;
    private javax.swing.JLabel titileLbl;
    // End of variables declaration//GEN-END:variables
}
