/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanPham;

import java.util.Date;

/**
 *
 * @author Hieu Vo
 */
public class SanPham {

    private String Masp, TenSp, DVT, XuatSu;
    private Date NgayNhap, HSD;
    private int Gia;

    public SanPham(String ma, String ten, int gia, String dvt, String Xs, Date nn, Date hsd) {
        Masp = ma;
        TenSp = ten;
        DVT = dvt;
        Gia = gia;
        XuatSu = Xs;
        NgayNhap = nn;
        HSD = hsd;
    }

    public String getMaSp() {
        return Masp;
    }

    public int getGia() {
        return Gia;
    }

    public String getTenSp() {
        return TenSp;
    }

    public String getDVt() {
        return DVT;

    }

    public String getXuatS() {
        return XuatSu;
    }

    public Date getNn() {
        return NgayNhap;
    }

    public Date getHSD() {
        return HSD;
    }
}
