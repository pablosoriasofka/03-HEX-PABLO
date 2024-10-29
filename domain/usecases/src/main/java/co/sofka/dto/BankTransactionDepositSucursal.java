package co.sofka.dto;


public class BankTransactionDepositSucursal {

    private String accountNumberClient;


    private Double amount;

    public BankTransactionDepositSucursal() {
    }

    public String getAccountNumberClient() {
        return accountNumberClient;
    }

    public void setAccountNumberClient(String accountNumberClient) {
        this.accountNumberClient = accountNumberClient;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
