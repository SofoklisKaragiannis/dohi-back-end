package dohi.programming.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Path extends BasicContext {

    @JsonProperty("places")
    private List<Place> places;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("length")
    private String length;

    @JsonProperty("polyline")
    private List<Position> polyline;

    @JsonProperty("duration")
    private String duration;

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public List<Position> getPolyline() {
        return polyline;
    }

    public void setPolyline(List<Position> polyline) {
        this.polyline = polyline;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Path that = (Path) o;
        return Objects.equals(places, that.places) &&
                Objects.equals(id, that.id) &&
                Objects.equals(length, that.length) &&
                Objects.equals(polyline, that.polyline) &&
                Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(places, id, length, polyline, duration);
    }
}
