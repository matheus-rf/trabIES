import java.util.InputMismatchException;
import java.util.Scanner;

public class TelaDeSelecao {
    private Cadeira cadeirasTotais[];
    private int cadeiraSelecionada[];
    private boolean compraLegal;

    public static final String RESET = "\u001b[0m";
    public static final String VERMELHO = "\u001B[41m";
    public static final String VERMELHO_LETRA = "\u001B[31m";
    public static final String VERDE = "\u001B[42m";
    public static final String VERDE_LETRA = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String AMARELO = "\u001B[33m";
    public static final int NUMERO_CADEIRAS = 100;


    public TelaDeSelecao() {
        compraLegal = true;
        cadeirasTotais = new Cadeira[NUMERO_CADEIRAS+1];
        cadeiraSelecionada = new int[NUMERO_CADEIRAS];

        for(int i = 0; i <NUMERO_CADEIRAS+1 ; i++) {
            if(i>79 && i<90 && i%2 == 0) { // fileira 8, cadeira sim e cadeira nao
                cadeirasTotais[i] = new CadeiraCadeirante("C", 1);
            } else {
                cadeirasTotais[i] = new Cadeira("-", 1);
            }
        }

        // TESTE PARA OCUPADOS
        cadeirasTotais[7].ocuparCadeira();
        cadeirasTotais[21].ocuparCadeira();
        cadeirasTotais[80].ocuparCadeira();
        cadeirasTotais[93].ocuparCadeira();
        cadeirasTotais[34].ocuparCadeira();
        cadeirasTotais[55].ocuparCadeira();
        //

        exibeCadeiras();
        selecionaCadeira();
        finalizarSelecao();
    }


    public void exibeCadeiras() {

        for(int i = 0; i < NUMERO_CADEIRAS+1; i++) {
            if((i%10 == 0 && i != 0)) {
                mostrafileira(i);
            }

            if(cadeirasTotais[i].getDisponibilidade() == 0) { // cadeira ocupada
                System.out.print(VERMELHO + cadeirasTotais[i].getDesenho() + RESET+ " ");
            } else if((cadeirasTotais[i].getDisponibilidade() == 1) && (cadeirasTotais[i].assentoEspecial())==false && i!=100) { // cadeira livre
                System.out.print(cadeirasTotais[i].getDesenho() + " ");
            } else if(cadeirasTotais[i].getDisponibilidade() == 2) { // cadeira selecionada
                System.out.print(VERDE + cadeirasTotais[i].getDesenho()+ RESET + " ");
            } else if(cadeirasTotais[i].assentoEspecial()) { // cadeira especial
                System.out.print(AZUL + "C" + RESET+ " ");
            }
        }
        System.out.println(AMARELO+"0 1 2 3 4 5 6 7 8 9"+RESET);
        exibeLegenda();
    }

    public void exibeLegenda() {
        System.out.println("LEGENDA: "+VERMELHO_LETRA + "Ocupada" + RESET + " " + VERDE_LETRA + "Selecionada" + RESET + " " + AZUL + "Cadeirante" + RESET);
    }

    public void selecionaCadeira() {
        int j = 0;//indice do vetor cadeiraSelecionada
        int qtdCadeiras = 0;
        Scanner sc = new Scanner(System.in);

        while (true){// para que o usuario digite um numero, se nao for numero, ele pede para digitar novamente
            try {
                System.out.println("quantas cadeiras deseja selecionar?");
                qtdCadeiras = sc.nextInt();
                break;
            }
            catch (InputMismatchException e) { // caso o usuario digite uma letra
                System.out.println("Formato indesejado, tente novamente!");
                compraLegal = false;
                sc.nextLine();

            }
            catch (Exception e) { //algum outro erro
                System.out.println("Ocorreu um erro, tente novamente!");
                compraLegal = false;
                sc.nextLine();
            }
        }
        sc.nextLine();
        for (int i = 0; i < qtdCadeiras; i++) {
            try {
                System.out.println("Digite a cadeira que deseja selecionar: ");

                String cadeira = sc.nextLine();
                cadeira = cadeira.toUpperCase();

                //transforma letra em numero, concatena com o numero da cadeira e transforma em int
                int numeroCadeira = Integer.parseInt(Integer.toString(transformaLetraNumero(cadeira.substring(0, 1))) + cadeira.substring(1, 2));

                if ((numeroCadeira > 0 && numeroCadeira < 101) && (cadeirasTotais[numeroCadeira].getDisponibilidade() == 1)) { // se exister e se estiver livre
                    cadeirasTotais[numeroCadeira].selecionarCadeira(); // seleciona a cadeira
                    cadeiraSelecionada[j++] = numeroCadeira; // adiciona a cadeira selecionada no vetor cadeiraSelecionada
                    System.out.println("Cadeira selecionada com sucesso!!!!");
                    compraLegal = true;
                } else {
                    System.out.println("Não foi possível selecionar a cadeira, tente novamente!");
                    i--; // para que o usuario digite novamente a cadeira
                    compraLegal = false;
                }
                exibeCadeiras(); // atualiza a tela, para que o usuario veja a cadeira selecionada
            } catch (NumberFormatException e) { // caso o usuario digite em outro formato
                System.out.println("Formato indesejado, tente novamente!");
                compraLegal = false;
            }
            catch (Exception e) { //algum outro erro
                System.out.println("Ocorreu um erro, tente novamente!");
                compraLegal = false;
            }
        }
        sc.close();

    }

    public void finalizarSelecao() {
        if(compraLegal) {
            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Compra não realizada!");
        }
    }

    private void gerenciarConflito() {//TODO

    }
    private int transformaLetraNumero(String letra){ //nao sei se tem jeito melhor de fazer isso

        if (letra.equals("A")){
            return 0;
        } else if (letra.equals("B")){
            return 1;
        } else if (letra.equals("C")){
            return 2;
        } else if (letra.equals("D")){
            return 3;
        } else if (letra.equals("E")){
            return 4;
        } else if (letra.equals("F")){
            return 5;
        } else if (letra.equals("G")){
            return 6;
        } else if (letra.equals("H")){
            return 7;
        } else if (letra.equals("I")){
            return 8;
        } else if (letra.equals("J")){
            return 9;
        } else {
            compraLegal = false;
            return -1;
        }
    }

    public void mostrafileira(int i) {
        if(i==10) {
            System.out.println(AMARELO+"A"+RESET);
        }
        if(i==20) {
            System.out.println(AMARELO+"B"+RESET);
        }
        if(i==30) {
            System.out.println(AMARELO+"C"+RESET);
        }
        if(i==40) {
            System.out.println(AMARELO+"D"+RESET);
        }
        if(i==50) {
            System.out.println(AMARELO+"E"+RESET);
        }
        if(i==60) {
            System.out.println(AMARELO+"F"+RESET);
        }
        if(i==70) {
            System.out.println(AMARELO+"G"+RESET);
        }
        if(i==80) {
            System.out.println(AMARELO+"H"+RESET);
        }
        if(i==90) {
            System.out.println(AMARELO+"I"+RESET);
        }
        if(i==100) {
            System.out.println(AMARELO+"J"+RESET);
        }

    }
}
