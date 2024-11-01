package co.sofka;


import co.sofka.command.create.RegisterTransactionDepositSucursalHandler;
import co.sofka.command.create.RegisterTransactionDepositTransferHandler;
import co.sofka.command.create.RegisterTransactionWithDrawFromATMHandler;
import co.sofka.command.dto.BankTransactionDepositSucursal;
import co.sofka.command.dto.BankTransactionDepositTransfer;
import co.sofka.command.dto.BankTransactionWithdrawFromATM;
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
    public ResponseEntity<ResponseAPI<Transaction>> bankTransactionWithdrawFromATM(@Valid @RequestBody BankTransactionWithdrawFromATM bankTransaction) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( ResponseAPI.<Transaction>builder().bodyOut(handler.withdrawFromATM(bankTransaction))
                .message("Transaccion bancaria guardada").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseAPI<Transaction>> bankTransactionDeposit(@Valid @RequestBody BankTransactionDepositSucursal item) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( ResponseAPI.<Transaction>builder().bodyOut(handlerDeposit.apply(item))
                .message("Transaccion bancaria guardada").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }


    @PostMapping("/depositTrasfer")
    public ResponseEntity<ResponseAPI<Transaction>> bankTransactionDepositTrasfer(@Valid @RequestBody BankTransactionDepositTransfer item) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( ResponseAPI.<Transaction>builder().bodyOut(handlerTransfer.apply(item))
                .message("Transaccion bancaria guardada").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }



}
