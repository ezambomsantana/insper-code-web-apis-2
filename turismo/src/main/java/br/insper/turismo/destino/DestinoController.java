package br.insper.turismo.destino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @GetMapping("/destinos")
    public Page<Destino> list(@RequestParam(required = false) String pais,
                              Pageable pageable) {
        return destinoService.list(pais, pageable);
    }

    @PostMapping("/destinos")
    public Destino save(@RequestBody Destino destino) {
        return destinoService.salvar(destino);
    }
}
