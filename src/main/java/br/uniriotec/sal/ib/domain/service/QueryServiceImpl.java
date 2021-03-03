package br.uniriotec.sal.ib.domain.service;

import br.uniriotec.sal.ib.domain.exception.NotFoundException;
import br.uniriotec.sal.ib.domain.model.Query;
import br.uniriotec.sal.ib.domain.repository.QueryRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements QueryService {
    private final QueryRepository repository;

    public QueryServiceImpl(QueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Query create(Query entity) {
        return repository.save(entity);
    }

    @Override
    public Query findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Query não encontrada."));
    }

    @Override
    public Page<Query> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, Query entity) {
        Query exemplo = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, exemplo);

        repository.save(exemplo);
    }

    @Override
    public void delete(Long id) {
        Query exemplo = findById(id);
        repository.delete(exemplo);
    }
}
