package br.insper.turismo.visitante;

import br.insper.turismo.atracao.Atracao;
import br.insper.turismo.atracao.AtracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VisitanteService {

    @Autowired
    private VisitanteRepository visitanteRepository;

    @Autowired
    private AtracaoService atracaoService;

    public Visitante salvar(Visitante visitante) {
        return visitanteRepository.save(visitante);
    }

    public List<Visitante> list() {
        return visitanteRepository.findAll();
    }

    public Visitante adicionaVisitanteAtracao(int idVisitante, int idAtracao) {

        Atracao atracao = atracaoService.getById(idAtracao);
        Visitante visitante = getById(idVisitante);

        visitante.getAtracaos().add(atracao);
        atracao.getVisitantes().add(visitante);

        return visitanteRepository.save(visitante);

    }

    private Visitante getById(int idVisitante) {
        return visitanteRepository
                .findById(idVisitante)
                .orElseThrow(() -> new RuntimeException("Visitante nao encontrado"));
    }
}
