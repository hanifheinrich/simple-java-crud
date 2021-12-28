import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class transaksi implements crud {
    public String NamaBarang;
    public Integer NoFaktur;
    public Integer HargaBarang;
    public Integer Jumlah;
    public Integer SubTotal;
    public Integer Discount;
    public Integer TotalHarga;
   
    Scanner input = new Scanner (System.in);

    public void display(){
        System.out.println("Data Transaksi");

        String sql ="SELECT * FROM transaksi";
        conn = DriverManager.getConnection(url,"root","");
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

        while(result.next()){
            System.out.print("\nNo Faktur\t  : ");
            System.out.print(result.getInt("NoFaktur"));
            System.out.print("\nNama Barang\t  : ");
            System.out.print(result.getString("NamaBarang"));
            System.out.print("\nHarga Barang\t\t  : ");
            System.out.print(result.getInt("HargaBarang"));
            System.out.print("\nJumlah : ");
            System.out.print(result.getInt("Jumlah"));
            System.out.print("\nSubTotal : ");
            System.out.print(result.getInt("SubTotal"));
            System.out.print("\nDiscount : ");
            System.out.print(result.getInt("Discount"));
            System.out.print("\nTotal Harga\t  : ");
            System.out.print(result.getInt("TotalHarga"));
            System.out.print("\n");  
        }
   }

   public void insert()throws SQLException {
     System.out.println("Masukkan Data Transaksi");
    try{
        //NoFaktur   
        System.out.println("Inputkan No Faktur : ");
        NoFaktur = input.nextInt();

        //NamaBarang
        System.out.println("Inputkan Nama Barang : ");
        NamaBarang = input.nextLine();

        //HargaBarang
        System.out.println("Inputkan Harga Barang : ");
        HargaBarang = input.nextInt(); 

        //Jumlah
        System.out.println("Inputkan Jumlah : ");
        Jumlah = input.nextInt();

        //SubTotal
        SubTotal = Jumlah * HargaBarang;

        //Discount
        if (SubTotal >= 100000){
            Discount = SubTotal * 30/100;
        }

        else if (SubTotal >= 50000){
            Discount = SubTotal * 20/100; 
        }

        else if (SubTotal >= 25000){
            Discount = SubTotal * 10/100;
        }

        else {
            Discount = 0;

        }

        //TotalHarga
        TotalHarga = SubTotal - Discount;

        String sql = "INSERT INTO transaksi (NoFaktur, NamaBarang, HargaBarang, Jumlah, SubTotal, Discount, TotalHarga) VALUES ('"+NoFaktur+"','"+NamaBarang+"','"+HargaBarang+"','"+Jumlah+"','"+SubTotal+"','"+Discount+"','"+TotalHarga+"')";
        conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil input data!!");
    }
    catch (SQLException e) {
        System.err.println("Terjadi kesalahan input data");
    } 
    catch (InputMismatchException e) {
        System.err.println("Inputan harus berupa angka");
       }
    }

   public void update(){
    System.out.print("Ubah Data Transaksi");

    try {
        display();
        System.out.print("\nMasukkan No Faktur yang akan di ubah : ");
        Integer NoFaktur = Integer.parseInt(input.nextLine());
        
        String sql = "SELECT * FROM transaksi WHERE NoFaktur = " +NoFaktur;
        conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
        if(result.next()){
            
            System.out.print("Nama Barang ["+result.getString("NamaBarang")+"]\t: ");
            String NamaBarang = input.nextLine();
               
            sql = "UPDATE transaksi SET NamaBarang='"+NamaBarang+"' WHERE NoFaktur='"+NoFaktur+"'";
            //System.out.println(sql);

            if(statement.executeUpdate(sql) > 0){
                System.out.println("Berhasil memperbaharui data  (NoFaktur "+NoFaktur+")");
            }
        }
        statement.close();        
    } 
    catch (SQLException e) {
        System.err.println("Terjadi kesalahan dalam mengedit data");
        System.err.println(e.getMessage());
    }
   }

   public void search(){
    System.out.print("Cari Data Transaksi");

    System.out.print("Masukkan No Faktur yang ingin dilihat : ");    
    Integer keyword = Integer.parseInt(input.nextLine());

    String sql = "SELECT * FROM transaksi WHERE NoFaktur LIKE '%"+keyword+"%'";
    conn = DriverManager.getConnection(url,"root","");
    Statement statement = conn.createStatement();
    ResultSet result = statement.executeQuery(sql);

    while(result.next()){
        System.out.print("\nNo Faktur\t  : ");
        System.out.print(result.getInt("NoFaktur"));
        System.out.print("\nNama Barang\t  : ");
        System.out.print(result.getString("NamaBarang"));
        System.out.print("\nHarga Barang\t\t  : ");
        System.out.print(result.getInt("HargaBarang"));
        System.out.print("\nJumlah : ");
        System.out.print(result.getInt("Jumlah"));
        System.out.print("\nSubTotal : ");
        System.out.print(result.getInt("SubTotal"));
        System.out.print("\nDiscount : ");
        System.out.print(result.getInt("Discount"));
        System.out.print("\nTotal Harga\t  : ");
        System.out.print(result.getInt("TotalHarga"));
        System.out.print("\n");  
    }
   }

   public void delete(){
    System.out.print("Hapus Data");

    try{
        display();
        System.out.print("\nMasukan No Faktur yang akan Anda Hapus : ");
        Integer NoFaktur= Integer.parseInt(input.nextLine());
        
        String sql = "DELETE FROM transaksi WHERE NoFaktur = "+ NoFaktur;
        conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        //ResultSet result = statement.executeQuery(sql);
        
        if(statement.executeUpdate(sql) > 0){
            System.out.println("Berhasil menghapus data transaksi (Nomor "+NoFaktur+")");
        }
   }
    catch(SQLException e){
        System.out.println("Terjadi kesalahan dalam menghapus data");
    }
    catch(Exception e){
        System.out.println("masukan data yang benar");
    }
  }
}

