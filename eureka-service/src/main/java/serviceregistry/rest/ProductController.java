package serviceregistry.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serviceregistry.demo.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

	private final ProductServiceClient client;

	@GetMapping
	public List<Product> productList(){
		return client.getAllProducts();
	}

	@GetMapping("{id}")
	public Product getProduct(@PathVariable("id") Long id){
		return client.getProductById(id);
	}

}
