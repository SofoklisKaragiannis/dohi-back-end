package dohi.programming.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bundle extends BasicContext {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("paths")
    private List<Path> paths;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bundle that = (Bundle) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(paths, that.paths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paths);
    }
}
