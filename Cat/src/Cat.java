public class Cat {
    public double originWeight;
    private double weight;

    private final double MIN_WEIGHT = 1000.0;
    private final double MAX_WEIGHT = 9000.0;
    private boolean isAlive = false; //без этого можно было кошку сперва замяукать досмерти, но потом покормить, и она как бы снова живая. Мне не очень нравится идея воскрешения кошек

    private double eatenFood;
    private double drunkWater;

    private static int count;
    private final int ayeCount = 4;

    private CatColor colour;


    public Cat() {
        this(1500.0 + 3000.0 * Math.random());
    }

    public Cat(double weight) {
        originWeight = weight;
        isAlive = true;
        count++;
        colour = CatColor.values()[(int)(Math.random() * CatColor.values().length)];
    }

    public Cat(Cat cat) {
            weight = cat.weight;
            originWeight = cat.originWeight;
            isAlive = cat.isAlive;
            eatenFood = cat.eatenFood;
            drunkWater = cat.drunkWater;
            colour = cat.colour;
            if (cat.isAlive) {
                count++;
            }
    }

    public void meow() {
        if (isAlive) {
            weight--;
            System.out.println("Meow");
            killIfAnormalWeight();
        } else {
            printDeadCantDo("meow");
        }
    }

    public void feed(Double amount) {
        if (isAlive) {
            weight += amount;

            eatenFood += amount;
            killIfAnormalWeight();
        } else {
            printDeadCantDo("eat");
        }
    }

    public void drink(Double amount) {
        if (isAlive) {
            weight += amount;
            drunkWater += amount;
            killIfAnormalWeight();
        } else{
            printDeadCantDo("drink");
        }
    }

    public Double getWeight() {
        return weight;
    }

    public String getStatus() {
        if (!isAlive) {
            return "Dead";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }

    public static void feedAllCats(Cat[] cats) {

        for (Cat cat : cats) {
            cat.feed(100.0);
        }
    }

    public static void printAllCatsWeight(Cat[] cats) {
        int totalWeight = 0;
        System.out.println();
        System.out.print("Cats' weight: ");
        for (Cat cat : cats) {
            System.out.print(cat.getWeight() + ", ");
            totalWeight += cat.getWeight();
        }
        System.out.println("total cats' weight = " + totalWeight);
    }

    private void killIfAnormalWeight() {
        if (weight < MIN_WEIGHT) {
            System.out.println("Your cat just died. Sorry");
            kill();
        } else if (weight > MAX_WEIGHT) {
            System.out.println("Your cat has exploded. It's gone.");
            kill();
        }
    }

    public boolean getAlive() {
        return isAlive;
    }

    public double getEatenFood() {     // к уроку 2
        return eatenFood;
    }

    public void toilet() {
        if (isAlive) {
            if (drunkWater == 0 && eatenFood == 0) {
                System.out.println("The cat doesn't want to go to toilet");
            } else if (drunkWater >= eatenFood) {
                System.out.println("Hide your slippers...");
                weight -= drunkWater;
                drunkWater = 0;
            } else {
                System.out.println("Digging noise from the toilet...");
                weight -= eatenFood;
                eatenFood = 0;
            }
            killIfAnormalWeight();
        } else {
            printDeadCantDo("go to the toilet");
        }
    }

    private void printDeadCantDo(String actionName) {
        System.out.println("Dead cat can't " + actionName);
    }

    private void kill() {
        isAlive = false;
        weight = 0;
        eatenFood = 0;
        drunkWater = 0;
        count--;
    }

    public static int getCount() {
        return count;
    }

    public void setColour(CatColor colour){
        this.colour = colour;
    }

    public void setColour(int n){
        colour = CatColor.values()[n];
    }

    public CatColor getColour(){
        return colour;
    }


}