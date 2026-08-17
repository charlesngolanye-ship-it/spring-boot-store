package com.codewithmosh.store.products;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@Tag(name = "Products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


    @GetMapping
    public List<ProductDto> getAllProducts(
           // @RequestParam(required = false, defaultValue = "", name = "sort") String sort
           @RequestParam(name = "categoryId", required = false) Byte categoryId
    ) {
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }


        return products
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        var product =  productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        //var productDto = new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategory());
        return ResponseEntity.ok(productMapper.toDto(product));
    }

//    @PostMapping
//    public ResponseEntity<ProductDto> createProduct(
//            @RequestBody CreateProductRequest request,
//            UriComponentsBuilder uriBuilder) {
//
//        if (request.getCategoryId() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        var managedCategory = categoryRepository.findById(request.getCategoryId())
//                .orElse(null);
//
//        if (managedCategory == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        var product = productMapper.toEntity(request);
//        product.setCategory(managedCategory);
//
//        productRepository.save(product);
//
//        var productDto = productMapper.toDto(product);
//        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();
//
//        return ResponseEntity.created(uri).body(productDto);
//    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto,
            UriComponentsBuilder uriBuilder) {

        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(productDto);
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(product.getId());


        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody ProductDto productDto) {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }


        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productMapper.update(productDto, product);
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(product.getId());

        return ResponseEntity.ok(productDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
