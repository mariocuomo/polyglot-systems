# CONSISTENCE POLYGLOT
Questo repository contiene 3 progetti relativi a 3 diversi patterns per gestire la consistenza dei dati in sistemi poliglotti.

Lo scenario è semplice.<br>
Ipotizziamo di avere una applicazione a microservizi che utilizza due diversi database: un database relazionale **MySQL** e una database basato su documenti **Mongodb**. 

Il database relazionale è il database master: esso riceve le richieste di scrittura.<br>
Il database NoSQL è il database slave: esso non riceve richieste di scrittura dall'esterno ma per motivi organizzativi deve essere allineato al contenuto del master.

Per semplicità consideriamo l'inserimento di record (**email**,**nome**) e creazione di documenti con struttura equivalente.

<div align="center">
  <img src="https://github.com/mariocuomo/polyglot-systems/blob/main/imgs/MySQLMongo.jpg">
</div>


La sincronizzazione può avvenire con 3 modalità differenti

- [Batch Processes](https://github.com/mariocuomo/polyglot-systems/tree/main/consistence%20polyglot/progetto-Batch-Processes)<br>
  Si utilizzano routine che si occupano di sincronizzare il contenuto del master con lo slave ogni _x_ unità di tempo.
- [Coordinator Asynchronous Queue](https://github.com/mariocuomo/polyglot-systems/tree/main/consistence%20polyglot/progetto-Coordinator-Asynchronous-Queue)<br>
  Si utilizza una coda asincrona che memorizza gli aggiornamenti sul master e ogni _x_ unità di tempo si estraggono gli eventi non ancora sincronizzati.
- [Event Based](https://github.com/mariocuomo/polyglot-systems/tree/main/consistence%20polyglot/progetto-Event-Based)<br>
  Si utilizza un meccanismo event based: quando il master riceve una richiesta di inserimento si comunica anche allo slave la richiesta tramite un meccanismo di messaggistica.

<br>
<br>

Per eseguire i progetti sono necessari i seguenti strumenti
- Gradle versione 6.4.1 per la compilazione dei progetti springboot e la gestione delle dipendenze
- Docker versione 20.10.16 per conteinerizzazione dei microservizi, delle basi di dati, dei sisemi di messaggistica e di service discovery
- docker-compose versione 1.25.0 per composizione dei microservizi

```Shell
gradle assemble
docker-compose build --no-cache 
docker-compose up
#oppure sh start.sh
```

La cartella ```sito``` contiene una semplice applicazione web sviluppata in Python col framework Flask e pensata per essere eseguita in locale.<br>
È una interfaccia in cui l'utente può interagire con il sistema eseguendo una richiesta di inserimento e visualizzando il contenuto delle basi di dati in tempo reale. 


```Shell
python run.py
```
<div align="center">
  <img src="https://github.com/mariocuomo/polyglot-systems/blob/main/imgs/webapp.png">
</div>


---
Repository collegato al corso di Big Data - @Università degli Studi Roma Tre

