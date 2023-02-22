package ua.meta.kuzmenko.yevhenii.cafe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.meta.kuzmenko.yevhenii.cafe.models.OrderCafe;
import ua.meta.kuzmenko.yevhenii.cafe.models.Product;
import ua.meta.kuzmenko.yevhenii.cafe.models.Worker;
import ua.meta.kuzmenko.yevhenii.cafe.services.ProductService;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        double checkCost = productService.checkCost();
        List<Product> products = productService.findAll();
        List<OrderCafe> orderCafes = productService.findProductsInOrder();
        model.addAttribute("products", products);
        model.addAttribute("orderCafes", orderCafes);
        model.addAttribute("checkCost", checkCost);
        return "index";
    }

    @PostMapping("/product/add")
    public String productAdd(@RequestParam("photo") MultipartFile photo,
                             @RequestParam String name,
                             @RequestParam double price, Model model) {
        productService.addProduct(photo, name, price);
        return "redirect:/admin";
    }

    @PostMapping("/worker/add")
    public String addWorker(@RequestParam String name, Model model) {
        productService.addWorker(name);
        return "redirect:/admin";
    }

    @GetMapping("/productInOrder/add/{productId}")
    public String addProductInOrder(@PathVariable(value = "productId") Long productId, Model model) {
        productService.addProductInOrder(productId);
        return "redirect:/";
    }

    @GetMapping("/orderCafe/delete/{orderCafeId}")
    public String orderCafeDelete(@PathVariable(value = "orderCafeId") Long orderCafeId) {
        productService.orderCafeDelete(orderCafeId);
        return "redirect:/";
    }

    @GetMapping("/payCash")
    public String payCash(Model model) {
        productService.payCash();
        return "redirect:/";
    }

    @GetMapping("/payCard")
    public String payCard(Model model) {
        productService.payCard();
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<Product> products = productService.findAll();
        List<Worker> workers = productService.findAllWorkers();
        model.addAttribute("products", products);
        model.addAttribute("workers", workers);
        return "admin";
    }

    @GetMapping("/finishWorking")
    public String finishWorkingPage(Model model) {
        List<Worker> workers = productService.findAllWorkers();
        model.addAttribute("workers", workers);
        return "finishWorking";
    }

    @PostMapping("/finishWorking")
    public String finishWorking(@RequestParam(value = "workerId") long workerId, Model model) {
        productService.finishWorking(workerId);
        return "redirect:/";
    }

    @PostMapping("/product/remove")
    public String productRemove(@RequestParam(value = "productId") long productId, Model model) {
        productService.productRemoveById(productId);
        return "redirect:/admin";
    }

    @PostMapping("/worker/remove")
    public String workerRemove(@RequestParam(value = "workerId") long workerId, Model model) {
        productService.workerRemoveId(workerId);
        return "redirect:/admin";
    }

}
