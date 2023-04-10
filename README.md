# Proiectare Sofware
## _Magazin de Resell Sneakers Online_

#
#
#
# Moldovan Adelin - grupa 6


Comertul electronic a devenit tot mai popular in ultima vreme, iar aceaasta industrie a cumparaturilor online a devenit foarte populara in ultima vreme, datorita faptului ca oamenii nu trebuie sa se mai deplaseze din locuinta lor pentru a cumpara ceva, astfel acest aspect l-a facut sa se extinda atat de tare intr-un timp foarte scurt relativ altor industrii.

De asemena in ultima vreme in Romania, au devenit foarte pupularii sneakersii de colectie, care sunt din totdeauna in america. Astfel ca la noi este foarte greu sa ajungi la aceste perechi care se lanseaza intr-un numar limitat in toata lumea, si cum acestea lansari se fac in general in america si pe site-urile de acolo, e foarte greu ca o persoana pasionata sa ajunga sa prinda o pereche de genul. 

Astfel prin proiectul meu vreau sa rezolv aceasta problem. In cadrul acestuia vreau sa realizez un magazin online de resell de sneakersii populari precum sunt air jordan 1, jordan 4 sau dunk-urile pe care toata lumea pe indrageste. Astfel pe acest site se vor putea gasi genul acesta de papuci si ulterior comanda. 



## ✨ Implementare si Functionalitati

#
In acest proiect, pentru partea de backend voi folosi java iar ca si mediu de dezvolate intellij. Pentru lucrul cu baze de date voi folosi postgres. Iar pentru salvarea proiectul si pentru siguranta ca nu o sa se piarda progresul am incarcat proiectul pe github si pe parcursul semestrului voi adauga functionalitati noi si implementari pentru imbunatatirea proiectului.
Pentru partea de frontend nu m-am gandit in totalitate, dar cel mai probabil o sa folosesc
react, desi inca nu sunt sigur.

#
In cadrul acestui proiect vreau sa am o pagina de start unde se va face partea de login si register. Astfel pentru acest proiect voi avea doi tipi de utilizatori, si anume un user normal care poate face o comanda, poate adauga produse in cos, sau sterge un produs si un admin care va putea modifica pretul anumitor produse, poate sa genereze anumite oferte, poate modifica anumite aspecte ale pagini, sau poate sterge contul unui user normal daca considera ca ceva nu este in regula. Voi folosi o baza de date pentru a stoca userii si toate datele despre aceestia. Pe parcusul proiectul voi crea si o baza de date pentru produsele de pe site-ul care vor putea fi manipulate de admin si care vor putea fi folosite pentru a face o comanda. Ma gandeam ca as vrea sa implementez fie o chitanta in momentul finalizarii unei comenzi care sa contina datele personale ale user-ului si datele de livrare fie o functionalitate care sa trimita un mail clientului cu datele respective.

Pe parcursul proiectul, dupa ce se va face registerul si login, voi lucra la pagina proptriu zisa pentru magazinul in sine. In cadrul acesteia o sa vrea sa implementez mai multe functionalitati cum ar fi notificarea userlui normal cand vor fi anumite oferte pe pagina, filtrarea produselor in functie de anumite criterii cum ar fi pretul, disponibilitatea marimilor, etc si de asemenea afisarea tuturor produselor. Pentru partea de comanda clientul va putea adauga cate produse doreste in cos, iar acesta se va actualiza atat din punct de vedere al cantitatii si al calcularii pretului noi. De asemenea ma gandeam ca as putea face o parte de adaugare de cupoate pentru a beneficia de o anumita reducere si ulterior un buton pentru realizarea comenzii, care va redirectiona user-ul pe o alta pagina unde acesta va trebui sa completeze datele de livrare si pentru plata comenzi si ulterior sa comfirme comanda. 

Pe parcurs daca vor interveni anumite probleme, sau ma voi gandi la functionalitati noi le voi adauga si explica ulterior, dar pentru inceput acestea ar fi.


## ✨  Continuare Implementare si tema 2

Continuad mai departe implementarea proiectului am actualizat clasele aferente si le am grupat pe toate in pachete specifice pentru o mai buna intelegere a codului si pentru a putea fi implementat mult mai usor. Astfel proiectul are acuma urmatoarele pachete cu clasele aferente: controller, exception, model, repository, service si validator, care dupa cum le spune numele fiecare cu rolul si functionalitatea specifica. 

Pentru partea de observar am creat un package observer cu clasa "CustomerObser" unde este implementat Observable Design patter. Asfel: Interfață Observable: Interfața Observable este implementată de clasa care dorește să-și notifice observatorii despre schimbări (în acest caz, ProductServiceImpl). Definește metode pentru a înregistra, dezabona și notifica observatorii.

Interfață Observer: Interfața Observer este implementată de clasa care dorește să fie notificată despre schimbări (în acest caz, Clientul). Definește metoda de actualizare, care va fi apelată de către Observable atunci când are loc o schimbare (se adaugă un produs nou).

ProductServiceImpl: Această clasă implementează acum interfața Observable. Menține o listă de observatori (clienți) pe care îi notifică atunci când se adaugă un produs nou. Când se adaugă un produs nou, apelează metoda notifyObservers() pentru a informa toți observatorii despre noul produs.

Client: Această clasă implementează acum interfața Observer. Are metoda de actualizare, care va fi apelată de către Observable atunci când se adaugă un produs nou. În această metodă, puteți defini logica pe care doriți să o efectuați atunci când un client este notificat despre un produs nou (de exemplu, trimiterea unui e-mail sau actualizarea interfeței utilizator).

CustomerServiceImpl: Această clasă a fost actualizată pentru a înregistra clienții ca observatori de fiecare dată când este creat un client nou sau când se abonează la notificări. Apelează metoda registerObserver() din clasa ProductService pentru a adăuga clientul în lista observatorilor.

De asemenea dupa cum se vede si in structura proiectul am modificat si baza de date pentru a putea fi mai usor de implementat astfel am mai adaugat baze de date pentru adresa de livrare si facturare si pentru produsele din cosul de cumparaturi. Aceasta se poate observa mai jos.

Pentru finalul proiectul mai este nevoie de rezolvat o eroare si de finalizat implemetarea pentru a se putea realiza partea de order.

> Note: `--capt-add=SYS-ADMIN` is required for PDF rendering.




[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>

