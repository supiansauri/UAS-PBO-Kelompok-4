/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekipal;
import java.sql.Date;

public class Pemesanan {
    private int idPemesanan;
    private int idPengguna;
    private int idFilm;
    private Date tanggalPesan;
    private String kursi;

    public Pemesanan(int idPemesanan, int idPengguna, int idFilm, Date tanggalPesan, String kursi) {
        this.idPemesanan = idPemesanan;
        this.idPengguna = idPengguna;
        this.idFilm = idFilm;
        this.tanggalPesan = tanggalPesan;
        this.kursi = kursi;
    }

    public int getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(int idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public Date getTanggalPesan() {
        return tanggalPesan;
    }

    public void setTanggalPesan(Date tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public String getKursi() {
        return kursi;
    }

    public void setKursi(String kursi) {
        this.kursi = kursi;
    }
}
