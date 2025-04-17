
import java.util.concurrent.Semaphore;

public class Semaphores {
    // Semáforos do problema produtor/consumidor
    // para controlar o acesso à região crítica
    public static Semaphore mutex = new Semaphore(1);
    // para contar entradas vazias
    public static Semaphore empty = new Semaphore(10); // Using a fixed capacity of 10
    // para contar entradas cheias
    public static Semaphore full = new Semaphore(0);
}
