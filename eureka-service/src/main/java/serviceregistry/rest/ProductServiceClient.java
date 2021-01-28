package serviceregistry.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import serviceregistry.demo.Product;

import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class ProductServiceClient {
	private final RestTemplate restTemplate;
	public ProductServiceClient(@LoadBalanced RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	//	@LoadBalanced
//	private final RestTemplate restTemplate;

	public List<Product> getAllProducts() {
		ProductList products = restTemplate.getForObject(
			"http://eureka-service/products",   //유레카 클라이언트 이름
//				"http://localhost:8080/products",
				ProductList.class);
		return products.getProducts();
	}

	public Product getProductById(Long id) {
		return restTemplate.getForObject(
				"http://localhost:8080/products/{id}",
				Product.class);
	}


}

@Getter
class ProductList {
	private List<Product> products;

	public ProductList() {
		this.products = new ArrayList<>();
	}
}
