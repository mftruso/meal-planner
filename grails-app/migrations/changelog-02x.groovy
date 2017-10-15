databaseChangeLog = {

    changeSet(author: "mike (generated)", id: "1497937898900-1") {
        createTable(tableName: "mp_user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "mp_userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "mike (generated)", id: "1497937898900-2") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_MP_USERUSERNAME_COL", tableName: "mp_user")
    }

    changeSet(author: "mike (generated)", id: "1497937898900-3") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK1durosgccal6gaj1bwf8wqynd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "mp_user")
    }

    changeSet(author: "mike (generated)", id: "1497937898900-4") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "oauthid", constraintName: "FKj0r7ciqx3g0pswyedrmmn6m78", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "mp_user")
    }

    changeSet(author: "mike (generated)", id: "1497937898900-5") {
        dropForeignKeyConstraint(baseTableName: "user_role", constraintName: "FK859n2jvi8ivhui0rl0esws6o")
    }

    changeSet(author: "mike (generated)", id: "1497937898900-6") {
        dropForeignKeyConstraint(baseTableName: "oauthid", constraintName: "FKjdj2ffpjv8glg48eipmnc3wko")
    }

    changeSet(author: "mike (generated)", id: "1497937898900-7") {
        dropUniqueConstraint(constraintName: "uc_userusername_col", tableName: "user")
    }

    changeSet(author: "mike (generated)", id: "1497937898900-8") {
        dropTable(tableName: "user")
    }

    changeSet(author: "mike (generated)", id: "1497937898900-9") {
        dropIndex(indexName: "identity_idx", tableName: "oauthid")

        createIndex(indexName: "identity_idx", tableName: "oauthid") {
            column(name: "access_token")

            column(name: "provider")
        }
    }
}
