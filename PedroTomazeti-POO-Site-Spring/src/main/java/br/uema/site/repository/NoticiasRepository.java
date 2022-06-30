package br.uema.site.repository;

import br.uema.site.model.Noticia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface NoticiasRepository extends CrudRepository <Noticia, Long>{
}
