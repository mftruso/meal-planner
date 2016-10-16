drop database if exists mealplanner_test;
DROP OWNED BY mealplanner_test;
drop user if exists mealplanner_test;

drop database if exists mealplanner_dev;
DROP OWNED BY mealplanner_dev;
drop user if exists mealplanner_dev;

create database mealplanner_test;
create user mealplanner_test;
\c mealplanner_test
GRANT ALL PRIVILEGES ON schema public to mealplanner_test;

create database mealplanner_dev;
create user mealplanner_dev;
\c mealplanner_dev
GRANT ALL PRIVILEGES ON schema public to mealplanner_dev;