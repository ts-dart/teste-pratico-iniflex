package gerenciamentoDeFuncionarios;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(
            String nome,
            String dataNascimento,
            BigDecimal salario,
            String funcao
    ) {
        super(nome, dataNascimento);
        this.setSalario(salario);
        this.setFuncao(funcao);
    }

    public String getSalario() {
        return new DecimalFormat("#,##0.00").format(this.salario);
    }

    public void setSalario(BigDecimal salario) {
        if (salario instanceof BigDecimal) this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        if (funcao instanceof String) this.funcao = funcao;
    }

    public String toString() {
        return super.toString() + "\tSalário: " + this.getSalario() + "\tFunção: " + this.getFuncao();
    }
}
