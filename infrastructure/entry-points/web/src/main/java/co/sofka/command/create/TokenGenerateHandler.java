package co.sofka.command.create;

import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.request.TokenInicilizer;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.config.TokenByDinHeaders;
import co.sofka.security.configuration.entity.LoginPartnerRequest;
import co.sofka.security.configuration.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TokenGenerateHandler {

    private static final Logger logger = LoggerFactory.getLogger(TokenGenerateHandler.class);


   private final TokenByDinHeaders utils;

    private final JwtUtils jwtUtils;

    public ResponseMs<String> apply(RequestMs<TokenInicilizer> request) {

        ResponseMs<String> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();
        responseMs.setDinError(error);

        String encode = utils.encode(request.getDinBody().getTokenValue());

        LoginPartnerRequest loginPartnerRequest = new LoginPartnerRequest();
        loginPartnerRequest.setIdentificationNumber("123456789");
        loginPartnerRequest.setIdentificationDevice("123456789");
        loginPartnerRequest.setIdentificationType("123456789");
        String tokenString = jwtUtils.generateJwtToken(loginPartnerRequest);

        responseMs.setDinBody(tokenString);

        return responseMs;
    }
}
