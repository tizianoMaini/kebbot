Ogni volta che viene eseguita la push su master (o viene accettata una MR) viene affettuato il deploy su ambiente staging:

https://demo-kebbot.herokuapp.com/kebbot/hello

ogni volta che viene eseguita la push di un tag viene effettuato il deploy in produzione

https://kebbot.herokuapp.com/kebbot/hello


Creare un nuovo database postgresql e inserire la relativa stringa di connessione come variabile d'ambiente

DATABASE_URL=postgres://postgres:postgres@localhost:5432/kebbot

In una prima implementazione di base, l'app dovrebbe inserire un nuovo record e mostrarli con un json, scrivendo sul browser

https://kebbot.herokuapp.com/kebbot/users
