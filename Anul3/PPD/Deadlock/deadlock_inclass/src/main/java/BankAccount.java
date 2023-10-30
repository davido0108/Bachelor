class BankAccount {
    private int id;
    private double amount;
    BankAccount(int id, double amount)
    {
        this.id = id;
        this.amount = amount;
    }
    public synchronized int getId() {
        return id;
    }
    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized double getAmount() {
        return amount;
    }

    public synchronized boolean withdraw(double sum){
        if (amount < sum) return false;
        amount -= sum; return true;
    }

    public synchronized void deposit(double sum){
        amount += sum;
    }

    public synchronized boolean transfer(double sum, BankAccount receiver){
        if (amount < sum) return false;
        amount -= sum; receiver.amount += sum;
        return true;
    }
    public static boolean transferBetween(double sum, BankAccount from, BankAccount to){
        synchronized (from){
            synchronized (to){
                if (from.amount < sum) return false;
                from.amount -= sum; to.amount += sum;
                return true;
            }
        }

    }

    public synchronized static void main(String[] args){
        BankAccount b1 = new BankAccount(1, 200.0d),
                b2 = new BankAccount(2, 300.0d);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                b1.deposit(20d);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                b2.withdraw(30);
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                b1.transfer(50d, b2);
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                b2.transfer(100d, b1);
            }
        });
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                transferBetween(45d,b1,b2);
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        System.out.println("Cont1: " + b1.getAmount() + " \n" + "Cont2: " +b2.getAmount());
    }
}


