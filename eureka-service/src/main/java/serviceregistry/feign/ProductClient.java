package serviceregistry.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import serviceregistry.demo.Product;

@FeignClient("eureka-service")
public interface ProductClient {
	@GetMapping("/products/{id}")
	Product getProduct(@PathVariable("id") Long id);
}
