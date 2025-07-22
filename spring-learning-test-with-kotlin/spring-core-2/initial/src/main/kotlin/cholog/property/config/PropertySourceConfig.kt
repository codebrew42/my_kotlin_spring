package cholog.property.config

import cholog.property.GoogleDriveRestClient
import cholog.property.GoogleMapsRestClient

// TODO: Add @Configuration annotation to the class for Spring to recognize it as a configuration class
// TODO: Add configuration for using ext-api.properties file
class PropertySourceConfig() {
    // TODO: Get the value of the property "google.api.endpoint" from the environment
    // TODO: Register GoogleMapsRestClient as a bean using the previously obtained value
    fun googleMapsRestClient(): GoogleMapsRestClient {
        return GoogleMapsRestClient("")
    }

    // TODO: Get the value of the property "google.api.endpoint" using @Value annotation
    // TODO: Register googleDriveRestClient as a bean using the previously obtained valued
    fun googleDriveRestClient(): GoogleDriveRestClient {
        return GoogleDriveRestClient("")
    }
}
