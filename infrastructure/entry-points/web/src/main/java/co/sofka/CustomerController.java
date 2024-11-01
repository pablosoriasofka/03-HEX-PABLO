package co.sofka;


import co.sofka.command.dto.CustomerDTO;
import co.sofka.command.query.ListAllCustomerHandler;
import co.sofka.data.ResponseAPI;
import co.sofka.usecase.IGetAllCustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
@AllArgsConstructor
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ListAllCustomerHandler handler;


    @GetMapping("/all")
    public ResponseEntity<ResponseAPI<List<CustomerDTO>>> getAll() {
        logger.info("Buscando todos los Customer");
        return new ResponseEntity<>( ResponseAPI.<List<CustomerDTO>>builder().bodyOut(handler.getAll())
                .message("Customer encontrados").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }



}
