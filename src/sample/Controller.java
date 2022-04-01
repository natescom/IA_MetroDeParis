package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import sample.model.AStarMetro;
import sample.model.Estacao;
import sample.model.Vertice;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

  public RadioButton rb_E1;
  public RadioButton rb_E2;
  public RadioButton rb_E3;
  public RadioButton rb_E4;
  public RadioButton rb_E5;
  public RadioButton rb_E6;
  public RadioButton rb_E7;
  public RadioButton rb_E8;
  public RadioButton rb_E9;
  public RadioButton rb_E10;
  public RadioButton rb_E11;
  public RadioButton rb_E12;
  public RadioButton rb_E13;
  public RadioButton rb_E14;

  public Line arest_E1E2;
  public Line arest_E2E3;
  public Line arest_E3E4;
  public Line arest_E4E5;
  public Line arest_E5E6;
  public Line arest_E2E9;
  public Line arest_E9E3;
  public Line arest_E5E7;
  public Line arest_E8E12;
  public Line arest_E2E10;
  public Line arest_E13E14;
  public Polyline arest_E8E4;
  public Polyline arest_E9E8;
  public Polyline arest_E8E5;
  public Polyline arest_E11E9;
  public Polyline arest_E4E13;
  public Polyline arest_E3E13;
  public Label lbl_log;

  private Shape[] arestas;

  private int[][] distanciasDiretas;
  private RadioButton[] radioButtons;

  private Estacao e1;
  private Estacao e2;
  private Estacao e3;
  private Estacao e4;
  private Estacao e5;
  private Estacao e6;
  private Estacao e7;
  private Estacao e8;
  private Estacao e9;
  private Estacao e10;
  private Estacao e11;
  private Estacao e12;
  private Estacao e13;
  private Estacao e14;

  private Estacao[] estacoes;

  private ArrayList<Estacao> linha1;
  private ArrayList<Estacao> linha2;
  private ArrayList<Estacao> linha3;
  private ArrayList<Estacao> linha4;

  private ArrayList<Estacao>[] linhas;

  private ArrayList<Estacao> caminhoMaisCurto;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    radioButtons = new RadioButton[]{rb_E1, rb_E2, rb_E3, rb_E4,
        rb_E5, rb_E6, rb_E7, rb_E8, rb_E9, rb_E10, rb_E11, rb_E12,
        rb_E13, rb_E14};

    arestas = new Shape[]{arest_E1E2, arest_E2E3, arest_E3E4, arest_E4E5, arest_E5E6
        , arest_E2E9, arest_E9E3, arest_E5E7, arest_E8E12, arest_E2E10, arest_E13E14
        , arest_E8E4, arest_E9E8,arest_E8E5,arest_E11E9,arest_E4E13,arest_E3E13
    };

    distanciasDiretas = new int[][]{
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


    e1 = new Estacao("E1");
    e2 = new Estacao("E2");
    e3 = new Estacao("E3");
    e4 = new Estacao("E4");
    e5 = new Estacao("E5");
    e6 = new Estacao("E6");
    e7 = new Estacao("E7");
    e8 = new Estacao("E8");
    e9 = new Estacao("E9");
    e10 = new Estacao("E10");
    e11 = new Estacao("E11");
    e12 = new Estacao("E12");
    e13 = new Estacao("E13");
    e14 = new Estacao("E14");

    estacoes = new Estacao[]{ e1, e2, e3, e4, e5, e6, e7, e8, e9,
        e10, e11, e12, e13, e14};

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

    e1.setArestas(arest_E1E2);
    e2.setArestas(arest_E1E2,arest_E2E9,arest_E2E3,arest_E2E10);
    e3.setArestas(arest_E3E4,arest_E3E13,arest_E2E3,arest_E9E3);
    e4.setArestas(arest_E4E5,arest_E4E13,arest_E3E4,arest_E8E4);
    e5.setArestas(arest_E5E6,arest_E5E7,arest_E4E5,arest_E8E5);
    e6.setArestas(arest_E5E6);
    e7.setArestas(arest_E5E7);
    e8.setArestas(arest_E8E5,arest_E8E4,arest_E8E12,arest_E9E8);
    e9.setArestas(arest_E9E8,arest_E9E3,arest_E2E9,arest_E11E9);
    e10.setArestas(arest_E2E10);
    e11.setArestas(arest_E11E9);
    e12.setArestas(arest_E8E12);
    e13.setArestas(arest_E13E14,arest_E4E13,arest_E3E13);
    e14.setArestas(arest_E13E14);

    linha1 = new ArrayList<>();
    linha2 = new ArrayList<>();
    linha3 = new ArrayList<>();
    linha4 = new ArrayList<>();

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

    linhas = new ArrayList[] {linha1,linha2,linha3,linha4};

  }

  public void onClick(ActionEvent e) {
    for (RadioButton radioButton : radioButtons) {
      if (e.getSource().equals(radioButton))
        chamarbusca(radioButton);
    }
  }

  public void chamarbusca(RadioButton radioButton) {
    if (radioButton.isSelected()) {
      int index = 0;
      for (int i = 0; i < radioButtons.length; i++) {
        if (radioButton == radioButtons[i])
          index = i;
      }

      for (int i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].isSelected() && radioButtons[i] != radioButton) {
          AStarMetro aStar = new AStarMetro(linhas, linha1);
          if(i>index){
            int b = i;
            i = index;
            index = b;
          }
          ArrayList<Vertice> caminho = aStar.buscar(estacoes[i],estacoes[index], distanciasDiretas);
          caminhoMaisCurto = new ArrayList<>();
          for (Vertice vertice : caminho) {
            caminhoMaisCurto.add((Estacao) vertice);
            System.out.print(((Estacao) vertice).getNome() + " ");
            formatarCaminho(true);
          }
            System.out.println();

          desativarRB(radioButton, radioButtons[i]);
          lbl_log.setText("Esse é o caminho mais curto");
          break;
        }else{
          lbl_log.setText("Selecione outra estação");
        }
      }
    }else {
      ativarRB();
      formatarCaminho(false);
      lbl_log.setText("Selecione uma estação");
    }
  }

  public void desativarRB(RadioButton rb1, RadioButton rb2) {
    for (RadioButton radioButton : radioButtons) {
      radioButton.setDisable(true);
    }
    rb1.setDisable(false);
    rb2.setDisable(false);
  }

  public void ativarRB() {
    for (RadioButton radioButton : radioButtons) {
      radioButton.setDisable(false);
    }
  }

  public void formatarCaminho(boolean destaque){
    for (Shape aresta : arestas) {
      if(destaque)
        aresta.setOpacity(0.3);
      else
        aresta.setOpacity(1);
    }
    for (int i = 0; i < caminhoMaisCurto.size()-1; i++) {
      for (Shape aresta : caminhoMaisCurto.get(i).getArestas()) {
        for (Shape shape : caminhoMaisCurto.get(i + 1).getArestas()) {
          if(aresta.equals(shape))
            Platform.runLater(() -> {
              if(destaque) {
                shape.setStrokeWidth(7);
                shape.setOpacity(1);
              }
              else {
                shape.setStrokeWidth(4);
              }
            });
        }
      }
    }
  }




}
