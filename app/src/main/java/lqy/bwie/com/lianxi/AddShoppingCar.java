package lqy.bwie.com.lianxi;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/17
 * desc:
 */
public class AddShoppingCar {

    private int commodityId;
    private int count;

    public AddShoppingCar(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    @Override
    public String toString() {
        return "AddShoppingCar{" +
                "commodityId=" + commodityId +
                ", count=" + count +
                '}';
    }
}
