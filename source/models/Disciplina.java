
public class Disciplina {

    private String nome;
    private boolean disciplinaAtiva;
    private boolean disciplinaDisponivel;
    private boolean obrigatoria;
    private boolean optativa;
    private double preco;

    public Disciplina(String nome, boolean disciplinaAtiva, boolean disciplinaDisponivel, boolean obrigatoria, boolean optativa, double preco) {
        this.nome = nome;
        this.disciplinaAtiva = disciplinaAtiva;
        this.disciplinaDisponivel = disciplinaDisponivel;
        this.obrigatoria = obrigatoria;
        this.optativa = optativa;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isDisciplinaAtiva() {
        return disciplinaAtiva;
    }

    public void setDisciplinaAtiva(boolean disciplinaAtiva) {
        this.disciplinaAtiva = disciplinaAtiva;
    }

    public boolean isDisciplinaDisponivel() {
        return disciplinaDisponivel;
    }

    public void setDisciplinaDisponivel(boolean disciplinaDisponivel) {
        this.disciplinaDisponivel = disciplinaDisponivel;
    }

    public boolean isObrigatoria() {
        return obrigatoria;
    }

    public void setObrigatoria(boolean obrigatoria) {
        this.obrigatoria = obrigatoria;
    }

    public boolean isOptativa() {
        return optativa;
    }

    public void setOptativa(boolean optativa) {
        this.optativa = optativa;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean verificaStatusDisciplina() {
        // need to discuss
        return true;
    }


    public boolean verificaDisponibilidadeDisciplina() {
        // need to discuss
        return true;
    }
}
