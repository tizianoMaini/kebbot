Ogni volta che viene eseguita la push su master (o viene accettata una MR) viene affettuato il deploy su ambiente staging:

https://demo-kebbot.herokuapp.com/hello-world

ogni volta che viene eseguita la push di un tag viene effettuato il deploy in produzione

https://kebbot.herokuapp.com/hello-world

## Esecuzione in locale

Creare un nuovo database postgresql e inserire la relativa stringa di connessione come variabile d'ambiente

DATABASE_URL=postgres://postgres:postgres@localhost:5432/kebbot