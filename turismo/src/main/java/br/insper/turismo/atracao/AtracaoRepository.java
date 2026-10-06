package br.insper.turismo.atracao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtracaoRepository extends JpaRepository<Atracao, Integer> {
}
