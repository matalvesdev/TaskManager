# TaskFlow

O **TaskFlow** é uma aplicação de gestão de tarefas desenvolvida para ajudar usuários a organizar, executar e conquistar seus objetivos. Este projeto tem como foco o ensino de conceitos fundamentais de Programação Orientada a Objetos (POO) e boas práticas de desenvolvimento em Java, através de uma solução interativa e funcional para gerenciamento de tarefas.

## Funcionalidades

- **Adicionar Tarefas:** Crie novas tarefas fornecendo um ID e uma descrição.
- **Remover Tarefa por ID:** Remova uma tarefa específica utilizando seu identificador único.
- **Listar todas as Tarefas:** Visualize todas as tarefas cadastradas.
- **Marcar Tarefa como Concluída:** Altere o status da tarefa para finalizada.
- **Procurar Tarefas por Descrição:** Busque tarefas utilizando palavras-chave da descrição.

## Estrutura do Projeto

O projeto está dividido em três classes principais:

### 1. Classe `Tarefa`

Responsável por representar uma tarefa individual.

**Atributos:**
- `id`: Identificador único da tarefa.
- `descricao`: Texto descritivo da tarefa.
- `finalizada`: Status booleano indicando se a tarefa foi concluída.

**Principais Métodos:**
- Construtor para inicializar uma tarefa.
- Métodos getters.
- `finalizarTarefa()`: Marca a tarefa como concluída.
- `toString()`: Representação textual detalhada da tarefa.

```java
public class Tarefa {
    private String id;
    private String descricao;
    private boolean finalizada;

    public Tarefa(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.finalizada = false;
    }

    public String getId() { return id; }
    public String getDescricao() { return descricao; }
    public boolean isFinalizada() { return finalizada; }
    public void finalizarTarefa() { this.finalizada = true; }

    @Override
    public String toString() {
        String estaFinalizada = this.finalizada ? "FINALIZADA" : "EM ANDAMENTO";
        return String.format("Tarefa [id=%s] [descrição=%s] [%s]", this.id, this.descricao, estaFinalizada);
    }
}
```

### 2. Classe `GerenciadorTarefa`

Gerencia o conjunto de tarefas, permitindo operações de cadastro, remoção, busca e atualização.

**Atributo:**
- `tarefas`: Mapa (`HashMap`) que armazena tarefas utilizando o ID como chave.

**Principais Métodos:**
- `adicionarTarefa`: Adiciona nova tarefa.
- `removerTarefa`: Remove tarefa por ID.
- `imprimirTarefas`: Lista todas as tarefas.
- `finalizarTarefa`: Marca tarefa como concluída.
- `procurarTarefa`: Busca tarefas por descrição.

```java
import java.util.HashMap;
import java.util.Map;

public class GerenciadorTarefa {
    private Map<String, Tarefa> tarefas = new HashMap<>();

    public void adicionarTarefa(String id, String descricao) {
        if (tarefas.get(id) == null) {
            Tarefa t = new Tarefa(id, descricao);
            tarefas.put(t.getId(), t);
            System.out.println(String.format("Tarefa [%s] adicionada com sucesso!", descricao));
        } else {
            System.out.println("Tarefa com este identificador já existe.");
        }
    }

    public void removerTarefa(String id) {
        if (tarefas.get(id) == null) {
            System.out.println("Tarefa com este identificador não foi encontrada.");
        } else {
            tarefas.remove(id);
            System.out.println(String.format("Tarefa [%s] removida com sucesso!", id));
        }
    }

    public void imprimirTarefas() {
        System.out.println("========================");
        System.out.println("Imprimindo Tarefas:");
        for (Tarefa t : tarefas.values()) {
            System.out.println(t);
        }
        System.out.println("========================");
    }

    public void finalizarTarefa(String id) {
        Tarefa tarefa = tarefas.get(id);
        if (tarefa == null) {
            System.out.println("Tarefa com este identificador não foi encontrada.");
        } else {
            tarefa.finalizarTarefa();
            System.out.println(String.format(
                "Tarefa [%s] [%s] foi finalizada com sucesso!",
                tarefa.getId(), tarefa.getDescricao()
            ));
        }
    }

    public void procurarTarefa(String descricao) {
        boolean tarefaEncontrada = false;
        for (Tarefa tarefa : tarefas.values()) {
            if (tarefa.getDescricao().contains(descricao)) {
                System.out.println("Tarefa encontrada!");
                System.out.println(tarefa);
                tarefaEncontrada = true;
            }
        }
        if (!tarefaEncontrada) {
            System.out.println("Tarefa não encontrada com esta descrição!");
        }
    }
}
```

### 3. Classe `Main`

Responsável pelo menu e interação com o usuário no console.

- Apresenta opções numeradas para as funcionalidades.
- Recebe e processa as escolhas do usuário.
- Exibe mensagens de confirmação e feedback.

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciadorTarefa gerenciadorTarefa = new GerenciadorTarefa();
        System.out.println("Bem vindo ao TaskFlow!");

        boolean finalizarTaskFlow = false;
        do {
            printMenu();
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: adicionarTarefa(sc, gerenciadorTarefa); break;
                case 2: removerTarefaPorId(sc, gerenciadorTarefa); break;
                case 3: gerenciadorTarefa.imprimirTarefas(); break;
                case 4: finalizarTarefa(sc, gerenciadorTarefa); break;
                case 5: procurarTarefa(sc, gerenciadorTarefa); break;
                case 6: finalizarTaskFlow = true; break;
            }
        } while (!finalizarTaskFlow);
    }

    private static void procurarTarefa(Scanner sc, GerenciadorTarefa gerenciadorTarefa) {
        System.out.println("Digite a descrição:");
        String descricao = sc.nextLine();
        gerenciadorTarefa.procurarTarefa(descricao);
    }

    private static void finalizarTarefa(Scanner sc, GerenciadorTarefa gerenciadorTarefa) {
        System.out.println("Digite o ID:");
        String id = sc.nextLine();
        gerenciadorTarefa.finalizarTarefa(id);
    }

    private static void removerTarefaPorId(Scanner sc, GerenciadorTarefa gerenciadorTarefa) {
        System.out.println("Digite o ID:");
        String id = sc.nextLine();
        gerenciadorTarefa.removerTarefa(id);
    }

    private static void adicionarTarefa(Scanner sc, GerenciadorTarefa gerenciadorTarefa) {
        System.out.println("Digite o ID:");
        String id = sc.nextLine();
        System.out.println("Digite a descrição:");
        String descricao = sc.nextLine();
        gerenciadorTarefa.adicionarTarefa(id, descricao);
    }

    private static void printMenu() {
        System.out.println("#################################");
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Remover Tarefa por ID");
        System.out.println("3. Listar Tarefas");
        System.out.println("4. Marcar tarefa como concluida");
        System.out.println("5. Procurar tarefa");
        System.out.println("6. Sair do TaskFlow");
        System.out.println("#################################");
    }
}
```

## Como Executar

1. **Clone este repositório**  
   ```bash
   git clone https://github.com/matalvesdev/TaskManager.git
   cd TaskManager
   ```

2. **Compile os arquivos Java:**
   ```bash
   javac Main.java GerenciadorTarefa.java Tarefa.java
   ```

3. **Execute o programa:**
   ```bash
   java Main
   ```

## Objetivos de Aprendizagem

- Consolidar habilidades em Programação Orientada a Objetos (POO).
- Praticar implementação de sistemas organizados e boas práticas.
- Desenvolver uma aplicação real de gerenciamento de tarefas, pronta para uso ou expansão.

---

Desenvolvido por [matalvesdev](https://github.com/matalvesdev)
