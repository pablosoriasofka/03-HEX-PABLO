package co.sofka;


import co.sofka.command.create.TokenGenerateHandler;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.request.TokenInicilizer;
import co.sofka.command.dto.response.ResponseMs;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/utils")
@AllArgsConstructor
public class UtilsController {

    private static final Logger logger = LoggerFactory.getLogger(UtilsController.class);

    private final TokenGenerateHandler handler;




    @GetMapping("/generate")
    public ResponseEntity<ResponseMs<String>> generateToken(@RequestBody RequestMs<TokenInicilizer> request) {
        logger.info("Buscando todos los Customer");
        return new ResponseEntity<>( handler.apply(request), HttpStatus.OK);
    }



}
