# ForoHub API

Este código fue realizado como propuesta al reto presentado en el curso Java y Spring Framework de Oracle ONE con Alura LATAM, denominado **"Practicando Spring Framework: Challenge Foro Hub"**, y consiste en API REST para la gestión de un foro en el cual es posible la creación, modificación, eliminación y consulta de topicos del foro.

La aplicación implementa **autenticación con JWT (JSON Web Tokens)** para garantizar que solo usuarios registrados puedan realizar operaciones críticas.

---

El código está realizado en su totalidad con **Java (17.0.15)** y **Spring Boot (3.5.4)**. La estructura de los archivos es la siguiente:

```
└── ChallengeForoHub/
    ├── .gitattributes
    ├── .gitignore
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── .mvn/
    │   └── wrapper/
    │       └── maven-wrapper.properties
    └── src/
        ├── main/
        │   ├── resources/
        │   │   ├── application.properties
        │   │   └── db/
        │   │       └── migration/
        │   │           ├── V1__create-table-topico.sql
        │   │           ├── V2__add_unique_constraints_topico.sql
        │   │           └── V3__create-table-usuarios.sql
        │   └── java/
        │       └── com/
        │           └── m4uawa/
        │               └── forohub/
        │                   ├── ForoHubApplication.java
        │                   ├── controller/
        │                   │   ├── AuthenticationController.java
        │                   │   └── TopicosController.java
        │                   ├── infra/
        │                   │   └── security/
        │                   │       ├── SecurityConfigurations.java
        │                   │       ├── SecurityFilter.java
        │                   │       ├── TokenData.java
        │                   │       └── TokenService.java
        │                   └── domain/
        │                       ├── user/
        │                       │   ├── AuthenticationData.java
        │                       │   ├── AuthenticationService.java
        │                       │   ├── Usuario.java
        │                       │   └── UsuarioRepository.java
        │                       └── topico/
        │                           ├── DTODTopico.java
        │                           ├── DTORTopico.java
        │                           ├── DTOUTopico.java
        │                           ├── StatusTopico.java
        │                           ├── Topico.java
        │                           └── TopicoRepository.java
        └── test/
            └── java/
                └── com/
                    └── m4uawa/
                        └── forohub/
                            └── ForohubApplicationTests.java

```

La aplicación comienza en el archivo `ForoHubApplication.java` en donde tenemos una aplicación Spring Boot convencional. A partir de ahí se definen los controladores, repositorios, servicios y configuraciones de seguridad necesarias para el funcionamiento del foro.

Dentro de los controladores encontramos dos clases principales:

> **TopicosController**
>
> > Se encarga de manejar los endpoints relacionados con los tópicos del foro (`/topicos`). Permite listar todos los tópicos o mostrar uno específico, crear nuevos, eliminarlos y actualizarlos.
> >
> > * **GET /topics**: Lista todos los tópicos existentes.
> > * **GET /topicos/{id}**: Lista un tópico específico.
> > * **POST /topics**: Crea un nuevo tópico, siempre que el usuario esté autenticado.
> > * **DELETE /topics/{id}**: Elimina un tópico existente.
> > * **PUT /topics/{id}**: Actualiza un tópico específico.

> **AuthenticationController**
>
> > Expone el endpoint `/login`, el cual recibe las credenciales (email y contraseña) y retorna un **JWT válido** que debe utilizarse en las siguientes solicitudes como *Bearer Token*, expirando despues de 2hrs de su generación.

---

En cuanto a la **seguridad**, se implementan la clases `SecurityConfigurations.java` y `SecurityFilter.java` Las cuales utilizan Spring Security entre otras dependencias para que sus métodos sean capaces de realizar las filtraciones de los requests y el manejo de los tokens.

El servicio `TokenService` se encarga de la generación y validación de los JWT.

---

El flujo básico de la aplicación es el siguiente:

1. **Autenticación**

   * El usuario envía un `POST /login` con sus credenciales.
   * El sistema devuelve un token JWT.

2. **Creación de un tópico**

   * El usuario envía un `POST /topicos` con los datos del nuevo tópico.
   * El request debe incluir el *Bearer Token*.
   * Si es válido, se guarda en la base de datos y se retorna un **201 Created** con el objeto creado y la dirección donde se puede consultar.

3. **Listado de tópicos**

   * Con una petición `GET /topicos`, se devuelve la lista de todos los tópicos registrados.
   * El request debe incluir el *Bearer Token*.
   * La respuesta correcta devuelve un **200 OK** junto con los datos.
   * (También es posible la consulta de un tópico en especifico con `GET /topicos/{id}`)

4. **Actualización de un tópico**

   * Se realiza con `PUT /topicos/{id}` con los datos a actualizar.
   * El request debe incluir el *Bearer Token*.
   * Si la operación es exitosa, se actualiza el recurso y se devuelve un **200 OK** con los datos del tópico modificado 

5. **Eliminación de un tópico**

   * Se realiza con `DELETE /topicos/{id}`.
   * El request debe incluir el *Bearer Token*.
   * Si la operación es exitosa, se devuelve un **204 No Content**.

---

El programa cuenta con manejo de errores y respuestas HTTP apropiadas:

* `200 OK` -> Operación exitosa (listar o actualizar).
* `201 Created` -> Creación exitosa de un recurso.
* `403 Forbidden` -> Intento de acceder sin autenticación válida.
* `404 Not Found` -> Recurso inexistente.
---
