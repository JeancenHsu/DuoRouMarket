package duorou;

import java.util.HashMap;
import java.util.Map;
public class ShoppingCart {
	private Map<Integer, ShopingcartItem> items=new HashMap<Integer, ShopingcartItem>();
	private double price;//������Ʒ�ܼ�
	private int whole_quantity;
	public Map<Integer, ShopingcartItem> getItems() {
		return items;
	}
	public void setItems(Map<Integer, ShopingcartItem> items) {
		this.items = items;
	}
	public double getPrice() {
		 //�����ܼ�
        int price=0;
        for(ShopingcartItem item:items.values())
        {
            price+=item.getPrice();
        }
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	//���ﳵ��������Ʒ����Ŀ
	public int getWhole_quantity() {
		int whole_quantity=0;
		for(ShopingcartItem item:items.values()){
			whole_quantity+=item.getQuantity();
		}
		return whole_quantity;
	}
	public void setWhole_quantity(int whole_quantity) {
		this.whole_quantity = whole_quantity;
	}
}
