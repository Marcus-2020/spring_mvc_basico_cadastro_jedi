package com.marstechnologiesbr.spring_mvc_teste.repository;

import com.marstechnologiesbr.spring_mvc_teste.model.Jedi;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JediRepository {

    private List<Jedi> jedi; // atributo de uma lista de jedi

    public JediRepository() {
        this.jedi = new ArrayList<>();
        this.jedi.add(new Jedi("Luke", "Skywalker"));
    } // instancia um objeto repositório de jedi que é uma lista de jedi com um inicial

    public List<Jedi> getAllJedi(){

        return this.jedi; // retorna o repositório de jedi
    }

    public void add(Jedi jedi) {

        if (jedi != null) { // verifica se jedi esta vázio
            // se não estiver vázio então...
            if (this.jedi != null) { // verifica se o repostório não foi inicializado
                // se foi, adiciona o novo jedi
                this.jedi.add(jedi);
            } else { // se o repositório não foi inicializado...
                this.jedi = new ArrayList<>(); // inicializa o repositório
                this.jedi.add(jedi); // e adiciona o novo jedi
            }
        }
    }
}
