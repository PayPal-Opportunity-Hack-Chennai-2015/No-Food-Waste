createdb nfw
psql nfw -f create_user.sql
psql nfw -f database_setup.sql