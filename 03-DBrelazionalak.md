# 3. Datu Base Relazionalak

**Helburuak**:
- Konektoreek ematen dituzten **API**ak ezagutzea, batez ere **JDBC**renak (*Java Database Connectivity*) datu base erlazionaletarako.
- Datu base batera konekzioa irekitzea JDBCrekin bere *driver*rak erabiliz.
- Java programak garatzea JDBC erabiliz SQL sententziak exekutatzeko. Datuen definizioa (**DDL**) eta datuan modifikazio eta kontsultarako (**DML**).
- Trantsakzioekin lan egitea

### 3.1 Konektoreak
Konektorea API bat da, aplikazio-programei datu-baseekin lan egiteko aukera ematen diena. 

Hainbat motatako datu-baseak kudeatzeko sistemek (DBKS) beren lengoaia espezializatuak dituzte biltegiratzen dituzten datuekin jarduteko. Aldiz, aplikazio-programak helburu orokorreko programazio-lengoaiekin idazten dira, adibidez, Javaz. Aplikazio-programek eta DBKS elkarreragin ahal izateko, mekanismoak behar dira, aplikazio-programek lengoaia horietan datu-baseekin komunikatzeko aukera izan dezaten. Horiek APIan inplementatzen dira eta ***konektore*** deitzen dira.

<img src="img/03-dbrelazionala/konektoreAPIa.png" alt= "Konektorea programa eta DBKS bitartekari" width="700px">

### 3.2 Datu base erlazionaletarako konektoreak

SQL lengoaia estandarra da. Baina datu-base erlazional desberdin asko daude, eta bakoitzak bere SQL bertsioa du, bere berezitasunekin.

*Driver*rak erabiliz arkitektura generiko bat gara daiteke, non konektoreak intefaze komun bat duen aplikazioetarako zein datu-base desberdinetarako, eta driverrak datu-base desberdinen berezitasunez arduratzen dira.

<img src="img/03-dbrelazionala/konektoreDriverrak.png" alt= "Konektorea programa eta DBKS bitartekari" width="700px">

Horrela, konektorea ez da API sinple bat bakarrik, arkitektura bat baizik, *driver* desberdinek datu-base partikularretara sartzeko inplementatu behar dituzten interfaze batzuk zehazten dituelako. Lehenengoa ODBC (Open DataBase Connectivity) izan zen, Microsoftek 90. hamarkadaren hasieran garatua.

Datu-base garrantzitsu guztiek ODBCren eta JDBCren driverrak eskaintzen dituzte gaur egun.

Driverretan oinarritutako konektoreek ematen duten abantailek (batez ere, datu basearekiko independentzia), konplexutasuna dakar.

### 3.3 Datu-base erlazionaletako kontsulten emaitzetarako sarbidea, konektoreen bidez

Konektoreek datu-base erlazional baten gainean era guztietako eragiketak egiteko aukera ematen dute. Atal honetan kontsultei erreparatuko diegu.

Datu-base erlazionaletan, informazioaren biltegiratzea **taula** da. Taula bakoitzak **zutabe-multzo finko bat** du, **datu mota jakin batekin**. SQL kontsulta batek lerroak edo *recordset* multzo bat itzultzen du. Konektoreek aukera ematen dute emaitza horiek lerroz lerro berreskuratzeko, *iteratzaile* edo kurtsore gisa jarduten duen objektu baten bidez.

```java
Connection c = getConnection(konexio datuak);
Statement s = c.createStatement();
ResultSet rs = s.executeQuery("SELECT...");
while(rs.next()) { //rs-n kontsultako emaitza gehiago daude
    String datu1 = rs.getString(1); //Lehen zutabeko Stringa hartu
    int datu2 = rs.getInt(2); //Bigarren zutabeko integerra hartu
}
s.close();
c.close();
```









