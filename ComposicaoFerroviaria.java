import java.io.*;

public class ComposicaoFerroviaria extends Deque implements Serializable {
 ObjetoPersistente arqComp;

      public ComposicaoFerroviaria(int N, String nomeArquivo) {
      super(N); // Chama o construtor de Deque.
      arqComp = new ObjetoPersistente(nomeArquivo);
      carregar(); // Carrega arquivo da composição ferroviária se existir.
      }

      private void salvar() { // Salva a composição ferroviária em arquivo.
      arqComp.salvar(this);
      }
      private void carregar() {
      ComposicaoFerroviaria cf = (ComposicaoFerroviaria) arqComp.carregar();
      if (!(cf==null)) { // Se o objeto foi recuperado, atualiza composição ferroviária.
      // É necessário redefinir os atributos do "deque pai" com os dados da
      // composição carregada.
      this.front = cf.front;
      this.rear = cf.rear;
      this.ip = cf.ip;
      this.size = cf.size;
      this.N = cf.N;
      this.data = cf.data;
      }
      }
      // retorna o primeiro vagao da composição ou retorna null
      public Vagao primeiroVagao() {
            return (Vagao) peekFront();
      }
      //retorna o ultimo vagao da composição ou retorna null
      public Vagao ultimoVagao() {
            return (Vagao) peekRear();
      }

      public void criarComposicaoPadrao() { // Monta a composicao padrao do Anexo do PDF.
            while (!isEmpty())
                  deleteLast();
            addLast(new Locomotiva(20, 150, 2500));
            for (int i = 0; i < 50; i++)
                  addLast(new Passageiro(24, 40, 30));
            for (int i = 0; i < 30; i++)
                  addLast(new Carga(17, 20));
      salvar();
      }
      private int calcularTotalPassageiros() { // Soma os passageiros de todos os vagoes de passageiro.
            int total = 0;
            rewind(); // Volta o ponteiro do deque para o inicio.
            for (int i = 0; i < getSize(); i++) {
                  Object obj = next(); // Pega o proximo vagao da composicao.
                  if (obj instanceof Passageiro)
                        total += ((Passageiro) obj).getPassageiros();
            }
            return total;
      }
      private int contarVagoesPassageiros() { // Conta quantos vagoes de passageiro existem na composicao.
            int total = 0;
            rewind(); // Volta o ponteiro do deque para o inicio.
            for (int i = 0; i < getSize(); i++) {
                  Object obj = next(); // Pega o proximo vagao da composicao.
                  if (obj instanceof Passageiro)
                        total++;
            }
            return total;
      }
      private int contarLocomotivas() { // Conta quantas locomotivas existem na composicao.
            int total = 0;
            rewind(); // Volta o ponteiro do deque para o inicio.
            for (int i = 0; i < getSize(); i++) {
                  Object obj = next(); // Pega o proximo vagao da composicao.
                  if (obj instanceof Locomotiva)
                        total++;
            }
            return total;
      }
      private int contarVagoesCarga() { // Conta quantos vagoes de carga existem na composicao.
            int total = 0;
            rewind(); // Volta o ponteiro do deque para o inicio.
            for (int i = 0; i < getSize(); i++) {
                  Object obj = next(); // Pega o proximo vagao da composicao.
                  if (obj instanceof Carga)
                        total++;
            }
            return total;
      }
      private double calcularCargaTotal() { // Soma a carga de todos os vagoes de carga.
            double total = 0;
            rewind(); // Volta o ponteiro do deque para o inicio.
            for (int i = 0; i < getSize(); i++) {
                  Object obj = next(); // Pega o proximo vagao da composicao.
                  if (obj instanceof Carga)
            total += ((Carga) obj).getCarga();
      }
      return total;
      }
      private double calcularPesoTotal() { // Soma o peso de todos os vagões da composição.
            double total = 0;
            rewind();
            for (int i = 0; i < getSize(); i++) {
                  Object obj = next();
                  total += ((Vagao) obj).getPeso();
            }
            return total;
      }
      private double calcularComprimentoTotal() { // Soma o comprimento de todos os vagões e os espaços entre eles.
            double total = 0;
            rewind();
            for (int i = 0; i < getSize(); i++) {
                  Object obj = next();
                  total += ((Vagao) obj).getComprimento();
            }
            if (getSize() > 0)
                  total += 2 * (getSize() - 1);
            return total;
      }
      public void inserirInicio(Vagao v) { // Insere um vagão no início da composição.
            if (isFull()) {
                  System.out.println("Composição cheia. Não foi possível inserir no início.");
                  return;
            }
            addFirst(v);
            salvar();
      }
      public void inserirFim(Vagao v) { // Insere um vagão no fim da composição.
            if (isFull()) {
                  System.out.println("Composição cheia. Não foi possível inserir no fim.");
                  return;
            }
            addLast(v);
            salvar();
      }
      public Vagao removerInicio() { // Remove e retorna o primeiro vagão da composição.
            if (isEmpty()) {
                  System.out.println("Composição vazia. Não há vagões para remover do início.");
                  return null;
            }
            Vagao vagao = (Vagao) deleteFirst();
            salvar();
            return vagao;
      }
      public Vagao removerFim() { // Remove e retorna o último vagão da composição.
            if (isEmpty()) {
                  System.out.println("Composição vazia. Não há vagões para remover do fim.");
                  return null;
            }
            Vagao vagao = (Vagao) deleteLast();
            salvar();
            return vagao;
      }
 
 public Locomotiva criarLocomotivaIgual() {
  rewind();
  for (int i=0; i<getSize(); i++) {
      Object obj = next();
   if (obj instanceof Locomotiva) {
    Locomotiva l = (Locomotiva) obj;
    return new Locomotiva(l.getComprimento(), l.getPeso(), l.getPotencia());
   } 
  }
  return null;
 }
 
 private String verificarPotencia(){
  double potenciaTotal = 0;
  int qtd = 0;
  rewind();
  for (int i=0; i<getSize(); i++){
  Object obj = next();
   if (obj instanceof Locomotiva){
    potenciaTotal += ((Locomotiva) obj).getPotencia();
    qtd ++;
   }
  }
  if (qtd==0)
   return "Não há Locomotivas.";
  double pesoTotal = calcularPesoTotal();
  double hpt = potenciaTotal/pesoTotal;
  if (hpt>=1.05)
   return String.format("Suficiente, já que potência/peso = %.2f .", hpt);
  double falta = 1.05 * pesoTotal - potenciaTotal;
  Locomotiva l = criarLocomotivaIgual();
  double denominador = l.getPotencia() - 1.05 * l.getPeso();
  if (denominador<=0)
   return "Uma Locomotiva igual não resolve";
  int n = (int) Math.ceil(falta/denominador);
  return String.format("Como a relação HP por tonelada é %.2f , a potência não é suficiente: falta %.2f HP. Então devemos adicionar %d locomotiva(s) iguais.", hpt, falta, n);
 }

 public void diagnostico() {
      System.out.println("Composição ferroviária:");
      System.out.println("Quantidade de vagões de passageiros: " + contarVagoesPassageiros());
      System.out.println("Quantidade de locomotivas: " + contarLocomotivas());
      System.out.println("Quantidade de vagões de carga: " + contarVagoesCarga());
      System.out.println("Total de passageiros: " + calcularTotalPassageiros());
      System.out.println("Carga total: " + calcularCargaTotal());
      System.out.println("Peso total: " + calcularPesoTotal());
      System.out.println("Comprimento total: " + calcularComprimentoTotal());
      System.out.println(verificarPotencia());
 }
 
}
