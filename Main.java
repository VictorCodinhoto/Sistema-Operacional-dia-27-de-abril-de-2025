public class Main {
    public static void main(String[] args) {
        // Cria contas separadas para cada cliente
        Client[] clients = {
            new Client("Augustus", new Account(100)),
            new Client("Lucius", new Account(100)),
            new Client("Claudius", new Account(100)),
            new Client("Tiberius", new Account(100))
        };

        // Inicia as threads dos clientes
        for (Client client : clients) {
            client.start();
        }

        // Aguarda indefinidamente (o programa será encerrado com CTRL+C)
        try {
            while (true) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}