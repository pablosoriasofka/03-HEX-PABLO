package co.sofka.command.dto;



public class BankTransactionBuys {


    private String accountNumberClient;


    private String accountNumberStore;


    private Double amount;


    private int typeBuys;

    private String pin;

    public BankTransactionBuys() {
    }

    public String getAccountNumberClient() {
        return accountNumberClient;
    }

    public void setAccountNumberClient(String accountNumberClient) {
        this.accountNumberClient = accountNumberClient;
    }

    public String getAccountNumberStore() {
        return accountNumberStore;
    }

    public void setAccountNumberStore(String accountNumberStore) {
        this.accountNumberStore = accountNumberStore;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getTypeBuys() {
        return typeBuys;
    }

    public void setTypeBuys(int typeBuys) {
        this.typeBuys = typeBuys;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
