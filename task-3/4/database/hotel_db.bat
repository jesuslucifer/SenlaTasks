@echo off
mysql -uroot -proot -e "CREATE DATABASE IF NOT EXISTS hotel_db;"
mysql -uroot -proot hotel_db < hotel_db.sql
mysql -uroot -proot hotel_db < insert.sql
pause