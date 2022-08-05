# pressApi
**RestApi dla artykułów prasowych**
***
**aplikacja uruchamia sie na http://localhost:8080/article**

*POST http://localhost:8080/article -> tworzymy artykuł*

*GET http://localhost:8080/article/{id} -> pobieramy artykuł o określonym ID*

*GET http://localhost:8080/article/sortDate -> pobieramy listę artykułów posortowaną po dacie malejąco*

*GET http://localhost:8080/article/search?keyWord={słowo kluczowe} -> pobieramy listę artykułów zawierających słowo kluczowe w tytule lub w treści*

*PATCH http://localhost:8080/article -> aktualizujemy artykuł*

*DELETE http://localhost:8080/article/{id} -> usuwamy artykół o określonym ID*
