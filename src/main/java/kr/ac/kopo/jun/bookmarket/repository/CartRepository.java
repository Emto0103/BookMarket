package kr.ac.kopo.jun.bookmarket.repository;

import kr.ac.kopo.jun.bookmarket.domain.Cart;

public interface CartRepository {
    Cart create(Cart cart);
    Cart read(String cartId);
}
