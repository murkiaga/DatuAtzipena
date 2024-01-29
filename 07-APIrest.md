# 7. API REST


## 7.1 Sarrera

API edo "*Application Programming Interface*" (Aplikazio Programazio Interfazea) informatikan erabilera berezia duten **programen artean komunikatzeko** erabiliko den interfazea da. API-ak aplikazio batzuk beste aplikazioekin komunikatzeko modu bat ematen du, hau da, informazioa edo funtzionalitatea partekatzen dute. API-ak erabilera estandarrean, programazio lengoaietan (Java, Python, Ruby, etab.) idatzita daude eta web zerbitzuak, sistema eragileak edo aplikazio berriak bezalako erakundeak eskuragarri dauden funtzioak eskaintzen dituzte.

API-ak hainbat erabilera izan ditzakete, baina hainbat mota desberdinak izan ditzakete ere. Hemen dituzun batzuk:

- **Web API**: HTTP edo HTTPS protokoloa erabiliz web zerbitzuetatik informazioa eskuratzeko edo bidaltzeko erabil daitezkeen API-ak dira. RESTful edo GraphQL protokoloak erabil daitezke web API-en diseinuan.

- **Library API**: Programazio lengoaiak erabiliz, programatzaileek liburutegi batzuk erabil ditzaten moduan interfazea eskaintzen dutenak.

- **Operating System API**: Sistema eragilearen funtzioak erabiltzen dituzten aplikazioekin komunikatzeko erabil daitezkeen interfazeak

- **Database API**: Datu-base sistema batetik informazioa eskuratzeko edo gordetzeko erabiltzen diren interfazeak.

## 7.2 API REST
API REST edo *Representational State Transfer* API, **web zerbitzuetan erabiltzeko diseinu estandarra da. Hau da, web aplikazioek elkarrekin komunikatzeko erabil daitekeen interfazea. REST protokoloak HTTP edo HTTPS erabiliz lan egiten du, eta hainbat aplikazioekin komunikazioa erraztu egiten du.

<img src="img/07-APIrest/api-rest-model.png" alt= "API-rest model" width="700px">

- **Errepresentazioa** (Representation): REST-ek errepresentazio bat bidaltzen du, hau da, informazioa JSON, XML edo beste formatu batzuetan. Komunikazioa erabilgarri eta zuzena egiten du, eta formatu horiek erabilgarriak dira hainbat programazio lengoaietan.

- **Egoera** (Stateless): REST-ek egoeraik gabeko sistema bat eraikitzen du. Eskaerek, aurretik egindako eskaerak edo egoerak ez ditu gogoratzen.

- **Baliabideen Kudeaketa** (Resource Management): REST-ek baliabideak kudeatzea errazten du.

- **HTTP Metodoak**: REST-ek HTTP metodo estandarrak erabiliz funtzionatzen du. Hauek dira gehien erabilitakoak:

    * GET: Informazioa eskuratzea.
    * POST: Baliabide berria sortzea.
    * PUT: Baliabide bat edo gehiago eguneratzea.
    * DELETE: Baliabide bat ezabatzea.

Eskaera jakin bat egiten denean, oso garrantzitsua da jakitea eragiketa hori behar bezala egin den edo, aitzitik, akatsen bat gertatu den. Horretarako, HTTP webguneak errore/arrakasta kode ugari ditu, API REST bat erabiliz baliabide bat manipulatzen saiatzen denean erabiltzaileak jaso ditzakeen erantzun posible guztiak estaltzen dituztenak.

Hauek dira ohikoenak:

* **200 OK**. Erantzun estandarra eskaera zuzenetarako.
* **201 Created**. Eskaera osatu egin da eta errekurtso berri bat sortu da.
* **202 Accepted**. Eskaera onartu da prozesatzeko, baina ez da bete.
* **400 Bad Request**. Eskabidean okerreko sintaxia dago.
* **403 Forbidden**. Eskaera legezkoa izan zen, baina zerbitzariak uko egin dio erantzuteari, bezeroak ez baitu hori egiteko pribilegiorik.
* **404 Not Found**. Baliabidea ez da aurkitu. Web zerbitzariak eskatutako orria edo baliabidea aurkitzen ez duenean erabiltzen da.
* **500 Barneko Server Error**. Web zerbitzarietan sartutako aplikazioek ematen duten kode bat da, web zerbitzariaren izaerarekin zerikusirik ez duten errore-egoerekin topo egiten dutenean.


## 7.3 Autentifikazioa
API bati anonimoki deia egin daiteke, hau da, autentifikazioa egin gabe, baina hainbat faktoreengatik, API bat sarbide kontrola inplementatu beharko du. Hemen daude sarbide kontrola kudeatzeko zenbaki batzuk:

- **API Key**: APIa erabiliko denean, beste erabilzaileek identifikatzea errazten duen gako bat eskatzeko modua da. API keya erabiliz, zerbitzariak zein aplikazioak erabiliko duten erabilzailea identifikatu egiten da. Hala ere, API keyak gutxitu egin daitezke, hainbat segurtasun egitasmo erabiliz (SSL/TLS erabiliz, tokenak erabiliz, etab.).

- **Token-Based Authentication**: Erabilzailea autentifikatzeko, tokenak erabil daitezke. Erabilzaileak erabiltzaile eta pasahitza ematen ditu, eta sistema honek token bat sortzen du, hau da, identifikatzaile bakarra. Tokena erabiliz, erabilzaileak identifikatzen dira. OAuth protokoloa ere erabil daiteke tokenak baliatzeko.

- **OAUTH (Open Authorization)**: OAUTH protokoloak erabiltzaileak aplikazioei eskubideak ematen dizkie, baina hainbat etapa ditu autentifikazio prozesuan. Aplikazioak token bat jasoko du erabilzailearen izenean eta horrekin erabilgarriak izango dira token horrek eskubide guztiak dituztenei.

- **IP Whitelisting**: Hainbat API-etak IP helbideak baimendu dezakete, horrela, baimendutako IP helbideetatik bakarrik erabilzaileek sarbidea izango dute.

- **Rate Limiting**: Deiak neurtzen dira denbora-tarte batean edo kopuru batean, hau da, erabilzaileak exedentzia handitzen badu, atzerapenak edo baimendutako kopurua murriztu daitezke.

- **SSL/TLS**: Sarbidea segurutzeko, SSL/TLS erabil daitezke. Hau da, informazioa enkriptatzen da bidalketa prozesuan, hainbat segurtasun arazoren aurka.

## 7.4 Abantailak




API REST bat erabiltzearen abantaila nagusia edozein kontsumitzaileren aurrean ematen duen **independentzia** da, edozein hizkuntza edo plataformarekin sartzen dela ere. Horri esker, API REST bera hamaika bezerok kontsumi dezakete, bezero horien izaera edozein izanik ere, eta beste edozein kontsumitzaile motatara aldatzeak ez du inolako eraginik izango harengan. Ezaugarri horrek **fidagarritasuna, eskalagarritasuna eta eramangarritasun erraza** ematen dizkio beste edozein plataformari, **bezeroa zerbitzaritik erabat isolatzen baitu**. 

Erantzunen informazio-trukea euskarri batean egitea baino ez da eskatzen, normalean **JSON edo XML formatuan**. Bezeroaren eta zerbitzariaren arteko bereizketa horren ondorioz, beste zerbitzari edo datu-base batzuetara modu gardenean migratu daiteke, betiere datuak behar bezala bidaltzen badira. Horrek REST APIak edozein lan-inguruneri ematen dioten malgutasunagatik gehien erabiltzen diren web-arkitekturetako bat bihurtzen ditu, edozein dela ere haien izaera.

