package cholog.property

class GoogleDriveRestClient(private val endpoint: String) : RestClient {
    override fun getEndpoint(): String {
        return endpoint
    }
}
