package yarden.cerebri.model;

/**
 * Data object representing a product.
 * <p>
 * No args constructor, getters, and setters are mandatory in order for the {@link com.fasterxml.jackson.databind.ObjectMapper} to correctly map this POJO to and from JSON Objects.
 */
public class Product {

    private int productId;
    private String skuId;

    /**
     * No args constructor for databinding
     */
    public Product() {
    }

    /**
     * All args constructor
     *
     * @param productId the ID of the product
     * @param skuId     the sku ID of the product
     */
    public Product(int productId, String skuId) {
        this.productId = productId;
        this.skuId = skuId;
    }

    /**
     * @return productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the ID of the product
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return skuId
     */
    public String getSkuId() {
        return skuId;
    }

    /**
     * @param skuId the sku ID of the product
     */
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
