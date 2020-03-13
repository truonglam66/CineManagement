/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThanhToan;

import Ghe.Ghe;
import KhuyenMai.KhuyenMai;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import static qlrap.util.ConnectionUtils.getJDBCConnection;

/**
 *
 * @author Hieu Vo
 */
public final class HoaDon extends javax.swing.JFrame {

    private String MaLC, SoHD;
    private double TongTientt = 0, tt = 0;
    private ArrayList<Ghe> GheDC;
    private ArrayList<SanPham> SPDC;
    private ArrayList<KhuyenMai> KM;

    /**
     * Creates new form
     */
    public HoaDon(String malc, ArrayList<Ghe> ghedc, ArrayList<SanPham> spdc, String sohd) {
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        MaLC = malc;
        GheDC = ghedc;
        SoHD = sohd;
        SPDC = spdc;
        txtMaKM.setText(null);
        KM = KMList();
        showSP(spdc, ghedc);
        jLabelTongTien.setText(Double.toString(tt));
        JLableSoHD.setText(sohd);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat dmy = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
        JLableDay.setText(dmy.format(date));
        jbtnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ve a;
                for (int i = 0; i < SPDC.size(); i++) {
                    if (SPDC.get(i).getSLDC() > 0) {
                        InsertCtSP(SPDC.get(i));
                    }
                }
                for (int i = 0; i < GheDC.size(); i++) {
                    InsertCTVe(GheDC.get(i).getMaG(), GheDC.get(i).getGia());
                    a = new Ve(MaLC, GheDC.get(i).getGia(), GheDC.get(i).getMaG());
                    a.setVisible(true);
                    setVisible(false);
                }

            }
        });
        txtMaKM.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                change();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                change();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                change();
            }
        });

    }

    public void showSP(ArrayList<SanPham> spdc, ArrayList<Ghe> ghedc) {
        int vt = 0, vv = 0, gt = 0, gv = 0;
        for (int i = 0; i < GheDC.size(); i++) {
            if (GheDC.get(i).getLoaiG().equals("Thường")) {
                vt += 1;
                gt = GheDC.get(i).getGia();
            } else {
                vv += 1;
                gv = GheDC.get(i).getGia();

            }
        }

        DefaultTableModel model = (DefaultTableModel) JtbCTHD.getModel();
        Object[] row = new Object[5];
        int n = 0;
        if (vt > 0) {
            n += 1;
            row[0] = n;
            row[1] = "Vé Thường";
            row[2] = vt;
            row[3] = gt;
            row[4] = vt * gt;
            tt += vt * gt;
            model.addRow(row);
        }
        if (vv > 0) {
            n += 1;
            row[0] = n;
            row[1] = "Vé VIP";
            row[2] = vv;
            row[3] = gv;
            row[4] = vv * gv;
            tt += vv * gv;
            model.addRow(row);
        }

        for (int i = 0; i < spdc.size(); i++) {
            if (spdc.get(i).getSLDC() > 0) {
                n += 1;
                row[0] = n;
                row[1] = spdc.get(i).getTensp();
                row[2] = spdc.get(i).getSLDC();
                row[3] = spdc.get(i).getGia();
                row[4] = spdc.get(i).getSLDC() * spdc.get(i).getGia();
                tt += spdc.get(i).getSLDC() * spdc.get(i).getGia();
                model.addRow(row);
            }
        }

    }

    public ArrayList<KhuyenMai> KMList() {
        ArrayList<KhuyenMai> list = new ArrayList();
        try {

            Connection connection = getJDBCConnection();

            String sql = "Select * from KhuyenMai";
            // show data
            PreparedStatement statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            KhuyenMai p;
            while (resultSet.next()) {

                p = new KhuyenMai(resultSet.getString("MAKM"), resultSet.getString("TenKM"), resultSet.getString("MaSK"), resultSet.getFloat("TyLeKM"));
                list.add(p);
            }
            // close connection
            connection.close();
        } catch (Exception e) {
        }
        return list;
    }

    public double getTiLeKM(String MaKM) {
        float TLKM = 0;
        for (int i = 0; i < KM.size(); i++) {
            if (MaKM.equals(KM.get(i).getMaKM())) {

                TLKM = KM.get(i).getPTKM();
            }
        }
        double a = Math.round(TLKM * 100.00) / 100.00;
        return a;
    }

    public void InsertCtSP(SanPham a) {

        try {

            Connection connection = getJDBCConnection();
            String sql = "insert into ctsp values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setInt(1, Integer.parseInt(SoHD));
            statement.setString(2, a.getMaSP());
            statement.setInt(3, a.getSLDC());
            if (txtMaKM.getText().equals("")) {
                statement.setDouble(4, a.getSLDC() * a.getGia());
                statement.setString(5, null);
            } else {
                statement.setString(5, txtMaKM.getText());
                statement.setDouble(4, a.getSLDC() * a.getGia() * getTiLeKM(txtMaKM.getText()));
            }
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void InsertCTVe(String mag, int TriGia) {
        try {

            Connection connection = getJDBCConnection();
            String sql = "insert into ctve values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setInt(1, Integer.parseInt(SoHD));
            statement.setInt(2, Integer.parseInt(MaLC));
            statement.setString(3, mag);
            if (txtMaKM.getText().equals("")) {
                statement.setString(4, null);
                statement.setDouble(5, TriGia);
            } else {
                statement.setString(4, txtMaKM.getText());
                statement.setDouble(5, TriGia * getTiLeKM(txtMaKM.getText()));
            }

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        JLableDay = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JLableSoHD = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtbCTHD = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabelTongTien = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        LbTenRapChieu = new javax.swing.JLabel();
        JlableDiaChi = new javax.swing.JLabel();
        jbtnThanhToan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelTongTienTT = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jlableKM = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hóa đơn");
        setBackground(new java.awt.Color(51, 255, 51));

        jLabel12.setText("Cinema/POS:  8028-203");

        JLableDay.setText("21 - May - 2019 03:03:37  ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("HÓA ĐƠN THANH TOÁN");

        JLableSoHD.setText("HD01");

        JtbCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(JtbCTHD);

        jLabel6.setText("Tổng tiền :");

        jLabelTongTien.setText("100000");

        jLabel7.setText("Mã khuyến mãi ");

        txtMaKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtMaKMMouseExited(evt);
            }
        });
        txtMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(JLableDay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(33, 33, 33))
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTongTien)
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JLableSoHD))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(31, 31, 31)
                        .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLableSoHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(JLableDay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelTongTien)))
        );

        LbTenRapChieu.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        LbTenRapChieu.setText("HD CINEMA");

        JlableDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JlableDiaChi.setText("Lương Hữu Bích - phường Cẩm Nam - Thành phố Hội An");

        jbtnThanhToan.setText("Thanh toán");
        jbtnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnThanhToanActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Tổng tiền thanh toán :");

        jLabelTongTienTT.setText("100000");

        jLabel5.setText("Khuyến mãi");

        jlableKM.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTongTienTT)
                    .addComponent(jlableKM))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(LbTenRapChieu))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(JlableDiaChi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(LbTenRapChieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JlableDiaChi)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jlableKM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTongTienTT)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jbtnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnThanhToanActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jbtnThanhToanActionPerformed

    private void txtMaKMMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaKMMouseExited
        // TODO add your handling code here:


    }//GEN-LAST:event_txtMaKMMouseExited

    private void txtMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKMActionPerformed
    public void change() {
        jlableKM.setText(Double.toString(getTiLeKM(txtMaKM.getText())));
        jLabelTongTienTT.setText(Double.toString(tt * (1 - getTiLeKM(txtMaKM.getText()))));
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
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLableDay;
    private javax.swing.JLabel JLableSoHD;
    private javax.swing.JLabel JlableDiaChi;
    private javax.swing.JTable JtbCTHD;
    private javax.swing.JLabel LbTenRapChieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelTongTien;
    private javax.swing.JLabel jLabelTongTienTT;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnThanhToan;
    private javax.swing.JLabel jlableKM;
    private javax.swing.JTextField txtMaKM;
    // End of variables declaration//GEN-END:variables
}
