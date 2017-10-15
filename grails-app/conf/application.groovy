import grails.util.Environment

import static java.sql.Connection.TRANSACTION_READ_COMMITTED

String envShortName = Environment.envNameMappings.entrySet().find { it.value == Environment.current.name }.key

dataSource {
    driverClassName = 'org.postgresql.Driver'
    jmxExport = true
    password = ''
    pooled = true
    url = 'jdbc:postgresql://localhost/mealplanner_' + envShortName
    username = 'mealplanner_' + envShortName
    logSql = false
}

grails {
    profile = web
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
//  Whether to autowire entities.
//  Disabled by default for performance reasons.
    gorm.autowire = true

//  Whether to translate GORM events into Reactor events
//  Disabled by default for performance reasons
    gorm.reactor.events = false

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
    flush.mode = 'AUTO'
}


spring.groovy.template.'check-template-location' = false
spring.main.'banner-mode' = "off"

/**
 * Spring Actuator Endpoints are Disabled by Default
 */
endpoints.enabled = false
endpoints.jmx.enabled = true


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

grails {
    plugin {
        springsecurity {
            oauth2 {
                active =  true
                registration {
                    askToLinkOrCreateAccountUri = '/oauth2/ask'
                    roleNames = ['ROLE_USER']
                }
                providers {
                    google {
                        api_key = System.getenv('MP_GCAL_CLIENT_ID')              //needed
                        api_secret =  System.getenv('MP_GCAL_CLIENT_SECRET')    //needed
                        successUri = "/oauth2/google/success"    //optional
                        failureUri = "/oauth2/google/failure"    //optional
                        callback = "/oauth2/google/callback"     //optional
                        scopes = "https://www.googleapis.com/auth/calendar.readonly" //optional
                    }
                }
            }
        }
    }
}

gcal.clientId = System.getenv('MP_GCAL_CLIENT_ID')
gcal.clientSecret = System.getenv('MP_GCAL_CLIENT_SECRET')

// Added by the Spring Security OAuth2 Google Plugin:
grails.plugin.springsecurity.oauth2.domainClass = 'com.trusowebdev.mealplanner.OAuthID'


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.trusowebdev.mealplanner.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.trusowebdev.mealplanner.UserRole'
grails.plugin.springsecurity.authority.className = 'com.trusowebdev.mealplanner.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]
