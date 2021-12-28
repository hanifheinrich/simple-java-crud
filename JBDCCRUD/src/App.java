import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class App {
	//static Scanner scanner;
	static Connection conn;
	
    public static void main(String[] args) {
    	Scanner input = new Scanner (System.in);
    	String pilihan;
    	boolean isLanjutkan = true;
    	
    	String url = "jdbc:mysql://localhost:3306/db_transaksi";
		try {
			
		String salamSapa = "Selamat Datang, Selamat menggunakan Program Ini";
		String sapa = salamSapa.replace("Selamat Datang", "Silahkan gunakan aplikasi ini dengan benar!"); 

		System.out.println(sapa.toLowerCase());

		Date datenow = new Date();
		SimpleDateFormat tgl = new SimpleDateFormat("E, dd/MM/yyyy");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss zzz");
	

			System.out.println("Tanggal \t: " + tgl.format(datenow));
			System.out.println("Waktu \t\t: " + time.format(datenow));
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan!!!");
			transaksi transaksi = new transaksi();
			
			while (isLanjutkan) {
				System.out.println("----------------------");
				System.out.println("Database Transaksi");
				System.out.println("----------------------");
				System.out.println("1. Lihat Data Transaksi");
				System.out.println("2. Tambah Data Transaksi");
				System.out.println("3. Ubah Data Transaksi");
				System.out.println("4. Hapus Data Transaksi");
				System.out.println("5. Cari Data Transaksi");
				
				System.out.print("\nPilihan anda (1/2/3/4/5): ");
				pilihan = input.next();
			
				switch (pilihan) {
				case "1":
					transaksi.display();
					break;
				case "2":
					transaksi.insert();
					break;
				case "3":
					transaksi.update();
					break;
				case "4":
					transaksi.delete();
					break;
				case "5":
					transaksi.search();
					break;
				default:
					System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
				}
				
				System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
				pilihan = input.next();
				isLanjutkan = pilihan.equalsIgnoreCase("y");

			}
			System.out.println("Program selesai...");
			
		}
		catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
		}
		catch(SQLException e){
			System.err.println("Tidak berhasil koneksi");
		}
    }
}
