package br.insper.turismo.visitante;

import br.insper.turismo.atracao.Atracao;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;

    @ManyToMany(mappedBy = "visitantes")
    private List<Atracao> atracaos = new ArrayList<>();

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Atracao> getAtracaos() {
        return atracaos;
    }

    public void setAtracaos(List<Atracao> atracaos) {
        this.atracaos = atracaos;
    }
}
