/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhuyenMai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
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
public class QLKhuyenMai extends javax.swing.JFrame {

    private ArrayList<KhuyenMai> list;

    /**
     * Creates new form QLKhuyenMai
     */
    public QLKhuyenMai() {
        initComponents();
        list = KMList();
        AddMaSK();
        showKM();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        txtTim.getDocument().addDocumentListener(new DocumentListener() {
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

    public void change() {
        DefaultTableModel model = (DefaultTableModel) jTableKM.getModel();
        Object[] row = new Object[4];
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTenKM().toLowerCase().matches("(.*)" + txtTim.getText().toLowerCase() + "(.*)") == true) {
                row[0] = list.get(i).getMaKM();
                row[1] = list.get(i).getTenKM();
                row[2] = list.get(i).getPTKM();
                row[3] = list.get(i).getMaSK();
                model.addRow(row);
            }
        }
    }

    public void AddMaSK() {
        ArrayList<String> Item = new ArrayList<>();

        try {

            Connection connection = getJDBCConnection();

            String sql = "Select MaSK from SuKien";
            // show data
            PreparedStatement statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            String a;

            while (resultSet.next()) {
                a = resultSet.getString("MaSK");
                Item.add(a);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        DefaultComboBoxModel model1 = (DefaultComboBoxModel) CbxMaSK.getModel();
        model1.removeAllElements();
        Object[] MaSK = new Object[Item.size()];
        for (int j = 0; j < Item.size(); j++) {
            MaSK[j] = Item.get(j);
            model1.addElement(MaSK[j]);
        }
        CbxMaSK.setModel(model1);

    }

    public ArrayList<KhuyenMai> KMList() {
        ArrayList<KhuyenMai> KMList = new ArrayList();

        try {

            Connection connection = getJDBCConnection();

            String sql = "Select * from KhuyenMai";
            // show data
            PreparedStatement statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            KhuyenMai p;
            while (resultSet.next()) {

                p = new KhuyenMai(resultSet.getString("MAKM"), resultSet.getString("TenKM"), resultSet.getString("MaSK"), resultSet.getFloat("TyLeKM"));
                KMList.add(p);
            }
            // close connection
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return KMList;
    }

    //Show   phim  lên JTable
    public void showKM() {
        ArrayList<KhuyenMai> list = KMList();
        DefaultTableModel model = (DefaultTableModel) jTableKM.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getMaKM();
            row[1] = list.get(i).getTenKM();
            row[2] = list.get(i).getPTKM();
            row[3] = list.get(i).getMaSK();

            model.addRow(row);
        }
        this.list = list;
    }

    public void btnXacNhan(String MaKM, String TenKM, String TLKM, String MaSK) {
        try {

            Connection connection = getJDBCConnection();
            String sql = "update KhuyenMai  set   TenKM='" + TenKM + "',TyLeKM='" + TLKM + "',MASK='" + MaSK + "' where MAKM='" + MaKM + "'";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) jTableKM.getModel();
            model.setRowCount(0);
            showKM();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKM = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenKM = new javax.swing.JTextField();
        CbxMaSK = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtTLKM = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quàn lý khuyến mãi\n");

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jTableKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khuyến mãi", "Tên khuyến mãi", "Tỷ lệ khuyến mãi", "Mã sự kiện"
            }
        ));
        jScrollPane1.setViewportView(jTableKM);

        jLabel1.setText("Tìm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCapNhat)
                        .addGap(36, 36, 36)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat)
                    .addComponent(btnXoa))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chi tiết", jPanel1);

        jLabel2.setText("Tên khuyến mãi");

        jLabel3.setText("Mã sự kiện");

        CbxMaSK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Tỷ lệ khuyến mãi");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenKM)
                    .addComponent(CbxMaSK, 0, 198, Short.MAX_VALUE)
                    .addComponent(txtTLKM))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTLKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CbxMaSK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHuy)
                .addGap(18, 18, 18)
                .addComponent(btnThem))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy)
                    .addComponent(btnThem))
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("Thêm", jPanel2);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("QUẢN LÝ KHUYẾN MÃI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        txtTLKM.setText("");
        txtTenKM.setText("");

    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        try {

            Connection connection = getJDBCConnection();
            String sql = "insert into KhuyenMai values(null,?,?,?)";
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1, txtTenKM.getText());
            statement.setString(2, txtTLKM.getText());
            statement.setString(3, CbxMaSK.getSelectedItem().toString());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            DefaultTableModel model = (DefaultTableModel) jTableKM.getModel();
            model.setRowCount(0);
            showKM();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtTLKM.setText("");
        txtTenKM.setText("");
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        updateKhuyenMai update = new updateKhuyenMai();
        int row = jTableKM.getSelectedRow();
        String MaKM = jTableKM.getModel().getValueAt(row, 0).toString();
        String TenKM = jTableKM.getModel().getValueAt(row, 1).toString();
        String TLKM = jTableKM.getModel().getValueAt(row, 2).toString();
        String MASK = jTableKM.getModel().getValueAt(row, 3).toString();

        update.setMaKM(MaKM);
        update.setTenKM(TenKM);
        update.setPTKM(TLKM);
        update.setMaSK(MASK);

        update.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:

        int x = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa hay không ?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            try {

                Connection connection = getJDBCConnection();
                int row = jTableKM.getSelectedRow();
                String MAKM = jTableKM.getModel().getValueAt(row, 0).toString();
                String sql = "Delete from  KhuyenMai where MaKM='" + MAKM + "'";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel) jTableKM.getModel();
                model.setRowCount(0);
                showKM();
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

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
            java.util.logging.Logger.getLogger(QLKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLKhuyenMai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CbxMaSK;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableKM;
    private javax.swing.JTextField txtTLKM;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
