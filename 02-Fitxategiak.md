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