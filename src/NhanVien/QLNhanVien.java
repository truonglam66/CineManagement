/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;

import qlrap.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class QLNhanVien extends javax.swing.JFrame {

    private String MaNV;
    private ArrayList<NhanVien> list;

    /**
     * Creates new form QLNhanVien
     */
    public QLNhanVien() {
        MaNV = getMaNV();
        initComponents();
        showNV();
        list = NhanVienList();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        txtTenNV.setText("");
        txtCMND.setText("");
        txtDiaChi.setText("");
        txtDienThoai.setText("");
        RbtnNam.setSelected(false);
        RbtnNu.setSelected(false);
        DCNgaySinh.setDate(null);
        DCNgayVL.setDate(null);
        txtTimNV.getDocument().addDocumentListener(new DocumentListener() {
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
        DefaultTableModel model = (DefaultTableModel) jTableNV.getModel();
        Object[] row = new Object[9];
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTenNV().toLowerCase().matches("(.*)" + txtTimNV.getText().toLowerCase() + "(.*)") == true) {
                row[0] = list.get(i).getMaNV();
                row[1] = list.get(i).getTenTK();
                row[2] = list.get(i).getTenNV();
                row[3] = list.get(i).getGioiTinh();
                row[4] = list.get(i).getNgaySinh();
                row[5] = list.get(i).getdt();
                row[6] = list.get(i).getdc();
                row[7] = list.get(i).getCMND();
                row[8] = list.get(i).getNgayVL();
                model.addRow(row);
            }
        }
    }

    //Lấy Từng dòng của Thể loại phim từ CSDL thêm vào List
    public ArrayList<NhanVien> NhanVienList() {
        ArrayList<NhanVien> NhanVienList = new ArrayList();

        try {

            Connection connection = getJDBCConnection();

            String sql = "Select * from NhanVien NV left join TaiKhoan HS on NV.MaNV=HS.MANV order by nv.manv";
            // show data
            PreparedStatement statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            NhanVien NV;
            while (resultSet.next()) {

                NV = new NhanVien(resultSet.getInt("MaNV"), resultSet.getString("TenNV"), resultSet.getString("GioiTinh"), resultSet.getDate("NgaySinh"), resultSet.getString("DiaChi"), resultSet.getString("DienThoai"), resultSet.getString("CMND"), resultSet.getDate("NgayVL"), resultSet.getString("TenDN"));
                NhanVienList.add(NV);
            }
            // close connection
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return NhanVienList;
    }

    public ArrayList<TaiKhoan> taiKhoanList(String MaNV) {
        ArrayList<TaiKhoan> List = new ArrayList();

        try {

            Connection connection = getJDBCConnection();

            String sql = "Select * from TaiKhoan where MaNV=" + MaNV + "";
            // show data
            PreparedStatement statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            TaiKhoan TK;
            while (resultSet.next()) {

                TK = new TaiKhoan(resultSet.getString("TenDN"), resultSet.getString("Password"), resultSet.getString("ViTri"));
                List.add(TK);
            }
            // close connection
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return List;
    }

    //Show  Thể loại phim  lên JTable
    public void showNV() {
        ArrayList<NhanVien> list = NhanVienList();
        DefaultTableModel model = (DefaultTableModel) jTableNV.getModel();
        model.setRowCount(0);
        Object[] row = new Object[9];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getMaNV();
            row[1] = list.get(i).getTenTK();
            row[2] = list.get(i).getTenNV();
            row[3] = list.get(i).getGioiTinh();
            row[4] = list.get(i).getNgaySinh();
            row[5] = list.get(i).getdc();
            row[6] = list.get(i).getdt();
            row[7] = list.get(i).getCMND();
            row[8] = list.get(i).getNgayVL();
            model.addRow(row);
        }
        this.list = list;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrSex = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtTimNV = new javax.swing.JTextField();
        BtnUpdateNv = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNV = new javax.swing.JTable();
        btnPhanQuyen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        DCNgayVL = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        DCNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        RbtnNam = new javax.swing.JRadioButton();
        RbtnNu = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        btnHuyNV = new javax.swing.JButton();
        btnThemNV = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý nhân viên\n");

        BtnUpdateNv.setText("Cập nhật");
        BtnUpdateNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateNvActionPerformed(evt);
            }
        });

        btnXoaNV.setText("Xóa");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

        jTableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên tài khoản", "Tên  nhân viên", "Giới tính", "Ngày sinh", "Điện thoại", "Địa chỉ", "CMND", "Ngày vào làm"
            }
        ));
        jScrollPane1.setViewportView(jTableNV);

        btnPhanQuyen.setText("Tài khoản");
        btnPhanQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanQuyenActionPerformed(evt);
            }
        });

        jLabel1.setText("Tìm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPhanQuyen)
                .addGap(53, 53, 53)
                .addComponent(BtnUpdateNv, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUpdateNv, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPhanQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Liệt kê", jPanel1);

        jLabel3.setText("Tên nhân viên");

        jLabel7.setText("Điện thoại");

        jLabel6.setText("Địa chỉ ");

        jLabel9.setText("CMND");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(21, 21, 21)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jLabel10.setText("Ngày vào làm");

        jLabel5.setText("Ngày sinh");

        jLabel4.setText("Giới tính ");

        btnGrSex.add(RbtnNam);
        RbtnNam.setText("Nam");

        btnGrSex.add(RbtnNu);
        RbtnNu.setText("Nữ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(RbtnNam)
                                .addGap(49, 49, 49)
                                .addComponent(RbtnNu)
                                .addGap(0, 52, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(DCNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(DCNgayVL, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(RbtnNam)
                    .addComponent(RbtnNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DCNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(79, 79, 79)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DCNgayVL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        btnHuyNV.setText("Hủy");
        btnHuyNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyNVActionPerformed(evt);
            }
        });

        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(btnHuyNV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyNV)
                    .addComponent(btnThemNV))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(512, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm mới", jPanel2);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("     QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(342, 342, 342)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
        try {

            Connection connection = getJDBCConnection();
            String sql = "insert into nhanVien values (null,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1, txtTenNV.getText());
            if (RbtnNam.isSelected()) {
                statement.setString(2, "Nam");
            } else {
                statement.setString(2, "Nữ");
            }
            statement.setDate(3, new java.sql.Date(DCNgaySinh.getDate().getTime()));
            statement.setString(4, txtDiaChi.getText());
            statement.setString(5, txtDienThoai.getText());
            statement.setString(6, txtCMND.getText());
            statement.setDate(7, new java.sql.Date(DCNgayVL.getDate().getTime()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            DefaultTableModel model = (DefaultTableModel) jTableNV.getModel();
            model.setRowCount(0);
            showNV();
            connection.close();
            jTabbedPane1.setSelectedIndex(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtTenNV.setText("");
        txtCMND.setText("");
        txtDiaChi.setText("");
        txtDienThoai.setText("");
        RbtnNam.setSelected(false);
        RbtnNu.setSelected(false);
        DCNgaySinh.setDate(null);
        DCNgayVL.setDate(null);

    }//GEN-LAST:event_btnThemNVActionPerformed
    public String getMaNV() {
        String MaNV = null;
        try {
            Connection connection = getJDBCConnection();
            String sql = "SELECT AUTO_INCREMENT -1 as MANV FROM information_schema.TABLES WHERE TABLE_SCHEMA = \"qlbanve\" AND TABLE_NAME = \"nhanvien\"";
            // show data
            PreparedStatement statement;
            statement = connection.prepareCall(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MaNV = "NV" + resultSet.getString("MANV");
            }
            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return MaNV;
    }
    private void btnHuyNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyNVActionPerformed
        // TODO add your handling code here:
        txtTenNV.setText("");
        txtCMND.setText("");
        txtDiaChi.setText("");
        txtDienThoai.setText("");
        RbtnNam.setSelected(false);
        RbtnNu.setSelected(false);
        DCNgaySinh.setDate(null);
        DCNgayVL.setDate(null);

    }//GEN-LAST:event_btnHuyNVActionPerformed

    private void BtnUpdateNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateNvActionPerformed
        // TODO add your handling code here:
        updateNV update = new updateNV();
        int row = jTableNV.getSelectedRow();
        String MaNV = jTableNV.getModel().getValueAt(row, 0).toString();
        String TenNV = jTableNV.getModel().getValueAt(row, 2).toString();
        String GioiTinh = jTableNV.getModel().getValueAt(row, 3).toString();
        String NgaySinh = jTableNV.getModel().getValueAt(row, 4).toString();
        String DiaChi = jTableNV.getModel().getValueAt(row, 5).toString();
        String DienThoai = jTableNV.getModel().getValueAt(row, 6).toString();
        String CMND = jTableNV.getModel().getValueAt(row, 7).toString();
        String NgayVL = jTableNV.getModel().getValueAt(row, 8).toString();

        update.setMaNV(MaNV);
        update.setTenNV(TenNV);
        update.setDiaChi(DiaChi);
        update.setDienThoai(DienThoai);
        update.setCMND(CMND);
        update.setGioiTinh(GioiTinh);
        update.setNgaySinh(NgaySinh);
        update.setNgayVL(NgayVL);

        update.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BtnUpdateNvActionPerformed

    public void btnXacNhan(String Ma, String Ten, String GioiTinh, Date da, String DiaChi, String DienThoai, String CMND, Date vl) {

        try {

            Connection connection = getJDBCConnection();

            String sql = "update NhanVien  set  TenNV =N'" + Ten + "' , GioiTinh=N'" + GioiTinh + "' , NgaySinh=? , DiaChi=N'" + DiaChi + "' , DienThoai='" + DienThoai + "'  , CMND='" + CMND + "' , NgayVL=? where MaNV='" + Ma + "'";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setDate(1, new java.sql.Date(da.getTime()));
            pst.setDate(2, new java.sql.Date(vl.getTime()));
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) jTableNV.getModel();
            model.setRowCount(0);
            showNV();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa hay không ?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            try {
                Connection connection = getJDBCConnection();
                int row = jTableNV.getSelectedRow();
                String Values = jTableNV.getModel().getValueAt(row, 0).toString();
                String sql = "Delete from NhanVien where MaNV=N'" + Values + "'";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel) jTableNV.getModel();
                model.setRowCount(0);
                showNV();
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Bạn phải xóa khóa ngoại trước");
            }
        }
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnPhanQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanQuyenActionPerformed
        // TODO add your handling code here:

        int row = jTableNV.getSelectedRow();
        String MANV = jTableNV.getModel().getValueAt(row, 0).toString();
        ArrayList<TaiKhoan> list = taiKhoanList(MANV);
        if (KtraTK(MANV) != 0) {
            HoSo hoSo = new HoSo(MANV, 1);
            hoSo.setTenTK(list.get(0).getTenDN());
            hoSo.setMatKhau(list.get(0).getMatKhau());
            hoSo.setViTri(list.get(0).getViTri());
            //showNV();
            hoSo.setVisible(true);
            hoSo.setTitle("Cập nhật tài khoản");
            hoSo.setLocationRelativeTo(null);
            setVisible(false);
        } else {
            HoSo hoSo = new HoSo(MANV, 0);
           // showNV();
            hoSo.setVisible(true);
            hoSo.setTitle("Thêm mới tài khoản");
            hoSo.setLocationRelativeTo(null);
            setVisible(false);
        }
    }//GEN-LAST:event_btnPhanQuyenActionPerformed
    public int KtraTK(String MANV) {
        int x = 0;
        try {

            Connection connection = getJDBCConnection();

            String sql = "Select * from TaiKhoan where MANV='" + MANV + "'";
            // show data
            PreparedStatement statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                x++;
            }
            // close connection
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return x;
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
            java.util.logging.Logger.getLogger(QLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnUpdateNv;
    private com.toedter.calendar.JDateChooser DCNgaySinh;
    private com.toedter.calendar.JDateChooser DCNgayVL;
    private javax.swing.JRadioButton RbtnNam;
    private javax.swing.JRadioButton RbtnNu;
    private javax.swing.ButtonGroup btnGrSex;
    private javax.swing.JButton btnHuyNV;
    private javax.swing.JButton btnPhanQuyen;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableNV;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimNV;
    // End of variables declaration//GEN-END:variables
}
