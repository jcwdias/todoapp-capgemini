package controller;

import model.Project;
import model.Task;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TaskController {

    public void save(Task task){
        String sql = "INSERT INTO tarefas (ID_PROJETO,"
            + "NOME,"
            + "DESCRICAO,"
            + "CONCLUIDA,"
            + "OBSERVACOES,"
            + "PRAZO,"
            + "DATA_CRIACAO,"
            + "DATA_ATUALIZACAO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement=null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,task.getIdProject());
            statement.setString(2, task.getNome());
            statement.setString(3,task.getDescricao());
            statement.setBoolean(4,task.isCompleto());
            statement.setString(5, task.getObservacoes());
            statement.setDate(6, new Date(task.getPrazo().getTime()));
            statement.setDate(7, new Date(task.getDataCriacao().getTime()));
            statement.setDate(8, new Date(task.getDataAtualizacao().getTime()));
            statement.execute();

        }catch (Exception ex){
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);

            }
    }



    public void update(Task task){

        String sql = "UPDATE tarefas SET "
                + "ID_PROJETO = ?, "
                + "NOME = ?, "
                + "DESCRICAO = ?, "
                + "CONCLUIDA = ?, "
                + "OBSERVACOES = ?, "
                + "PRAZO = ?, "
                + "DATA_CRIACAO = ?, "
                + "DATA_ATUALIZACAO = ? "
                + "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores do statement
            statement.setInt(1,task.getIdProject());
            statement.setString(2,task.getNome());
            statement.setString(3,task.getDescricao());
            statement.setBoolean(4,task.isCompleto());
            statement.setString(5,task.getObservacoes());
            statement.setDate(6,new Date(task.getPrazo().getTime()));
            statement.setDate(7,new Date(task.getDataCriacao().getTime()));
            statement.setDate(8,new Date(task.getDataAtualizacao().getTime()));
            statement.setInt(9, task.getId());

            //Executando a query
            statement.execute();

        }catch (Exception ex){
            throw new RuntimeException("Erro ao atualizar a tarefa"+ex.getMessage(), ex);
        }

    }

    public void removeById(int taskId) throws SQLException {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement= null;

        try {

            //Criando conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            //Setando os valores
            statement.setInt(1,taskId);

            //Executando a query
            statement.execute();
        } catch (Exception e){
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll(int id_Project){

        String sql = "SELECT * FROM tarefas WHERE id_Projeto = ? ";

        //Lista de tarefas que será devolvida quando o método for chamado
        List<Task> tasks = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            //Criando conexão
            connection = ConnectionFactory.getConnection();

            //Preparando conexão
            statement = connection.prepareStatement(sql);

            //Setando valor correspondente ao filtro de busca
            statement.setInt(1,id_Project);

            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();

            //Enquanto houverem valores a serem percorridos no resultSet
            while (resultSet.next()){
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("id_Projeto"));
                task.setNome(resultSet.getString("nome"));
                task.setDescricao(resultSet.getString("descricao"));
                task.setObservacoes(resultSet.getString("observacoes"));
                task.setCompleto(resultSet.getBoolean("concluida"));
                task.setPrazo(resultSet.getDate("prazo"));
                task.setDataCriacao(resultSet.getDate("data_Criacao"));
                task.setDataAtualizacao(resultSet.getDate("data_Atualizacao"));
                tasks.add(task);
            }
        }catch (Exception ex){
            throw new RuntimeException("Erro ao retornar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        //Retorna a lista de tarefas
        return tasks;
    }

}
