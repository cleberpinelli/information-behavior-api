package br.uniriotec.sal.ib.domain.repository;

import br.uniriotec.sal.ib.domain.model.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface QueryRepository extends RevisionRepository<Query, Long, Integer>, JpaRepository<Query, Long> {
}
