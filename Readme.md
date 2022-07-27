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

## Usage - AtocPlayground1

Enter a date in DD/MM/YYYY format, a command, a stop identifier, or a service identifier, followed by a line break.

* `q` quit
* `ps` print services: toggle whether the list of services is printed for a query.
* `pfl` print fixed links: toggle whether the list of fixed links are printed for a query.
* A _date_ (such as `26/07/2022`) activates a filter to only show services scheduled for that day.
* A _stop_ (such as `GLC` or `EUS`) prints information about the stop, including services and fixed links if enabled.
* A _service_ (such as `Y58732.16/05/2022.09/12/2022.1111100`, found by listing all services for a stop) prints information about the service including all its stops.

