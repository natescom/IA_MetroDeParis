package sample.model;

import javafx.scene.shape.Shape;

public class Estacao extends Vertice{
  private final String nome;      // Nome
  private Shape[] arestas;


  public Estacao(String nome) {
    this.nome = nome;
  }

  public void setArestas(Shape... arestas) {
    this.arestas = arestas;
  }

  public String getNome() {
    return nome;
  }

  public Shape[] getArestas() {
    return arestas;
  }
}
