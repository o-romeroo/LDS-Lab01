
public class Professor extends Usuario {

    Disciplina[] disciplinas;

    public Professor(String nome, String login, String password, Disciplina[] disciplinas) {
        super(nome, login, password);
        this.disciplinas = disciplinas;
    }

    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Disciplina[] disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Aluno[] consultarAlunosMatriculados(Disciplina disciplina) {
        // has to implement
        return null;
    }

}
