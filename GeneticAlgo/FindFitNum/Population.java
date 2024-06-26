import java.util.Arrays;

public class Population {
    private Chromosome[] chromosomes;

    public Population(int length) {
        chromosomes = new Chromosome[length];
    }

    public Population initializePopulation() {
        for (int x = 0; x < chromosomes.length; x++) {
            chromosomes[x] = new Chromosome(GeneticAlgorithms.TARGET_CHROMOSOME.length).initializeChromosome();
        }
        sortChromosomesByFitness();
        return this;
    }

    public Chromosome[] getChromosome() {
        return chromosomes;
    }

    public void sortChromosomesByFitness() {
        Arrays.sort(chromosomes, (chromosome1, chromosome2) -> {
            int flag = 0;
            if (chromosome1.getFitness() > chromosome2.getFitness())
                flag = -1;
            else if (chromosome1.getFitness() < chromosome2.getFitness())
                flag = 1;
            return flag;
        });
    }
}
