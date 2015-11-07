package ch.hslu.edu.enapp.webshop.converter;

import java.io.Serializable;

import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.webservice.Item;

public class ProductItemConverter implements Serializable {

    private static final long serialVersionUID = 1L;

    public Item convertToItem(ProductDTO dto) {
        Item item = new Item();
        item.setNo(dto.getNo());
        item.setDescription(dto.getDescription());
        item.setMediafileName(dto.getMediapath());
        item.setOwner(dto.getOwner());
        item.setKind(dto.getKind());
        item.setUnitPrice(dto.getUnitprice());
        return item;
    }

    public ProductDTO convertToProductDTO(Item item) {
        ProductDTO dto = new ProductDTO();
        dto.setNo(item.getNo());
        dto.setDescription(item.getDescription());
        dto.setMediapath(item.getMediafileName());
        dto.setOwner(item.getOwner());
        dto.setKind(item.getKind());
        dto.setUnitprice(item.getUnitPrice());
        return dto;
    }

}
