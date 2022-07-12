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
	for persona in nomi:
		s = 'CREATE (:Person {nome: \''+persona+'\'});'
		f.write(s)
		f.write('\n')

	for libro in libri:
		s = 'CREATE (:Libro {nome_libro: \''+libro+'\'});'
		f.write(s)
		f.write('\n')


	for persona,lst in ha_letto:
		for libro, rating in lst:
			s = 'MATCH (a:Person), (b:Libro) WHERE a.nome = \''+persona+'\' AND b.nome_libro = \''+libro+'\' '
			s = s+ 'CREATE (a)-[r:ha_letto {rating: '+ str(rating)+' }]->(b);'
			f.write(s)
			f.write('\n')
	

