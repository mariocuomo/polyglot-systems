import names
import pandas as pd
import random
import string

nomi=set()
for i in range(20):
	nomi.add(names.get_first_name())



libri=[]
df = pd.read_csv('books.csv')
libri = df['Title'].tolist()
libri_cleaned = []

for libro in libri:
	_libro = libro
	for char in string.punctuation:
		_libro = _libro.replace(char, ' ')
	libri_cleaned.append(_libro)

libri=libri_cleaned

ha_letto = []
for persona in nomi:
	numero_libri_letti = random.randint(0, 7)

	if numero_libri_letti!=0:
		libri_letti = []
		for i in range(numero_libri_letti):
			rating = random.randint(1, 5)
			libro =  random.choice(libri)
			libri_letti.append([libro, rating])
		ha_letto.append([persona, libri_letti])		



with open('script.cql', 'a') as f:
	f.write('CREATE KEYSPACE utenze_libri WITH replication = {\'class\': \'SimpleStrategy\', \'replication_factor\': 1 }; \n USE utenze_libri;')
	f.write('\n')

	f.write('CREATE TABLE utenze_libri (nome_utente text, nome_libro text, rating text, PRIMARY KEY ((nome_utente, nome_libro)));')
	f.write('\n')

	for persona,lst in ha_letto:
		for libro, rating in lst:
			s = 'INSERT INTO utenze_libri (nome_utente,nome_libro, rating) VALUES (\''+persona+'\',\''+libro+'\', \''+str(rating)+'\') IF NOT EXISTS;'
			f.write(s)
			f.write('\n')
	

