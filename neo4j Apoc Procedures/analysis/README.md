# ANAlYSIS

Questo repository contiene alcune query di analisi per un graph database in neo4j.<br>

Il grafo è composto da due tipi di nodi - **:Person** e **:Libro**.<br>
I nodi sono connessi tra loro tramite relazioni direzionali di tipo **ha_letto** da nodi :Person a nodi :Libro. La relazione ha un attributo _rating_.<br>

<div align="center">
  <img src="https://github.com/mariocuomo/polyglot-systems/blob/main/imgs/neo4jAnalysis.png">
</div>

Per eseguire il progetto sono necessari i seguenti strumenti
- Docker versione 20.10.16 per conteinerizzazione il servizio neo4j
- docker-compose versione 1.25.0 per l'esecuzione

```Shell
docker-compose up
```

Il file ```neo4j\plugins\script.cql``` è uno script per inizializzare il contenuto del grafo.<br>
Una volta avviato il container è possibile accedere al servizio neo4j all'indirizzo 172.18.1.2:7474.

```CQL
CREATE (:Person {nome: 'Michael'});
CREATE (:Person {nome: 'Gladys'});
...
CREATE (:Libro {nome_libro: 'Complete Sherlock Holmes  The   Vol II'});
CREATE (:Libro {nome_libro: 'Wealth of Nations  The'});
...
MATCH (a:Person), (b:Libro) WHERE a.nome = 'Michael' AND b.nome_libro = 'Sea of Poppies' CREATE (a)-[r:ha_letto {rating: 2 }]->(b);
MATCH (a:Person), (b:Libro) WHERE a.nome = 'Michael' AND b.nome_libro = 'Uncommon Wisdom' CREATE (a)-[r:ha_letto {rating: 4 }]->(b);
...
```

Il file ```generazione_cypher_script.py``` è uno script per la generazione del file ```script.cql```.


**QUERY 1** <br>
Per ogni persona restituire il numero di libri letti - ordinato in modo decrescente

```CQL
MATCH (n:Person)
WITH n, size((n)-->()) AS count
ORDER BY count DESC
RETURN n, count;
```

**QUERY 2** <br>
Restituire i 3 libri più letti e il numero di utenti che li hanno letti

```CQL
MATCH (n:Libro)
WITH n, size((n)<--()) AS count
ORDER BY count DESC
RETURN n, count
LIMIT 3;
```

**QUERY 3** <br>
Restituire i 3 libri che è sono stati più apprezzati - ovvero quelli col rating medio più alto

```CQL
MATCH ()-[r]->(n:Libro)
RETURN n, AVG(r.rating) as rating_medio
ORDER BY rating_medio DESC
LIMIT 3
```

**QUERY 4** <br>
Restituire tutte le coppie di utenti simili - ovvero utenti che hanno recensito lo stesso libro.<br>
Restituire anche il nome del libro.

```CQL
MATCH (a:Person)-->(n:Libro)<--(b:Person)
RETURN a.nome, b.nome, n.nome_libro
```

**QUERY 5** <br>
Recommender di libri.<br>
Se x e y hanno letto lo stesso libro, restituire tutte le coppie x-k dove k è un libro letto da y ma non da x.

```CQL
MATCH (a:Person)-->(n:Libro)<--(b:Person)-->(x:Libro)
WHERE NOT (a)-->(x)
RETURN a.nome, x.nome_libro
```

---
Repository collegato al corso di Big Data - @Università degli Studi Roma Tre

