/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author T450s
 */
public class DetailFoodOrder implements Serializable{
    private int idOrder;
    private String idFood;
    private int qty;
    private int price;
    private int amount;

    public DetailFoodOrder() {
        super();
    }

    public DetailFoodOrder(int idOrder, String idFood, int qty, int price, int amount) {
        super();
        this.idOrder = idOrder;
        this.idFood = idFood;
        this.qty = qty;
        this.price = price;
        this.amount = amount;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    

    
    
}
