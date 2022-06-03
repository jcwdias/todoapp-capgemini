package todoapp;

import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

       /* ProjectController projectController = new ProjectController();

        Project project = new Project();
        project.setNome("Projeto teste");
        project.setDescricao("descrição teste");
        projectController.save(project);

        project.setId(2);
        project.setNome("Novo nome");
        project.setDescricao("Nova Descrição");
        projectController.update(project);

        List<Project> projects = projectController.getAll();
        System.out.println(" Total de projetos = "+projects.size());

        ProjectController.removeById(1); */

        TaskController taskController = new TaskController();

        Task task = new Task();
      /*  task.setIdProject(2);
        task.setNome("Criar as telas da aplicação");
        task.setDescricao("Devem ser criadas as telas de cadastro");
        task.setObservacoes("Sem observações");
        task.setCompleto(false);
        task.setPrazo(new Date());

        taskController.save(task);*/
        task.setId(1);
       /* task.setIdProject(2);
        task.setNome("Alterar telas da aplicação");
        taskController.update(task); */


        List<Task> tasks= taskController.getAll(2);
        System.out.println("Total de tarefas = "+tasks.size());





    }

}
