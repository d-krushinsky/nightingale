# Nightingale

### how build:

download
```bash
git clone git@github.com:dyuzz16/nightingale.git
cd nightingale/src
```

compile + make jar

windows:
```bash
dir /b/s *.java >> sources.txt
javac @sources.txt
dir /b/s *.class >> sources.txt
jar cf Nightingale.jar @sources.txt
del sources.txt
```

linux:
```bash
find -name "*.java" > sources.txt
javac @sources.txt
find -name "*.class" > sources.txt
jar cf Nightingale.jar @sources.txt
rm sources.txt
```
