# Disney-API-Alkemy

<h1> BACKEND - Java </h1>
<h2>Spring Boot (API) 🚀</h2>
<h3>Goal</h3>
<p> Develop an API to explore the world of Disney, which will allow knowing and modifying the
     characters that compose it and understand in which films they participated. On the other hand, it must
     expose the information so that any frontend can consume it.</p>
<ul>
    <li>
        👉 Use Spring Boot.
    </li>
    <li>
        👉 No need of Frontend.
    </li>
    <li>
        👉 Paths must follow a REST pattern.
    </li>
    <li>
        👉 Use Spring Security library.
    </li>
</ul>

<h2>Technical requirements</h2>
<h3>1. Database Modeling</h3>
<ul>
    <li>
        Character: must have,
        <ul>
            <li>
                Image.
            </li>
            <li>
                Name.
            </li>
            <li>
                Age.
            </li>
            <li>
                Weight.
            </li>
            <li>
                Story.
            </li>
            <li>
                Associated movies.
            </li>
        </ul>
    </li>
    <li>
        Movies: must have,
        <ul>
            <li>
                Image.
            </li>
            <li>
                Title.
            </li>
            <li>
                Creation date.
            </li>
            <li>
                Rating (from 1 to 5).
            </li>
            <li>
                Associated characters.
            </li>
        </ul>
    </li>
    <li>
        Genre: must have,
        <ul>
            <li>
                Name.
            </li>
            <li>
                Image.
            </li>
        </ul>
    </li>
</ul>

<h3>2. User Authentication</h3>
<p>To make requests to subsequent endpoints, the user must have a token that
    will be given when authenticating. For this, the registration and login endpoints must be developed, which
    allow to obtain the token. <br>
    Endpoints in charge of authentication must be:</p>
<ul>
    <li>
        /auth/signin
    </li>
    <li>
        /auth/signup
    </li>
</ul>
<h3>3. Characters List</h3>
List must show:
<ul>
    <li>
        Image.
    </li>
    <li>
        Name.
    </li>
</ul>
Endpoint must be:
<ul>
    <li>
        /characters
    </li>
</ul>
<h3>
    4. Creation, Read, Update and Delete Characters (CRUD)
</h3>
There should be the basic operations of creating, editing and deleting characters.
<h3>5. Character Details</h3>
All the character's attributes should be listed, as well as his movies.
<h3>6. Character Search</h3>
It should allow to search by name, and filter by age, weight or movies in which the character has participated.
To specify the search term or filters, they must be sent as query parameters:
<ul>
    <li>
        GET /characters?name=nombre
    </li>
    <li>
        GET /characters?age=edad
    </li>
    <li>
        GET /characters?movies=idMovie
    </li>
</ul>
<h3>7. Movies List</h3>
It must show only image, title and creation date fields.<br>
Endpoint must be:
<ul>
    <li>
        GET /movies
    </li>
</ul>

<h3>8. Movie details with its characters</h3>
It will return all movie fields along with a list of characters associated to such movie.
<h3> 9. Creation, Update and Delete a Movie (CRUD)</h3>
There should be the basic operations of creating, editing and deleting movies.
<h3>10. Movie Search</h3>
It should allow searching by title, and filtering by genre. Also, allow to sort the results by
creation date in ascending or descending order. <br>
The search term, filter or sort must be specified as query parameters:
<ul>
    <li>
        /movies?name=nombre
    </li>
    <li>
        /movies?genre=idGenero
    </li>
    <li>
        /movies?order=ASC | DESC
    </li>
</ul>
<h3>11. Email Sending</h3>
When registering on the site, the user should receive a welcome email. It is recommended,
use of a third-party service such as SendGrid.
