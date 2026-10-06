package br.insper.turismo.atracao;

import br.insper.turismo.destino.Destino;
import br.insper.turismo.destino.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AtracaoService {

    @Autowired
    private AtracaoRepository atracaoRepository;

    @Autowired
    private DestinoService destinoService;

    public Atracao salvar(Atracao atracao) {
        Destino destino = destinoService.buscarPorId(atracao.getDestino().getId());

        atracao.setDestino(destino);

        return atracaoRepository.save(atracao);
    }

    public Page<Atracao> list(String pais, Pageable pageable) {
        return atracaoRepository.findAll(pageable);
    }


    public Atracao getById(int idAtracao) {
        return atracaoRepository
                .findById(idAtracao)
                .orElseThrow(() -> new RuntimeException("Atracao nao encontrada"));
    }
}
