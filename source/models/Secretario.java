import java.util.List;

public class Secretario extends Usuario {

    public Secretario(String nome, String login, String password, Disciplina[] disciplinas) {
        super(nome, login, password);
    }

    public boolean cadastrarAluno(Aluno aluno) {
        // has to implement
        return true;
    }

    public boolean removerAluno(Aluno aluno) {
        // has to implement
        return true;
    }

    public boolean consultarAluno(String loginAluno) {
        // has to implement
        return true;
    }

    public boolean atualizarDadosAluno(String loginAluno,Aluno aluno) {
        // has to implement
        return true;
    }


    public List<Disciplina> gerarCurriculo(Curso curso){
        // has to implement
        return null;
    }
}

