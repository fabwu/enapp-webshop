package ch.hslu.edu.enapp.webshop.converter;

import java.io.Serializable;

import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.entity.Product;

public class ProductConverter implements Serializable {

    private static final long serialVersionUID = 1L;

    public Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setDescription(dto.getDescription());
        product.setMediapath(dto.getMediapath());
        product.setName(dto.getName());
        product.setProductid(dto.getProductid());
        product.setUnitprice(dto.getUnitprice());
        return product;
    }

    public ProductDTO convertToDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setDescription(product.getDescription());
        dto.setMediapath(product.getMediapath());
        dto.setName(product.getName());
        dto.setProductid(product.getProductid());
        dto.setUnitprice(product.getUnitprice());
        return dto;
    }

}
