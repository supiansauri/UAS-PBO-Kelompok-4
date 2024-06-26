/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekipal;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Utama");
            System.out.println("1. Lihat Daftar Film");
            System.out.println("2. Pesan Tiket");
            System.out.println("3. Lihat Pemesanan Saya");
            System.out.println("4. Tambah Film");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    lihatDaftarFilm();
                    break;
                case 2:
                    pesanTiket();
                    break;
                case 3:
                    lihatPemesananSaya();
                    break;
                case 4:
                    tambahFilm();
                     break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void lihatDaftarFilm() {
        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Film")) {
            if (!rs.isBeforeFirst()) {
                System.out.println("Tidak ada film yang tersedia.");
                return;
            }
            while (rs.next()) {
                System.out.println("ID Film: " + rs.getInt("id_film"));
                System.out.println("Judul: " + rs.getString("judul"));
                System.out.println("Durasi: " + rs.getInt("durasi") + " menit");
                System.out.println("Rating: " + rs.getFloat("rating"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void pesanTiket() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID Film: ");
        int idFilm = scanner.nextInt();
        System.out.print("Masukkan Nomor Kursi: ");
        String kursi = scanner.next();

        try (Connection conn = DatabaseConnector.connect()) {
            String checkQuery = "SELECT * FROM Pemesanan WHERE id_film = ? AND kursi = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, idFilm);
                checkStmt.setString(2, kursi);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Kursi sudah dipesan.");
                        return;
                    }
                }
            }

            String insertQuery = "INSERT INTO Pemesanan (id_pengguna, id_film, tanggal_pesan, kursi) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, 1);  // Untuk contoh, id_pengguna di-set ke 1
                insertStmt.setInt(2, idFilm);
                insertStmt.setDate(3, new Date(System.currentTimeMillis()));
                insertStmt.setString(4, kursi);
                insertStmt.executeUpdate();
                System.out.println("Pemesanan berhasil.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lihatPemesananSaya() {
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Pemesanan WHERE id_pengguna = ?")) {
            stmt.setInt(1, 1);  // Untuk contoh, id_pengguna di-set ke 1
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("Tidak ada pemesanan yang ditemukan.");
                    return;
                }
                while (rs.next()) {
                    System.out.println("ID Pemesanan: " + rs.getInt("id_pemesanan"));
                    System.out.println("ID Film: " + rs.getInt("id_film"));
                    System.out.println("Tanggal Pesan: " + rs.getDate("tanggal_pesan"));
                    System.out.println("Kursi: " + rs.getString("kursi"));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void tambahFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan judul film: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan durasi film (menit): ");
        int durasi = scanner.nextInt();
        System.out.print("Masukkan rating film: ");
        float rating = scanner.nextFloat();

        try (Connection conn = DatabaseConnector.connect()) {
            String sql = "INSERT INTO Film (judul, durasi, rating) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, judul);
                stmt.setInt(2, durasi);
                stmt.setFloat(3, rating);
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Film berhasil ditambahkan.");
                } else {
                    System.out.println("Gagal menambahkan film.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
