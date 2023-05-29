package com.eticaret.service;

import com.eticaret.dto.request.CreateProductRequestDto;
import com.eticaret.dto.request.ProductSaleRequestDto;
import com.eticaret.dto.request.SaveImageRequestDto;
import com.eticaret.dto.response.GetProductResponseDto;
import com.eticaret.dto.response.ProductSaleResponseDto;
import com.eticaret.mapper.IProductMapper;
import com.eticaret.repository.IProductRepository;
import com.eticaret.repository.entity.Brand;
import com.eticaret.repository.entity.Category;
import com.eticaret.repository.entity.Product;
import com.eticaret.utility.JwtTokenProvider;
import com.eticaret.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService extends ServiceManager<Product,String> {
    private final IProductRepository repository;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ImageService imageService;
    private final JwtTokenProvider jwtTokenProvider;
    public ProductService(IProductRepository repository,
                          CategoryService categoryService,
                          BrandService brandService,
                          ImageService imageService,
                          JwtTokenProvider jwtTokenProvider){
        super(repository);
        this.repository = repository;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.imageService = imageService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Boolean createProduct(CreateProductRequestDto dto){
        Optional<Brand> optionalBrand = brandService.findById(dto.getBrandId());
        List<Category> categoryList = new ArrayList<>();
        //Düzeltilecek eksik kontrol bir tane bile category varsa başarılı sayılıyor
        dto.getCategoryIds().forEach(categoryId -> {
            categoryList.add(categoryService.findCategorieById(categoryId));
        });
        if(optionalBrand.isEmpty())
            throw new RuntimeException("BRAND NOT FOUND");
        Product product = IProductMapper.INSTANCE.fromCreateProductRequestDtoToProduct(dto);
        save(product);
        imageService.save(SaveImageRequestDto.builder()
                        .productId(product.getId())
                        .imageUrls(dto.getImageUrls())
                .build());
        return true;
    }

    public List<GetProductResponseDto> getAllProducts(){
        List<Product> productList = repository.findAll();
        List<GetProductResponseDto> getProductResponseDtoList = new ArrayList<>();
        productList.forEach(product -> {
            List<String> categoryNames = new ArrayList<>();
            List<String> imageUrls = imageService.findImagesById(product.getId()).getImageUrls();
            GetProductResponseDto dto = IProductMapper.INSTANCE.fromProductToGetProductResponseDto(product);
            Optional<Brand> optionalBrand = brandService.findById(product.getBrandId());
            product.getCategoryIds().forEach(categoryId -> {
                Optional<Category> optionalCategory = categoryService.findById(categoryId);
                if(optionalCategory.isEmpty())
                    throw new RuntimeException("CATEGORY NOT FOUND");
                categoryNames.add(categoryService.findCategorieById(categoryId).getCategoryName());
            });
            if(optionalBrand.isEmpty())
                throw new RuntimeException("BRAND NOT FOUND");
            if(imageUrls.isEmpty())
                throw new RuntimeException("IMAGE NOT FOUND");
            dto.setImageUrls(imageUrls);
            dto.setCategoryNames(categoryNames);
            dto.setBrandName(optionalBrand.get().getBrandName());
            getProductResponseDtoList.add(dto);
        });
        return getProductResponseDtoList;
    }

    public ProductSaleResponseDto getSaleProduct(ProductSaleRequestDto dto) {
       double totalPrice = 0;
        for(String productId : dto.getProductIds()){
            Optional<Product> optionalProduct = repository.findById(productId);
            if(optionalProduct.isEmpty())
                throw new RuntimeException("PRODUCT NOT FOUND");
            totalPrice += optionalProduct.get().getPrice();
        }
        ProductSaleResponseDto saleResponseDto = IProductMapper.INSTANCE.fromProductSaleRequestDtoToProductSaleResponseDto(dto);
        saleResponseDto.setTotalPrice(totalPrice);
        return saleResponseDto;
    }


    //Sepete ürün ekleme yapılacak

}
