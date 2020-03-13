/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlrap;

import BaoCao.BaoCaoDoanhThu;
import KhachHang.QLKhachHang;
import KhachHang.QLLoaiKH;
import KhuyenMai.QLKhuyenMai;
import KhuyenMai.QLSuKien;
import LichChieu.QLLichChieuStep1;
import NhanVien.QLNhanVien;
import Phim.QLPhim;
import PhongChieu.QLCTTB;
import PhongChieu.QLPhongChieu;
import PhongChieu.QLThietBi;
import SanPham.QLSanPham;
import ThanhToan.QLBanVeStep1;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import qlrap.util.ConnectionUtils;
import static qlrap.util.ConnectionUtils.getJDBCConnection;

/**
 *
 * @author Hieu Vo
 */
public class Menu extends javax.swing.JFrame {

    private QLBanVeStep1 banVeStep1;
    private QLLichChieuStep1 lichChieuStep1;
    private QLPhim phim;
    private QLPhongChieu phongChieu;
    private QLSanPham sanPham;
    private QLKhachHang khachHang;
    private QLLoaiKH loaiKH;
    private QLCTTB chiTietThietBi;
    private QLThietBi thietBi;
    private QLNhanVien nhanVien;
    private QLSuKien suKien;
    private QLKhuyenMai khuyenMai;
    private BaoCaoDoanhThu baoCaoDoanhThu;
    private boolean hasThayDoiCaChieu = false;
    private String maNV;
    private Date today;
    private int maPQ;

    /**
     * Creates new form Menu
     *
     */
    private JButton btnBaoCao, btnBanVe, btnCaChieu, btnPhim, btnPhongChieu, btnSanPham, btnKhachHang, btnLKhachHang, btnCTTB, btnThietBi, btnNhanVien, btnSuKien, btnKhuyenMai;

    public Menu(String tenTk, int quyen) {
        today = new Date();
        initComponents();
        InsertButton();
        btnLogout.setVisible(false);
        PhanQuen(quyen);
        setResizable(false);
        JlableTenNV.setVisible(false);

        lichChieuStep1 = new QLLichChieuStep1();
        phim = new QLPhim();
        phongChieu = new QLPhongChieu();
        sanPham = new QLSanPham();
        khachHang = new QLKhachHang();
        loaiKH = new QLLoaiKH();
        chiTietThietBi = new QLCTTB();
        thietBi = new QLThietBi();
        nhanVien = new QLNhanVien();
        suKien = new QLSuKien();
        khuyenMai = new QLKhuyenMai();
        baoCaoDoanhThu = new BaoCaoDoanhThu();
    }


    public void InsertButton() {
        this.setResizable(false);
        jPanel2.setLayout(new GridLayout(2, 6));
        btnBanVe = new JButton("Quản lý Bán vé");
        jPanel2.add(btnBanVe);
        btnCaChieu = new JButton("Quản lý Ca Chiếu");
        jPanel2.add(btnCaChieu);
        btnPhim = new JButton("Quản lý Phim");
        jPanel2.add(btnPhim);
        btnSanPham = new JButton("Quản lý sản phẩm");
        jPanel2.add(btnSanPham);
        btnKhachHang = new JButton("Quản lý Khách hàng");
        jPanel2.add(btnKhachHang);
        btnLKhachHang = new JButton("Quản lý loại Khách hàng");
        jPanel2.add(btnLKhachHang);
        btnKhuyenMai = new JButton("Quản lý Khuyến mãi");
        jPanel2.add(btnKhuyenMai);
        btnSuKien = new JButton("Quản lý Sự kiện");
        jPanel2.add(btnSuKien);
        btnNhanVien = new JButton("Quản lý Nhân viên");
        jPanel2.add(btnNhanVien);
        btnThietBi = new JButton("Quản lý Thiết bị");
        jPanel2.add(btnThietBi);
        btnPhongChieu = new JButton("Quản lý Phòng chiếu");
        btnCTTB = new JButton("Quản lý Chi tiết thiết bị");
        //jPanel2.add(btnCTTB);
        jPanel2.add(btnPhongChieu);
        btnBaoCao = new JButton("Báo cáo doanh thu");
        jPanel2.add(btnBaoCao);

        btnBaoCao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                if (baoCaoDoanhThu != null) {
                    baoCaoDoanhThu.setVisible(true);
                    baoCaoDoanhThu.setLocationRelativeTo(null);
                }

            }
        });
        btnBanVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date currentDate = new Date();

                if (banVeStep1 != null && hasThayDoiCaChieu == false && currentDate.getDate() == today.getDate()) {
                    banVeStep1.setVisible(true);
                    banVeStep1.setLocationRelativeTo(null);
                } else {
                    today = currentDate;
                    banVeStep1 = new QLBanVeStep1(maNV);
                    banVeStep1.setVisible(true);
                    banVeStep1.setLocationRelativeTo(null);
                    hasThayDoiCaChieu = false;
                }

            }
        });

        btnCaChieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lichChieuStep1 != null) {
                    lichChieuStep1.showLC();;
                    lichChieuStep1.setVisible(true);
                    lichChieuStep1.setLocationRelativeTo(null);
                    hasThayDoiCaChieu = true;
                    banVeStep1.setVisible(false);
                }

            }
        });

        btnPhim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (phim != null) {
                    phim.showPhim();
                    phim.setVisible(true);
                    phim.setLocationRelativeTo(null);
                }

            }
        });
        btnPhongChieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (phongChieu != null) {
                    phongChieu.showPc();
                    phongChieu.setVisible(true);
                    phongChieu.setLocationRelativeTo(null);
                }

            }
        });
        btnSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (sanPham != null) {
                    sanPham.showSanPham();
                    sanPham.setVisible(true);
                    sanPham.setLocationRelativeTo(null);
                }

            }
        });
        btnKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (khachHang != null) {
                    khachHang.showKH();
                    khachHang.AddMaLKH();
                    khachHang.setVisible(true);
                    khachHang.setLocationRelativeTo(null);
                }

            }
        });
        btnLKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (loaiKH != null) {
                    loaiKH.showLKH();
                    loaiKH.setVisible(true);
                    loaiKH.setLocationRelativeTo(null);
                }

            }
        });
        btnCTTB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (chiTietThietBi != null) {
                    chiTietThietBi.showCTTB();
                    chiTietThietBi.setVisible(true);
                    chiTietThietBi.setLocationRelativeTo(null);
                    chiTietThietBi.AddMaPC();
                    chiTietThietBi.AddMaTB();
                }

            }
        });
        btnThietBi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (thietBi != null) {
                    thietBi.showTB();
                    thietBi.setVisible(true);
                    thietBi.setLocationRelativeTo(null);
                }

            }
        });
        btnNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (nhanVien != null) {
                    nhanVien.showNV();
                    nhanVien.setVisible(true);
                    nhanVien.setLocationRelativeTo(null);
                }

            }
        });
        btnSuKien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (suKien != null) {
                    suKien.showSuKien();
                    suKien.setVisible(true);
                    suKien.setLocationRelativeTo(null);
                    suKien.AddMaNV();
                }

            }
        });
        btnKhuyenMai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (khuyenMai != null) {
                    khuyenMai.showKM();
                    khuyenMai.setVisible(true);
                    khuyenMai.setLocationRelativeTo(null);
                    khuyenMai.AddMaSK();
                }
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        JlableTenNV = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPW = new javax.swing.JPasswordField();
        userNameLb = new javax.swing.JLabel();
        passwordLb = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mền quản lý rạp phim");
        setMinimumSize(new java.awt.Dimension(1068, 700));
        setSize(new java.awt.Dimension(500, 500));
        getContentPane().setLayout(null);

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogout.setText("Đăng xuất");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogout);
        btnLogout.setBounds(960, 10, 100, 25);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("WECOME TO MY CINEMA");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(440, 10, 205, 22);

        JlableTenNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JlableTenNV.setForeground(new java.awt.Color(255, 255, 255));
        JlableTenNV.setText("jLabel3");
        JlableTenNV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(JlableTenNV);
        JlableTenNV.setBounds(30, 10, 220, 14);
        getContentPane().add(txtUserName);
        txtUserName.setBounds(470, 400, 210, 30);
        getContentPane().add(txtPW);
        txtPW.setBounds(470, 440, 210, 30);

        userNameLb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userNameLb.setForeground(new java.awt.Color(255, 255, 255));
        userNameLb.setText("Tên đăng nhập");
        getContentPane().add(userNameLb);
        userNameLb.setBounds(360, 400, 100, 30);

        passwordLb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordLb.setForeground(new java.awt.Color(255, 255, 255));
        passwordLb.setText("Mật khẩu");
        getContentPane().add(passwordLb);
        passwordLb.setBounds(370, 440, 100, 30);

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogin.setText("Đăng nhập");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(520, 480, 110, 25);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlrap/background.PNG"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 10, 1070, 500);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 520, 1060, 150);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        btnLogout.setText("Đăng nhập");
        PhanQuen(0);
        JlableTenNV.setText("");
        btnLogout.setVisible(false);
        txtUserName.setVisible(true);
        txtPW.setVisible(true);
        btnLogin.setVisible(true);
        userNameLb.setVisible(true);
        passwordLb.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String TenNV = null;
        try {

            Connection connection = ConnectionUtils.getJDBCConnection();

            String sql = "Select TenNV,MaTK,MaPQ,NhanVien.MaNV from TaiKhoan,NhanVien where TaiKhoan.MANV = NhanVien.MaNV and TenDN='" + txtUserName.getText() + "' and Password='" + txtPW.getText() + "'";
            PreparedStatement statement = connection.prepareCall(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                maPQ = resultSet.getInt("MaPQ");
                TenNV = resultSet.getString("TenNV");
                this.maNV = resultSet.getString("maNV");
                banVeStep1 = new QLBanVeStep1(this.maNV);
            }

            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (maPQ != 0) {
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
            PhanQuen(maPQ);
            JlableTenNV.setText(TenNV);
            JlableTenNV.setVisible(true);
            txtUserName.setVisible(false);
            txtPW.setVisible(false);
            txtPW.setText(null);
            txtUserName.setText(null);
            btnLogin.setVisible(false);
            userNameLb.setVisible(false);
            passwordLb.setVisible(false);
            btnLogout.setVisible(true);
            btnLogout.setText("Đăng Xuất");
        } else {
            JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu");
            txtPW.setText("");
            txtUserName.setText("");

        }
    }//GEN-LAST:event_btnLoginActionPerformed



    public void PhanQuen(int maPQ) {
        if (maPQ != 1) {
            btnBanVe.setEnabled(false);
            btnCaChieu.setEnabled(false);
            btnPhim.setEnabled(false);
            btnPhongChieu.setEnabled(false);
            btnSanPham.setEnabled(false);
            btnKhachHang.setEnabled(false);
            btnLKhachHang.setEnabled(false);
            btnCTTB.setEnabled(false);
            btnThietBi.setEnabled(false);
            btnNhanVien.setEnabled(false);
            btnSuKien.setEnabled(false);
            btnKhuyenMai.setEnabled(false);
            btnBaoCao.setEnabled(false);
            if (maPQ == 2) {
                btnBanVe.setEnabled(true);
            } else if (maPQ == 3) {
                btnSuKien.setEnabled(true);
                btnKhuyenMai.setEnabled(true);
            } else if (maPQ == 4) {
                btnCaChieu.setEnabled(true);
            } else if (maPQ == 5) {
                btnPhim.setEnabled(true);
            } else if (maPQ == 6) {
                btnPhongChieu.setEnabled(true);
                btnCTTB.setEnabled(true);
                btnThietBi.setEnabled(true);
            } else if (maPQ == 7) {
                btnKhachHang.setEnabled(true);
                btnLKhachHang.setEnabled(true);
            } else if (maPQ == 8) {
                btnSanPham.setEnabled(true);
            }

        } else {
            btnBanVe.setEnabled(true);
            btnCaChieu.setEnabled(true);
            btnPhim.setEnabled(true);
            btnPhongChieu.setEnabled(true);
            btnSanPham.setEnabled(true);
            btnKhachHang.setEnabled(true);
            btnLKhachHang.setEnabled(true);
            btnCTTB.setEnabled(true);
            btnThietBi.setEnabled(true);
            btnNhanVien.setEnabled(true);
            btnSuKien.setEnabled(true);
            btnKhuyenMai.setEnabled(true);
            btnBaoCao.setEnabled(true);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */


 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Menu a = new Menu(null, 0);
                a.setVisible(true);
                a.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlableTenNV;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel passwordLb;
    private javax.swing.JPasswordField txtPW;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JLabel userNameLb;
    // End of variables declaration//GEN-END:variables
}
