package br.insper.turismo.destino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    public Destino salvar(Destino destino) {
        return destinoRepository.save(destino);
    }

    public Page<Destino> list(String pais, Pageable pageable) {
        if (pais != null) {
            return destinoRepository.findByPais(pais, pageable);
        }
        return destinoRepository.findAll(pageable);
    }


    public Destino buscarPorId(int id) {
        return destinoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));
    }
}
