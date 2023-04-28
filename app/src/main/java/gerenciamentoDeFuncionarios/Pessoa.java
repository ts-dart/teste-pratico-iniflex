package gerenciamentoDeFuncionarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    protected Pessoa(String nome, String dataNascimento) {
        this.setNome(nome);
        this.setDataNascimento(dataNascimento);
    }

    protected String getNome() {
        return this.nome;
    }

    protected void setNome(String nome) {
        if (nome instanceof String) this.nome = nome;
    }

    protected String getDataNascimento() {
        return this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    protected void setDataNascimento(String dataNascimento) {
        if (dataNascimento instanceof String) {
            this.dataNascimento = LocalDate.parse(
                    dataNascimento,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
            );
        }
    }

    public String toString() {
        return "Nome: "  + this.getNome() + "\tData de nascimento: " + this.getDataNascimento();
    }
}
