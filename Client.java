
import java.util.Random;

public class Client extends Thread {
    private final String name;
    private final Account account;
    private final Random random;
    private final int[] amounts = {10, 20, 50, 100};

    public Client(String name, Account account) {
        this.name = name;
        this.account = account;
        this.random = new Random();
    }

    private void execute() throws InterruptedException {
        int amount = amounts[random.nextInt(amounts.length)];
        boolean isDeposit = random.nextBoolean();

        if (isDeposit) {
            account.deposit(name, amount);
        } else {
            account.withdraw(name, amount);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                execute();
                Thread.sleep(4000); // Simula tempo entre operações
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 