/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;

import java.util.Date;
import qlrap.*;

/**
 *
 * @author Hieu Vo
 */
public class NhanVien {

    private String  TenNV, GioiTinh, DienThoai, DiaChi, CMND, TenTK;
    private Date NgaySinh, NgayVL;
    private int MaNV;

    public NhanVien(int Ma, String Ten, String Sex, Date NgayS, String Dt, String dc, String CM, Date Ngayvl, String tentk) {
        MaNV = Ma;
        TenNV = Ten;
        GioiTinh = Sex;
        NgaySinh = NgayS;
        DienThoai = Dt;
        DiaChi = dc;
        CMND = CM;
        NgayVL = Ngayvl;
        TenTK = tentk;
    }

    public int getMaNV() {
        return MaNV;
    }

    public String getTenNV() {
        return TenNV;
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

    public Date getNgayVL() {
        return NgayVL;
    }

    public String getTenTK() {
        return TenTK;
    }

}
