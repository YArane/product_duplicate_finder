package yarden.cerebri.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link DuplicateFinder} contains the logic for finding products with duplicate skuIds.
 * The algorithm makes use of the {@link MultiMap} object; iterating over the input array while
 * putting each product into the map. When complete, the map's {@link Map#keySet} contains all the unique skuIds,
 * and the {@link Map#values} are the corresponding lists of products who share the same skuId.
 * The {@link MultiMap} is then filtered such that only the duplicates are kept, and then returned as a flattened list.
 */
public class DuplicateFinder {

    /**
     * The runtime and space complexity both linearly scale with the number of products, O(n).
     *
     * @param products Array of products
     * @return List of products with duplicate skuIds.
     */
    public static List<Product> findDuplicates(Product[] products) {
        MultiMap<String, ArrayList<Product>> map = new MultiMap<>();

        for (Product product : products) {
            map.put(product.getSkuId(), product);
        }

        return map.values().stream()
                .filter(DuplicateFinder::isDuplicated)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static boolean isDuplicated(ArrayList<Product> value) {
        return value.size() > 1;
    }
}
