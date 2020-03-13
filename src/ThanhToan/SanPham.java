/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThanhToan;

/**
 *
 * @author Hieu Vo
 */
public class SanPham {

    private String Tensp, Masp, Link;
    private int Gia, SLDC;

    public SanPham() {

    }

    public SanPham(String tensp, int gia, String link, String masp) {
        Masp = masp;
        Tensp = tensp;
        Gia = gia;
        Link = link;
    }

    public void setSLDC(int x) {
        SLDC = x;
    }

    public int getSLDC() {
        return SLDC;
    }

    public String getTensp() {
        return Tensp;
    }

    public String getLink() {
        return "/ThanhToan/LinkAnh/"+this.Tensp+".PNG";
    }

    public int getGia() {
        return Gia;
    }

    public String getMaSP() {
        return Masp;
    }
}
