# Phosphorus textual-adventure 👽

Progetto finale per il corso di Metodi Avanzati di Programmazione [Uniba](https://www.uniba.it/it/ricerca/dipartimenti/informatica).

## Trama 🪐

Il protagonista, l’agente f24, si trova su una navicella spaziale in ritorno alla Terra da una missione che ha consistito nel catturare alieni per produrre il fosforo necessario alla sopravvivenza della Terra, infatti, sulla quest'ultima, il fosforo, che riveste un ruolo fondamentale per la sopravvivenza dei vegetali e quindi per il sostentamento dell’uomo  è cominciato a diminuire drasticamente, per questo si organizzano spedizioni per catturare alieni in grado di produrlo.

Inizialmente, f24 si sveglierà dal sonno criogenico nel dormitorio con un ordine, impartito dal comandante, di indagare sulla misteriosa scomparsa di due alieni prigionieri. Il protagonista cercherà i due fuggitivi, districandosi tra le stanze dell’astronave ed interrogando i membri dell’equipaggio, fino a scoprire cosa viene fatto agli alieni prigionieri. Sarà solo a lui decidere se mantenere lo _status quo_ o cambiare la situazione.

## Requisiti 📜
- [**Java**](https://www.java.com) v11 o versioni successive.

## Usage 💪

To use this program, follow these steps:

1. Assicurati di avere [Java](https://www.java.com) installato sul tuo sistema;
2. Effettua il Download del repository ```git clone https://github.com/Giut0/Phosphorus.git```
3. Effettua il Download del file jar `Phosphorus.jar` dalla sezione Releases di questo repository;
4. Aggiungi il file jar alla directory principale del repository;
5. Esegui il file jar con un doppio click o ```java -jar Phosphorus.jar``` da terminale;
6. Goditi l'avventura.

### Organizzazione repository 📐

```
Phosphorus/
|
├── docs/
│   ├── Report.tex
│   ├── Report.pdf
│   └── ...
|
├── resources/
│   ├── music/
│   │   └── Short_circuit.wav
│   ├── saves/
│   │   └── sav.mv.db
│   ├── characters.json
│   ├── items.json
│   ├── rooms.json
│   └── stopwords
|
├── src/java/di/uniba/map
│   ├── game/
│   │   ├── AirQuality.java
│   │   ├── GameEngine.java
│   │   ├── GameTimer.java
│   │   ├── PhosphorusGame.java
│   │   └── SaveGame.java
│   ├── parser/
│   │   ├── Parser.java
│   │   └──  ParserOutput.java
│   ├── type/
│   │   ├── Action.java
│   │   ├── ActionType.java
│   │   ├── Character.java
│   │   ├── Enemy.java
│   │   ├── Inventory.java
│   │   ├── Item.java
│   │   ├── KeyItem.java
│   │   ├── Room.java
│   │   └── Weapon.java
│   ├── ui/
│   │   ├── JKeypad.java
│   │   └── UI.java
│   ├── App.java
│   └── Utils.java
│
├── pom.xml
├── README.xml
└── LICENSE
```