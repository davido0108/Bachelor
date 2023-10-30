import java.util.concurrent.locks.ReentrantLock;

class BankAccount2 {
    private int id;
    private double amount;
    private ReentrantLock lock;
    BankAccount2(int id, double amount) {
        this.id = id; this.amount = amount;
        lock = new ReentrantLock();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        lock.lock();

        this.id = id;
    }
    public double getAmount() {
        lock.lock();
        try{
            return amount;
        }
        finally {
            lock.unlock();
        }
    }
    public boolean withdraw(double sum){

        if (amount < sum)
            return false;
        amount -= sum;
        return true;
    }
    public void deposit(double sum){ amount += sum;
    }
    public boolean transfer(double sum, BankAccount2 receiver){
        if (amount < sum)
            return false;
        amount -= sum;
        receiver.amount += sum;
        return true;
    }
    public static boolean transferBetween(double sum, BankAccount2 from, BankAccount2 to){
        BankAccount2 first, second;
        

        if (from.amount < sum) return false;
        from.amount -= sum;
        to.amount += sum;
        return true;
    }
}