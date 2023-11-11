# 4. Mapaketa Objektu-Erlazioanala (ORM)

ORM tresnek objektuak datu base erlazionaletan gordetzea ahalbideratzen dute. Objektuei orientatutako programazio (OOP) lengoaia batean programatzen badugu, Javaz esaterako, objektuen instantziak erabiliko dugu. Maneiatzen ditugun objektu hauek datu base erlazional batean gorde behar badira, objektuen konplexutasunaren arabehera arazoak izan ditzakegu. Nola gorde objektu bat beste objektu baten instantzien zerrenda bat daukanean, eta bigarren objektu honek bere barnean hainbat atributu multibaluatu baditu? **Ondo pentsatu beharko genuke zeintzuk INSERT egin behar diren, eta ze ordenatan integritate murriztapenak betetzeko.**

**ORM tresnek** abstrakzio kapa bat gehitzen dute eta aipatutako arazoaren konponbide dira. Taula erlazionaletan pentsatu beharrik izan gabe gorde ditzakegu objektuak, **bera arduratuko da eta taulen kudeaketaz**.

## 4.1 Hibernate ORM
Unitate hau lantzeko, [**Eclipse**](https://www.eclipse.org)n [**Maven**](https://maven.apache.org/) proiektuak erabiliko ditugu, dependentzien kudeaketa errazagoa egiteko. Dependentziak **pom.xml** fitxategian jarriko ditugu eta berak automatikoki deskargatuko ditu dagozkien *.jar* fitxategiak. Dependentzia eguneratuak bilatzeko, [MVN Repository](https://mvnrepository.com/).


### 4.1.1 Dependentziak

Gure proiektuetan, gutxienez 2 dependentzia hauek gehituko ditugu (Hibernate ORM + MySQL connector):

```xml
<dependencies>
    <dependency>
  		<groupId>org.hibernate</groupId>
  		<artifactId>hibernate-core</artifactId>
  		<version>5.4.24.Final</version>
  	</dependency>
  	<dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.22</version>
    </dependency>
</dependencies>
```

### 4.1.2 Hibernate eta JPA (Java Persistence API)