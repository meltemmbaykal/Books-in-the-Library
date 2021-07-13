// MELTEM BAYKAL-170315068

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    
    public static void main(String[] args) {
        
         Random random=new Random();
         Student[] students=null;
         Books[] books=null;
         
         ExecutorService executor = Executors.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS); //constants sınıfında oluşturulan öğrenci 
                                                                                                //sayısı kadar thread havuzu oluşturuluyor
             
         try{        
         books=new Books[Constants.NUMBER_OF_BOOKS]; //constants sınıfında belirttiğimiz sayıyı books içine atar
         students=new Student[Constants.NUMBER_OF_STUDENTS]; //constants sınıfında belirttiğimiz sayıyı students içine atar         
             
         
         for(int i=0;i<Constants.NUMBER_OF_BOOKS;i++) //constants sınıfında belirttiğimiz sayı kadar kitap üretir
         {
             books[i]=new Books(i); //oluşturulan her kitabı books arrayine atar
            
         }
         
          for(int j=0;j<Constants.NUMBER_OF_STUDENTS;j++) //constants sınıfında belirttiğimiz sayı kadar öğrenci üretir
         {
            students[j]=new Student(j,books); //oluşturulan öğrencilerin hepsine kitapların hepsi tanımlanır
            executor.execute(students[j]);     //thread havuzu oluşturuldu                   
         }  
          
         }        
         
         catch(Exception ex) //programda hata yakalanması sonucunda bu hatayı ekrana yazdırıyor
         {
             executor.shutdown(); //thread havuzu kapatılıyor
         }
               
          finally
         {           
             executor.shutdown(); //programda herhangi bir hata yakalanmadığında thread havuzunu kapatılıyor
             
         }
   
        
    }
    
     
}
