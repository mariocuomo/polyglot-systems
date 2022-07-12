# BATCH PROCESSES

<div align="center">
  <img src="https://github.com/mariocuomo/polyglot-systems/blob/main/imgs/batch.jpg">
</div>


In questo scenario si utilizza un meccanismo di sincronizzazione basato su processi batch.

1. il sistema riceve una chiamata POST per inserire una nuova utenza
2. l'api gateway cattura la richiesta e la inoltra al servizioA
3. il servizioA salva la nuova utenza sul database MySQL


Ogni minuto si attiva la funzione di sincronizzazione del microservizio batchprocess
1. si estrae il contenuto dal database MySQL
2. si estrae il contenuto dal database Mongodb
3. si aggiorna il contenuto dal database Mongodb


---
Repository collegato al corso di Big Data - @Università degli Studi Roma Tre

