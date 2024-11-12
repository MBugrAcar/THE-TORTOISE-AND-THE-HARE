import java.util.Random;

public class YarisSimulasyonu {

    // Hayvan sınıfı: İsmini ve pozisyonunu tutar
    static class Hayvan {
        String isim;
        int pozisyon;

        Hayvan(String isim) {
            this.isim = isim;
            this.pozisyon = 1;  // 1. kareden başla
        }

        // Hayvanın hareketini güncelle
        void hareketEt(int adim) {
            this.pozisyon += adim;
            if (this.pozisyon < 1) {
                this.pozisyon = 1;  //eksili sayi yazmasın diyeee
            }
        }
    }

    // Kaplumbağa için hareket belirleme fonksiyonu
    public static int kaplumbagaHareketi() {
        Random rand = new Random();
        int sans = rand.nextInt(100);  // 0-99 arası rastgele sayı

        if (sans < 50) 
        	return 3;   // %50 ihtimalle 3 adım ileri
        else if (sans < 70) 
        	return -6;  // %20 ihtimalle 6 adım geri
        else 
        	return 1;  // %30 ihtimalle 1 adım ileri
    }

    // Tavşan için hareket belirleme fonksiyonu
    public static int tavsanHareketi() {
        Random rand = new Random();
        int sans = rand.nextInt(100);  // 0-99 arası rastgele sayı

        if (sans < 20) 
        	return 0;   // %20 ihtimalle hareket yok
        else if (sans < 40) 
        	return 9;  // %20 ihtimalle 9 adım ileri
        else if (sans < 50) 
        	return -12;  // %10 ihtimalle 12 adım geri BURADA YÜZDELİK OLARAK BELİRLEMEK İÇİN HER SEFERİNDE KOŞULLARIN İÇİNDE SAYIYI YUKSELTMEMİZ GEREKİR
        else if (sans < 80) 
        	return 1;   // %30 ihtimalle 1 adım ileri
        else 
        	return -2;  // %20 ihtimalle 2 adım geri
    }

    // Pistteki durumu ekrana yazdırma
    public static void pistYazdir(Hayvan kaplumbaga, Hayvan tavsan) {
        char[] pist = new char[70];  // 70 karelik pist

        // Pist boşluklarla dolduruluyor
        for (int i = 0; i < pist.length; i++) {
            pist[i] = '-';
        }

        // Kaplumbağa ve tavşanın pozisyonlarını pistte göster
        if (kaplumbaga.pozisyon == tavsan.pozisyon) {
            pist[kaplumbaga.pozisyon - 1] = 'B';  // Aynı yerdeyse "B" yaz BURADA Kamlubağa olamk zorunluluğu yoktur (BANG)
        } else {
            pist[kaplumbaga.pozisyon - 1] = 'K';  // 'K' = Kaplumbağa
            pist[tavsan.pozisyon - 1] = 'T';  // 'T' = Tavşan
        }

        // Pisti ekrana yazdır
        System.out.println(new String(pist) + "[FİNİSH]");
       
    }

    public static void main(String[] args) {
        Hayvan kaplumbaga = new Hayvan("Kaplumbağa");
        Hayvan tavsan = new Hayvan("Tavşan");

        System.out.println("YARIŞ BAŞLIYOR!");
        System.out.println("BANG (SİLAH SESİ)!!!!!");
        System.out.println("HAYDİ KOŞUN !!!!");

       
        while (kaplumbaga.pozisyon < 70 && tavsan.pozisyon < 70) {
           
            kaplumbaga.hareketEt(kaplumbagaHareketi());
            tavsan.hareketEt(tavsanHareketi());

           
            pistYazdir(kaplumbaga, tavsan);

            
            try {
                Thread.sleep(1000);// 1 saniye bekleme (gerçek zamanlı simülasyon için)
            } catch (InterruptedException e) {
                e.printStackTrace(); //BU KISIMDA CHATGPTDEN YARDIM ALINMIŞTIR. BU KISIM YAZILMADIĞINDA KOD AŞIRI YUKLENİP ÇALIŞMIYOR.
            }
        }

      
        if (kaplumbaga.pozisyon >= 70 && tavsan.pozisyon >= 70) {
            System.out.println("Berabere!");
        } else if (kaplumbaga.pozisyon >= 70) {
            System.out.println("Kaplumbağa kazandı! YAY!!!");
        } else {
            System.out.println("Tavşan kazandı. ");
        }
    }
}
