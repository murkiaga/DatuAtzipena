# 2. Fitxategiak

**Helburuak**:
- Testu fitxategi eta fitxategien bitarren kudeaketen ezberdintasunak ezagutzea.
- Fitxategietarako sarbide sekuentziala eta ausazkoa bereiztea
- Fitxategiak erabiltzeko Java klase nagusiak ikastea 
- Buffering-mekanismoa ulertzea, irakurketa eta idazketa eragiketak bizkortzeko.

<img src="img/02-fitxategiak/MapaKontzeptuala2.jpg" alt= "Fitxategien Mapa kontzeptuala" width="700px">

### 2.1 Datuen iraunkortasuna fitxategietan


Fitxategiak dira informazioa biltegiratzeko metodorik oinarrizkoena. Izan ere, **azken batean, biltegiratzeko metodo guztiek**, nahiz eta sofistikatuak izan, **datuak fitxategietan gordetzen dituzte**.

Datu base erlazionalak agertu zirenera arte (80. hamarkada), fitxategiak ziren datuak gordetzeko baliabide erabilienak. Gaur egun, fitxategietan gordetzen diren datuak *XML, JSON, YAML*, ... formatukoak dira. *CSV* formatuko fitxategiak ere erabiltzen dira, besteak beste exportazio-inportazio sinpleak egiteko.

### 2.2 Fitxategi motak edukiaren arabera

Fitxategi bat *byte*-sekuentzia bat besterik ez da, eta, beraz, hasiera batean edozein informazio mota gorde dezake.

Fitxategi bat bere izenagatik eta kokapenagatik (fitxategi hierarkia baten) identifikatzen da.

Bi talde nagusi bereizten ditugu:
* **Testu fitxategiak**: karaktere-sekuentzia bat baino ez dute. Horiek ageriko karaktereak izan daitezke, hala nola letrak edo zenbakiak eta baita espazioak eta bereizgailuak ere, hala nola tabuladoreak eta lerro jauziak. Edozein testu editorekin ireki/editatu daitezke (notepad, nano, ...)

* **Fitxategi bitarrak**: gainontzeko fitxategiak dira. Normalean, programa bereziak behar dira bertako edukia bistaratzeko.

### 2.3 Testu kodifikatzaileak
Testu berdina, kodifikazioaren arabera, byte sekuentzia bat edo bestez errepresentatu daiteke. Gaur egun **Unicode** edo **UTF-8** kodifikazioak erabiltzea gomendatzen da.

UTF-8 bateragarria da ASCII kodearekin; horrek esan nahi du ASCIIn kodetutako edozein testu berdin-berdin adierazten dela UTF-8n. Hori funtsezko arrazoia izan da UTF-8 oro har hartzeko.

### 2.4 Javako File klasea

Fitxategiekin lan egitea ahalbidetzen duten klaseak <code>java.io</code> paketean daude.

Dokumentazioa (SE8): http://docs.oracle.com/javase/8/docs/api

<code>File</code> klaseak direktorio eta fitxategiei buruzko informazioa eskuratzea ahalbideratzen du, fitxategi sistema baten barruan. [File klase erabilera adibidea](/adibideak/01-Fitxategiak/ListadoDirectorio.java).

Javaz egindako edozein programatan komando lerro bidez aldagaiak pasatu daitezke, <code>String args[]</code> aldagaiak jasotzen ditu sarrera parametro horiek.

> Proposatutako Ariketa: aurreko adibideko programa aldatu, fitxategiei buruzko informazio gehiago erakus dezan: fitxategien tamaina, fitxategi eta direktorioen baimenak eta azken aldatze data.

### 2.5 Salbuespenak (*exception*) kudeatzea javan

Salbuespena da programa bat gauzatzean gertatzen den gertaera bat, programa gauzatzeko ohiko bidea eteten duena. Adibidez, zenbaki bat zati 0 egitean.

<code>Exception in thread "main" java.lang.ArithmeticException: / by zero</code> errorea itzuliko luke.

#### 2.5.1 Salbuespenak atzematea eta kudeatzea

Salbuespen bat dagoenean **kudeatu gabe**, programaren exekuzioa bertan behera uzten da. Salbuespenen bat ematen duten kode zati guztiak kudeatuak egon beharko lirateke.

[Salbuespen adibidea](adibideak/01-Fitxategiak/ZatiZeroSalbuespena.java), zati zero egitean.

> Proposatutako ariketa: Nola aldatuko litzateke aurreko programa, try/catch bloke asko egon beharrean bakarra egongo balitz 3 eragiketetarako?

#### 2.5.2 Salbuespen ezberdinen kudeaketa

<code>try{} catch{}</code> bloke batean salbuespen mota ezberdinak kudeatu daitezke.

>> Kontuan izan, praktikan *loggin* edo logak gordetzen dira programaren nondik norakoak gordetzeko (error, warning, info eta debug logak).

Salbuespen ezberdinen kudeaketa [adibidea](adibideak/01-Fitxategiak/SalbuespenDesberdinak.java).

#### 2.5.3 Klase bateko metodoan ezarritako salbuespenak

Konpilatzaileak ikusten badu klase bateko metodo batek errorea emon dezakela eta ez dagoela <code>catch(){}</code> bloke batekin kudeatua, **konpilazioa errorez bukatu**ko da.

Metodo bat throw salbuespenarekin [adibidea](adibideak/01-Fitxategiak/SalbuespenakThrowrekin.java).

#### 2.5.4 Salbuespenak, hasieratzea eta baliabideak askatzea: <code>finally</code> eta <code>try</code> blokeak baliabideekin

Java programa baten egitura orokorra honakoa da:

```
Hasieratzea eta baliabide esleipena
Gorputza
Bukaera eta baliabide askapena
```
**Lehenengo eta bukaerako zatiak beti exekutatu behar dira**, naiz eta gorputzean erroreak edo salbuespenak gertatu. 

Sarritan, gorputzeko baldintzaren bat betetzen bada exekuzioa bukatu nahi izaten da <code>return, break, continue</code> batekin adibidez. Baina hau eginda, ez da bukaerako kode zatia exekutatuko. Baina bukaera atala <code>finally {}</code> bloke barruan sartzen bada, funtziotik/programatik irten aurretik beti exekutatuko da, naiz eta aurretik return bat egin.

Hemen finally erabiltzen duen programa baten [adibide](adibideak/01-Fitxategiak/ExcepcionesConFinally.java) bat.