from flask import Flask, flash, redirect, render_template, request, session, abort
import requests
import pandas as pd
from pymongo import MongoClient
from py2neo import Graph





client = MongoClient('mongodb://mongo:neo4j@172.18.1.3', 27017);
db = client.test
col = db.utenti_libri



app = Flask(__name__)


@app.route("/")
def index():
    return render_template('index.html',flash_message=True)


@app.route('/inserisci', methods=['POST', 'GET'])
def getPage():
    nome = request.form['nome']
    nome_libro = request.form['nome_libro']
    autore_libro = request.form['autore_libro']
    rating = request.form['rating']

    if col.count({ 'nome_libro':  nome_libro}, limit = 1) != 0:
        myquery = { "nome_libro": nome_libro }
        newvalues = { "$push": { "utenti": { "nome": nome, "rating": rating } } }
        col.update_one(myquery, newvalues)
    else:
        mydict = { "nome_libro": nome_libro, "utenti": [{ "nome": nome, "rating": rating }]}
        col.insert_one(mydict)




    g = Graph("bolt://172.18.1.2:7687", auth=("neo4j", "mongo"))

    query = """match (a) -[r] -> () delete a, r"""
    g.run(query)  

    query = """match (a) delete a"""
    g.run(query)  


    query = """CALL apoc.mongodb.get('mongodb://mongo:neo4j@mongo:27017', 'test', 'utenti_libri', {}, true) YIELD value
CALL apoc.graph.fromDocument(value,{
            write: true, 
            skipValidation: true, 
            mappings: {
                `$`: 'Libro{!nome_libro}',
                `$.utenti`: 'Person{nome,rating}'
                }
            }
            )YIELD graph AS g1
return g1"""
    g.run(query)

    query = """MATCH (a)-[r]->(b)
SET r.rating = b.rating
"""
    g.run(query)

    query = """MATCH (n:Person)
WITH toLower(n.nome) as nome, collect(n) as nodes
CALL apoc.refactor.mergeNodes(nodes) yield node
RETURN *
"""
    g.run(query)

    query = """MATCH (n:Person)
REMOVE n.rating"""
    g.run(query)

    query = """MATCH ()-[rel]->()
WITH collect(rel) AS rels
CALL apoc.refactor.rename.type("UTENTI", "letto_da", rels)
YIELD committedOperations
RETURN committedOperations"""
    g.run(query)

    return index()


if __name__ == "__main__":
    app.run()
