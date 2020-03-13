/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaoCao;

import java.util.Date;

/**
 *
 * @author Hieu Vo
 */
public class HoaDon {

    private String SoHD;
    private Date NgayHD;
    private int TongHD;

    public HoaDon(String so, Date Ngay, int Tong) {
        SoHD = so;
        NgayHD = Ngay;
        TongHD = Tong;
    }

    public String getSoHD() {
        return SoHD;
    }

    public Date getNgay() {
        return NgayHD;
    }

    public int getTriGia() {
        return TongHD;
    }
}
