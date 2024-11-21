import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {
        // Criando o scanner para entrada de dados
        Scanner scanner = new Scanner(System.in);

        // Declarando o tabuleiro como uma matriz 3x3
        char[][] tabuleiro = new char[3][3];

        // Inicializando o tabuleiro
        inicializarTabuleiro(tabuleiro);

        // Solicitando os nomes dos jogadores
        System.out.print("Digite o nome do jogador 1 (X): ");
        String jogador1 = scanner.nextLine();
        System.out.print("Digite o nome do jogador 2 (O): ");
        String jogador2 = scanner.nextLine();

        boolean jogoAtivo = true; // Controla se o jogo está em andamento
        char simboloAtual = 'X'; // Começa com o jogador 1
        String jogadorAtual = jogador1; // Nome do jogador atual

        while (jogoAtivo) {
            // Exibir o tabuleiro atualizado
            exibirTabuleiro(tabuleiro);

            // Solicitar a jogada
            System.out.println(jogadorAtual + ", é sua vez! Escolha a linha e a coluna (0-2).");
            int linha, coluna;

            while (true) {
                System.out.print("Linha: ");
                linha = scanner.nextInt();
                System.out.print("Coluna: ");
                coluna = scanner.nextInt();

                if (jogadaValida(tabuleiro, linha, coluna)) {
                    break;
                } else {
                    System.out.println("Jogada inválida! Tente novamente.");
                }
            }

            // Atualizar o tabuleiro com a jogada
            tabuleiro[linha][coluna] = simboloAtual;

            // Verificar se houve um vencedor ou empate
            if (verificarVencedor(tabuleiro, simboloAtual)) {
                exibirTabuleiro(tabuleiro);
                System.out.println("Parabéns, " + jogadorAtual + "! Você venceu!");
                jogoAtivo = false;
            } else if (verificarEmpate(tabuleiro)) {
                exibirTabuleiro(tabuleiro);
                System.out.println("Empate! O jogo terminou sem vencedor.");
                jogoAtivo = false;
            } else {
                // Alternar entre os jogadores
                simboloAtual = (simboloAtual == 'X') ? 'O' : 'X';
                jogadorAtual = (jogadorAtual.equals(jogador1)) ? jogador2 : jogador1;
            }
        }

        scanner.close(); // Fechando o scanner
    }

    // Função para inicializar o tabuleiro com espaços vazios
    public static void inicializarTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    // Função para exibir o tabuleiro no console
    public static void exibirTabuleiro(char[][] tabuleiro) {
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println(" ---+---+---");
        }
    }

    // Função para verificar se uma jogada é válida
    public static boolean jogadaValida(char[][] tabuleiro, int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ';
    }

    // Função para verificar se houve um vencedor
    public static boolean verificarVencedor(char[][] tabuleiro, char simbolo) {
        // Verificar linhas, colunas e diagonais
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == simbolo && tabuleiro[i][1] == simbolo && tabuleiro[i][2] == simbolo) || // Linhas
                    (tabuleiro[0][i] == simbolo && tabuleiro[1][i] == simbolo && tabuleiro[2][i] == simbolo)) { // Colunas
                return true;
            }
        }
        return (tabuleiro[0][0] == simbolo && tabuleiro[1][1] == simbolo && tabuleiro[2][2] == simbolo) || // Diagonal principal
                (tabuleiro[0][2] == simbolo && tabuleiro[1][1] == simbolo && tabuleiro[2][0] == simbolo);   // Diagonal secundária
    }

    // Função para verificar se o jogo terminou em empate
    public static boolean verificarEmpate(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false; // Ainda há espaços vazios
                }
            }
        }
        return true; // Todos os espaços estão preenchidos
    }
}

