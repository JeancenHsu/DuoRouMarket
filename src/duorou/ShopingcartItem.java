package duorou;
//���ﳵ�� ÿ����Ʒ���˶��ٸ� �ܹ�����Ǯ
public class ShopingcartItem {
	private Goods_info goods_info;
	private int quantity;
	private double price;
	public Goods_info getGoods_info() {
		return goods_info;
	}
	public void setGoods_info(Goods_info goods_info) {
		this.goods_info = goods_info;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.price=this.goods_info.getSingle_price()*quantity;//�����Ʒ���ܼ۸� ����*����
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price=price;
	}
}
