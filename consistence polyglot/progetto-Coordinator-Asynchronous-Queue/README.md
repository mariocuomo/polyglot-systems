# COORDINATOR ASYNCHRONOUS QUEUE

<div align="center">
  <img src="https://github.com/mariocuomo/consistence-polyglot/blob/main/imgs/queue.jpg">
</div>


In questo scenario si utilizza un meccanismo di sincronizzazione basato su una coda asincrona

1. il sistema riceve una chiamata POST per inserire una nuova utenza
2. l'api gateway cattura la richiesta e la inoltra al servizioA
3. il servizioA salva la nuova utenza sul database MySQL
4. il servizioA effettua una chiamata POST al servizio codaasincrona con le informazioni dell'utenza inserita***


Ogni minuto si attiva la funzione di sincronizzazione del microservizio coordinatore
1. il servizio coordinatore effettua una chiamata GET al servizio codaasincrona per ottenere gli ultimi aggiornamenti
2. il coordinatore salva le nuove utenze sul database Mongodb

*** si può pesare di avere un meccanismo di triggering attaccato alla comunicazione tra il servizioA e il database MySQL che genera la chiamata POST autonomamente senza l'intervento attivo del servizioA.

---
Repository collegato al corso di Big Data - @Università degli Studi Roma Tre

