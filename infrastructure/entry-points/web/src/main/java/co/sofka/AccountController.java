package co.sofka;


import co.sofka.command.query.FindAccountByNumberHandler;
import co.sofka.command.query.ListAllAccountHandler;
import co.sofka.data.ResponseAPI;
import co.sofka.usecase.IGetAllAccountService;
import co.sofka.usecase.IGetAllCustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final ListAllAccountHandler handler;

    private final FindAccountByNumberHandler findAccountByNumberHandler;


    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<Account>>> getAll() {
        logger.info("Buscando todos los Account");
        return new ResponseEntity<>( ResponseAPI.<List<Account>>builder().bodyOut(handler.getAll())
                .message("Account encontrados").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }


    @GetMapping("/byNumber")
    public ResponseEntity<ResponseAPI<Account>> getByNumber(@RequestParam String number) {
        logger.info("Buscando todos los Account");
        return new ResponseEntity<>( ResponseAPI.<Account>builder().bodyOut(findAccountByNumberHandler.findByNumber(number))
                .message("Account encontrados").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }



}
