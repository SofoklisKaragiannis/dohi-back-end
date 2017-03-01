package dohi.programming.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Place extends BasicContext {

    @JsonProperty("radius")
    private Integer radius;

    @JsonProperty("position")
    private Position position;

    @JsonProperty("media")
    private List<Media> media;

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Place that = (Place) o;
        return Objects.equals(radius, that.radius) &&
                Objects.equals(position, that.position) &&
                Objects.equals(media, that.media);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, position, media);
    }
}
