package ch.hslu.edu.enapp.webshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.hslu.edu.enapp.webshop.common.ProductServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.converter.ProductConverter;
import ch.hslu.edu.enapp.webshop.entity.Product;

/**
 * Session Bean implementation class ProductService
 */
@Stateless
@LocalBean
public class ProductService implements ProductServiceLocal {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    ProductConverter productConverter;

    @Override
    public List<ProductDTO> getAll() {
        List<Product> productList = entityManager.createNamedQuery(
                "getProduct", Product.class).getResultList();
        ArrayList<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList) {
            ProductDTO dto = productConverter.convertToDto(product);
            productDTOList.add(dto);
        }

        return productDTOList;
    }

}
