package com.template;

import com.template.controller.MainController;
import com.template.validator.ILivroValidador;
import com.template.validator.LivroValidador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ILivroValidador livroValidador = new LivroValidador();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == MainController.class) {
                return new MainController(livroValidador);
            }
            try {
                return controllerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Scene scene = new Scene(loader.load(), 900, 650);

        stage.setTitle("Cadastro de Livros");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
