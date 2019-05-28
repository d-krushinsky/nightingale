# Nightingale

### how build:

download
```bash
git clone git@github.com:dyuzz16/nightingale.git
cd nightingale/src
```

compile + make jar

linux:
```bash
find -name "*.java" > sources.txt
javac @sources.txt
find -name "*.class" > sources.txt
jar cf Nightingale.jar @sources.txt
rm sources.txt
```
