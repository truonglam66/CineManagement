/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhuyenMai;

import java.util.Date;

/**
 *
 * @author Hieu Vo
 */
public class SuKien {

    private String Mask, Tensk, APTT, DoiTuong, PhanLoai, MaNV, MoTa;
    private Date TGBD, TGKT;

    public SuKien(String ma, String ten, Date bd, Date kt, String ap, String dt, String pl, String manv, String mt) {
        Mask = ma;
        Tensk = ten;
        TGBD = bd;
        TGKT = kt;
        APTT = ap;
        DoiTuong = dt;
        PhanLoai = pl;
        MaNV = manv;
        MoTa = mt;
    }

    public String getMaSK() {
        return Mask;
    }

    public String getTenSK() {
        return Tensk;
    }

    public Date getTGBD() {
        return TGBD;
    }

    public Date getTGKT() {
        return TGKT;
    }

    public String getAPTT() {
        return APTT;
    }

    public String getDoiTuong() {
        return DoiTuong;
    }

    public String getPL() {
        return PhanLoai;
    }

    public String getMaNV() {
        return MaNV;
    }

    public String getMoTa() {
        return MoTa;
    }
}
