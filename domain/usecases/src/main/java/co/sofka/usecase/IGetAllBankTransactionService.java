package co.sofka.usecase;




import co.sofka.BankTransaction;
import co.sofka.dto.*;

import java.util.List;

@FunctionalInterface
public interface IGetAllBankTransactionService {
    List<BankTransaction> getAll();

}
