# POLYBASE - Virtual table for MongoDB
Questa cartella contiene un semplice script per la creazione di una tabella virtuale in SQL Server per una sorgente dati esterna quale MongoDB.

<div align="center">
  <img src="https://github.com/mariocuomo/consistence-polyglot/blob/main/imgs/polybase.png">
</div>


```T-SQL
CREATE MASTER KEY ENCRYPTION BY PASSWORD = 'password@123’;

CREATE DATABASE SCOPED CREDENTIAL myMongoDBCredential
WITH IDENTITY = ‘admin’, Secret = ‘admin123’;

CREATE EXTERNAL DATA SOURCE MongoDBSource
WITH (
     LOCATION = ‘mongodb://127.0.0.1:27017’,
     CREDENTIAL = myMongoDBCredential,
     CONNECTION_OPTIONS = ‘ssl=false;’
);

CREATE EXTERNAL TABLE mongodbtable (
    nome NVARCHAR(MAX) NULL,
    autore NVARCHAR(MAX) NOT NULL,
    nome_utente  NVARCHAR(MAX),
    voto_utente INT )
WITH (
    LOCATION=‘admin.utenti_libri’,
    DATA_SOURCE= MongoDBSource
);

```


---
Repository collegato al corso di Big Data - @Università degli Studi Roma Tre

