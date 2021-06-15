public class Menufacture {

    Menufacture(){
    }

    static void stir(){
        String s = "Mixing";
        for(int i=0;i<s.length();i++)
            System.out.println(s.charAt(i));
        System.out.println("Finish");

        UserPanel.receiveCoffee();
        return;
    }

    static void getWater(){
        System.out.println("물을 전달 받았습니다.");
        return;
    }

    static void getIngredient(){
        System.out.println("재료를 전달 받았습니다.");
        stir();
        return;
    }
}
