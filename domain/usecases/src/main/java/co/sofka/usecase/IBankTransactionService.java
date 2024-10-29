package co.sofka.usecase;




import co.sofka.BankTransaction;
import co.sofka.dto.*;

import java.util.List;

public interface IBankTransactionService {
    List<BankTransaction> getAll();

    BankTransaction withdrawFromATM(BankTransactionWithdrawFromATM bankTransaction);

    BankTransaction buys(BankTransactionBuys item);

    BankTransaction depositSucursal(BankTransactionDepositSucursal item);

    BankTransaction depositCajero(BankTransactionDepositCajero item);

    BankTransaction depositTrasferencia(BankTransactionDepositTransfer item);
}
