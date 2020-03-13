/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThanhToan;

/**
 *
 * @author Hieu Vo
 */
public class HoaDonCL {

    private String Tensp;
    private int SL, DonGia, ThanhTien;

    public HoaDonCL(String tensp, int sl, int dg, int tt) {
        Tensp = tensp;
        SL = sl;
        DonGia = dg;
        ThanhTien = tt;
    }

    public String getTenSp() {
        return Tensp;
    }

    public int getSL() {
        return SL;
    }

    public int getDonGia() {
        return DonGia;
    }

    public int getThanhTien() {
        return ThanhTien;
    }
}
