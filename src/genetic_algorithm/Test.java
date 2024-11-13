package genetic_algorithm;

public class Test {
    String target;
    int popmax;
    float mutationRate;
    Population population;
    boolean isRun = true;

    public Test(){
        popmax = 150;
        target = "To be or not to be.";
        mutationRate = 0.10f;
        population = new Population(target, mutationRate, popmax);
    }

    public void Run(){
        while (isRun) {
            population.naturalSelection();
            population.generate();
            population.calcFitness();
           displayInfo(); 
           if(population.finished()){
            isRun = false;
           }
        }
    }

    private void displayInfo() {
        System.out.println("Best Answer: " + population.getBest());
    }

public static void main(String[] args) {
    Test test = new Test();
    test.Run();
}
}
