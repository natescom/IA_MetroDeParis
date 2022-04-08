package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.AStar;
import sample.model.AStarMetro;
import sample.model.Estacao;
import sample.model.Vertice;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sistemas Inteligentes - Metrô de Paris");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        }); // Ao fechar a janela todos os processos sao fechados tambem
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        return;
    }


    public void calcularBucarest(){
        Estacao bucharest = new Estacao("bucharest");
        Estacao arad = new Estacao("arad");
        Estacao craiova = new Estacao("craiova");
        Estacao fargaras = new Estacao("fargaras");
        Estacao oradea = new Estacao("oradea");
        Estacao pitesti = new Estacao("pitesti");
        Estacao riminicu = new Estacao("riminicu");
        Estacao sibiu = new Estacao("sibiu");
        Estacao timisoara = new Estacao("timisoara");
        Estacao zering = new Estacao("zering");

        oradea.getFilhos().put(zering,75);
        oradea.getFilhos().put(sibiu,151);
        zering.getFilhos().put(arad,75);
        zering.getFilhos().put(oradea,71);
        arad.getFilhos().put(sibiu,140);
        arad.getFilhos().put(timisoara,118);
        arad.getFilhos().put(zering,75);
        sibiu.getFilhos().put(fargaras,99);
        sibiu.getFilhos().put(riminicu,80);
        sibiu.getFilhos().put(oradea,151);
        sibiu.getFilhos().put(arad,140);
        riminicu.getFilhos().put(pitesti,97);
        riminicu.getFilhos().put(craiova,138);
        riminicu.getFilhos().put(sibiu,80);
        pitesti.getFilhos().put(bucharest,101);

        int[][] matrizDistancia = {{0,366,160,178,380,98,193,253,329,374}};


        ArrayList<Vertice> caminho;
        AStar aStar = new AStar() {
            @Override
            protected int calcularCustoCondicional(Vertice v1, Vertice v2) {
                return 0;
            }
        };
        caminho = aStar.buscar(arad,bucharest,matrizDistancia);


        // ESPERADO: 1 7 6 5 0
        for (Vertice vertice : caminho) {
            System.out.print(((Estacao) vertice).getNome() + " ");
        }
    }
}
