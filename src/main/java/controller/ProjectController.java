package controller;

import model.Project;
import model.Task;
import util.ConnectionFactory;
import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    public void save(Project project){

        String sql = "INSERT INTO projetos ("
                + "NOME,"
                + "DESCRICAO,"
                + "DATA_CRIACAO,"
                + "DATA_ATUALIZACAO) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement=null;

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getNome());
            statement.setString(2,project.getDescricao());
            statement.setDate(3, new Date(project.getDataCriacao().getTime()));
            statement.setDate(4, new Date(project.getDataAtualizacao().getTime()));
            statement.execute();

        }catch (Exception ex){
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);

        }
    }



    public void update(Project project){

        String sql = "UPDATE projetos SET "
                + "NOME = ?, "
                + "DESCRICAO = ?, "
                + "DATA_CRIACAO = ?, "
                + "DATA_ATUALIZACAO = ? "
                + "WHERE ID = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo conexão com o banco de dados
            connection = (Connection) ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores do statement
            statement.setString(1,project.getNome());
            statement.setString(2,project.getDescricao());
            statement.setDate(3,new Date(project.getDataCriacao().getTime()));
            statement.setDate(4,new Date(project.getDataAtualizacao().getTime()));
            statement.setInt(5, project.getId());

            //Executando a query
            statement.execute();

        }catch (Exception ex){
            throw new RuntimeException("Erro ao atualizar a tarefa"+ex.getMessage(), ex);
        }

    }

    public void removeById(int projectID) {
        String sql = "DELETE FROM projetos WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement= null;

        try {

            //Criando conexão com o banco de dados
            connection = (Connection) ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores
            statement.setInt(1,projectID);

            //Executando a query
            statement.execute();
        } catch (Exception e){
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Project> getAll(){

        String sql = "SELECT * FROM projetos";

        //Lista de projetos que será devolvida quando o método for chamado
        List<Project> projects = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        //Classe que irá recuperar os dados do banco
        ResultSet resultSet = null;

        try {

            //Criando conexão
            connection = (Connection) ConnectionFactory.getConnection();

            //Preparando conexão
            statement = connection.prepareStatement(sql);

            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();

            //Enquanto houverem valores a serem percorridos no resultSet
          while (resultSet.next()){
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setNome(resultSet.getString("nome"));
                project.setDescricao(resultSet.getString("descricao"));
                project.setDataCriacao(resultSet.getDate("data_Criacao"));
                project.setDataAtualizacao(resultSet.getDate("data_Atualizacao"));
                projects.add(project);
            }
        }catch (Exception ex){
            throw new RuntimeException("Erro ao retornar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        //Retorna a lista de tarefas
        return projects;
    }

}
