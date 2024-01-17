# 5. Spring Boot

## 5.1 Spring Boot + Thymeleaf
[Spring webgune ofiziala](https://spring.io/).

Spring boot erabiliko dugu, horretarako, webgune ofizialetik "*Projects*"->"*Spring tools 4*" deskargatuko dugu. Eclipse IDEa da Spring Booterako prestatuta. *Spring Boot*ek **Java Web aplikazio** bat sortzea ahalbideratuko digu MVC modeloa erabiliz (*Movel View Controller*).

**Dokumentazio honetan ezinezkoa da *Spring Boot*i buruzko teoria guztia jartzea, kasuistika asko dagoelako.**

## 5.2 Model
New -> "Spring Starter Project" batekin hasiko gara. Pakete berri bat sortuko dugu gure modeloentzako. [Student modeloaren adibidea](/klaseko_ariketak/4-SpringBoot/university/src/main/java/com/university/app/model/Student.java).

## 5.3 Controller

Hau da "negozio logika". Beste pakete batean egituratuko ditugu kontrolatzaile guztiak. Webgune bat denez, *controller*reko funtzio bakoitza URL batekin mapeatu (lotu) beharko dugu. Adibidez, *localhost:8080/* URLa kudeatuko duen kontrolatzailea hau izan daiteke:
```java
package com.university.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping({"/", ""})
	public String index(){
		return "index";
	}
}
```

<code>return "index"</code> horrek esan nahi du, **src/main/resources/templates** barruan index.html fitxategia existitzen dela, eta horra bideratzen duela eskaera.

## 5.4 View (Thymeleaf)

HTML txantiloietan [Thymeleaf](https://www.thymeleaf.org/) erabiliko dugu kontrolatzailetik jasotako datuak kudeatzeko.

## 5.5 JPA repository

Dokumentu honetan, Spring Boot-en JPA erabiliz `JpaRepository` interfazea nola erabiliko den ikusiko dugu.

`JpaRepository` Spring Data JPA-ren interfazea da, eta hainbat aukera eskaintzen ditu datu-baseen kudeaketa errazteko. 

```java
package com.university.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.university.app.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String name, String lastname);
}
```

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




