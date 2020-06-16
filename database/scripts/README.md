# Database Scripts

## Dataset Parsing & DB Insertion
For parsing the dataset and insert its data on DB, you just need to run:
```
python3 citiesInserter.py
```

## Command to Insert Data into DB

cd freetours/database/scripts/

psql -h localhost -d freetour -U postgres -a -q -f creation_script.sql
