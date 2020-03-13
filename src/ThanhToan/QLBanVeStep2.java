/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThanhToan;

import Ghe.Ghe;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import qlrap.util.ConnectionUtils;
import static qlrap.util.ConnectionUtils.getJDBCConnection;

/**
 *
 * @author Hieu Vo
 */
public class QLBanVeStep2 extends JFrame implements ActionListener {

    private JButton[][] dayGhe = new JButton[10][10];
    private String MaPC, MaCC, SoHD, MaNV;

    public void setMaCC(String macc) {
        MaCC = macc;
    }

    public QLBanVeStep2(String Macc, String ma) {

        super();
        MaCC = Macc;
        MaPC = getPC(Macc);
        MaNV = ma;
        setTitle("Quản lý bán vé bước 2:Chọn ghế");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        GridBagLayout grib = new GridBagLayout();
        setLayout(grib);
        GridBagConstraints c = new GridBagConstraints();

        //Ma hinh
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 30, 0);
        c.fill = GridBagConstraints.NONE;
        JLabel ManHinh = new JLabel("Màn hình");
        ManHinh.setFont((new java.awt.Font("Tahoma", 1, 25)));
        this.add(ManHinh, c);

        //Cac hang ghe
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 0);
        c.fill = GridBagConstraints.BOTH;
        JPanel jPanelGhe = new JPanel();
        jPanelGhe.setLayout(new GridLayout(10 + 1, 10 + 1));

        String nameJButton;
        String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        int x;
            x = Integer.parseInt(MaPC);
       
        ArrayList<String> gheDaBan = getGheDaBan(Macc);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                nameJButton = a[i] + Integer.toString(j);
                JButton gheng = new JButton(nameJButton);
                setCorlorForGhe(gheng, x + nameJButton, gheDaBan);
                dayGhe[i][j] = gheng;
                dayGhe[i][j].addActionListener(this);
                jPanelGhe.add(dayGhe[i][j]);
            }
        }
        this.add(jPanelGhe, c);

        //button
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(30, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.SOUTH;
        JButton TiepTuc = new JButton("Tiếp tục");
        JButton Back = new JButton("Trở về");
        JButton xoaVe = new JButton("Xóa vé");
        JPanel addButton = new JPanel();
        addButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        addButton.add(Back);
        addButton.add(xoaVe);
        addButton.add(TiepTuc);
        this.add(addButton, c);

        //Trạng thái ghế
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;

        JButton GheTrong = new JButton();
        JButton GheDaBan = new JButton();
        JButton GheDaChon = new JButton();
        GheDaChon.setBackground(Color.GREEN);
        GheDaBan.setBackground(Color.red);
        GheTrong.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hieu Vo\\Documents\\Anh3.PNG"));
        JPanel JPnchairStatius = new JPanel();
        JPnchairStatius.setLayout(new GridLayout(1, 6, 5, 5));
        JPnchairStatius.add(GheDaChon);
        JPnchairStatius.add(new JLabel("       Ghế đã chọn "));
        JPnchairStatius.add(GheTrong);
        JPnchairStatius.add(new JLabel("   Ghế trống "));
        JPnchairStatius.add(GheDaBan);
        JPnchairStatius.add(new JLabel("   Ghế đã bán"));
        this.add(JPnchairStatius, c);

        // Phòng chiếu
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(30, 0, 0, 0);
        JLabel mapc = new JLabel("Mã phòng chiếu :" + MaPC);
        this.add(mapc, c);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        TiepTuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        createHoaDom(date);
                        ArrayList<Ghe> gheDC = new ArrayList();
                        Ghe g;
                        ArrayList<Ghe> list = GheList();
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (dayGhe[i][j].getBackground() == Color.GREEN) {
                                    for (int k = 0; k < list.size(); k++) {
                                        if (list.get(k).getMaG().equals(x + a[i] + Integer.toString(j))) {
                                            gheDC.add(list.get(k));
                                        }
                                    }
                                }
                            }
                        }
                        if (gheDC.size() == 0) {
                            JOptionPane.showMessageDialog(null, "Bạn chưa chọn ghế nào");
                        } else {
                            QLBanVeStep3 st3 = new QLBanVeStep3(MaCC, gheDC, SoHD);
                            st3.setVisible(true);
                            st3.setLocationRelativeTo(null);
                            setVisible(false);
                            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    }
                });
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        QLBanVeStep1 a = new QLBanVeStep1(MaNV);

                        setVisible(false);
                        a.setVisible(true);
                        a.setLocationRelativeTo(null);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }
        });
        xoaVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XoaVe xv = new XoaVe(dayGhe, getGheDaBan(Macc));
                xv.setVisible(true);
                xv.setLocationRelativeTo(null);
            }
        });

    }

    public void createHoaDom(Date date) {
        try {
            Connection connection = ConnectionUtils.getJDBCConnection();
            String sql = "insert into Hoadon values (null,?,?,?,?)";
            // show data
            PreparedStatement statement;
            statement = connection.prepareCall(sql);
            statement.setDate(1, new java.sql.Date(date.getTime()));
            statement.setDate(2, null);
            statement.setString(3, MaNV);
            statement.setInt(4, 0);

            statement.executeUpdate();
            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        try {
            Connection connection = ConnectionUtils.getJDBCConnection();
            String sql = "SELECT AUTO_INCREMENT -1 as SOHD FROM information_schema.TABLES WHERE TABLE_SCHEMA = \"qlbanve\" AND TABLE_NAME = \"hoadon\"";;
            // show data
            PreparedStatement statement;
            statement = connection.prepareCall(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SoHD =  resultSet.getString("SoHD");
            }
            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Ghe> GheList() {
        ArrayList<Ghe> GheList = new ArrayList();

        try {

            Connection connection = ConnectionUtils.getJDBCConnection();

            String sql = "Select * from Ghe";
            // show data
            PreparedStatement statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            Ghe p;
            while (resultSet.next()) {

                p = new Ghe(resultSet.getString("MAGhe"), resultSet.getString("Hang"), resultSet.getString("Cot"), resultSet.getString("MaPC"), resultSet.getInt("Gia"), resultSet.getString("LoaiGhe"));
                GheList.add(p);
            }
            // close connection
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return GheList;
    }

    public String getPC(String Malc) {
        String a = new String();
        try {
            Connection connection = ConnectionUtils.getJDBCConnection();
            String sql = "Select MaPc from cachieu where MaCC='" + Malc + "'";
            // show data
            PreparedStatement statement;

            statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                a = resultSet.getString("MAPC");
            }
            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public ArrayList<String> getGheDaBan(String Macc) {
        ArrayList<String> gheDaBan = new ArrayList<String>();
        try {
            Connection connection = ConnectionUtils.getJDBCConnection();

            String sql = "Select MaGhe from ctve where  MaCC='" + Macc + "'";
            // show data
            PreparedStatement statement;
            statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                gheDaBan.add(resultSet.getString("Maghe"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gheDaBan;
    }

    public void setCorlorForGhe(JButton a, String maG, ArrayList<String> gheDaBan) {
        for (String ghe : gheDaBan) {
            if (ghe.equals(maG)) {
                a.setBackground(Color.red);
                a.setEnabled(false);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (e.getSource() == dayGhe[i][j]) {
                    if (dayGhe[i][j].getBackground() != Color.RED) {
                        if (dayGhe[i][j].getBackground() == Color.GREEN) {
                            dayGhe[i][j].setBackground(null);
                        } else {
                            dayGhe[i][j].setBackground(Color.GREEN);
                        }
                    }
                }
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
