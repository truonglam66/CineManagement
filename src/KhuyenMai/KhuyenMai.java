/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhuyenMai;

/**
 *
 * @author Hieu Vo
 */
public class KhuyenMai {

    private String MaKM, TenKM, MASK;
    private Float PTKM;

    public KhuyenMai(String ma, String ten, String Mask, Float PTKM) {
        MaKM = ma;
        TenKM = ten;
        MASK = Mask;
        this.PTKM = PTKM;
    }

    public String getMaKM() {
        return MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public String getMaSK() {
        return MASK;
    }

    public Float getPTKM() {
        return PTKM;
    }
}
