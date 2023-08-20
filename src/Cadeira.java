public class Cadeira {
    private String desenho;
    private int disponibilidade;

    public Cadeira(String desenho, int disponibilidade) {
        this.desenho = desenho;
        this.disponibilidade = disponibilidade;
    }

    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void selecionarCadeira() { //0 = ocupada, 1 = livre, 2 = selecionada
        if (disponibilidade == 1) {
            disponibilidade = 2;
        }
    }

    public void ocuparCadeira() {
        if (disponibilidade == 1) {
            disponibilidade = 0;
        }
    }

    public void liberarCadeira() {
        if (disponibilidade == 0) {
            disponibilidade = 1;
        }
    }

    protected String getDesenho() {
        return desenho;
    }

    public boolean assentoEspecial() {
        return false;
    }

}
