import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaResponse {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;

    public NasaResponse(@JsonProperty("copyright") String copyright,
                        @JsonProperty("date") String date,
                        @JsonProperty("explanation") String explanation,
                        @JsonProperty("hdurl") String hdurl,
                        @JsonProperty("media_type") String media_type,
                        @JsonProperty("service_version") String service_version,
                        @JsonProperty("title") String title,
                        @JsonProperty("url") String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = media_type;
        this.serviceVersion = service_version;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return title +
                "\n" + explanation;
    }

    public String getHdurl() {
        return hdurl;
    }
}
