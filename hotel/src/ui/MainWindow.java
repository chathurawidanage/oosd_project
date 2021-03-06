/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import lk.chathurawidanage.motions.linearmotion.LinearMotion;

/**
 *
 * @author Chathura Widanage<chathurawidanage@gmail.com>
 */
public class MainWindow extends javax.swing.JFrame {

    private static MainWindow mainWindow;
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private boolean showigMainIcons = true;

    public final static int UI_ADD_HALL = 0;
    public final static int UI_ADD_SUPP = 1;
    public final static int UI_ADD_CUS = 2;
    public final static int UI_ADD_EMP = 3;

    public final static int UI_SEARCH_HALL = 4;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        mainWindow = this;
        // this.setUndecorated(true);

        this.setExtendedState(MAXIMIZED_BOTH);
        createUI();
        createIcons();
    }

    private void createIcons() {
        /**
         * Suppliers
         */
        Icon supllierIcon = new Icon("Supplier", new ImageIcon(getClass().getResource("/ui/images/icons/supplier.png")));
        iconPanel.add(supllierIcon);
        //
        SubIcon addSupllierIcon = new SubIcon("Add Supplier", new ImageIcon(getClass().getResource("/ui/images/icons/accounts.png")), UI_ADD_SUPP);
        supllierIcon.addSubIcon(addSupllierIcon);

        /**
         * Customers
         */
        Icon customerIcon = new Icon("Customer", new ImageIcon(getClass().getResource("/ui/images/icons/customer.png")));
        iconPanel.add(customerIcon);
        //
        SubIcon addCustomerIcon = new SubIcon("Add Customer", new ImageIcon(getClass().getResource("/ui/images/icons/customer.png")), UI_ADD_CUS);
        customerIcon.addSubIcon(addCustomerIcon);

        /**
         * Halls
         */
        Icon hallsIcon = new Icon("Halls", new ImageIcon(getClass().getResource("/ui/images/icons/hall.png")));
        iconPanel.add(hallsIcon);
        //
        SubIcon addHallIcon = new SubIcon("Add New Hall", new ImageIcon(getClass().getResource("/ui/images/icons/hall.png")), UI_ADD_HALL);
        hallsIcon.addSubIcon(addHallIcon);
        //
        SubIcon seacrhHall = new SubIcon("Search Hall", new ImageIcon(getClass().getResource("/ui/images/icons/hall.png")), UI_SEARCH_HALL);
        hallsIcon.addSubIcon(seacrhHall);

        /**
         * Accounts
         */
        Icon accountsIcon = new Icon("Accounts", new ImageIcon(getClass().getResource("/ui/images/icons/accounts.png")));
        iconPanel.add(accountsIcon);

        /**
         * Employees
         */
        Icon employeesIco = new Icon("Employees", new ImageIcon(getClass().getResource("/ui/images/icons/employee.png")));
        iconPanel.add(employeesIco);
        //
        SubIcon addEmployeeIcon = new SubIcon("Add Employee", new ImageIcon(getClass().getResource("/ui/images/icons/employee.png")), UI_ADD_EMP);
        employeesIco.addSubIcon(addEmployeeIcon);
    }

    /**
     * Shows an error to the user
     *
     * @param title title of the error message
     * @param error the content of the error message
     */
    public static void showError(String title, String error) {
        JOptionPane.showMessageDialog(MainWindow.getInstance(), error, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showInformation(String title, String info) {
        JOptionPane.showMessageDialog(MainWindow.getInstance(), info, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void generateSubWindow(int ui) {
        switch (ui) {
            case UI_ADD_HALL:
                openWindow(new AddHall());
                break;
            case UI_ADD_SUPP:
                openWindow(new AddSupCus(true));
                break;

            case UI_ADD_CUS:
                openWindow(new AddSupCus(false));
                break;

            case UI_ADD_EMP:
                openWindow(new AddEmployee());
                break;

            case UI_SEARCH_HALL:
                openWindow(new SearchHalls());
                break;
        }
    }

    /**
     * Swaps between two icon panels
     */
    public void swapIconPanels() {
        if (showigMainIcons) {
            showigMainIcons = false;
            LinearMotion lm = new LinearMotion(iconPanel);
            lm.setSpeed(50);
            lm.moveY(-screen.height);

            LinearMotion lm2 = new LinearMotion(subIconPanel);
            lm2.setSpeed(40);
            lm2.moveY(80);
        } else {
            showigMainIcons = true;
            LinearMotion lm = new LinearMotion(iconPanel);
            lm.setSpeed(80);
            lm.moveY(100);

            LinearMotion lm2 = new LinearMotion(subIconPanel);
            lm2.setSpeed(50);
            lm2.moveY(screen.height);
        }
    }

    /**
     * Sets the title of the icon panel
     *
     * @param title
     */
    public void setSubIconPanelTitle(String title) {
        this.subIconTxt.setText(title);
    }

    public void removeAllInSubIconPanel() {
        subIconContainer.removeAll();
    }

    public void addToSubIconPanel(ArrayList<SubIcon> icons) {
        for (SubIcon icon : icons) {
            subIconContainer.add(icon);
        }
    }

    /**
     * Creates all the UI components
     */
    private void createUI() {

        JLayeredPane desktop = this.desktop;
        desktop.setBounds(360, 80, screen.width - 360, screen.height - 80);

        backgroundTxt.setIcon(new ImageIcon(getClass().getResource("/ui/images/background.jpg")));
        backgroundTxt.setBounds(0, 0, screen.width, screen.height);

        topBar.setSize(screen.width, 60);
        topBar.setLocation(0, 0);

        //time
        TimerTask timeUpdate = new TimerTask() {

            @Override
            public void run() {
                Date d = new Date();
                DecimalFormat df = new DecimalFormat("##");
                dateTxt.setText(String.format("%02d", d.getHours()) + ":" + String.format("%02d", d.getMinutes()) + ":" + String.format("%02d", d.getSeconds()));
            }
        };

        Timer t = new Timer();
        t.scheduleAtFixedRate(timeUpdate, 0, 1000);

        dateTxt.setBounds(0, screen.height - 150, screen.width - 30, 120);

        iconPanel.setBounds(20, 100, 360, screen.height);
        iconPanel.setOpaque(false);

        subIconPanel.setBounds(20, screen.height, 360, screen.height);
        subIconPanel.setOpaque(false);

        backLbl.setBounds(0, 0, subIconPanel.getWidth(), 60);
        subIconTxt.setBounds(0, 60, subIconPanel.getWidth(), 100);
        subIconContainer.setBounds(0, 140, subIconPanel.getWidth(), screen.height - 140);
        subIconContainer.setOpaque(false);

    }

    /**
     * Return the current instance of the MainWindow
     *
     * @return
     */
    public static MainWindow getInstance() {
        return mainWindow;
    }

    /**
     * Opens a sub window inside the main window
     *
     * @param frame
     */
    public void openWindow(JInternalFrame frame) {
        JLayeredPane desktop = this.desktop;

        desktop.add(frame);

        LinearMotion lm = new LinearMotion(frame);
        lm.setSpeed(100);

        frame.setLocation((this.desktop.getWidth()), (this.desktop.getHeight() - frame.getHeight()) / 2);

        lm.moveX((desktop.getWidth() - frame.getWidth()) / 2);

        frame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topBar = new javax.swing.JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

                g.setColor(new Color(0f,0f,0f,0.5f));
                g.fillRect(0, 0, screen.width, 60);
            }
        };
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        desktop = new javax.swing.JLayeredPane();
        iconPanel = new javax.swing.JPanel();
        subIconPanel = new javax.swing.JPanel();
        backLbl = new javax.swing.JLabel();
        subIconTxt = new javax.swing.JLabel();
        subIconContainer = new javax.swing.JPanel();
        dateTxt = new javax.swing.JLabel();
        backgroundTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jButton1.setBackground(new java.awt.Color(255, 0, 51));
        jButton1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Invictuz Hotels");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(topBar);
        topBar.setBounds(0, 0, 639, 66);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        getContentPane().add(desktop);
        desktop.setBounds(397, 184, 242, 116);

        iconPanel.setBackground(new java.awt.Color(0, 255, 153));
        iconPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));
        getContentPane().add(iconPanel);
        iconPanel.setBounds(470, 90, 160, 170);

        subIconPanel.setBackground(new java.awt.Color(255, 102, 51));
        subIconPanel.setLayout(null);

        backLbl.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        backLbl.setForeground(new java.awt.Color(255, 255, 255));
        backLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons/up.png"))); // NOI18N
        backLbl.setText("Back to main");
        backLbl.setToolTipText("");
        backLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLblMouseClicked(evt);
            }
        });
        subIconPanel.add(backLbl);
        backLbl.setBounds(0, 1, 310, 36);

        subIconTxt.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        subIconTxt.setForeground(new java.awt.Color(255, 255, 255));
        subIconTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        subIconTxt.setText("Title");
        subIconTxt.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        subIconPanel.add(subIconTxt);
        subIconTxt.setBounds(0, 37, 310, 36);

        subIconContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        subIconPanel.add(subIconContainer);
        subIconContainer.setBounds(0, 73, 310, 36);

        getContentPane().add(subIconPanel);
        subIconPanel.setBounds(10, 180, 310, 110);

        dateTxt.setFont(new java.awt.Font("Segoe UI", 0, 130)); // NOI18N
        dateTxt.setForeground(new java.awt.Color(255, 255, 255));
        dateTxt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateTxt.setText("date");
        getContentPane().add(dateTxt);
        dateTxt.setBounds(200, 110, 150, 100);
        dateTxt.getAccessibleContext().setAccessibleName("date");

        backgroundTxt.setText("jLabel1");
        getContentPane().add(backgroundTxt);
        backgroundTxt.setBounds(80, 120, 76, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLblMouseClicked
        swapIconPanels();
    }//GEN-LAST:event_backLblMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
         // Install WebLaF as application L&F
         WebLookAndFeel.install();
         }
         });*/
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }//*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backLbl;
    private javax.swing.JLabel backgroundTxt;
    private javax.swing.JLabel dateTxt;
    private javax.swing.JLayeredPane desktop;
    private javax.swing.JPanel iconPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel subIconContainer;
    private javax.swing.JPanel subIconPanel;
    private javax.swing.JLabel subIconTxt;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
