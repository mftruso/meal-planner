import grails.util.Environment

import static java.sql.Connection.TRANSACTION_READ_COMMITTED

String envShortName = Environment.envNameMappings.entrySet().find { it.value == Environment.current.name }.key

dataSource {
    dialect = 'net.kaleidos.hibernate.PostgresqlExtensionsDialect'
    driverClassName = 'org.postgresql.Driver'
    jmxExport = true
    password = ''
    pooled = true
    postgresql.extensions.sequence_per_table = true
    url = 'jdbc:postgresql://localhost/mealplanner_' + envShortName
    username = 'mealplanner_' + envShortName
}

grails {
    codegen.defaultPackage = 'com.trusowebdev.mealplanner'
    controllers {
        defaultScope = 'singleton'
        upload {
            maxFileSize = 1_000_000_000
            maxRequestSize = 1_000_000_000
        }
    }
    converters.encoding = 'UTF-8'
    mime {
        disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
        types = [
                all          : '*/*',
                atom         : 'application/atom+xml',
                css          : 'text/css',
                csv          : 'text/csv',
                form         : 'application/x-www-form-urlencoded',
                html         : ['text/html', 'application/xhtml+xml'],
                js           : 'text/javascript',
                json         : ['application/json', 'text/json'],
                multipartForm: 'multipart/form-data',
                pdf          : 'application/pdf',
                rss          : 'application/rss+xml',
                text         : 'text/plain',
                hal          : ['application/hal+json', 'application/hal+xml'],
                xml          : ['text/xml', 'application/xml']
        ]
    }

    spring.transactionManagement.proxies = false
    urlmapping.cache.maxsize = 1000
    views.default.codec = 'html'
    views {
        gsp {
            codecs {
                expression = 'html'
                scriptlets = 'html'
                staticparts = 'none'
                taglib = 'none'
            }
            encoding = 'UTF-8'
            htmlcodec = 'xml'
        }
    }
}

hibernate {
    cache {
        queries = false
        use_second_level_cache = true
        use_query_cache = false
        region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory'
    }
}


spring.groovy.template.'check-template-location' = false

endpoints.jmx.'unique-names' = true

environments {
    development {
        server.port = 8080
        grails.serverURL = "http://localhost:8080"
    }

    test {
        server.port = 9090
        grails.serverURL = "http://localhost:9090"
        migrations.enabled = false
    }

    production {
        dataSource {
			password = System.getenv('MEALPLANNER_PASS')
            properties {
                defaultTransactionIsolation = TRANSACTION_READ_COMMITTED
                initialSize = 5
                jdbcInterceptors = 'ConnectionState'
                jmxEnabled = true
                maxActive = 50
                maxAge = 600_000
                maxIdle = 25
                maxWait = 10000
                minEvictableIdleTimeMillis = 60000
                minIdle = 5
                testOnBorrow = true
                testOnReturn = false
                testWhileIdle = true
                timeBetweenEvictionRunsMillis = 5000
                validationInterval = 15000
                validationQuery = 'SELECT 1'
                validationQueryTimeout = 3
            }
        }

        grails {
            serverURL = System.getenv('MP_GRAILS_SERVER_URL')
        }

        server.port = 5000
    }
}

grails.plugin.databasemigration.updateOnStart = true
grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']