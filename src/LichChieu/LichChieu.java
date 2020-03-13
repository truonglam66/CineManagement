/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LichChieu;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Hieu Vo
 */
public class LichChieu {

    private String MaLC, MaP, MaPC;
    private Date NgayChieu;
    private Time GioBD, GioKT;

    public LichChieu(String malc, Date nc, Time bd, Time kt, String map, String mapc) {
        MaLC = malc;
        NgayChieu = nc;
        GioBD = bd;
        GioKT = kt;
        MaP = map;
        MaPC = mapc;
    }

    public String getMaLC() {
        return MaLC;
    }

    public Date getNgayChieu() {
        return NgayChieu;
    }

    public Time getGioBD() {
        return GioBD;
    }

    public Time getGioKT() {
        return GioKT;
    }

    public String getMaP() {
        return MaP;
    }

    public String getMaPC() {
        return MaPC;
    }
}
