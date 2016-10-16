databaseChangeLog = {

    changeSet(author: "mike (generated)", id: "1476639139588-1") {
        createSequence(sequenceName: "seq_dish")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-2") {
        createSequence(sequenceName: "seq_dish_category")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-3") {
        createSequence(sequenceName: "seq_dish_type")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-4") {
        createSequence(sequenceName: "seq_meal")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-5") {
        createTable(tableName: "dish") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "dishPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "notes", type: "VARCHAR(255)")

            column(name: "recipe_location", type: "VARCHAR(255)")

            column(name: "type_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "mike (generated)", id: "1476639139588-6") {
        createTable(tableName: "dish_category") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "dish_categoryPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "mike (generated)", id: "1476639139588-7") {
        createTable(tableName: "dish_dish_category") {
            column(name: "dish_categories_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "dish_category_id", type: "BIGINT")
        }
    }

    changeSet(author: "mike (generated)", id: "1476639139588-8") {
        createTable(tableName: "dish_type") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "dish_typePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "sort_order", type: "INT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "mike (generated)", id: "1476639139588-9") {
        createTable(tableName: "meal") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "mealPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "groceries_purchased", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "grocery_list", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "meal_date", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "notes", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "mike (generated)", id: "1476639139588-10") {
        createTable(tableName: "meal_dish") {
            column(name: "meal_dishes_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "dish_id", type: "BIGINT")
        }
    }

    changeSet(author: "mike (generated)", id: "1476639139588-11") {
        addUniqueConstraint(columnNames: "name", tableName: "dish_category")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-12") {
        addUniqueConstraint(columnNames: "name", tableName: "dish_type")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-13") {
        addForeignKeyConstraint(baseColumnNames: "dish_id", baseTableName: "meal_dish", constraintName: "FK_76lujwcvqhob2jfsbpmsqaquf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "dish")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-14") {
        addForeignKeyConstraint(baseColumnNames: "dish_categories_id", baseTableName: "dish_dish_category", constraintName: "FK_7wlkwf0tj1rv6hr0qnabh25w9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "dish")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-15") {
        addForeignKeyConstraint(baseColumnNames: "meal_dishes_id", baseTableName: "meal_dish", constraintName: "FK_d8scse7vvx838rrmhl75xkh4u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "meal")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-16") {
        addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "dish", constraintName: "FK_grkvb3yo964xusqce1acs8o3j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "dish_type")
    }

    changeSet(author: "mike (generated)", id: "1476639139588-17") {
        addForeignKeyConstraint(baseColumnNames: "dish_category_id", baseTableName: "dish_dish_category", constraintName: "FK_ms7dcvhe9ssxmc1xpamaxuued", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "dish_category")
    }
}
