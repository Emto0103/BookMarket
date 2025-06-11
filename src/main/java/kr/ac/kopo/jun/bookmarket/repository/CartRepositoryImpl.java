package kr.ac.kopo.jun.bookmarket.repository;


import kr.ac.kopo.jun.bookmarket.domain.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepositoryImpl implements CartRepository {
    private Map<String, Cart> listOfCarts;

    public CartRepositoryImpl(Map<String, Cart> listOfCartMap) {
        listOfCarts = new HashMap<String, Cart>();
    }

    @Override
    public Cart create(Cart cart) {
        // 만약 cartId가 이미 존재한다면
        if (listOfCarts.containsKey(cart.getCartId())) {
            throw new IllegalArgumentException(
                    "해당 장바구니의 ID(" + cart.getCartId() + ")가 이미 존재하여 새로 생성할 수 없습니다."
            );
        }
        listOfCarts.put(cart.getCartId(), cart);
        return cart;
    }

    @Override
    public Cart read(String cartId) {
        return listOfCarts.get(cartId);
    }
}
