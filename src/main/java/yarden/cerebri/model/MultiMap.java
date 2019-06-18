package yarden.cerebri.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * {@link MultiMap} is a subclass of {@link HashMap} constrained to the key being of type {@link String} and the value being an {@link ArrayList} of {@link Product}s.
 * This map allows for more than one value may be associated with a given key.
 */
public class MultiMap<K, V> extends HashMap<String, ArrayList<Product>> {

    /**
     * Puts the key/value pair into the map. If the key is not present in the map, create a new {@link ArrayList} to act as the bucket,
     * and call {@link HashMap#put(Object, Object)} to put the empty list into the map. Then, add the new value to the existing list.
     *
     * @param key   key to put into the map
     * @param value value to put into the map
     */
    public void put(String key, Product value) {
        if (!containsKey(key)) {
            super.put(key, new ArrayList<>());
        }
        get(key).add(value);
    }
}
