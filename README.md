# Phosphorus 👽

Progetto finale del corso di Metodi avanzati di programmazione [Uniba](https://www.uniba.it/it/ricerca/dipartimenti/informatica).

## Trama 🪐

TODO...

## Requisiti 📜
- [**Java**](https://www.java.com) v11 o versioni successive.

## Usage 💪

To use this program, follow these steps:

1. Ensure that you have [Java](https://www.java.com) installed on your system;
2. Download this repository ```git clone https://github.com/Giut0/Phosphorus.git```
3. Download the jar file `Phosphorus.jar` from the Release section of this repository;
4. Add jar file in main directory of this repository;
5. Run the downloaded file with double click or ```java -jar Phosphorus.jar```;
6. Enjoy game.

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