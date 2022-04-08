package sample.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public abstract class AStar {

  /**
   * Busca o menor caminho entre dois vertices
   * de um Grafo com pesos em suas arestas
   *
   * @param estadoInicial Estado Inicial
   * @param estadoFinal   Estado Desejado
   * @param matrizPesos   Matriz de distancia entre todos os estados
   * @return Menor caminho entre dois vertices quaisquer
   */
  public ArrayList<Vertice> buscar(Vertice estadoInicial, Vertice estadoFinal, int[][] matrizPesos) {
    ArrayList<Vertice> arrayList = new ArrayList<>();
    arrayList.add(estadoInicial);
    return buscar(estadoInicial, estadoFinal, matrizPesos, arrayList);
  }

  /**
   * Busca o menor caminho entre o Estado Inicial e o Estado Final
   *
   * @param estadoAtual Estado Atual
   * @param estadoFinal Estado desejago
   * @param matrizPesos Matriz de distancia entre todos os estados
   * @param caminho     Caminho percorrido
   * @return Lista de estados percorridos
   */
  private ArrayList<Vertice> buscar(Vertice estadoAtual, Vertice estadoFinal, int[][] matrizPesos, ArrayList<Vertice> caminho) {
    HashMap<Vertice, Integer> nosFilhos = estadoAtual.getFilhos();
    ArrayList<EstadoExpandido> nosExpandidos = new ArrayList<>();    // Vertice e o custo ate o Estado Final


    // PARA CADA NO FILHO EU VOU EXPANDIR E CALCULAR O CUSTO ATE O ESTADO FINAL
    nosFilhos.forEach((vertice, custo) -> {
      int custoDireto = calcularCusto(estadoAtual, vertice, estadoFinal, custo, matrizPesos);
        nosExpandidos.add(new EstadoExpandido(vertice, custoDireto));
    });


    // ORDENA PARA QUE O QUE TENHA A MENOR CUSTO FIQUE EM PRIMEIRO LUGAR
    Collections.sort(nosExpandidos, Comparator.comparingInt(EstadoExpandido::getCusto));

    // PEGA O VERTICE DE MENOR CUSTO
    Vertice verticeDeMenorCusto = nosExpandidos.get(0).getVertice();


    // CASO GERAL DA RECURSAO
    if (!verticeDeMenorCusto.equals(estadoFinal)) {         // Ter o vertice de menor custo igual ao estado final significa que a solucao foi encontrada
      caminho.add(verticeDeMenorCusto);
      buscar(verticeDeMenorCusto, estadoFinal, matrizPesos, caminho);
    } else {
      caminho.add(estadoFinal);
    }
    return caminho;
  }

  /**
   * Funcao de Avaliacao de A*
   * Busca na matriz a distancia
   * do estado atual ate o final
   * f(n) = g(n) + h(n)
   *
   * @param estadoAtual     Usado para pegar o indice do estado inicial
   * @param estadoFinal     Usado para pegar o indice do estado final
   * @param custo           g(n) Custo do estado anterior ate o atual
   * @param matrizDistancia h(n) Custo do estado atual ate o final
   * @return Custo ate o estado final
   */
  private int calcularCusto(Vertice estadoAnterior, Vertice estadoAtual, Vertice estadoFinal, Integer custo, int[][] matrizDistancia) {
    return custo + matrizDistancia[estadoFinal.getIndice()][estadoAtual.getIndice()]*2 + calcularCustoCondicional(estadoAnterior, estadoAtual);
  }
  /*
    30km/h -> 30km/60m -> 3km/6m -> 1km/2m -> 500m/min
    500 - 1
    y - x
    x = y/500
  */


  protected abstract int calcularCustoCondicional(Vertice v1, Vertice v2);

  /**
   * Classe que representa uma tupla entre um vertice e o seu custo ate o estado final
   */
  private static class EstadoExpandido {
    private final Vertice vertice;
    private final int custo;

    EstadoExpandido(Vertice vertice, int custo) {
      this.vertice = vertice;
      this.custo = custo;
    }

    public Vertice getVertice() {
      return vertice;
    }

    public int getCusto() {
      return custo;
    }
  }
}
