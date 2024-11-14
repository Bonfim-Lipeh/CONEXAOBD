package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Funcoes {
	

	public static void Inserir(Connection conexao, String nome, int idade) {
	if(conexao != null) {
		String sql = "INSERT INTO alunos (nome, idade) VALUES (?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nome);
			stmt.setInt(2, idade);
			stmt.executeUpdate();
			int inseririd = stmt.executeUpdate();
	        if (inseririd > 0) {
	            ResultSet generatedkeys = stmt.getGeneratedKeys();
	            if (generatedkeys.next()) {
	                int id = generatedkeys.getInt(1);
	                System.out.println("Novo ID: " + id);
	            }
	        }
						
			System.out.println("Dados inseridos com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir dados: " + e.getMessage());
		} finally {
			try {
				if(conexao != null) conexao.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão: " + e.getMessage());
			}
		}
	}
	}
	public static void atualizar(Connection conexao, String novoNome, int novaIdade, int id) {//atualização de registro
        if (conexao != null) {
            String sql = "UPDATE alunos SET nome = ?, idade = ? WHERE id = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, novoNome);//criação de novas variaveis para a inserção de informações
                stmt.setInt(2, novaIdade);//nova idade
                stmt.setInt(3, id);// id
                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Dados atualizados com sucesso!");
                    
                } else {
                    System.out.println("Nenhum registro encontrado para o ID informado.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar dados: " + e.getMessage());
            }
        }
	}

	public static void deletar(Connection conexao, int id) {
        if (conexao != null) {
            String sql = "DELETE FROM alunos WHERE id = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Registro deletado com sucesso!");
                } else {
                    System.out.println("Registro não encontrado.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao deletar dados: " + e.getMessage());
            }
        }
    }
	    	
	 

	public static void listarTodos(Connection conexao) {
        if (conexao != null) {
            String sql = "SELECT * FROM alunos";
            try (PreparedStatement stmt = conexao.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");

                    int idade = rs.getInt("idade");
                    System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade);
                }
            } catch (SQLException e) {
                System.out.println("Erro ao listar dados: " + e.getMessage());
            }
        }

	}
	}
