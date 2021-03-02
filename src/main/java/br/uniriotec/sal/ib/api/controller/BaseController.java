package br.uniriotec.sal.ib.api.controller;

import br.uniriotec.sal.ib.domain.exception.ValidationException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseController {
    protected static final String SEM_ENTRADA = "Um ou mais parâmetros obrigatórios de entrada estão nulos";
    protected static final String NENHUM_CONTEUDO = "Nenhum retorno encontrado para os parâmetros passados";


    protected void verify(BindingResult result) {
        if (result.hasErrors()) {
            List<String> messages = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            Map<String, Object> erro = new HashMap<>();
            erro.put("errors", messages);

            throw new ValidationException(erro);
        }
    }

    protected ResponseEntity<Object> respondOk(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    protected ResponseEntity<?> responseString(String string) {
        return new ResponseEntity<>(string, HttpStatus.OK);
    }

    protected ResponseEntity<?> respondOk() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ResponseEntity<Object> respondCreated(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    ResponseEntity<Object> respondAccepted(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }

    protected ResponseEntity<Object> respondNoContent(Object message) {
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

    protected ResponseEntity<Object> respondFound(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.FOUND);
    }

    protected ResponseEntity<Object> respondBadRequest(Object message) {
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    ResponseEntity<Object> respondForbidden(Object message) {
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    protected ResponseEntity<Object> respondError(String message) {
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<Object> respondError(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<?> respondNotFound(String message) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");

        return new ResponseEntity<Object>(new JSONObject().put("erro",
                new JSONObject().put("messsage", message)
                        .put("statusCode", 404)).toString(),
                headers, HttpStatus.NOT_FOUND);
    }
}
