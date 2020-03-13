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
public class PhongChieu {

    private String MaPC, TenPC;
    private int DienTich;

    public PhongChieu(String ma, String Ten, int dt) {
        MaPC = ma;
        TenPC = Ten;
        DienTich = dt;
    }

    public String getMaPC() {
        return MaPC;
    }

    public String getTenPc() {
        return TenPC;
    }

    public int getDienTich() {
        return DienTich;
    }
}
