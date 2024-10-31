package co.sofka;


import co.sofka.data.ResponseAPI;
import co.sofka.dto.*;
import co.sofka.usecase.IBankTransactionService;
import co.sofka.usecase.IGetAllBankTransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bankTransaction")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final IBankTransactionService service;

    private final IGetAllBankTransactionService getAllBankTransactionService;

    public MainController(IGetAllBankTransactionService getAllBankTransactionService,IBankTransactionService service) {
        this.service = service;
        this.getAllBankTransactionService = getAllBankTransactionService;
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseAPI<String>> tets() {
        logger.info("Test");
        return new ResponseEntity<>( ResponseAPI.<String>builder().bodyOut("test")
                .message("test").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<BankTransaction>>> getAll() {
        logger.info("Buscando todos los bancos");
        return new ResponseEntity<>( ResponseAPI.<List<BankTransaction>>builder().bodyOut(getAllBankTransactionService.getAll())
                .message("Bancos encontrados").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }



    @PostMapping("/retitarCajero")
    public ResponseEntity<ResponseAPI<BankTransaction>> bankTransactionWithdrawFromATM(@Valid @RequestBody BankTransactionWithdrawFromATM bankTransaction) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( ResponseAPI.<BankTransaction>builder().bodyOut(service.withdrawFromATM(bankTransaction))
                .message("Transaccion bancaria guardada").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }


    @PostMapping("/buys")
    public ResponseEntity<ResponseAPI<BankTransaction>> bankTransactionBuys(@Valid @RequestBody BankTransactionBuys item) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( ResponseAPI.<BankTransaction>builder().bodyOut(service.buys(item))
                .message("Transaccion bancaria guardada").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }




    @PostMapping("/deposit")
    public ResponseEntity<ResponseAPI<BankTransaction>> bankTransactionDeposit(@Valid @RequestBody BankTransactionDepositSucursal item) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( ResponseAPI.<BankTransaction>builder().bodyOut(service.depositSucursal(item))
                .message("Transaccion bancaria guardada").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }


    @PostMapping("/depositCajero")
    public ResponseEntity<ResponseAPI<BankTransaction>> bankTransactionDepositCajero(@Valid @RequestBody BankTransactionDepositCajero item) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( ResponseAPI.<BankTransaction>builder().bodyOut(service.depositCajero(item))
                .message("Transaccion bancaria guardada").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }


    @PostMapping("/depositTrasfer")
    public ResponseEntity<ResponseAPI<BankTransaction>> bankTransactionDepositTrasfer(@Valid @RequestBody BankTransactionDepositTransfer item) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( ResponseAPI.<BankTransaction>builder().bodyOut(service.depositTrasferencia(item))
                .message("Transaccion bancaria guardada").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }
}
