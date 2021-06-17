public class Solution {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.selectItem("coke");
        //System.out.println(vm.getState());

        vm.printState();
    }
}
