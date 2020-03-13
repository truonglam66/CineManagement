/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachHang;

/**
 *
 * @author Hieu Vo
 */
class LoaiKH {

    private String MaLKH, TenLKH;
    private int Diem_ToiThieu;

    public LoaiKH(String Ma, String Ten, int Diem) {
        MaLKH = Ma;
        TenLKH = Ten;
        Diem_ToiThieu = Diem;
    }

    public String getMaLKH() {
        return MaLKH;
    }

    public String getTenLKH() {
        return TenLKH;
    }

    public int getDiemToiThieu() {
        return Diem_ToiThieu;
    }

}
