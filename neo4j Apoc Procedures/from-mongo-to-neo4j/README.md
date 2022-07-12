# FROM MONGODB TO NEO4J
Questo repository contiene un esempio di come trasformare dati da un database Mongodb a una struttura a grafo in neo4j attraverso le **Awesome Procedures On Cypher** (APOC).

<div align="center">
  <img src="https://github.com/mariocuomo/consistence-polyglot/blob/main/imgs/apoc.png">
</div>

Per eseguire i progetti sono necessari i seguenti strumenti
- Docker versione 20.10.16 per conteinerizzazione delle basi di dati
- docker-compose versione 1.25.0 per composizione dei container

```Shell
docker-compose up
```

La cartella ```sito``` contiene una semplice applicazione web sviluppata in Python col framework Flask e pensata per essere eseguita in locale.<br>
È una interfaccia in cui l'utente può interagire con il sistema eseguendo una richiesta di inserimento e visualizzando il contenuto delle basi di dati in tempo reale. 


```Shell
python run.py
```
<div align="center">
  <img src="https://github.com/mariocuomo/consistence-polyglot/blob/main/imgs/webappAPOC.png">
</div>

---
Repository collegato al corso di Big Data - @Università degli Studi Roma Tre

