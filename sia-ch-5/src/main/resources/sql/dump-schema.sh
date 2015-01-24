#!/bin/bash
mysql -u john -p -h delicious stocks < schema-dump.sql > schema.sql


