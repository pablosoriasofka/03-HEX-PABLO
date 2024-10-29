package co.sofka.dto;


public class BankTransactionWithdrawFromATM {


    private String accountNumber;


    private Double amount;


    private String pin;

    public BankTransactionWithdrawFromATM() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
