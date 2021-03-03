package br.uniriotec.sal.ib.domain.repository;

import br.uniriotec.sal.ib.domain.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface ResultRepository extends RevisionRepository<Result, Long, Integer>, JpaRepository<Result, Long> {
}
