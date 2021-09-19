# Global-Movie-Database

<p align="justify">A dynamic website for a movie store where customers can purchase and rate movies an admin user maintains on a database.</p> 

<p align="justify">This was initially a project assigned for a subject under the topic of Object Relational Persistence which I decided to improve upon to learn the basics of the Spring Framework. I created a few UML diagrams to help understand the entities persisted in the database as well as the requirements of the website.</p>

<p align="justify">The project was developed using Spring MVC with Hibernate as persistence provider and a MySQL database. Using Spring MVC without Spring Security meant having 
to use lots of additional code for login, logout and managing user session, but I didn't want to use it or Spring Boot since I wanted to stick to the basics. I used Java and annotation based configuration as much as possible, but since error handling still has no annotation based configuration option I had to configure the web.xml file.</p>

<p align="justify">Designing the database to be more efficient when storing and retrieving data was an important task, especially since many entities had multiple many-to-many 
relationships with other entities. This could be a severe issue when fetching data, especially if a Lazy loading strategy for fetching entities was not used. When working on the project a few articles written by Vlad Mihalcea on his <a href="https://vladmihalcea.com/hibernate-multiplebagfetchexception/">website</a> helped me understand and learn some important and less-known concepts on Hibernate, SQL querying and database design. I'd recommend anyone trying to design an efficent database platform using Java to go through his interesting articles.</p>

<p align="justify">When developing the EAO classes for the project I decided to use the Criteria API to write the SQL queries since it was an approach I hadn't tried before. While
using the Criteria API does make writing queries more safe and less prone to errors, the syntax is a bit counter intuitive for beginners and there aren't many examples on the 
internet which use this approach. This meant I had to go through quite a bit of reading to figure out how to write some queries and conditions.</p>

<p align="justify">While I didn't have enough time to put much effort on designing the front-end of the website, I hope to replace the existing views in the future with something 
more modern and palatable than plain JSPs.</p>

## UML Diagrams

### ER diagram
<img src="https://github.com/asitha-navaratne/Global-Movie-Database/blob/main/GlobalMovieDatabase/UML/gmdb-er.drawio.png" alt="ER diagram" width="500" />

### Use-case diagram for User
<img src="https://github.com/asitha-navaratne/Global-Movie-Database/blob/main/GlobalMovieDatabase/UML/gmdb-user-ucd.png" alt="Use-case diagram for User" width="500" />

### Use-case diagram for Admin
<img src="https://github.com/asitha-navaratne/Global-Movie-Database/blob/main/GlobalMovieDatabase/UML/gmdb-admin-ucd.png" alt="Use-case diagram for Admin" width="500" />
