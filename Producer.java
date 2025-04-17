
import java.util.Random;

class Producer extends Thread {
    // Referência para buffer compartilhado
    private Buffer buffer;

    // Construtor
    public Producer(Buffer buffer, String p) {
        super(p); // chama o construtor de Thread e passa o nome do parâmetro
        this.buffer = buffer;
    }

    // Método redefinido que executa a função da thread
    @Override
    public void run() {
        Random random = new Random();
        try {
            // Tenta produzir um número inteiro aleatório
            while (true) {
                // se produz, reduz o número livre de 1
                Semaphores.empty.acquire();
                // adquire semáforo para acesso exclusivo
                Semaphores.mutex.acquire();
                buffer.put(random.nextInt(1000));
                // libera acesso exclusivo
                Semaphores.mutex.release();
                // como produziu, incrementa em 1 a contagem produzida
                Semaphores.full.release();
                // Thread.yield();
                //Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
