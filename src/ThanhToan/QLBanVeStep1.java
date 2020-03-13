/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThanhToan;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import static qlrap.util.ConnectionUtils.getJDBCConnection;

/**
 *
 * @author Hieu Vo
 */
public class QLBanVeStep1 extends javax.swing.JFrame {


    private String MaLC, SoHD, MaNV;
    private QLBanVeStep2 banVeStep2;

    public QLBanVeStep1(String ma) {
        MaNV = ma;
        setTitle("Quản lý bán vé bước 1: Chọn phim và lịch chiếu");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTablePane();
    }

    public void setTablePane() {
        JTabbedPane LichChieu = new JTabbedPane();
        String text = setDateForPanel(0);

        JPanel Ngay1 = new JPanel();
        JPanel Ngay2 = new JPanel();
        JPanel Ngay3 = new JPanel();
        JPanel Ngay4 = new JPanel();
        JPanel Ngay5 = new JPanel();
        JPanel Ngay6 = new JPanel();
        JPanel Ngay7 = new JPanel();

        showPhim(Ngay1, getDateNow(0));
        showPhim(Ngay2, getDateNow(1));
        showPhim(Ngay3, getDateNow(2));
        showPhim(Ngay4, getDateNow(3));
        showPhim(Ngay5, getDateNow(4));
        showPhim(Ngay6, getDateNow(5));
        showPhim(Ngay7, getDateNow(6));

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setViewportView(Ngay1);
        scrollPane1.setVisible(true);
        scrollPane1.setPreferredSize(new Dimension(1000, 800));

        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setViewportView(Ngay2);
        scrollPane2.setVisible(true);
        scrollPane2.setPreferredSize(new Dimension(1000, 800));

        JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setViewportView(Ngay3);
        scrollPane3.setVisible(true);
        scrollPane3.setPreferredSize(new Dimension(1000, 800));

        JScrollPane scrollPane4 = new JScrollPane();
        scrollPane4.setViewportView(Ngay4);
        scrollPane4.setVisible(true);
        scrollPane4.setPreferredSize(new Dimension(1000, 800));

        JScrollPane scrollPane5 = new JScrollPane();
        scrollPane5.setViewportView(Ngay5);
        scrollPane5.setVisible(true);
        scrollPane5.setPreferredSize(new Dimension(1000, 800));

        JScrollPane scrollPane6 = new JScrollPane();
        scrollPane6.setViewportView(Ngay6);
        scrollPane6.setVisible(true);
        scrollPane6.setPreferredSize(new Dimension(1000, 800));

        JScrollPane scrollPane7 = new JScrollPane();
        scrollPane7.setViewportView(Ngay7);
        scrollPane7.setVisible(true);
        scrollPane7.setPreferredSize(new Dimension(1000, 800));

        LichChieu.addTab(setDateForPanel(0), null, scrollPane1, "");
        LichChieu.addTab(setDateForPanel(1), null, scrollPane2, "");
        LichChieu.addTab(setDateForPanel(2), null, scrollPane3, "");
        LichChieu.addTab(setDateForPanel(3), null, scrollPane4, "");
        LichChieu.addTab(setDateForPanel(4), null, scrollPane5, "");
        LichChieu.addTab(setDateForPanel(5), null, scrollPane6, "");
        LichChieu.addTab(setDateForPanel(6), null, scrollPane7, "");

        // set form cho từng 
        this.add(LichChieu);
    }

    public String setDateForPanel(int n) {
        String Date;
        //Lay ngày hiện tại
        Calendar cal = Calendar.getInstance();
        //Cộng thêm n ngày
        cal.add(Calendar.DAY_OF_MONTH, n);
        Date date = cal.getTime();
        //Lấy thứ
        SimpleDateFormat day_of_week = new SimpleDateFormat("E");
        SimpleDateFormat DMY = new SimpleDateFormat("dd-MM-YYYY");
        //Set định dạng Thứ - Ngày/Tháng/Năm
        Date = day_of_week.format(date) + "-" + DMY.format(date);
        return Date;
    }

    public Date getDateNow(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, n);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        Date date = cal.getTime();
        return date;
    }

    public ArrayList<String> getTenPhim(Date date) {
        ArrayList<String> tenPhimList = new ArrayList();
             SimpleDateFormat dmy = new SimpleDateFormat("YYYY-MM-dd");
        try {
            Connection connection = getJDBCConnection();
            String sql = "Select DISTINCT TenPhim from Phim,CaChieu where Phim.MaPhim=CaChieu.MaPhim and NgayChieu ='"+dmy.format(date)+"'";
            // show data
       
            
            PreparedStatement statement;
            statement = connection.prepareCall(sql);
            //statement.setDate(1, new java.sql.Date(date.getTime()));

            //JOptionPane.showMessageDialog(null, new java.sql.Date(date.getTime()));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tenPhimList.add(resultSet.getString("TenPhim"));
            }
            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenPhimList;
    }

    public String getLink(String tenP) {
        String Link = new String();
        Link="/ThanhToan/LinkPhim/"+tenP+".PNG";
        return Link;
    }

    public void showPhim(JPanel a, Date date) {

        ArrayList<String> tenPhim = getTenPhim(date);
        int soHang;
        if (tenPhim.size() % 4 == 0) {
            soHang = tenPhim.size() / 4;
        } else {
            soHang = tenPhim.size() / 4 + 1;
        }
        a.setLayout(new GridLayout(soHang, 4));
        for (int i = 0; i < tenPhim.size(); i++) {
            JPanel desing = new JPanel();
            designPhim(desing, tenPhim.get(i).toString(), date, getLink(tenPhim.get(i).toString()));
            a.add(desing);
        }

    }

    public void designPhim(JPanel a, String tenPhim, Date date, String Link) {

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
        JLabel name = new JLabel(tenPhim);
        name.setFont(new java.awt.Font("Tahoma", 0, 18));
        a.add(name, q);

        q.gridx = 0;
        q.gridy = 2;
        q.fill = GridBagConstraints.HORIZONTAL;
        JSeparator ngang = new JSeparator(SwingConstants.HORIZONTAL);
        a.add(ngang, q);

        q.gridx = 0;
        q.gridy = 3;
        JPanel khungGio = new JPanel();
        khungGio.add(new JLabel("Ca chiếu :"));
        JComboBox cachieu = new JComboBox();
        setComboBox(tenPhim, date, cachieu);
        khungGio.add(cachieu);
        a.add(khungGio, q);

        a.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        evt.consume();
                        String macc = cachieu.getSelectedItem().toString();
                        String[] ma=macc.split("\\(");
                        banVeStep2 = new QLBanVeStep2(ma[0], MaNV);
                        banVeStep2.setVisible(true);
                        setVisible(false);
                    }
                });
            }
        });
    }
    //Show  Thể loại phim  lên JTable

    public void setComboBox(String tenPhim, Date date, JComboBox cc) {
        
        SimpleDateFormat dmy = new SimpleDateFormat("YYYY-MM-dd");
        ArrayList<String> gioChieu = new ArrayList<>();
        try {
            Connection connection = getJDBCConnection();
            String sql = "Select MaCC,GioBD,GioKT from CaChieu,Phim where CaChieu.MaPhim=Phim.MaPhim and NgayChieu='"+dmy.format(date)+"' and TenPhim='" + tenPhim + "'";
            // show data
            PreparedStatement statement;
            statement = connection.prepareCall(sql);
            //statement.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                gioChieu.add(resultSet.getString("MaCC") + "(" + resultSet.getTime("GioBD") + "  - " + resultSet.getTime("GioKT") + ")");

            }
            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) cc.getModel();
        model.removeAllElements();
        Object[] gc = new Object[gioChieu.size()];
        for (int j = 0; j < gioChieu.size(); j++) {
            gc[j] = gioChieu.get(j);
            model.addElement(gc[j]);
        }
        cc.setModel(model);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new QLBanVeStep1();
            }
        });
    }
}
