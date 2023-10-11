# Tehnologii Java Lab. 1

# Compulsory
 
Am creat un servlet pe ruta /hello-servlet care are nevoie de un parametru `numVertices`.
Pe post de iesire, acesta afiseaza intr-o lista HTML ordonata cifrele acestui numar

# Homework

Primul task:
    Am facut servletul FormServlet care afiseaza un formular in care se poate introduce un numar de noduri si un buton de submit.
    Trimite datele catre servletul TreeAdjacencyMatrixServlet care afiseaza matricea generata random pe ecran

Al doilea task:
    De la linia 75 in TreeAdjacencyMatrixServlet puteti vedea log-urile pe care le creez: user-agenr, limba, parametri, IP
    Acestea sunt retinute in log file-ul de la glassfish local

Al treilea task:
    Aici spune ca trebuie sa invocam servlet-ul dintr-o aplicatie desktop, asa ca am folosit Postman pentru a-mi testa API-ul
    La linia 37 din TreeAdjacencyMatrixServlet am inclus un parametru extra `application` care, odata prezent in request,
    va trimite date despre matricea generata in forma JSON.