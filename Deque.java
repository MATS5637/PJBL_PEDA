import java.io.Serializable;

public class Deque implements Serializable {
    protected int N;          // Guarda o tamanho máximo do deque.
    protected Object[] data;  // Guarda os elementos do deque no vetor circular.
    protected int size;       // Guarda a quantidade atual de elementos.
    protected int front;      // Guarda o índice do início do deque.
    protected int rear;       // Guarda o índice do fim do deque.
    protected int ip;         // Guarda o índice de percurso usado para percorrer o deque.

    public Deque(int N) {
        // Recebe o tamanho máximo do deque e inicializa seus dados.
        this.N = N;           // Copia o tamanho máximo para o atributo da classe.
        data = new Object[N]; // Cria o vetor que armazenará os elementos.
        size = 0;             // Define que o deque começa vazio.
        front = 0;            // Define a posição inicial do início do deque.
        rear = 0;             // Define a posição inicial do fim do deque.
        ip = 0;               // Define a posição inicial do índice de percurso.
    }

    public boolean isEmpty() {
        // Retorna verdadeiro quando o deque não possui elementos.
        return size == 0;     // Compara o tamanho atual com zero.
    }

    public boolean isFull() {
        // Retorna verdadeiro quando o deque atingiu a capacidade máxima.
        return size == N;     // Compara a quantidade atual com a capacidade máxima.
    }

    public int getSize() {
        // Retorna a quantidade atual de elementos do deque.
        return size;          // Devolve o valor armazenado em size.
    }

    public Object peekFront() {
        // Retorna o primeiro elemento sem removê-lo do deque.
        if (isEmpty())        // Verifica se não existe elemento no deque.
            return null;      // Retorna null quando o deque está vazio.
        return data[front];   // Retorna o elemento localizado no início.
    }

    public Object peekRear() {
        // Retorna o último elemento sem removê-lo do deque.
        if (isEmpty())        // Verifica se não existe elemento no deque.
            return null;      // Retorna null quando o deque está vazio.
        return data[rear];    // Retorna o elemento localizado no fim.
    }

    public String toString() {
        // Monta e retorna uma string com os elementos do deque em ordem.
        if (isEmpty())        // Verifica se o deque não possui elementos.
            return "Deque vazio."; // Retorna mensagem apropriada para deque vazio.
        String lista = "";    // Cria a string que acumulará os elementos.
        rewind();             // Posiciona o índice de percurso no início.
        int strSize = 0;      // Controla quantos elementos já foram copiados.
        while (strSize < size) {   // Repete enquanto faltarem elementos a copiar.
            lista += next().toString(); // Concatena o próximo elemento à string.
            strSize++;        // Incrementa a quantidade de elementos copiados.
            if (strSize < size) // Verifica se ainda faltam elementos.
                lista += " "; // Acrescenta um espaço entre os elementos.
        }
        return lista;         // Retorna a string montada com os elementos.
    }

    protected void rewind() {
        // Coloca o índice de percurso no início atual do deque.
        ip = front;   // Faz o índice de percurso igual ao índice de início.
    }

    protected Object next() {
        // Retorna o elemento apontado e avança o índice de percurso no vetor circular.
        if (isEmpty())        // Verifica se o deque não possui elementos.
            return null;      // Retorna null quando o deque está vazio.
        Object e = data[ip];  // Guarda o elemento apontado atualmente.
        ip++;                 // Avança o índice de percurso para a próxima posição.
        if (ip == N)          // Verifica se o índice de percurso ultrapassou o fim do vetor.
            ip = 0;           // Faz o índice de percurso circular para a posição zero.
        return e;             // Retorna o elemento que foi guardado.
    }

    public void addFirst(Object e) {
        if (isFull()) {
            System.out.println("Erro: Deque cheio.");
            System.exit(1);
        }
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            front--;
            if (front < 0) {
                front = N - 1; // Recua circulando para o final do vetor
            }
        }
        data[front] = e;
        size++;
    }

    public void addLast(Object e) {
        if (isFull()) {
            System.out.println("Erro: Deque cheio.");
            System.exit(1);
        }
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear++;
            if (rear == N) {
                rear = 0; // Avança circulando para o início do vetor
            }
        }
        data[rear] = e;
        size++;
    }

    public Object deleteFirst() {
        if (isEmpty()) {
            System.out.println("Erro: Deque vazio.");
            System.exit(1);
        }
        Object e = data[front];
        data[front] = null;
        size--;
        
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            front++;
            if (front == N) {
                front = 0; // Avança circulando para o início do vetor
            }
        }
        return e;
    }

    public Object deleteLast() {
        if (isEmpty()) {
            System.out.println("Erro: Deque vazio.");
            System.exit(1);
        }
        Object e = data[rear];
        data[rear] = null;
        size--;
        
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear--;
            if (rear < 0) {
                rear = N - 1; // Recua circulando para o final do vetor
            }
        }
        return e;
    }
}