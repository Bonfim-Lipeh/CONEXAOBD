package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class InserirDados {
	
	public static void main(String[] args) {
			    
	    
		Scanner scanner = new Scanner(System.in);
		
		
		try (Connection conexao = ConexaoBD.conectar()) {
		while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Inserir Aluno");
            System.out.println("2. Atualizar Aluno");
            System.out.println("3. Deletar Aluno");
            System.out.println("4. Ler Registros de Alunos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
            case 1:
                System.out.print("Digite o nome: ");
                String nome = scanner.next();
                scanner.nextLine();
                
                System.out.print("Digite a idade: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Idade inválida. Por favor, digite um número inteiro.");
                    scanner.next(); // Limpa o buffer
                }
                int idade = scanner.nextInt();
                scanner.nextLine();
                
                Funcoes.Inserir(conexao, nome, idade);
                break;
            case 2:
                System.out.print("Digite o ID do aluno a ser atualizado: ");
                int id = scanner.nextInt();
                System.out.print("Digite o novo nome: ");
                String novoNome = scanner.next();
                System.out.print("Digite a idade: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Idade inválida. Por favor, digite um número inteiro.");
                    scanner.next(); // Limpa o buffer
                }
                int NovaIdade = scanner.nextInt();
                scanner.nextLine();
                Funcoes.atualizar(conexao, novoNome, NovaIdade, id);
                break;
            case 3:
                System.out.print("Digite o ID do aluno a ser deletado: ");
                id = scanner.nextInt();
                Funcoes.deletar(conexao, id);
                break;
            case 4:
                Funcoes.listarTodos(conexao);
                break;
            case 0:
                System.out.println("Saindo...");
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
} catch (SQLException e) {
    System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
}

	}
}


