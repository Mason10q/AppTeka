package com.example.appteka.model.basket;

public interface DrugAmountController {

    void increase(String name, String email);
    void decrease(String name, String email);
    void delete(String name, String email);

}
