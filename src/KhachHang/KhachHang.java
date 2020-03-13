/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachHang;

import java.util.Date;

/**
 *
 * @author Hieu Vo
 */
public class KhachHang {

    private String MaKH, TenKH, GioiTinh, DienThoai, DiaChi, CMND, MaLKH;
    private Date NgaySinh, NgayDK;
    private int DiemTichLuy;

    public KhachHang(String Ma, String Ten, String Sex, Date NgayS, String Dt, String dc, String CM, Date Ngaydk, int Diem, String Malkh) {
        MaKH = Ma;
        TenKH = Ten;
        GioiTinh = Sex;
        NgaySinh = NgayS;
        DienThoai = Dt;
        DiaChi = dc;
        CMND = CM;
        NgayDK = Ngaydk;
        MaLKH = Malkh;
        DiemTichLuy = Diem;

    }

    public String getMaKH() {
        return MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getGioiTinh() {
        return GioiTinh;

    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public String getdt() {
        return DienThoai;
    }

    public String getdc() {
        return DiaChi;
    }

    public String getCMND() {
        return CMND;
    }

    public Date getNgayDK() {
        return NgayDK;
    }

    public String getMaLKH() {
        return MaLKH;
    }

    public int getDiemTichLuy() {
        return DiemTichLuy;
    }

}
