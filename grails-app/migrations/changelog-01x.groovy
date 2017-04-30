databaseChangeLog = {

    changeSet(author: "mike (generated)", id: "1493429474588-1") {
        createSequence(sequenceName: "seq_category")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-2") {
        createSequence(sequenceName: "seq_dish")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-3") {
        createSequence(sequenceName: "seq_dish_category")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-4") {
        createSequence(sequenceName: "seq_dish_type")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-5") {
        createSequence(sequenceName: "seq_meal")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-6") {
        createTable(tableName: "category") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "categoryPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "mike (generated)", id: "1493429474588-7") {
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

    changeSet(author: "mike (generated)", id: "1493429474588-8") {
        createTable(tableName: "dish_category") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "dish_categoryPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "category_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "dish_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "mike (generated)", id: "1493429474588-9") {
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

    changeSet(author: "mike (generated)", id: "1493429474588-10") {
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

    changeSet(author: "mike (generated)", id: "1493429474588-11") {
        createTable(tableName: "meal_dish") {
            column(name: "meal_dishes_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "dish_id", type: "BIGINT")
        }
    }

    changeSet(author: "mike (generated)", id: "1493429474588-12") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_CATEGORYNAME_COL", tableName: "category")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-13") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_DISH_TYPENAME_COL", tableName: "dish_type")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-14") {
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "dish_category", constraintName: "FK_72sb8v8yvkp4mnsq7lcnhnnnj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-15") {
        addForeignKeyConstraint(baseColumnNames: "dish_id", baseTableName: "meal_dish", constraintName: "FK_76lujwcvqhob2jfsbpmsqaquf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "dish")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-16") {
        addForeignKeyConstraint(baseColumnNames: "dish_id", baseTableName: "dish_category", constraintName: "FK_ahq4depm25b5dm6ia7lq0ms56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "dish")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-17") {
        addForeignKeyConstraint(baseColumnNames: "meal_dishes_id", baseTableName: "meal_dish", constraintName: "FK_d8scse7vvx838rrmhl75xkh4u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "meal")
    }

    changeSet(author: "mike (generated)", id: "1493429474588-18") {
        addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "dish", constraintName: "FK_grkvb3yo964xusqce1acs8o3j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "dish_type")
    }
}
