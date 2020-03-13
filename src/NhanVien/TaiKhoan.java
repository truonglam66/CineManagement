/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;

/**
 *
 * @author Hieu Vo
 */
public class TaiKhoan {
    private String tenDN,matKhau,viTri;
    
    public TaiKhoan(String ten,String pw,String vt)
    {
        tenDN=ten;
        matKhau=pw;
        viTri=vt;
    }
    public String getTenDN()
    {
        return tenDN;
    }
    public String getMatKhau(){
        return matKhau;
    }
    public String getViTri(){
        return viTri;
    }
}
