package br.uniriotec.sal.ib.api.controller;

import br.uniriotec.sal.ib.api.http.resources.request.ResultRequest;
import br.uniriotec.sal.ib.api.http.resources.response.ResultResponse;
import br.uniriotec.sal.ib.domain.model.Result;
import br.uniriotec.sal.ib.domain.service.ResultService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/result")
public class ResultController extends BaseController{
    private final ResultService service;
    private final ModelMapper modelMapper;

    public ResultController(ResultService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Buscar result por ID", nickname = "getResultById", notes = "Returns a single Result", response = ResultResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ResultResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Result not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final Result model = service.findById(id);
        ResultResponse response = modelMapper.map(model, ResultResponse.class);
        return respondOk(response);

    }


    @ApiOperation(value = "Criar nova Result", nickname = "addResult", notes = "Criar Result")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ResultRequest modelRequest) {
        Result request = modelMapper.map(modelRequest, Result.class);
        Result created = service.create(request);
        ResultResponse response = modelMapper.map(created, ResultResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar Result existente ", nickname = "updateResult", notes = "Atualiza Result", response = ResultResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = ResultResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ResultModel not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody ResultRequest request) {
        Result data = modelMapper.map(request, Result.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar Result existente ", nickname = "deleteResult", notes = "deleta Result", response = ResultResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ResultResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ResulteModel not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar Result", nickname = "findAll", notes = "Multiple search params can be provided", response = Result.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Result.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Result> modelPage = service.findAll(pageable);
        List<ResultResponse> content = modelPage.stream()
                .map(item -> modelMapper.map(item, ResultResponse.class))
                .collect(Collectors.toList());
        Page<ResultResponse> modelResponses = new PageImpl<>(content, pageable, modelPage.getTotalElements());
        return respondOk(modelResponses);
    }
}
