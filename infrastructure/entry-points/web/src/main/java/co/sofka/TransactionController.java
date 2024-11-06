package co.sofka;


import co.sofka.command.create.RegisterTransactionDepositSucursalHandler;
import co.sofka.command.create.RegisterTransactionDepositTransferHandler;
import co.sofka.command.create.RegisterTransactionWithDrawFromATMHandler;
import co.sofka.command.dto.BankTransactionDepositSucursal;
import co.sofka.command.dto.BankTransactionDepositTransfer;
import co.sofka.command.dto.BankTransactionWithdrawFromATM;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.data.ResponseAPI;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final RegisterTransactionWithDrawFromATMHandler handler;

    private final RegisterTransactionDepositSucursalHandler handlerDeposit;

    private final RegisterTransactionDepositTransferHandler handlerTransfer;


    @PostMapping("/retitarCajero")
    public ResponseEntity<ResponseMs<Transaction>> bankTransactionWithdrawFromATM(@Valid @RequestBody RequestMs<BankTransactionWithdrawFromATM> bankTransaction) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>(handler.withdrawFromATM(bankTransaction), HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseMs<Transaction>> bankTransactionDeposit(@Valid @RequestBody RequestMs<BankTransactionDepositSucursal> item) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( handlerDeposit.apply(item), HttpStatus.OK);
    }


    @PostMapping("/depositTrasfer")
    public ResponseEntity<ResponseMs<Transaction>> bankTransactionDepositTrasfer(@Valid @RequestBody RequestMs<BankTransactionDepositTransfer> item) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( handlerTransfer.apply(item), HttpStatus.OK);
    }



}
