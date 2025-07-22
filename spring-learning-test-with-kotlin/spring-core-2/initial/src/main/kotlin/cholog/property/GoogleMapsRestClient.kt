package cholog.property

class GoogleMapsRestClient(private val endpoint: String) : RestClient {
    override fun getEndpoint(): String {
        return endpoint
    }
}
