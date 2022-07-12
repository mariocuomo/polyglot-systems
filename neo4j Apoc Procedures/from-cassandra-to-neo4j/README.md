# FROM CASSANDRA TO NEO4J

Questo repository contiene un esempio di come trasformare dati da un column database a una struttura a grafo in neo4j attraverso le **Awesome Procedures On Cypher** (APOC).<br>

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

Il file ```script.cql``` è uno script per inizializzare il contenuto del database colonnare.<br>
Una volta avviato il container è possibile accedere al servizio neo4j all'indirizzo 172.18.1.3:7474 e al servizio cassandra all'indirizzo 172.18.1.2:9042.

```CQL
CREATE KEYSPACE utenze_libri WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 }; 

USE utenze_libri;

CREATE TABLE utenze_libri (nome_utente text, nome_libro text, rating text, PRIMARY KEY ((nome_utente, nome_libro)));


INSERT INTO utenze_libri (nome_utente,nome_libro, rating) VALUES ('Connie','Apulki', '3') IF NOT EXISTS;
...
INSERT INTO utenze_libri (nome_utente,nome_libro, rating) VALUES ('Connie','Batman Handbook', '2') IF NOT EXISTS;
```

Il file ```generazione_cypher_script.py``` è uno script per la generazione del file ```script.cql```.


Si vuole creare un grafo composto da due tipi di nodi - **:Person** e **:Libro**.<br>
I nodi sono connessi tra loro tramite relazioni direzionali di tipo **ha_letto** da nodi :Person a nodi :Libro. La relazione ha un attributo _rating_.<br>

Per fare ciò è sufficiente utilizzare due chiamate APOC: una che crea uno schema (o meglio degli indici) e una che effettua la chiamata al servizio cassandra, recupera i dati e ne effettua il pattern matching per creare nodi e relazioni.

```CQL
CALL apoc.schema.assert(
  {User:['nome_utente']},
  {Libro:['nome_libro']});


CALL apoc.load.jdbc('jdbc:cassandra://172.18.1.2:9042/utenze_libri','utenze_libri') yield row
MERGE (u:User {nome:row.nome_utente})
MERGE (l:Libro {nome_libro:row.nome_libro})
CREATE (u)-[:ha_letto{rating:row.rating}]->(l);
```

---
Repository collegato al corso di Big Data - @Università degli Studi Roma Tre

