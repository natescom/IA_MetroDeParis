package sample.model;

import java.util.HashMap;

public class Vertice{
  private final int indice;                             // Indice do vertice
  private final HashMap<Vertice, Integer> filhos;       // Vertice filho e Custo para chegar ate ele
  private static int cont = 0;

  public Vertice() {
    this.indice = cont;
    this.filhos = new HashMap<>();
    cont++;
  }

  public int getIndice() {
    return indice;
  }

  public void connect(Vertice v, int custo){
    filhos.put(v, custo);
    v.getFilhos().put(this,custo);
  }

  public HashMap<Vertice, Integer> getFilhos() {
    return filhos;
  }
}
