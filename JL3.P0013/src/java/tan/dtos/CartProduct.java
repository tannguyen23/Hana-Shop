/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.dtos;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author tanta
 */
public class CartProduct implements Serializable {

    private String customer;
    private HashMap<String, ProductDTO> productCart;

    public CartProduct() {
    }

    public CartProduct(String customer) {
        this.customer = customer;
        productCart = new HashMap<>();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public HashMap<String, ProductDTO> getProductCart() {
        return productCart;
    }

    public void setProductCart(HashMap<String, ProductDTO> productCart) {
        this.productCart = productCart;
    }

    public void addToCart(ProductDTO dto, int addQuantity) throws Exception {
        if (this.productCart.containsKey(dto.getId())) {
            int quantity = this.productCart.get(dto.getId()).getQuantity() + addQuantity;
            dto.setQuantity(quantity);
        }
        this.productCart.put(dto.getId(), dto);
    }

    public void update(String id, int quantity) throws Exception {
        if (this.productCart.containsKey(id)) {
            this.productCart.get(id).setQuantity(quantity);
        }
    }

    public void remove(String id) throws Exception {
        if (this.productCart.containsKey(id)) {
            this.productCart.remove(id);
        }
    }
    
    public float total() throws Exception {
        float total = 0;
        for (ProductDTO productDTO : this.productCart.values()) {
            total += (productDTO.getPrice() * productDTO.getQuantity());
        }
        return total;
    }
    
    public int getTotalQuantity() throws Exception {
        int totalQuantity = 0;
        for (ProductDTO productDTO : this.productCart.values()) {
            totalQuantity += productDTO.getQuantity();
        }
        return totalQuantity;
    }

}
