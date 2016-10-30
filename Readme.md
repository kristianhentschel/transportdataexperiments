# UK Rail Data Experiments

An early stage parser and CLI interface for exploring UK Rail Timetables.

## Data

[data.atoc.org](http://data.atoc.org/)

Download the full timetable data file and extract it to data/:

```
ls data/ttf331
```

```
sf331.alf ttisf331.flf ttisf331.msn ttisf331.tsi
ttisf331.dat ttisf331.mca ttisf331.set ttisf331.ztr
```

## Build

```
./gradlew build
```

## Tests

```
./gradlew test
```

Results will be in `build/reports/tests/test/index.html`.

## Run

```
java -cp build/libs/transportdataexperiments.jar com.kristianhentschel.transportexp.applications.AtocPlayground1 data/ttf331/
```
