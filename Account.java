
import java.util.concurrent.Semaphore;

public class Account {
    private double balance;
    private final Semaphore mutex;
    private final Semaphore withdrawSemaphore;

    public Account(double initialBalance) {
        this.balance = initialBalance;
        this.mutex = new Semaphore(1);
        this.withdrawSemaphore = new Semaphore(1);
        System.out.println("Conta criada com saldo inicial de: " + initialBalance);
    }

    public void deposit(String name, double amount) throws InterruptedException {
        mutex.acquire();
        try {
            balance += amount;
            String message = String.format("Cliente: %s depositou %.2f%nConta: saldo atualizado de %.2f", name, amount, balance);
            System.out.println(message);
        } finally {
            mutex.release();
        }
    }

    public void withdraw(String name, double amount) throws InterruptedException {
        withdrawSemaphore.acquire();
        try {
            mutex.acquire();
            try {
                if (balance >= amount) {
                    balance -= amount;
                    String message = String.format("Cliente: %s retirou %.2f%nConta: saldo atualizado de %.2f", name, amount, balance);
                    System.out.println(message);
                } else {
                    String message = String.format("Cliente: %s tentou retirar %.2f, mas saldo insuficiente (%.2f)", name, amount, balance);
                    System.out.println(message);
                }
            } finally {
                mutex.release();
            }
        } finally {
            withdrawSemaphore.release();
        }
    }

    public double getBalance() {
        return balance;
    }
} 