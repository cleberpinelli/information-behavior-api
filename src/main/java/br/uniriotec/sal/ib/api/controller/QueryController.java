package br.uniriotec.sal.ib.api.controller;

import br.uniriotec.sal.ib.api.http.resources.request.QueryRequest;
import br.uniriotec.sal.ib.api.http.resources.response.QueryResponse;
import br.uniriotec.sal.ib.domain.model.Query;
import br.uniriotec.sal.ib.domain.service.QueryService;
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
@RequestMapping(value = "/query")
public class QueryController extends BaseController {
    private final QueryService service;

    private final ModelMapper modelMapper;

    public QueryController(QueryService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Buscar query por ID", nickname = "getQueryById", notes = "Returns a single Query", response = QueryResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = QueryResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Query not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final Query model = service.findById(id);
        QueryResponse response = modelMapper.map(model, QueryResponse.class);
        return respondOk(response);

    }


    @ApiOperation(value = "Criar nova Query", nickname = "addQuery", notes = "Criar Query")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid QueryRequest modelRequest) {
        Query request = modelMapper.map(modelRequest, Query.class);
        Query created = service.create(request);
        QueryResponse response = modelMapper.map(created, QueryResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar Query existente ", nickname = "updateQuery", notes = "Atualiza Query", response = QueryResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = QueryResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "QueryModel not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody QueryRequest request) {
        Query data = modelMapper.map(request, Query.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar Query existente ", nickname = "deleteQuery", notes = "deleta Query", response = QueryResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = QueryResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "QueryeModel not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar Query", nickname = "findAll", notes = "Multiple search params can be provided", response = Query.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Query.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Query> modelPage = service.findAll(pageable);
        List<QueryResponse> content = modelPage.stream()
                .map(item -> modelMapper.map(item, QueryResponse.class))
                .collect(Collectors.toList());
        Page<QueryResponse> modelResponses = new PageImpl<>(content, pageable, modelPage.getTotalElements());
        return respondOk(modelResponses);
    }
}
