package business.campeonatos;

public class Setor {
    private int dificuldade;
    private String tipoSetor;

    private Setor(){}

    public Setor(int dificuldade, String tipoSetor){
        this.dificuldade = dificuldade;
        this.tipoSetor = tipoSetor;
    }


    public int getDificuldade() {
        return this.dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getTipoSetor() {
        return this.tipoSetor;
    }

    public void setTipoSetor(String tipoSetor) {
        this.tipoSetor = tipoSetor;
    }

}
