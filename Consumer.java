
class Consumer extends Thread {
    // Referência para buffer compartilhado
    private Buffer buffer;

    // Construtor
    public Consumer(Buffer buffer, String c) {
        super(c);
        this.buffer = buffer;
    }

    // Método redefinido que executa a função da thread
    @Override
    public void run() {
        try {
            // Tenta consumir um número inteiro
            while (true) {
                // se consome, reduz o número de produzido de 1
                Semaphores.full.acquire();
                // adquire semáforo para acesso exclusivo
                Semaphores.mutex.acquire();
                buffer.get();
                // libera acesso exclusivo
                Semaphores.mutex.release();
                // como consumiu, incrementa em 1 a contagem de livres
                Semaphores.empty.release();
                // Thread.yield();
                //Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
