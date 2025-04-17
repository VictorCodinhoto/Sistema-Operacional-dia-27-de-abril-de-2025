public class Main {
    public static void main(String[] args) {
        // Cria a conta com saldo inicial
        Account account = new Account(100);

        Client[] clients = {
            new Client("Augustus", account),
            new Client("Lucius", account),
            new Client("Claudius", account),
            new Client("Tiberius", account)
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
