JAZ : PROJEKT ZALICZENIOWY  

 

Termin: do 23 czerwca do godziny 10:45 

Max. punktów: 50 

 

 

Stwórz trzy moduły: 

Moduł „api-gen” generujący API z specyfikacji OpenApi przy pomocy pluginu OpenApiTools. Następne moduły powinny podpiąć api-gen poprzez pom.xml 

(skorzystanie z Swagger Editor -5 pkt) 

 

Moduł „bookshop”, który umożliwia wyświetlenie, filtrowanie oraz kupienie książki przez zalogowanego użytkownika.  

Uprawnienia do dodania/edycji/usunięcia książki powinien mieć administrator. 

(brak uprawnień -5 pkt) 

 

Książka powinna zawierać: 

Autora, Gatunek, Cenę, Ilość stron, licznik odwiedzin, oraz informację czy jest dostępna (ile sztuk). 

 

Autor powinien być osobną klasą, encją bazodanową. 

Książka, po jej wyszukaniu powinna inkrementować ilość odwiedzin. 

 

Zaimplementuj metodę przy użyciu biblioteki feign, aby komunikować się z trzecim modułem. (oraz endpoint dla administratora /order-report który ją wywoła)  

 

Moduł „book-order” , który przyjmuje listę obiektów {id książki, nazwa, ilość odwiedzin}. Każde 10 odwiedzin danej książki to jedna książka którą należy zamówić do magazynu. W bazie danych trzymaj jedynie encję {id książki, ilość do zamówienia}. 

Dodaj endpoint „/print” który stworzy plik .pdf z zamówieniem. 

        (nie zwracanie pliku, a jedynie informacji -5 pkt) 

 

Zadbaj o to by w każdym module była walidacja oraz obsługa błędów wraz z aspektowym ich przechwytywaniem.  

(Brak  -10 pkt) 

 

Przetestuj aplikacje z użyciem testów jednostkowych. 

(Brak -5pkt) 

 

 

 
