/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThanhToan;

import Ghe.Ghe;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import qlrap.util.ConnectionUtils;

/**
 *
 * @author Hieu Vo
 */
public class QLBanVeStep3 extends JFrame {

    private String MaLc, SoHD;
    private ArrayList<Ghe> Ghedc;
    private ArrayList<SanPham> SPDC = getSanPham();

    public QLBanVeStep3(String malc, ArrayList<Ghe> ghedc, String sohd) {
        super();
        Ghedc = ghedc;
        SoHD = sohd;
        MaLc = malc;
        setTitle("Quản lý bán vé bước 3: Thêm sản phẩm");
        setVisible(true);
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        JLabel danhmuc = new JLabel("Danh mục sản phẩm");
        danhmuc.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.add(danhmuc, BorderLayout.PAGE_START);

        JPanel SanPham = new JPanel();
        showSanPham(SanPham);
        JScrollPane scr = new JScrollPane();
        scr.setViewportView(SanPham);
        this.add(scr, BorderLayout.CENTER);

        JPanel Mua = new JPanel();
        Mua.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton MuaNgay = new JButton("Mua ngay");
        JButton BoQua = new JButton("Bỏ qua");
        Mua.add(MuaNgay);
        Mua.add(BoQua);
        this.add(Mua, BorderLayout.PAGE_END);

        MuaNgay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HoaDon hd = new HoaDon(MaLc, Ghedc, SPDC, SoHD);
                        hd.setVisible(true);
                        setVisible(false);
                        setLocationRelativeTo(null);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }
        });
        BoQua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<SanPham> a = new ArrayList();
                        HoaDon hd = new HoaDon(MaLc, Ghedc, a, SoHD);
                        hd.KMList();
                        hd.setVisible(true);
                        setVisible(false);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }
        });
    }

    public void showSanPham(JPanel a) {
        ArrayList<SanPham> SanPhamList = getSanPham();
        int soHang;
        if (SanPhamList.size() % 4 == 0) {
            soHang = SanPhamList.size() / 4;
        } else {
            soHang = SanPhamList.size() / 4 + 1;
        }
        a.setLayout(new GridLayout(soHang, 4));

        for (int i = 0; i < SanPhamList.size(); i++) {
            JPanel desing = new JPanel();
            designSanPham(desing, SanPhamList.get(i));
            a.add(desing);
        }

    }

    public void designSanPham(JPanel a, SanPham s) {
        String tensp = s.getTensp();
        String Link = s.getLink();
        String masp = s.getMaSP();
        int gia = s.getGia();
        int SoLuongspdc = s.getSLDC();
        ImageIcon x = new ImageIcon();
        a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        GridBagLayout grib = new GridBagLayout();
        GridBagConstraints q = new GridBagConstraints();
        a.setLayout(grib);
        q.gridx = 0;
        q.gridy = 0;
        JLabel Poster = new JLabel();
        x = new ImageIcon(getClass().getResource(Link));
        Poster.setIcon(x);
        a.add(Poster, q);

        q.gridx = 0;
        q.gridy = 1;
        JLabel name = new JLabel(tensp);
        name.setFont(new java.awt.Font("Tahoma", 0, 18));
        a.add(name, q);

        q.gridx = 0;
        q.gridy = 2;
        q.fill = GridBagConstraints.HORIZONTAL;
        JSeparator ngang = new JSeparator(SwingConstants.HORIZONTAL);
        a.add(ngang, q);

        q.gridx = 0;
        q.gridy = 3;
        JPanel giaban = new JPanel();
        giaban.setLayout(new GridLayout(1, 2));
        giaban.add(new JLabel("Giá bán :"));
        giaban.add(new JLabel(Integer.toString(gia)));
        a.add(giaban, q);

        q.gridx = 0;
        q.gridy = 4;
        JPanel soluong = new JPanel();
        soluong.setLayout(new GridLayout(1, 2));
        soluong.add(new JLabel("Số lượng :"));
        JSpinner SL = new JSpinner();
        SL.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        SL.setFont(new java.awt.Font("Tahoma", 0, 14));
        soluong.add(SL);
        a.add(soluong, q);

        q.gridx = 0;
        q.gridy = 5;
        JPanel TongGia = new JPanel();
        TongGia.setLayout(new GridLayout(1, 2));
        TongGia.add(new JLabel("Tổng giá :"));
        JLabel Tgia = new JLabel(Integer.toString(0));
        TongGia.add(Tgia);
        a.add(TongGia, q);
        SL.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sol = Integer.parseInt(SL.getValue().toString());
                Tgia.setText(Integer.toString(gia * sol));

                capNhatSLDC(masp, sol);
            }
        });
    }

    public void capNhatSLDC(String maspdc, int SLdc) {
        for (int i = 0; i < SPDC.size(); i++) {
            if (SPDC.get(i).getMaSP().equals(maspdc)) {
                SPDC.get(i).setSLDC(SLdc);
            }
        }
    }

    public ArrayList<SanPham> getSanPham() {
        ArrayList<SanPham> sanPhamList = new ArrayList();

        try {
            Connection connection = ConnectionUtils.getJDBCConnection();
            String sql = "Select Masp,TenSP,Gia,LinkAnh from sanpham order by MaSP ";
            // show data
            PreparedStatement statement;
            statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            SanPham sp;
            while (resultSet.next()) {
                sp = new SanPham(resultSet.getString("Tensp"), resultSet.getInt("Gia"), resultSet.getString("LinkAnh"), resultSet.getString("Masp"));
                sanPhamList.add(sp);
            }
            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }

    public static void main(String[] args) {
        // new QLBanVeStep3();
    }

}
