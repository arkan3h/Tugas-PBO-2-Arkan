abstract class PaymentMethod{
    public String cekNamaPembayaran(){
        return "Anda melakukan pembayaran dengan "+this.getClass().getSimpleName();
    }
    abstract public int kredit(int jumlah);
    abstract public int deposit(int jumlah);
    abstract public int cekSaldo();
}

class debitBNI extends PaymentMethod{
    private int Saldo;

    public debitBNI(int pin){
        if(pin == 12345){
            System.out.println("Berhasil mengaktifkan kartu BNI!");
        }
        else{
            throw new ExceptionInInitializerError("PIN yang anda masukkan salah");
        }
    }

    public int catatTransaksi(String jenis, int jumlah){
        System.out.println("Mencatat transaksi "+jenis+" sejumlah "+jumlah+" ke Buku Tabungan");

        return 0;
    }

    public int kredit(int jumlah){
        this.catatTransaksi("transfer keluar", jumlah);
        this.Saldo -= jumlah;

        return this.Saldo;
    }

    public int deposit(int jumlah){
        this.catatTransaksi("deposit dana", jumlah);
        this.Saldo += jumlah;

        return this.Saldo;
    }

    public int cekSaldo(){
        return this.Saldo;
    }
}

class Paypal extends PaymentMethod{
    private int Balance;
    private String email;

    public Paypal(String email, int password){
        if (email == "morgan@gmail.com" & password == 12345){
            this.email = email;
            System.out.println("Berhasil login ke Paypal!");
        }
        else{
            throw new ExceptionInInitializerError("Tidak ada user dengan username/password tersebut");
        }
    }

    public int kirimNotifikasi(String pesan){
        System.out.println("Mengieim email notifikasi "+pesan+" ke "+this.email);

        return 0;
    }

    public int kredit(int jumlah){
        this.kirimNotifikasi("pengeluaran dana");
        this.Balance -= jumlah;

        return this.Balance;
    }

    public int deposit(int jumlah){
        this.kirimNotifikasi("penerimaan dana");
        this.Balance += jumlah;

        return this.Balance;
    }

    public int cekSaldo(){
        return this.Balance;
    }
}

class Pembeli{
    private String nama;
    private PaymentMethod payment;

    public Pembeli(String nama, PaymentMethod payment){
        this.nama = nama;
        this.payment = payment;
    }

    public int beli(String nama, int harga){
        if(this.payment.cekSaldo()<harga){
            System.out.println("Uang tidak cukup");
        }
        else{
            this.payment.kredit(harga);
            System.out.println("Berhasil melakukan pembelian "+nama+" seharga Rp. "+harga);
            System.out.println("Terima kasih "+this.nama+" :)");
        }

        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        try{
            PaymentMethod bayar = new Paypal("morgan@gmail.com",12345);
            bayar.deposit(12000000);
            Pembeli Arkan = new Pembeli("Morgan", bayar);
            Arkan.beli("Poster Smash Full Color", 100000);

            System.out.println("Saldo terakhir Rp. "+ bayar.cekSaldo());
            System.out.println(bayar.cekNamaPembayaran());
        }
        catch (Exception e){
            e.getMessage();
        }
    }
}