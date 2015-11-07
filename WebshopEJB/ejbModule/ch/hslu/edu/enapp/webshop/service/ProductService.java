package ch.hslu.edu.enapp.webshop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.hslu.edu.enapp.webshop.common.ProdcutServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.converter.ProductItemConverter;
import ch.hslu.edu.enapp.webshop.webservice.Item;
import ch.hslu.edu.enapp.webshop.webservice.ItemFields;
import ch.hslu.edu.enapp.webshop.webservice.ItemFilter;
import ch.hslu.edu.enapp.webshop.webservice.ItemList;
import ch.hslu.edu.enapp.webshop.webservice.ItemService;

/**
 * Session Bean implementation class ProductService
 */
@Stateless
@LocalBean
public class ProductService implements ProdcutServiceLocal {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    ProductItemConverter productConverter;

    @Inject
    ItemService itemService;

    @Override
    public List<ProductDTO> getAll() {
        ItemFilter itemFilter = new ItemFilter();
        itemFilter.setCriteria("MP3");
        itemFilter.setField(ItemFields.PRODUCT_GROUP_CODE);

        ItemList itemList = itemService.getItemPort().readMultiple(Collections.singletonList(itemFilter), null, 0);

        ArrayList<ProductDTO> productDTOList = new ArrayList<>();

        for (Item item : itemList.getItem()) {
            ProductDTO dto = productConverter.convertToProductDTO(item);
            productDTOList.add(dto);
        }

        return productDTOList;
    }

}
