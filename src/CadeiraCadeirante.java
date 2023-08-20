public class CadeiraCadeirante extends Cadeira {

    private int numeroRegistro;

    public CadeiraCadeirante(String desenho, int disponibilidade) {
        super(desenho, disponibilidade);
    }

    public void avisoCadeirante() {//TODO

    }

    public boolean pedeRegistro() {//TODO
        return false;
    }

    public boolean assentoEspecial() {
        return true;
    }


}
