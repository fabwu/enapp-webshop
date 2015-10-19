package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import javax.ejb.Local;

import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;

@Local
public interface ProductServiceLocal {

    List<ProductDTO> getAll();

}
