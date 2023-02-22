package ua.meta.kuzmenko.yevhenii.cafe.services;


import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.meta.kuzmenko.yevhenii.cafe.models.*;
import ua.meta.kuzmenko.yevhenii.cafe.repositories.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderCafeRepository orderCafeRepository;
    @Autowired
    private StatisticRepository statisticRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @Transactional
    public void addProduct(MultipartFile photo, String name, double price) {

        if (!photo.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String photoName = uuidFile + "." + photo.getOriginalFilename();
            try {
                photo.transferTo(new File(uploadPath + "/" + photoName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Product product = new Product(name, price, photoName);
            productRepository.save(product);
        }
    }

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public void addProductInOrder(Long productId) {
        Product product = productRepository.getReferenceById(productId);
        OrderCafe orderCafe = new OrderCafe(product, false);
        orderCafeRepository.save(orderCafe);
    }

    @Transactional
    public List<OrderCafe> findProductsInOrder() {
        return orderCafeRepository.findOrderCafeByCheckIsClosed(false);
    }

    @Transactional
    public void orderCafeDelete(Long OrderCafeId) {
        orderCafeRepository.deleteById(OrderCafeId);
    }

    @Transactional
    public void payCash() {
        List<OrderCafe> orderCafes = orderCafeRepository.findOrderCafeByCheckIsClosed(false);
        for (OrderCafe order : orderCafes) {
            order.setCheckIsClosed(true);
            order.setPayCash(true);
        }
        orderCafeRepository.saveAll(orderCafes);
    }

    @Transactional
    public void payCard() {
        List<OrderCafe> orderCafes = orderCafeRepository.findOrderCafeByCheckIsClosed(false);
        for (OrderCafe order : orderCafes) {
            order.setCheckIsClosed(true);
            order.setPayCash(false);
        }
        orderCafeRepository.saveAll(orderCafes);
    }

    @Transactional
    public double checkCost() {
        List<OrderCafe> orderCafes = orderCafeRepository.findOrderCafeByCheckIsClosed(false);
        double cost = 0;
        for (OrderCafe order : orderCafes) {
            cost += order.getProduct().getPrice();
        }
        return cost;
    }

    @Transactional
    public void finishWorking(Long workerId) {
        Worker worker = workerRepository.getReferenceById(workerId);
        Date date = new Date();
        System.out.println(date);
        List<OrderCafe> orderCafes = orderCafeRepository.findOrderCafeByCheckIsClosed(true);
        double cash = 0;
        double card = 0;
        for (OrderCafe orderCafe : orderCafes) {
            if (orderCafe.isPayCash() == true) {
                cash += orderCafe.getProduct().getPrice();
            } else card += orderCafe.getProduct().getPrice();
        }
        Statistics statistics = new Statistics(date, worker.getName(), cash, card);
        statisticRepository.save(statistics);
        orderCafeRepository.deleteAll(orderCafes);
    }

    @Transactional
    public void addWorker(String name) {
        Worker worker = new Worker(name);
        workerRepository.save(worker);
    }

    @Transactional
    public List<Worker> findAllWorkers() {
        return workerRepository.findAll();
    }

    @Transactional
    public void productRemoveById(Long productId) {
        Product product = productRepository.getReferenceById(productId);
        List<OrderCafe> orderCafes = orderCafeRepository.findOrderCafeByProduct(product);
        File photo = new File(uploadPath + "/" + product.getPhotoName());
        photo.delete();
        orderCafeRepository.deleteAll(orderCafes);
        productRepository.deleteById(productId);

    }

    @Transactional
    public void workerRemoveId(Long workerId) {
        workerRepository.deleteById(workerId);
    }

}
