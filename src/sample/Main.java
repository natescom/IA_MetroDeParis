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
        int[][] distanciasDiretas = new int[][]{
            {0,  11, 20, 27, 40, 43, 39, 28, 18, 10, 18, 30, 30, 32},
            {11,  0,  9, 16, 29, 32, 28, 19, 11,  4, 17, 23, 21, 24},
            {20,  9,  0,  7, 20, 22, 19, 15, 10, 11, 21, 21, 13, 18},
            {27, 16,  7,  0, 13, 16, 12, 13, 13, 18, 26, 21, 11, 17},
            {40, 29, 20, 13,  0,  3,  2, 21, 25, 31, 38, 27, 16, 20},
            {43, 32, 22, 16,  3,  0,  4, 23, 28, 33, 41, 30, 17, 20},
            {39, 28, 19, 12,  2,  4,  0, 22, 25, 29, 38, 28, 13, 17},
            {28, 19, 15, 13, 21, 23, 22,  0,  9, 22, 18,  7, 25, 30},
            {18, 11, 10, 13, 25, 28, 25,  9,  0, 13, 12, 12, 23, 28},
            {10,  4, 11, 18, 31, 33, 29, 22, 13,  0, 20, 27, 20, 23},
            {18, 17, 21, 26, 38, 41, 38, 18, 12, 20,  0, 15, 35, 39},
            {30, 23, 21, 21, 27, 30, 28,  7, 12, 27, 15,  0, 31, 37},
            {30, 21, 13, 11, 16, 17, 13, 25, 23, 20, 35, 31,  0,  5},
            {32, 24, 18, 17, 20, 20, 17, 30, 28, 23, 39, 37,  5,  0}
        };

        Estacao e1 = new Estacao("E1");
        Estacao e2 = new Estacao("E2");
        Estacao e3 = new Estacao("E3");
        Estacao e4 = new Estacao("E4");
        Estacao e5 = new Estacao("E5");
        Estacao e6 = new Estacao("E6");
        Estacao e7 = new Estacao("E7");
        Estacao e8 = new Estacao("E8");
        Estacao e9 = new Estacao("E9");
        Estacao e10 = new Estacao("E10");
        Estacao e11 = new Estacao("E11");
        Estacao e12 = new Estacao("E12");
        Estacao e13 = new Estacao("E13");
        Estacao e14 = new Estacao("E14");

        e1.connect(e2,11);
        e2.connect(e3,9);
        e2.connect(e9,11);
        e2.connect(e10,4);
        e3.connect(e4,7);
        e3.connect(e9,10);
        e3.connect(e13,19);
        e4.connect(e5,15);
        e4.connect(e8,16);
        e4.connect(e13,13);
        e5.connect(e6,3);
        e5.connect(e7,2);
        e5.connect(e8,28);
        e8.connect(e9,11);
        e8.connect(e12,7);
        e9.connect(e11,14);
        e13.connect(e14,5);


        ArrayList<Estacao> linha1 = new ArrayList<>();
        ArrayList<Estacao> linha2 = new ArrayList<>();
        ArrayList<Estacao> linha3 = new ArrayList<>();
        ArrayList<Estacao> linha4 = new ArrayList<>();

        linha1.add(e1);
        linha1.add(e2);
        linha1.add(e3);
        linha1.add(e4);
        linha1.add(e5);
        linha1.add(e6);
        linha2.add(e7);
        linha2.add(e8);
        linha2.add(e9);
        linha2.add(e10);
        linha2.add(e2);
        linha3.add(e11);
        linha3.add(e9);
        linha3.add(e3);
        linha3.add(e13);
        linha4.add(e12);
        linha4.add(e8);
        linha4.add(e4);
        linha4.add(e13);
        linha4.add(e14);

        ArrayList<Estacao>[] linhas = new ArrayList[] {linha1,linha2,linha3,linha4};

        AStarMetro aStar = new AStarMetro(linhas,linha1);
        ArrayList<Vertice> caminho = aStar.buscar(e1,e14,distanciasDiretas);
        for (Vertice vertice : caminho) {
            System.out.print(((Estacao) vertice).getNome() + " ");
        }

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
