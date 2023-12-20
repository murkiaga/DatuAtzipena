# Spring Boot


## X JPA repository

Dokumentu honetan, Spring Boot-en JPA erabiliz `JpaRepository` interfazea nola erabiliko den ikusiko dugu.

`JpaRepository` Spring Data JPA-ren interfazea da, eta hainbat aukera eskaintzen ditu datu-baseen kudeaketa errazteko. 

### Funtzio estandarrak
JPA Repository interfazeak jada ohiko funtzio batzuk inplementatuak ditu eta guk erabil al izango ditugu. Adibidez:

* ```getReferenceById(ID)```
* ```save(S entity)```
* ```delete(T entity)```
* [Ikusi guztiak hemen](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html)

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface IkasleaRepository extends JpaRepository<Ikaslea, Integer> {
    // Ikasleentzako berezko kontsultak gehitu hemen
}
``` 

### Funtzio gehigarriak
Beste funtzio batzuk ere definitu ditzakegu, funtzioaren izenean hitz gakoak erabiliz JpaRepository-k interpretatzen jakingo dituenak, eta guk gehiagorik garatu gabe funtzionatuko dutenak. 

Demagun **Ikaslea** modeloa daukagula eta honen atributu bi *Izena* eta *Abizena* direla. Ikasle bat izena edo abizenagatik bilatu nahi badugu, ```findBy``` erabiliko dugu funtzioaren izenean. Adibidez:

```java
public interface IkasleaRepository extends JpaRepository<Ikaslea, Integer> {

    List<Ikaslea> findByIzena(String izena);

}
``` 
Gehiago inplementatu gabe, ```findByIzena``` funtzioa erabili al izango dugu eta honek Ikasle zerrenda bat itzuliko du.

Existitzen diren hitz gakoen artean, hauek aurki ditzakegu:
|  Hitz gakoa   | Adibidea|
| --- |--- |
| ```And```| ```List<Ikaslea> findByIzenaAndAbizena(String izena, String abizena);```|
| ```Or```| ```List<Ikaslea> findByIzenaOrAbizena(String izena, String abizena);```|
|```Containing```| ```List<Ikaslea> findByIzenaContainingOrAbizenaContaining(String izena, String abizena);```|
|```IgnoreCase``` | ```List<Ikaslea> findByIzenaIgnoreCase(String izena)``` |
|```Between```| ```List<Ikaslea> findByIzenaOrAbizena(String izena, String abizena);```|
|```Like``` | ```List<Ikaslea> findByIzenaLike(String hitza);```|
|```OrderBy```| ```List<Ikaslea> findByIzenaOrderByAbizenaAsc(String izena);``` |
|```IsNull / IsNotNull```| ```List<Ikaslea> findByTelefonoIsNull();```|
|```In``` | ```List<Ikaslea> findByAdinaIn(List<Integer> adinaZerrenda); ``` |
|```Not```| ``` List<Ikaslea> findByAdinaNot(int adina); ```|

[Ikusi dokumentazioa.](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)




