package br.uniriotec.sal.ib.domain.service;

import br.uniriotec.sal.ib.domain.exception.NotFoundException;
import br.uniriotec.sal.ib.domain.model.Result;
import br.uniriotec.sal.ib.domain.repository.ResultRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService{
    private final ResultRepository repository;

    public ResultServiceImpl(ResultRepository resultRepository){
        this.repository = resultRepository;
    }
    @Override
    public Result create(Result entity) {
        return repository.save(entity);
    }

    @Override
    public Result findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Resultado não encontrado."));
    }

    @Override
    public Page<Result> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, Result entity) {
        Result result = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, result);

        repository.save(result);
    }

    @Override
    public void delete(Long id) {
        Result result = findById(id);
        repository.delete(result);
    }
}
