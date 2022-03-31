package sample.model;

public class Estacao extends Vertice{
  private final String nome;      // Nome

  public Estacao(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

}
