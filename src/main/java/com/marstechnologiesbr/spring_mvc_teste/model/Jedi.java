package com.marstechnologiesbr.spring_mvc_teste.model;

import javax.validation.constraints.NotBlank;

public class Jedi {

    @NotBlank(message = "Name can't be blank") // valida o nome para não ser aceito vázio
    private String name; // nome do jedi

    @NotBlank(message = "Last Name can't be blank") // valida o sobrenome para não ser aceito vázio
    private String lastName; // sobrenome do jedi

    public Jedi(final String name, final String lastName) {
        this.name = name; // define o nome para o jedi
        this.lastName = lastName; // define o sobrenome para o jedi
    }

    // instância um jedi padrão/vázio
    public Jedi() {
    }

    public String getName() { return name; } // retorna o nome do jedi

    public void setName(String name) {
        this.name = name;
    } // define o nome para o jedi

    public String getLastName() {
        return lastName;
    } // retorna o sobrenome do jedi

    public void setLastName(String lastName) {
        this.lastName = lastName;
    } // define o sobrenome do jedi
}
