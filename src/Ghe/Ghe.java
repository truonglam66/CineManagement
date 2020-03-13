/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ghe;

/**
 *
 * @author Hieu Vo
 */
public class Ghe {

    private String MaG, Hang, Cot, MaPC, LoaiGhe;
    private int Gia;

    public Ghe(String ma, String h, String c, String mapc, int gia, String lg) {
        MaG = ma;
        Hang = h;
        Cot = c;
        MaPC = mapc;
        Gia = gia;
        LoaiGhe = lg;
    }

    public String getMaG() {
        return MaG;
    }

    public String getHang() {
        return Hang;
    }

    public String getMaPc() {
        return MaPC;
    }

    public String getLoaiG() {
        return LoaiGhe;
    }


    public String getCot() {
        return Cot;
    }

    public int getGia() {
        return Gia;
    }
}
