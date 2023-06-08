# Datu atzipena (DAM)

## 1. Sarrera eta oinarrizko kontzeptuak

<img src="img/MapaKontzeptuala1.jpg" alt= "Mapa kontzeptuala" width="700px">

+ ### 1.1 Programak eta datuak

Informazioa hainbat modutan egituratuta egon daitezkeen datuen bidez adierazten da.

1. **Memoria nagusia**: Bere edukia ezabatu egiten da ordenagailua itzaltzean. Kapazitate txikia du baina datuetara sartzeko denbora oso laburra da. Adib. RAM memoria.

2. **Bigarren mailako biltegiratzea**: Datuak modu iraunkorrean (*persistente*) gordetzen diren tokia. Adib. HDD diska.


+ ### 1.2 Datuen iraunkortasuna

Programek soilik memoria nagusian dauden datuak atzitu ditzakete. Datuak memoria nagusian sor ditzakete eta bigarren mailako biltegiratzean gorde, iraunkortasuna edo persistentzia bermatzeko.

+ ### 1.3 Datuak iraunarazteko sistemak

Azken batean, **datu iraunkorrak fitxategietan gordetzen dira beti**, bigarren mailako biltegiratze batean. 

<img src="img/FitxategiSistema.jpg" alt= "Fitxategi Sistema" width="500px">

+ ### 1.4 Datuen biltegiratzea

#### 1.4.1 Fitxategiak

Fitxategi bat *byte*-sekuentzia bat besterik ez da. 
Oinarrizko euskarri honen gainean **edozein informazio mota** adieraz daiteke.

#### 1.4.2 Datu base erlazionalak
Datuak **tauletan** antolatzen dira eta hauen arteko erlazioak ezarri daitezke.

<img src="img/datuBaseErlazionala.png" alt= "datu base eralzionala" width="400px">

Gaur egun datu base erlazionalak dira erabilienak. **SQL** da erabiltzen den lengoaia estandarra.

#### 1.4.3 XML dokumentuak
Informazioa modu jerarkikoan antolatzen da (zuhaitz itxura hartzen du).
<img src="img/xmltree.png" alt= "XML zuhaitza" width="600px">

XML dokumentu bat testu-fitxategi batean gorde daiteke edo XML datu base batean.

#### 1.4.4 Objektuen datu baseak
Datu base mota honek objektuak gordetzen ditu, grafo itxura hartuz.

#### 1.4.5 NoSQL datu baseak
Oro har, oso egitura sinpleak eta, aldi berean, oso malguak erabiltzen dituzte.


### 1.5 Integritate murrizketak
Integritate-murrizketak biltegiratutako datuek bete beharreko baldintzak dira. Datu partikularretarako eta erlazionatuetarako defini daitezke.

Datuen gaineko aldaketa baten emaitzak integritate-murrizketaren bat urratzen badu, ez da onartuko.


### 1.6 Datu atzipena iteratzaileekin
Kontsulta baten emaitza guztiak memoria nagusira pasatzea ez da komeni, oro har, asko izan daitezkeelako. Iteratzaile bat kontsulta baterako sortzen da eta emaitzak banan-banan lortzeko aukera ematen du. Datuak bigarren mailako biltegiratzetik berreskuratzeko eragiketak nahiko motelak direnez, bakoitzean datu-blokeak berreskuratu ohi dira, nahiz eta ondoren iteratzaileak banan-banan eman.



### 1.7 Sarrera konkurrenteak eta transakzioak kontrolatzea
Biltegiratutako datuak aplikazio askoren artean partekatuak izan daitezke eta irakurketa/modifikazioak jasan ditzakete aldi berean. Horregatik, datu atzipena kontrolatu beharra dago. 

**Transakzio** bat, datuen sendotasuna mantenduz, beronen egoerak aldatzen dituen ekintza-multzo banaezina da, hau da, multzoko ekintza guztiak burutuko dira ala ez da bat bera ere burutuko.




