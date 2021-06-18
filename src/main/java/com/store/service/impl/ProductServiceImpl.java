package com.store.service.impl;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.product.ProductImagesDto;
import com.store.dtos.review.ReviewDto;
import com.store.dtos.seller.SellerProductDto;
import com.store.model.ProdImages;
import com.store.model.Product;
import com.store.model.Review;
import com.store.model.Subcategory;
import com.store.repository.ProductImagesRepo;
import com.store.repository.ProductRepo;
import com.store.repository.ReviewRepo;
import com.store.repository.SubCategoryRepo;
import com.store.service.ProductService;
import com.store.util.mappers.EntityDtoMapper;
import com.store.util.mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.stream.Collectors;

@Service
@SessionScope
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final SubCategoryRepo subCategoryRepo;
    private final ReviewRepo reviewRepo;
    private final ProductImagesRepo productImagesRepo;

    private final EntityDtoMapper<Product, ProdDetailDto> productMapperAPI;
    private final EntityDtoMapper<ProdImages, ProductImagesDto> productImagesMapper;
    private final EntityDtoMapper<Product, SellerProductDto> mapper;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo,
                              SubCategoryRepo subCategoryRepo,
                              ReviewRepo reviewRepo,
                              EntityDtoMapper<Product, ProdDetailDto> productMapperAPI,
                              EntityDtoMapper<ProdImages, ProductImagesDto> productImagesMapper,
                              ProductImagesRepo productImagesRepo,
                              EntityDtoMapper<Product, SellerProductDto> sellerProductMapper) {
        this.productRepo = productRepo;
        this.subCategoryRepo = subCategoryRepo;
        this.reviewRepo = reviewRepo;
        this.productMapperAPI = productMapperAPI;
        this.productImagesMapper = productImagesMapper;
        this.productImagesRepo = productImagesRepo;
        this.mapper = sellerProductMapper;

    }

    @Override
    public List<ProdDetailDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return productMapperAPI.entityListToDtoList(products);
    }

    @Override
    public List<ProdDetailDto> getAllProductsByFilters(Integer pageNumber,
                                                       Double priceMin,
                                                       Double priceMax,
                                                       List<Integer> subCategoriesIds,
                                                       String nameSearch) {

        Pageable pageable = PageRequest.of(pageNumber, 3, Sort.by("name"));

        // default value for subcategory
        if (subCategoriesIds == null) {
            subCategoriesIds = subCategoryRepo.findAll().stream().map(Subcategory::getId).collect(Collectors.toList());
        }
        List<Subcategory> subcategories = subCategoryRepo.findByIdIn(subCategoriesIds);

        if (nameSearch != null && !nameSearch.equals("%")) {
            nameSearch = "%".concat(nameSearch.concat("%"));
        }

        System.out.println(nameSearch);

        Page<Product> page = productRepo.findBySubcategoryInAndPriceBetweenAndNameLikeIgnoreCase(
                subcategories, priceMin, priceMax, nameSearch, pageable);

        if (page.hasContent()) {
            List<Product> products = page.getContent();

            return productMapperAPI.entityListToDtoList(products);
        } else {
            return null;
        }
    }

    @Override
    public ProdDetailDto getProductById(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

        ProdDetailDto prodDetailDto = productMapperAPI.toDto(product);

        List<ProdImages> prodImages = productImagesRepo.findProdImagesByProduct(product);

        List<String> imagesUrls = prodImages
                .stream()
                .map(images -> images.getImageUrl()).collect(Collectors.toList());

        prodDetailDto.setProdImages(imagesUrls);

        return prodDetailDto;
    }

    @Override
    public ProdDetailDto addOrUpdateProduct(ProdDetailDto prodDetailDto) {
        Product product = productMapperAPI.toEntity(prodDetailDto);
        product = productRepo.save(product);

        return productMapperAPI.toDto(product);
    }

    @Override
    public void deleteProduct(Integer id) {

        productRepo.deleteById(id);
    }

    @Override
    public List<ReviewDto> getProductReviews(Integer productId, Integer pageNumber) {
        Product product = productRepo.getOne(productId);

        ReviewMapper reviewMapper = new ReviewMapper();

        Pageable pageable = PageRequest.of(pageNumber, 3, Sort.by("createdAt"));

        Page<Review> page = reviewRepo.findReviewsByProduct(product, pageable);

        if (page.hasContent()) {
            List<Review> reviews = page.getContent();

            System.out.println(reviews);

            return reviewMapper.entityListToDtoList(reviews);
        } else {
            return null;
        }
    }

    @Override
    public List<SellerProductDto> getProductsByUserId(int sellerId, Pageable pageable) {

        List<Product> products = productRepo.findByUser_Id(sellerId, pageable);

        List<SellerProductDto> dtos = mapper.entityListToDtoList(products);

        return dtos;
    }

    @Override
    public ProductImagesDto addImageToProduct(ProductImagesDto productImagesDto, Integer productId) {
        ProdImages prodImages = productImagesMapper.toEntity(productImagesDto);

        Product product = productRepo.getOne(productId);
        prodImages.setProduct(product);

        prodImages = productImagesRepo.save(prodImages);

        return productImagesMapper.toDto(prodImages);
    }
}
