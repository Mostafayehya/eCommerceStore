package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.dtos.seller.SellerDto;
import com.store.dtos.seller.SellerProductDto;
import com.store.dtos.seller.SellerRequest;
import com.store.service.ProductService;
import com.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
@CrossOrigin(origins = "http://localhost:2323")
public class SellerController {


    private final SellerService sellerService;
    private final ProductService productService;


    @Autowired
    public SellerController(SellerService sellerService, ProductService productService) {

        this.sellerService = sellerService;

        this.productService = productService;

    }

    @GetMapping
    public GenericResponse<List<SellerDto>> getAllSellers() {

        List<SellerDto> sellerDtos = sellerService.getAll();

        GenericResponse<List<SellerDto>> response =
                new GenericResponse<>(sellerDtos, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return response;
    }


    @PostMapping
    public GenericResponse<SellerDto> addSeller(@RequestBody SellerRequest sellerRequest) {

        SellerDto dto = sellerService.addSeller(sellerRequest);

        GenericResponse<SellerDto> response =
                new GenericResponse<>(dto, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return response;

    }

    @GetMapping("/{sellerId}")
    public GenericResponse<SellerDto> getSeller(@PathVariable("sellerId") int sellerId) {

        SellerDto dto = sellerService.getBySellerId(sellerId);

        GenericResponse<SellerDto> response =
                new GenericResponse<>(dto, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return response;

    }

    @PutMapping("/{sellerId}")
    public GenericResponse<SellerDto> updateSeller(@PathVariable("sellerId") int sellerId,
                                                   @RequestBody SellerRequest sellerRequest) {

        SellerDto dto = sellerService.updateSeller(sellerId, sellerRequest);

        GenericResponse<SellerDto> response =
                new GenericResponse<>(dto, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return response;

    }

    @DeleteMapping("/{sellerId}")
    public GenericResponse<SellerDto> deleteSeller(@PathVariable("sellerId") int sellerId) {

        SellerDto dto = sellerService.deleteById(sellerId);

        GenericResponse<SellerDto> response =
                new GenericResponse<>(dto, HttpStatus.OK, "REQUEST SUCCESSFUL");


        return response;
    }

    @CrossOrigin(origins = "http://localhost:2323")
    @GetMapping(value = "/{sellerId}/products", params = {"page", "size"})
    public GenericResponse<List<SellerProductDto>>
    getSellerProducts(@PathVariable("sellerId") int sellerId,
                      @RequestParam(value = "page", defaultValue = "0") int page,
                      @RequestParam(value = "size", defaultValue = "1") int size) {


        Pageable pageable = PageRequest.of(page, size);
        System.out.println(page + size);
        List<SellerProductDto> dtos = productService.getProductsByUserId(sellerId, pageable);
        System.out.println(dtos);
        GenericResponse<List<SellerProductDto>> response =
                new GenericResponse<>(dtos, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return response;
    }

    @GetMapping("/{sellerId}/sold-items")
    public void getSellerSoldItems(@PathVariable("sellerId") int sellerId){

    }

}
