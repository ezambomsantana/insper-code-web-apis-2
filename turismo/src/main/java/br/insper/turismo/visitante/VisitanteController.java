package br.insper.turismo.visitante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VisitanteController {

    @Autowired
    private VisitanteService visitanteService;

    @GetMapping("/visitantes")
    public List<Visitante> list() {
        return visitanteService.list();
    }

    @PostMapping("/visitantes")
    public Visitante salvar(@RequestBody Visitante visitante) {
        return visitanteService.salvar(visitante);
    }

    @PostMapping("/visitantes/{idVisitante}/atracoes/{idAtracao}")
    public Visitante adicionaVisitanteAtracao(@PathVariable int idVisitante,
                                              @PathVariable int idAtracao) {
        return visitanteService.adicionaVisitanteAtracao(idVisitante, idAtracao);
    }
}
