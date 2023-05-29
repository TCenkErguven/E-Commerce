package com.eticaret.service;

import com.eticaret.dto.request.ProductSaleRequestDto;
import com.eticaret.dto.request.TransactionRequestDto;
import com.eticaret.dto.response.ProductSaleResponseDto;
import com.eticaret.dto.response.UserProfileSaleResponseDto;
import com.eticaret.manager.IProductManager;
import com.eticaret.manager.IUserManager;
import com.eticaret.repository.ISaleRepository;
import com.eticaret.repository.entity.Sale;
import com.eticaret.utility.JwtTokenProvider;
import com.eticaret.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleService extends ServiceManager<Sale,String> {
    private final ISaleRepository repository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IProductManager productManager;
    private final IUserManager userManager;
    public SaleService(ISaleRepository repository,
                       JwtTokenProvider jwtTokenProvider,
                       IProductManager productManager,
                       IUserManager userManager){
        super(repository);
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.productManager = productManager;
        this.userManager = userManager;
    }

    public Boolean transaction(TransactionRequestDto dto){
        Optional<Long> optionalAuthId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if(optionalAuthId.isEmpty())
            throw new RuntimeException("USER NOT FOUND");
        UserProfileSaleResponseDto userDto = userManager.getSaleUser(optionalAuthId.get()).getBody();
        ProductSaleResponseDto productDto = productManager.getSaleProduct(ProductSaleRequestDto
                        .builder()
                        .productIds(dto.getProductIds())
                .build()).getBody();
        save(Sale
                .builder()
                .userId(userDto.getId())
                .productIds(productDto.getProductIds())
                .totalPrice(productDto.getTotalPrice())
                .build());
        return true;
    }


}
