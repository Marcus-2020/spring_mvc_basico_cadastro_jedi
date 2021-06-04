package com.marstechnologiesbr.springwebmvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity // informa que esse objeto é uma entidade para ser persistida na base de dados
@Table(name = "jedi") // o nome da tabela em que esse objeto persiste
public class Jedi {

    @Id // informa que este atributo se trata de um identificador único deste objeto
    @Column(name = "id_jedi") // nome da coluna parao atributo na base de dados
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // identificador único deste jedi

    // valida se o nome tem entre 3 até 10 caracteres
    @Size(min = 3, max = 10, message = "Name needs to have between 3 to 10 characters. ")
    @NotBlank(message = "Name can't be blank") // valida o nome para não ser aceito vázio
    @Column(name = "name") // nome da coluna parao atributo na base de dados
    private String name; // nome do jedi

    @NotBlank(message = "Last Name can't be blank") // valida o sobrenome para não ser aceito vázio
    @Column(name = "last_name") // nome da coluna parao atributo na base de dados
    private String lastName; // sobrenome do jedi

    public Jedi(final String name, final String lastName) {
        this.name = name; // define o nome para o jedi
        this.lastName = lastName; // define o sobrenome para o jedi
    }

    // instância um jedi padrão/vázio
    public Jedi() {
    }

    public Long getId() { return id; } // retorna o id do jedi

    public void setId(Long id) { this.id = id; } // define o id do jedi

    public String getName() { return name; } // retorna o nome do jedi

    public void setName(String name) {
        this.name = name;
    } // define o nome para o jedi

    public String getLastName() {
        return lastName;
    } // retorna o sobrenome do jedi

    public void setLastName(String lastName) { this.lastName = lastName; } // define o sobrenome do jedi

}
