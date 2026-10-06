package br.insper.turismo.atracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class AtracaoController {

    @Autowired
    private AtracaoService atracaoService;

    @GetMapping("/atracoes")
    public Page<Atracao> list(@RequestParam(required = false) String pais,
                              Pageable pageable) {
        return atracaoService.list(pais, pageable);
    }

    @PostMapping("/atracoes")
    public Atracao save(@RequestBody Atracao atracao) {
        return atracaoService.salvar(atracao);
    }
}
