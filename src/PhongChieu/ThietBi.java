/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhongChieu;

/**
 *
 * @author Hieu Vo
 */
public class ThietBi {

    private String MaTB, TenTB, HangSX;
    private int SL;

    public ThietBi(String ma, String ten, String hsx, int sl) {
        MaTB = ma;
        TenTB = ten;
        HangSX = hsx;
        SL = sl;
    }

    public String getMaTB() {
        return MaTB;
    }

    public String getTenTB() {
        return TenTB;
    }

    public String getHangSX() {
        return HangSX;
    }

    public int getSL() {
        return SL;
    }
}
