import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private String currentSelectedItem;
    private int currentInsertedMoney;
    private AbstractState state;//state design pattern 抽象的状态state作为vm的attribute
    private NoSelectionState noSelectionState;//状态1
    private HasSelectionState hasSelectionState;//状态2
    private InsertedMoneyState insertedMoneyState;//状态2
    private Map<String, Integer> itemPrice;
    //VM的构造函数
    public VendingMachine() {
        currentInsertedMoney = 0;//插入多少钱
        currentSelectedItem = null;//当前选择的商品
        noSelectionState = new NoSelectionState(this);
        hasSelectionState = new HasSelectionState(this);
        insertedMoneyState = new InsertedMoneyState(this);
        //当前state是noSelectionState
        state = noSelectionState;
//记录当前商品价格
        itemPrice = new HashMap<>();
        itemPrice.put("Coke", 199);
        itemPrice.put("Sprite", 299);
        itemPrice.put("MountainDew", 399);
    }
//代码封装 design pattern builder pattern setter/getter好处：安全，可隐藏内部实现，可扩展（用户和design分离）
    public void setSelectedItem(String item) {
        this.currentSelectedItem = item;
    }

    public String getSelectedItem() {
        return currentSelectedItem;
    }

    public void insertMoney(int amount) {
        this.currentInsertedMoney += amount;
    }

    public void emptyInsertedMoney() {
        this.currentInsertedMoney = 0;
    }

    public int getInsertedMoney() {
        return currentInsertedMoney;
    }

    public int getSalePrice() {
        if (currentSelectedItem == null) {
            System.out.println("Please make a selection before asking price");
            return 0;
        } else {
            return itemPrice.get(currentSelectedItem);
        }
    }
//getter & setter
    public void changeToNoSelectionState() {
        state = noSelectionState;
    }

    public void changeToHasSelectionState() {
        state = hasSelectionState;
    }

    public void changeToInsertedMoneyState() {
        state = insertedMoneyState;
    }

    public void selectItem(String selection) {
        state.selectItem(selection);
    }

    public void addMoney(int value) {
        state.insertMoney(value);
    }

    public void executeTransaction() {
        state.executeTransaction();
    }

    public int cancelTransaction() {
        return state.cancelTransaction();
    }

    public String getState() {
        String res = "";

        res = "Current selection is: " + currentSelectedItem + ", current inserted money: " + currentInsertedMoney
                + ", current state is : " + state;

        return res;
    }

    public void printState() {
        System.out.println(this.getState());
    }
}