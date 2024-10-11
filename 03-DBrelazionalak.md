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

### 3.4 Objektu-erlazioal desfasea

Aurreko puntuan ikusi dugunaren alderantzizkoa egitea, hau da, memorian dauzkagun aldagaien datuak datu basean gordetzea, **zailagoa da**. Batez ere, objektuekin lanean gabiltzanean. Objektu konplexuen bilduma batek grafo-egitura du, eta ez da erraza informazio hori errenkadak eta zutabeak dituzten tauletan biltegiratzea. **Zailtasun guzti hauen multzoari objektu-erlazional desfasea deritzo**.

Objektu konplexuak gordetzeko 2 aukera ditugu: bata objektuen datu base bat erabiltzea da eta bestea, **ORM** (objektu-erlazionak korrespondentzia) tresnak erabiltzea.

### 3.5 Java Database Connectivity

Javarako JDBC konektoreen arkitektura *driver*-etan oinarrituta dago eta bere APIa <code>java.sql</code> paketean dago eskuragarri. 

### 3.6 Oinarrizko eragiketak JDBCn

Oinarrizko SQL sententziak exekutatu daitezke. **DML** (*Data Manipulation Language*) eta **DDL** (*Data Definition Language*).

* Kontsultak (<code>SELECT</code>) <code>executeQuery()</code> funtzioaren bidez egiten dira eta <code>ResultSet</code> bat itzultzen du zeinetan lerro bat edo batzuk egongo diren, guk *iteratu* ditzakegunak.

* Gainontzeko DML sententziak (<code>UPDATE, DELETE, INSERT</code>) <code>executeUpdate</code> bidez exekutatzen dira, zeinek eragindako lerro kopurua itzultzen dituen.

* DDL sentzentzuak <code>execute()</code> bidez exekutatzen dira.

Datu-base batekin jarduteko, beharrezko kontsultak eginda, gure aplikazioak honako hau egin beharko du:

1) Datu-base horrek erabiltzen duen protokoloa ulertzeko beharrezkoa den driverra kargatzea.
2) Datu-basearekiko konexioa ezartzea.
3) SQL kontsultak bidali eta emaitza prozesatu.
4) Baliabideak askatzea amaitzean.
5) Gerta daitezkeen akatsak kudeatzea


Honako sententzia-mota hauek erabil ditzakegu:

<code>Statement</code>: SQLko sententzia errazetarako.

<code>PreparedStatement</code>: prestatutako kontsultetarako, adibidez parametroak dituztenetarako.

<code>CallableStatement</code>: datu-basean biltegiratutako prozedurak gauzatzeko.

#### 3.6.1 Konexioak irekitzea eta zarratzea
JDBCren *driver*rak eskuragarri daude <code>.jar</code>ean. <code>DriverManager</code> bidez konexioa ezar daiteke <code>getConnection(String konexio_URL)</code> metodoa erabiz.

<code>konexio_URL</code>ari dagokionez, konexioa ezartzeko beharrezko informazioa eduki behar du. Adibidez MySQL datu base batera konektatzeko, itxura hau izango du: <code>jdbc:mysql:host:portua/DatuBasea</code> non *host* IP bat izan daitekeen edota localhost datu basea geure makinan badago. MySQLn defektuzko portua 3306 da.

*driver*ak konexioa ondo burutzen badu, <code>Connection</code> objektu bat itzultzen du, zein datu basearekin egingo ditugun eragiketa guztiez arduratuko den.

Gogoratu datu basearekin egiten ditugun eragiketek salbuespenak sor ditzatela eta beraz, hauek kudeatu behar ditugula <code>try-catch</code> blokeen bidez. Salbuespen orokorrez aparte, <code>SQLException</code> salbuespenak sor ditzakete.

Ikusi [JDBC konexio adibidea](/adibideak/02-Konektoreak/JDBC_konexioa.java).

#### 3.6.2 Statement interfazea
<code>Statement</code> interfazea edozein motatako SQL sententzia exekutatzeko erabiltzen da. Objektu hau eskuratu daiteke <code>Connection</code> objektuaren <code>getStatement()</code> metodoa erabiliz. 

[Statement-en informazioa gehiago](https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html)

#### 3.6.3 DDL sententzien exekuzioa
**DDL**k (*Data Definition Language*) taulak, bistak eta datu-base erlazional batean egon daitezkeen gainerako objektuak sortzeko, aldatzeko eta ezabatzeko sententziak biltzen ditu. <code>execute()</code> metodoaren bidez exekutatzen da.

#### 3.6.4 Datu-basearen edukiak aldatzeko sententziak
Sententzia hauek <code>executeUpdate()</code> metodoarekin exekutatzen dira, zeinek eragina izan duten lerro kopurua itzuliko duen. <code>INSERT</code>, <code>UPDATE</code> edo <code>DELETE</code> sententzietan erabiltzen da.

[Insert adibidea.](/adibideak/02-Konektoreak/JDBC_insert.java)

#### 3.6.5 Kontsulten exekuzioa eta <code>ResultSet</code>en kudeaketa
<code>SELECT</code> sententziak <code>executeQuery()</code> metodoaren bidez exekutatu daiteke, zeinek <code>ResultSet</code> bat itzuliko du.

Zer da <code>ResultSet</code>a? **Kontsultak bueltatzen dituen lerro multzoa**.

Erakusleak ResultSet-eko lerro bati apuntatzen dio. **Hasieran lehen lerroaren aurrean kokatzen da**, hau da, oraindik ez dago benetako datuei erreferentziatzen. ResultSet hori rekorritu beharko da, <code>.next()</code> bezalako metodoak erabiliz.

| Metodoa  | Funtzionalitatea   |
| ------- | -------- |
| `boolean next()`   | <code>ResultSet</code>eko erakuslea hurrengo erregistrora mugitzen du. **<code>true</code> itzuliko du baldin eta hurrengo lerroa existitzen bada.**    |
| `getXXX()`   | Zutabeko edukia eskuratzen du. adib: <code>.getInt()</code>, <code>.getString()</code>, <code>.getDate()</code>, zutabeko datu motaren arabera.    |
| `close()` | <code>ResultSet</code> zarratzeko. Beti itxi beharko litzateke gehiago erabiliko ez denean. |

Badago beste mota batetako <code>ResultSet</code>a sortzeko aukera, zeinek lerroen arteko mugimendua edo desplazamendu malguago bat eskeintzen duena. Hauek ***Scrollable* ResultSet**ak dira. Erabiltzeko, <code>Statement</code>a sortzean parametro batzuk pasatu behar zaizkio, esanez *scrollable* bezalako ResultSeta nahi dugula. Berdina gertatzen da <code>PreparedStatement</code>ekin.

```java
Statement createStatement(int mota, int konkurrentzia)
PreparedStatement prepareStatement(String sql, int mota, int konkurrentzia)
```

<code>mota</code> parametroaren balioak hauek izan daitezke:

* <code>ResultSet.TYPE_FORDWARD_ONLY</code>: Defektuzko balioa da, aurrerantz (edo next()) irakurtzeko. 
* <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>: *scrollable* motako ResultSeta sortzen du, zeinetan ez diren islatzen datu basean beste prozesu batzuk egindako aldaketak. Hau da, irakurketa egindako momentuko balioak soilik izango ditu.
* <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>: *scrollable* motako ResultSeta sortzen du eta beste prozesu batzuk datu basean egindako aldaketak islatzen ditu (datu eguneratuak). Datu base kudeatzaile batzuetan <code>ResultSet.refreshRow()</code> exekutatu behar da aldaketak benetan ikusteko.

*scrollable* motako ResultSeta nahi badugu, adierazi behar dugu ea ResultSet horretan **egingo ditugun aldaketak datu basean gordeko ditugun ala ez**. Hau <code>konkurrentzia</code> parametroan adierazi behar da.
* <code>ResultSet.CONCUR_READ_ONLY</code>: soilik irakurketarako
* <code>ResultSet.CONCUR_UPDATABLE</code>: eguneraketak burutzeko

Beste metodo hauek ere erabilgarri daude <code>ResultSet</code> ***scrollable*** erabiltzen denean:
| Metodoa   | Funtzionalitatea   |
| ------- | --- |
| `boolean previous()` `boolean first()` <br> `boolean last()` <br>`void beforeFirst()` <br> `void afterLast()` <br> `boolean absolute(int pos)` <br> `boolean isFirst()` <br>`boolean isLast()`<br>`boolean isBeforeFirst()`<br>`boolean isAfterLast()`<br>`int getRow()`| Erakuslea tokiz mugitzen duten metodoak eta erakuslearen kokapenaren informazioa bueltatzen duten metodoak. Adibidez,  <code>.next()</code> metodoarekin hurrengo lerrora mugitzen den modura,  <code>.previous()</code> metodoarekin aurrekora mugitzen da. <code>.absolute(int pos)</code> metodoarekin emandako posizio kopurua mugitzen da erakuslea kokatuta dagoen tokitik (positiboa ala negatiboa izan daiteke). <code>.getRow()</code> metodoarekin, erakuslea kokatuta dagoen lerro zenbakia itzuliko du. |
| `getXXX()`    | Zutabeko edukia eskuratzen du. adib: <code>.getInt()</code>, <code>.getString()</code>, <code>.getDate()</code>, zutabeko datu motaren arabera.    |
| `close()` | <code>ResultSet</code> zarratzeko. |


### 3.7 Sententzia prestatutak

Aurreko adibideetako SQL sententziak konstanteen bidez erabili dira, baina sarritan **erabiltzaileari teklatutik balioren bat** sartzeko eskatzen zaio. Ez dakigu erabiltzaileak sartutako hori SQL kodea izan daiteken (*SQL injection* eginez), komatxoak edo komatxo bikoitzak dituen... beraz kontu handia izan behar dugu honekin.

Orain arteko SQL sententziek honelako itxura izan zezaketeen:
```java
String cons = "SELECT * FROM erabiltzaileak WHERE DNI='"+dni+"'";
```
Honek izugarrizko **arazoak** eman ditzake.

1) **Segurtasunean**: <code>dni</code> hori erabiltzaileak teklatutik sartu badu, bere balioa SQL kode zati bat izan daiteke, eta gure kodean SQL injection egin. Adibidez balioa <code>a' or '1=1</code> bada, gure SQL sententzia honela geratuko litzateke:
```java
String cons = "SELECT * FROM erabiltzaileak WHERE DNI='a' or '1=1'";
```
Aurreko SQL sententziak erabiltzaile **guztiak** itzuliko lituzke eta ez soilik DNI konkretu batena. Adibide honetan SELECT egiten gabiltza, baina berdina gertatuko litzateke UPDATE eta INSERT kasuetan.

2) **Errendimenduan**: Datu-baseak kudeatzeko sistemara bidaltzen den kontsulta bat konpilatu egiten da exekutatu aurretik, hau da, aztertu egiten da eta horretarako gauzatze-plan bat sortzen da. Kontsulta baten eta bestearen artean aldagai batzuen balioa bakarrik aldatzen bada, kontsulta bakoitza konpilatuko da, beti gauzatze-plan bera lortzeko.

**Sententzia prestatuek arazo hauek ekiditen dituzte**.

<code>PreparedStatement</code> bidez egiten dira sententzia prestatuak. SQL sententzia ematen zaio parametro gisa eta "**?**" karakterea erabiltzen da markagailu (*placeholder*) gisa. Markagailu horietan jarriko ditugu gure balioak.

<code>PreparedStatement</code> dituen metodoak eta <code>Statement</code>-ek ez.
| Metodoa   | Funtzionalitatea   |
| ------- | --- |
| `ResultSet executeQuery()` <br> `int executeUpdate()` <br> `boolean execute()`| Hiru metodo hauek ez dite SQL kontsulta parametroan, eraikitzailean jarrita dagoelako. |
| `setXXX(int pos, YYYY balioa)`    | *placeholder* edo markagailu bati balioa ezartzeko. Posizioa "?"-ren posizioa da eta balioa zein balio eduki beharko luken. |
| `setNull(int post, int SQLmota)` | Zutabe bati NULL balioa ezartzen dio. Mota adierazi behar da, motak <code>java.sql.Types</code>-n daude ezarrita. |

Aurreko adibidea honela geratuko litzateke prestatutako sententzia erabiliz:

```java
String sql_sententzia = "SELECT * FROM erabiltzaileak WHERE DNI=?";
PreparedStatement ps_select = conexion.prepareStatement(sql_sententzia);
ps_select.setString(1, dni);
ResultSet rs = ps_select.executeQuery();
```
Kontutan izan DNI-ren markagailua ez dela komatxo bikoitzen artean jarri, nahiz eta testu motakoa izan. Gero <code>.setString(x,x)</code> egitean esaten diogu testu motakoa dela eta berak jarriko ditu komatxoak behar izanez gero.

### 3.8 Transakzioak

Hainbat SQL sententzia blokean exekutatu nahi ditugunean, Transakzioak erabili behar ditugu. Honek ahalbideratuko ditu **bloke guztiko sententziak exekutatzea, ala bat ere ez**.

```
SAIATU:
    START TRANSACTION
    sententzia 1
    sententzia 2
    ...
    sententzia n
    COMMIT
ERROREREN BAT EGON BADA:
    ROLLBACK
```

Transakzio bat <code>rollback</code> bidez abortatu daiteke, eta aldaketaren bat egin bada desegingo luke.

Transakzioak egiteko metodoak:
| Metodoa   | Funtzionalitatea   |
| ------- | --- |
| `vois setAutoCommit(boolean autoCommit)`| <code>autoCommit=false</code> jarrita Transakzio bat hasiko dugu. |
| `void commit()`    | Transakzio bukaera ezartzen du. Transakzio barruko sententziak datu basean islatuta geratuko dira. |
| `void rollback()` | Transakzio barruan egindako sententzia guztiak desegingo ditu. Transakzio barruko sententziaren batek errorea (*SQLException*) ematen duenean deitu beharko litzateke. |

Ikusi [transakzioen adibide bat](/klaseko_ariketak/2-Konektoreak/transakzioak.java). Adibide konplexuago bat ikusteko, 2 datu baseren arteko transakzio bat dago [adibide honetan](/klaseko_ariketak/2-Konektoreak/kutxazaina/main.java).

### 3.9 Gako autogeneratuen balioak
Sarritan taula bateko identifikatzaileak (gakoak) zenbaki autoinkrementalak dira eta Datu Base Kudeatzaileari uzten zaio kudeaketa hau (automatikoki ematen dio balio bat). Baina sarritan interesgarria izango da <code>INSERT</code> bat egitean gakoari ze balio eman dion jakitea, horretarako <code>Statement</code> eta <code>PreparedStatement</code>ek aukera bat eskeintzen dute.
| Metodoa   | Funtzionalitatea   |
| ------- | --- |
| `int executeUpdate(String sql, int autoGeneratedKeys)`| <code>autoGeneratedKeys</code> ek balio hauek har ditzake: <br>-`Statement.RETURN_GENERATED_KEYS` <br> -`Statement.NO_GENERATED_KEYS`<br> Lehenengoarekin automatikoki sortutako gakoa jaso daiteke, normalean bakarra izaten da. Metodo hau ezin daiteke `PreparedStatement`ekin erabili, Connection batekin sortzen delako.|
| `ResultSet getGeneratedKeys()` | `ResultSet` bat itzultzen du sortutako gako autogeneratuekin. Normalean bakarra itzuliko du. `PreparedStatement`ekin erabil daiteke. |

`Connection`-en metodoak gako autogeneratuekin erabili daitezkenak:
| Metodoa   | Funtzionalitatea   |
| ------- | --- |
| `PreparedStatement prepareStatement(String sql, int autoGeneratedKeys`| Sententzia bat prestatzen du. Gako autogeneratuen balioak eskuratzeko balioa ezarri behar da `PreparedStatement.RETURN_GENERATED_KEYS`|
| `ResultSet getGeneratedKeys()` | `ResultSet` bat itzultzen du sortutako gako autogeneratuekin. Normalean bakarra itzuliko du. `PreparedStatement`ekin erabil daiteke. |

Hemen [gako autogeneratuen adibide bat](/klaseko_ariketak/2-Konektoreak/gako_autogeneratuak.java).

### 3.10 Biltegiratutako prozeduretarako eta funtzioetarako deiak

Batzuetan datu base kudeatzailean prozedurak sortzea interesatzen da. JDBC erabiliz prozedura horiei deitu daiteke. Adibidez, datu base kudeatzailean prozedura hau daukagu:

```SQL
CREATE DEFINER=`root`@`%` PROCEDURE `get_euro_pertsona`(IN in_izena VARCHAR(10), OUT out_kantitatea DOUBLE)
BEGIN
DECLARE kant DOUBLE;
SELECT kantitatea FROM euroak WHERE izena=in_izena INTO kant;
    SET out_kantitatea = kant;
END
```

Prozedura horri `get_euro_pertsona(in izena, out kantitatea)` bezala dei diezaiokegu. Horretarako `CallableStatement`-en `prepareCall` metodoa erabiliko dugu.

|    | Parametroekin   | Parametro gabe|
| ------- | --- |--- |
| Prozedura| `{ call prozedura(?,?...) }` | `{call  prozedura }`|
| Funtzioa| `{ ? = call funtzioa(?,?...) }` | `{call  funtzioa }`|

`CallableStatement`en metodoak:
| Metodoa   | Funtzionalitatea   |
| ------- | --- |
| `setXXX(int pos, YYYY balioa)`|  `PreparedStatement`en modura.|
| `void registerOutParameter(int pos, int sqlType` | Deiak itzuliko (*out* balioa) duen balio mota zehazten da.  |
| `getXXX(int pos)` | Irteera balio bat hartzen du. |
| `ResultSet getResultSet()` | `ResultSet` bat jasotzen du prozedura edo funtzioaren emaitza gisa. |

[Prepared Call adibidea](/klaseko_ariketak/2-Konektoreak/prepareCall_procedura.java).

### 3.11 Kontsulta baten emaitzen eguneraketak
Beste modu batera esanda, `ResultSet` eguneragarriak dira.

**`PreparedStatement`** eta **`Statement`** eguneragarriak eskuratzeko **`Connection`**-en metodoak:

```java
Statement createStatement(int mota, int konkurrentzia)
PreparedStatement prepareStatement(String sql, int mota, int konkurrentzia)
```

<code>mota</code> parametroak `ResultSet` *scrollable* izatea ahalbideratzen du aurreko punturen batean ikusi dugun bezala. Baina orain konkurrentziaren balioei erreparatuko diegu:

* `ResultSet.CONCUR_READ_ONLY`: Irakurketak egiteko soilik
* `ResultSet.CONCUR_UPDATABLE`: ResultSet-eko balioak eguneratzeko eta datu basean gordetzeko.

Ikusi [dokumentazio ofiziala](https://docs.oracle.com/javadb/10.6.2.1/devguide/cdevconcepts28351.html).

Hemen [klasean ikusitako adibide bat](/klaseko_ariketak/2-Konektoreak/resultset_updatable.java).