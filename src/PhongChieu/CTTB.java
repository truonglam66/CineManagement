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
public class CTTB {

    private String MaTB, MaPc;
    private int SL;

    public CTTB(String matb, String mapc, int sl) {
        MaTB = matb;
        MaPc = mapc;
        SL = sl;
    }

    public String getMaTB() {
        return MaTB;
    }

    public String getMaPC() {
        return MaPc;
    }

    public int getSL() {
        return SL;
    }
}
