package dohi.programming.assignment.framework;

import dohi.programming.assignment.model.Bundle;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * BundleStorage used to store bundleId and Bundle in a map
 */
@Service
public class BundleStorage {
    Map<Integer, Bundle> bundleMap = new HashMap<>();

    public Map<Integer, Bundle> getBundleMap() {
        return bundleMap;
    }

    public void addBundle(Integer id, Bundle bundle) {
        this.bundleMap.put(id, bundle);
    }
}
