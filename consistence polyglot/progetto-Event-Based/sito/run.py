from flask import Flask, flash, redirect, render_template, request, session, abort
import requests
import pandas as pd

headers = {
    'accept': '*/*',
    'Content-Type': 'application/json',
}

app = Flask(__name__)




"""
============
funzioni di supporto
per il dataframe
============
"""
def clean_nome(x):
    s = 'nome: '
    s = s + x
    return s

def clean_email(x):
    s = 'email: '
    s = s + x + '; '
    return s

def clean_documento(x):
    s = '{'
    return s+x+'}'


"""
"""
def aggiorna_tabelle():
    utenze_relazionale = requests.get('http://localhost:8080/servizioa/utenze').json()
    utenze_non_relazionale = requests.get('http://localhost:8080/serviziod/utenze').json()

    if utenze_relazionale!=[]:
        utenze_relazionale = pd.DataFrame.from_records(utenze_relazionale)
        utenze_relazionale.rename(columns = {'nome':'NOME', 'email':'EMAIL'}, inplace = True)
        utenze_relazionale = utenze_relazionale[['EMAIL', 'NOME']]
        utenze_relazionale = utenze_relazionale.to_html(classes='data', index_names=False, index=False)
        utenze_relazionale = utenze_relazionale.replace('<table border="1" class="dataframe data">','<table border="1" id="relazionale" class="dataframe data">')
    else:
        utenze_relazionale = '<table border="1" id="relazionale" class="dataframe data"><thead><tr style="text-align: right;"><th>EMAIL</th><th>NOME</th></tr></thead><tbody></tbody></table>'


    if utenze_non_relazionale!=[]:
        utenze_non_relazionale = pd.DataFrame.from_records(utenze_non_relazionale)
        utenze_non_relazionale['nome'] = utenze_non_relazionale['nome'].apply(clean_nome)
        utenze_non_relazionale['email'] = utenze_non_relazionale['email'].apply(clean_email)
        utenze_non_relazionale['documento'] = utenze_non_relazionale['email'] + utenze_non_relazionale['nome']
        utenze_non_relazionale = utenze_non_relazionale.drop('nome', 1)
        utenze_non_relazionale = utenze_non_relazionale.drop('email', 1)
        utenze_non_relazionale['documento'] = utenze_non_relazionale['documento'].apply(clean_documento)
        utenze_non_relazionale.rename(columns = {'documento':'DOCUMENTO'}, inplace = True)
        utenze_non_relazionale = utenze_non_relazionale.to_html(classes='data', index_names=False, index=False)
        utenze_non_relazionale = utenze_non_relazionale.replace('<table border="1" class="dataframe data">','<table border="1" id="nonrelazionale" class="dataframe data">')
    else:
        utenze_non_relazionale = '<table border="1" id="nonrelazionale" class="dataframe data"><thead><tr style="text-align: right;"><th>id</th><th>DOCUMENTO</th></tr></thead><tbody></tbody></table>'

    return utenze_relazionale,utenze_non_relazionale

@app.route("/")
def index():
    utenze_relazionale, utenze_non_relazionale = aggiorna_tabelle()

    return render_template('index.html',
        tabellaRelazionale=utenze_relazionale,
        tabellaNonRelazionale=utenze_non_relazionale,
        )


@app.route('/inserisci', methods=['POST', 'GET'])
def getPage():
    data = '{ "nome": "'+request.form['nome']+'", "email": "'+request.form['email']+'"}'
    response = requests.post('http://localhost:8080/servizioa/nuovautenza', headers=headers, data=data)
    
    return index()




if __name__ == "__main__":
    app.run()
