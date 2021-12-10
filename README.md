# Disney-API-Alkemy
<h1> BACKEND - Java </h1>
<h2>Spring Boot (API) 🚀</h2>
<h3>Objetivo</h3>
<p> Desarrollar una API para explorar el mundo de Disney, la cual permitirá conocer y modificar los
    personajes que lo componen y entender en qué películas estos participaron. Por otro lado, deberá
    exponer la información para que cualquier frontend pueda consumirla.</p>
<ul>
    <li>
        👉 Utilizar Spring Boot.
    </li>
    <li>
        👉 No es necesario armar el Frontend.
    </li>
    <li>
        👉 Las rutas deberán seguir el patrón REST.
    </li>
    <li>
        👉 Utilizar la librería Spring Security.
    </li>
</ul>

<h2>Requerimientos técnicos</h2>
<h3>1. Modelado de Base de Datos</h3>
<ul>
    <li>
        ● Personaje: deberá tener,
        <ul>
            <li>
                ○ Imagen.
            </li>
            <li>
                ○ Nombre.
            </li>
            <li>
                ○ Edad.
            </li>
            <li>
                ○ Peso.
            </li>
            <li>
                ○ Historia.
            </li>
            <li>
                ○ Películas o series asociadas.
            </li>
        </ul>
    </li>
    <li>
        ● Película o Serie: deberá tener,
        <ul>
            <li>
                ○ Imagen.
            </li>
            <li>
                ○ Título.
            </li>
            <li>
                ○ Fecha de creación.
            </li>
            <li>
                ○ Calificación (del 1 al 5).
            </li>
            <li>
                ○ Personajes asociados.
            </li>
        </ul>
    </li>
    <li>
        ● Género: deberá tener,
        <ul>
            <li>
                ○ Nombre.
            </li>
            <li>
                ○ Imagen.
            </li>
            <li>
                ○ Películas o series asociadas.
            </li>
        </ul>
    </li>
</ul>

<h3>2. Autenticación de Usuarios</h3>
<p>Para realizar peticiones a los endpoints subsiguientes el usuario deberá contar con un token que
    obtendrá al autenticarse. Para ello, deberán desarrollarse los endpoints de registro y login, que
    permitan obtener el token. <br>
    Los endpoints encargados de la autenticación deberán ser:</p>
<ul>
    <li>
        ● /auth/login
    </li>
    <li>
        ● /auth/register
    </li>
</ul>
<h3>3. Listado de Personajes</h3>
El listado deberá mostrar:
<ul>
    <li>
        ● Imagen.
    </li>
    <li>
        ● Nombre.
    </li>
</ul>
El endpoint deberá ser:
<ul>
    <li>
        ● /characters
    </li>
</ul>
<h3>
    4. Creación, Edición y Eliminación de Personajes (CRUD)
</h3>
Deberán existir las operaciones básicas de creación, edición y eliminación de personajes.
<h3>5. Detalle de Personaje</h3>
En el detalle deberán listarse todos los atributos del personaje, como así también sus películas o
series relacionadas.
<h3>6. Búsqueda de Personajes</h3>
Deberá permitir buscar por nombre, y filtrar por edad, peso o películas/series en las que participó.
Para especificar el término de búsqueda o filtros se deberán enviar como parámetros de query:
<ul>
    <li>
        ● GET /characters?name=nombre
    </li>
    <li>
        ● GET /characters?age=edad
    </li>
    <li>
        ● GET /characters?movies=idMovie
    </li>
</ul>
<h3>7. Listado de Películas</h3>
Deberá mostrar solamente los campos imagen, título y fecha de creación. <br>
El endpoint deberá ser:
<ul>
    <li>
        ● GET /movies
    </li>
</ul>

<h3>8. Detalle de Película / Serie con sus personajes</h3>
Devolverá todos los campos de la película o serie junto a los personajes asociados a la misma
<h3> 9. Creación, Edición y Eliminación de Película / Serie</h3>
Deberán existir las operaciones básicas de creación, edición y eliminación de películas o series.
<h3>10.Búsqueda de Películas o Series</h3>
Deberá permitir buscar por título, y filtrar por género. Además, permitir ordenar los resultados por
fecha de creación de forma ascendiente o descendiente. <br>
El término de búsqueda, filtro u ordenación se deberán especificar como parámetros de query:
<ul>
    <li>
        ● /movies?name=nombre
    </li>
    <li>
        ● /movies?genre=idGenero
    </li>
    <li>
        ● /movies?order=ASC | DESC
    </li>
</ul>
<h3>11. Envío de emails</h3>
Al registrarse en el sitio, el usuario deberá recibir un email de bienvenida. Es recomendable, la
utilización de algún servicio de terceros como SendGrid.
