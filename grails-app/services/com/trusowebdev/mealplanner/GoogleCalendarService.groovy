package com.trusowebdev.mealplanner

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.DataStoreFactory
import com.google.api.client.util.store.MemoryDataStoreFactory
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.*
import grails.config.Config
import grails.core.GrailsApplication
import grails.core.support.GrailsConfigurationAware
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Value

@Transactional
class GoogleCalendarService implements GrailsConfigurationAware{

    private static String CLIENT_ID //= grailsApplication.config.getProperty('gcal.clientId')
    private static String CLIENT_SECRET //= grailsApplication.config.getProperty('gcal.clientSecret')

    private static final String APPLICATION_NAME = "MealPlanner"
    private static final List<String> SCOPES = [CalendarScopes.CALENDAR_READONLY]
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private static DataStoreFactory DATA_STORE_FACTORY = new MemoryDataStoreFactory()

    def googleOAuth2Service

    public List<Event> queryForEvents() {
        com.google.api.services.calendar.Calendar service = getCalendarService()
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute()
         events.getItems()
    }

    public com.google.api.services.calendar.Calendar getCalendarService() throws IOException {
//        Credential credential = authorize()
        Credential.Builder builder = new Credential.Builder()
        builder
        Credential credential = builder.build()
        credential.accessToken = googleOAuth2Service.getAccessToken('')
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build()
    }

    private static Credential authorize(){

        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build()

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user")
        credential
//        GoogleCredential credential =
//                new GoogleCredential.Builder()
//                        .setTransport(HTTP_TRANSPORT)
//                        .setJsonFactory(JSON_FACTORY)
//                        .setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
//        credential.setAccessToken(access_token)
//        credential.setRefreshToken(refreshToken)
    }

    @Override
    void setConfiguration(Config co) {
        CLIENT_ID = co.getProperty('widget.width')
        CLIENT_SECRET = co.getProperty('widget.height')
    }
}
