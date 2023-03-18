package com.example.carwash.services;

import com.example.carwash.models.Product;
import com.example.carwash.models.Schedule;
import com.example.carwash.repositories.ProductRepository;
import com.example.carwash.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ScheduleRepository scheduleRepository;


    public List<Product> listProduct(String title) {
        if (title != null) if (!title.equals("")) return productRepository.findAllByTitle(title);
        return productRepository.findAll();
    }

    public List<Product> listProduct(String title,boolean available) {
        if (title != null) if (!title.equals("")) return productRepository.findAllByTitleAndAvailable(title,available);
        return productRepository.findAllByAvailable(available);
    }

    public void saveProduct(Product product) {
        log.info("SAVING NEW PRODUCT {}", product);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id).orElse(null);
    }


    public void changeAvaliableStatus(Long id) {
        Product p = getProductById(id);
        if(p.isAvailable())p.setAvailable(false);
        else p.setAvailable(true);
        productRepository.save(p);

    }
}
