# product_duplicate_finder

## Question:

We have certain list of products with structure (productId: Int, skuId: String) where (productId, skuId) is expected to be always be a unique pair. Due to an error in data some ids got duplicate sku ids where as there was no change to productIds. You need to create an API which will return records with duplicate skuIds from the input request list.

### Example

#### HTTP POST Request
```json
[
  {"productId": 1, "skuId": "a10"},
  {"productId": 2, "skuId": "a10"},
  {"productId": 3, "skuId": "a11"},
  {"productId": 4, "skuId": "a12"}
]
```
#### Expected API Response
```json
[
  {"productId": 1, "skuId": "a10"},
  {"productId": 2, "skuId": "a10"}
]
```

## Prequisites:
* Java 1.8 JDK
* Maven

## Instructions:
1. Clone this repository

  `git clone https://github.com/YArane/product_duplicate_finder`
  
2. Go into the folder

  `cd product_duplicate_finder`
  
3. Build project with maven

  `mvn clean install`
  
4. Run java application

  `java -jar target/homework-1.0-SNAPSHOT-jar-with-dependencies.jar [port]`
  
5. To verify web server is running, navigate to

  `http://localhost:[port]`
  
6. Send POST request using curl

  `curl -H "Content-Type: application/json" -d '[{"productId": 1, "skuId": "a10"},{"productId": 2, "skuId": "a10"},                      {"productId": 3, "skuId": "a11"},{"productId": 4, "skuId": "a12"}]' http://localhost:[port]`
  
### Java Docs
1. Generate java docs 

`mvn javadoc:javadoc`

2. To open java doc in browser, navigate to

`target/site/apidocs/index.html`

    
  ### Docker image
Docker repository can be found [here](https://hub.docker.com/r/yarane/product_duplicate_finder/tags)
  
