package br.insper.turismo.atracao;

import br.insper.turismo.destino.Destino;
import br.insper.turismo.visitante.Visitante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Atracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_destino")
    private Destino destino;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "atracao_visitante",
            joinColumns = @JoinColumn(name = "id_atracao"),
            inverseJoinColumns = @JoinColumn(name = "id_visitante")
    )
    private List<Visitante> visitantes = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public List<Visitante> getVisitantes() {
        return visitantes;
    }

    public void setVisitantes(List<Visitante> visitantes) {
        this.visitantes = visitantes;
    }
}
