import java.util.Scanner;

public class ProgramaFerrovia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ComposicaoFerroviaria comp = new ComposicaoFerroviaria(100, "composicao.dat");
        int opcao = 0;

        while (opcao !=7){
            System.out.println("\n1. Criar composição padrão");
            System.out.println("2. Inserir vagão");
            System.out.println("3. Remover vagão");
            System.out.println("4. Descrição da composição");
            System.out.println("5. Primeiro vagão");
            System.out.println("6. Último vagão");
            System.out.println("7. Terminar");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    comp.criarComposicaoPadrao();
                    break;

                case 2: {
                    if (comp.isFull()) {
                        System.out.println("Erro: Composição cheia.");
                        break;
                    }
                    System.out.print("Inserir na frente (1) ou no final (2)? ");
                    int pos = sc.nextInt();
                    System.out.print("Tipo de vagão (1 - Locomotiva, 2 - Passageiro, 3 - Carga): ");
                    int tipo = sc.nextInt();
                    System.out.print("Comprimento: ");
                    double comprimento = sc.nextDouble();
                    System.out.print("Peso (ton): ");
                    double peso = sc.nextDouble();

                    Vagao v;
                    if (tipo==1) {
                        System.out.print("Potência (HP): ");
                        v = new Locomotiva(comprimento, peso, sc.nextDouble());
                    } else if (tipo==2) {
                        System.out.print("Número de passageiros: ");
                        v = new Passageiro(comprimento, peso, sc.nextInt());
                    } else {
                        v = new Carga(comprimento, peso);
                    }

                    if (pos==1)
                        comp.inserirInicio(v);
                    else
                        comp.inserirFim(v);
                    break;
                }
                
                case 3: {
                    if (comp.isEmpty()) {
                        System.out.println("Erro: Composição vazia.");
                        break;
                    }
                    System.out.print("Remover da frente (1) ou do final (2)? ");
                    int pos = sc.nextInt();
                    Vagao removido;
                    if (pos==1)
                        removido = comp.removerInicio();
                    else
                        removido = comp.removerFim();
                    System.out.println("Vagão removido:");
                    removido.imprime();
                    break;
                }

                case 4:
                    comp.diagnostico();
                    break;

                case 5:
                    if (comp.isEmpty()) {
                        System.out.println("Erro: Composição vazia.");
                    } else{
                        comp.primeiroVagao().imprime();
                    }
                    break;
                
                case 6:
                    if (comp.isEmpty()) {
                        System.out.println("Erro: Composição vazia.");
                    } else {
                        comp.ultimoVagao().imprime();
                    }
                    break;

                case 7:
                    System.out.println("Encerrando programa.");
                    break;
                
                    default:
                    System.out.println("Opção inválida.");
            }   
        }
        sc.close();
    }
}
