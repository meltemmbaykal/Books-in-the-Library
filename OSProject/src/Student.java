// MELTEM BAYKAL-170315068

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class Student implements Runnable{   
    
    Random random=new Random();
    private int id;
    private Books[] books; //kitapların listesini tutar
    public ArrayList<Books> readvalue=new ArrayList<Books>();  //öğrencinin okuduğu kitapların listesini tutar      
      
 
    @Override
    public void run() {        
        while(true)
        {
            int bookId=random.nextInt(Constants.NUMBER_OF_BOOKS); //oluşturulan her kitap için random olarak o kitaba özel id üretir
            
            try { 
                if(!(readvalue.contains(books[bookId]))){  //eğer öğrenci tarafından o kitap okunmadıysa bu if bloğu çalışır     
                    System.out.println(this+ " tried to catch the " + books[bookId]); //öğrenci o kitabı almaya çalışır           
                 
                 if((!books[bookId].lock1)) //book sınıfındaki kitap kilitli değilse bu blok çalışır  
                 { books[bookId].read(this); }//öğrencinin o kitabı okumasını sağlıyor                    
                    
                 else{ //eğer öğrenci tarafından o kitap okunduysa bu blok çalışır
                         Long duration = (long) (Math.random() *2000); //random bekleme süresi atandı maksimum 2sn olacak şekilde
                         System.out.println(this+ "  didn't get " + books[bookId] + " (Milliseconds waiting:)" +duration); //öğrencinin o 
                                                                               //kitabı alamadığı,bekleme süresi ile birlikte ekrana yazdırılır
                         Thread.sleep(duration);  //random bekleme süresi kadar beklemeye alınır                      
                     }
             }                
             if(readvalue.size()==Constants.NUMBER_OF_BOOKS){ //öğrencinin okuduğu kitaplarının sayısı constants sınıfında oluşturduğumuz 
                                                               //kitap sayısına eşitse bu blok çalışır
                 System.out.println(this+" project completed.."); //öğrencinin istenen tüm kitapları okuduğunu gösterir 
                 break;
             }  
            } catch (InterruptedException ex) {  //programda hata yakalanması sonucunda bu hatayı ekrana yazdırıyor
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex); 
            }     
        }   
    }   
    
    
    public Student(int id,Books[] books){  //öğrenciler için oluşturulmuş constructor
        
        this.id=id;
        this.books=books;  
    }
    
    @Override
    public String toString() {   //override edilmiş toString fonksiyonu
        return "Student# " + id;
    }
    
 }
