package sample.model;

import java.util.ArrayList;

public class AStarMetro extends AStar{
  private final ArrayList<Estacao>[] linhas;
  private ArrayList<Estacao> linhaAtual;

  public AStarMetro(ArrayList[] linhas, ArrayList<Estacao> linhaAtual) {
    this.linhas = linhas;
    this.linhaAtual = linhaAtual;
  }

  @Override
  protected int calcularCustoCondicional(Vertice v1, Vertice v2) {
    for (ArrayList<Estacao> linha : linhas) {
      if(linha.contains(v1) && linha.contains(v2)){
        if(linha == linhaAtual){
          return 0;
        }else{
          linhaAtual = linha;
          return 4;
        }
      }
    }
    return 0;
  }


}
