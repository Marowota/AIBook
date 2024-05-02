import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        Population population = new Population(GeneticAlgorithms.POPULATION_SIZE).initializePopulation();
        GeneticAlgorithms geneticAlgorithms = new GeneticAlgorithms();
        System.out.println("------------------------------------------------");
        System.out.println(
                "Generation # 0 " + " | Fittest chromosome fitness: " + population.getChromosome()[0].getFitness());
        printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithms.TARGET_CHROMOSOME));
        int generationNumber = 0;
        while (population.getChromosome()[0].getFitness() < GeneticAlgorithms.TARGET_CHROMOSOME.length) {
            generationNumber++;
            System.out.println("------------------------------------------------");
            population = geneticAlgorithms.evolve(population);
            population.sortChromosomesByFitness();
            System.out.println(
                    "Generation # " + generationNumber + "  | Fittest chromosome fitness: "
                            + population.getChromosome()[0].getFitness());
            printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithms.TARGET_CHROMOSOME));
        }
    }

    public static void printPopulation(Population population, String heading) {
        System.out.println(heading);
        System.out.println("------------------------------------------------");
        for (int x = 0; x < population.getChromosome().length; x++) {
            System.out.println("Chromosome  # " + x + "  : " + Arrays.toString(population.getChromosome()[x].getGenes())
                    + " | Fitness: " + population.getChromosome()[x].getFitness());
        }
    }
}
