package ecommerce.controller

import ecommerce.repository.ProductRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class ProductViewController(private val productRepository: ProductRepository) {
    @GetMapping
    fun showProducts(model: Model): String {
        model.addAttribute("products", productRepository.findAll())
        return "product-list"
    }
}
