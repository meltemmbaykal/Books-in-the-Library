// MELTEM BAYKAL-170315068

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Books {    
    private int id;
    private Lock lock;
    public boolean lock1=false; //lock1'in false yada true olması durumu kitabın kilitli olup olmadığının kontrolünü sağlar
    
        
    void read(Student student)throws InterruptedException {
        
        lock.lock();   //kitap o öğrenci tarafından alınmıştır.bu yüzden kitabın bir başka thread tarafından alınması engellenir
        lock1=true;    //lock1'i false yaparak kilitleme sağlanıyor
        Long duration = (long) (Math.random() *3000); //random bekleme süresi atandı maksimum 3sn olacak şekilde
        System.out.println(student+ " started reading the " + this + " (Estimated Reading time:  "+duration+ ")"); //öğrencinin okumaya başladığı kitap,
                                                                                                   //bekleme süresi ile birlikte ekrana yazdırılır
        Thread.sleep(duration);       //random bekleme süresi kadar o thread beklemeye alınır 
        student.readvalue.add(this);  //okunan kitabı o öğrenci için okunan kitaplar(readvalue)listesine eklenir
        System.out.println(student+ " finished reading the " + this ); //öğrencinin okuduğu kitap ekrana yazdırılır
        lock1=false; //lock1'i true yaparak kilidi sağlanıyor
        lock.unlock(); //kitabı başka bir thread'in erişebilmesi için kilid açılır    
    }
    
    @Override
    public String toString() {    //toString methodunu override etmemizi sağlar
        return "Book #" +id;      //ekrana kitabın idsini yazdırır
    }  
    
    public boolean lock1(){ //lock1 için getter methodu
        return lock1;
    }
    
    public Books(int id){  //books için getter methodu 
        this.id=id;
        this.lock=new ReentrantLock(); 
    }    
}
