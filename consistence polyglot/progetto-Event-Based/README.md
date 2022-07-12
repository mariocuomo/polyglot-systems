# EVENT BASED BACKBONE

<div align="center">
  <img src="https://github.com/mariocuomo/polyglot-systems/blob/main/imgs/event-based.jpg">
</div>


In questo scenario si utilizza un meccanismo di sincronizzazione basato su eventi.

1. il sistema riceve una chiamata POST per inserire una nuova utenza
2. l'api gateway cattura la richiesta e la inoltra al servizioA
3. il servizioA salva la nuova utenza sul database MySQL
4. il servizioA produce un messaggio con le informazioni dell'utenza inserita***
5. il servizioD è un consumatore dei messaggi prodotti dal servizioA
6. il servizioD salva la nuova utenza sul database Mongodb



*** si può pesare di avere un meccanismo di triggering attaccato alla comunicazione tra il servizioA e il database MySQL che genera il messaggio autonomamente senza l'intervento attivo del servizioA.

---
Repository collegato al corso di Big Data - @Università degli Studi Roma Tre

