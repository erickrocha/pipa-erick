package com.pipastudios.erick.config.database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * classe que representa o armazenamento de dados de uma maneira simples um map aonde a chave seria o nome da tabela
 * e retorn outro map aonde o id e a chave e o objeto é o valor
 */
public class InMemory {

  private Map<String, Map<Serializable, Object>> tables;

  public InMemory() {
    this.tables = new HashMap<>();
  }

  /**
   * Recebe o nome da tabela que retorna outro hastable sendo key representando o id e o value sendo
   * a entidade armazenada
   *
   * @param tableName
   * @param key
   * @param value
   */
  public void persist(String tableName, Serializable key, Object value) {
    if (!this.tables.containsKey(tableName)) {
      this.tables.put(tableName, new HashMap<>());
    }
    Map<Serializable, Object> table = this.tables.get(tableName);
    table.put(key, value);
  }

  /**
   * método que retorna a entidade pelo id, necessário passar o nome da tabela retorn null caso a
   * tabela não exista
   *
   * @param tableName
   * @param key
   * @return
   */
  public Object get(String tableName, Serializable key) {
    if (this.tables.containsKey(tableName)) {
      return this.tables.get(tableName).get(key);
    }
    return null;
  }

  /**
   * Método que retorna todos os registros da tabela retorna um stream para garantir a idéia de
   * somente leitura e fast forward
   *
   * @param tableName
   * @return
   */
  public Stream getAll(String tableName) {
    return this.tables.get(tableName).values().stream();
  }
}
