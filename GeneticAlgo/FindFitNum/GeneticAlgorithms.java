public class GeneticAlgorithms {
    public static final int POPULATION_SIZE = 8;
    public static final int[] TARGET_CHROMOSOME = { 1, 1, 0, 1, 0, 0, 1, 1, 1, 0 };
    private static final double MUTATION_RATE = 0.25;
    public static final int NUMB_OF_ELITE_CHROMOSOME = 1;
    public static final int TOURNAMENT_SELECTION_SIZE = 4;

    public Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }

    private Population crossoverPopulation(Population population) {
        Population crossoverPopulation = new Population(population.getChromosome().length);
        for (int x = 0; x < NUMB_OF_ELITE_CHROMOSOME; x++) {
            crossoverPopulation.getChromosome()[x] = population.getChromosome()[x];
        }
        for (int x = NUMB_OF_ELITE_CHROMOSOME; x < population.getChromosome().length; x++) {
            Chromosome chromosome1 = selectTournamentPopulation(population).getChromosome()[0];
            Chromosome chromosome2 = selectTournamentPopulation(population).getChromosome()[0];
            crossoverPopulation.getChromosome()[x] = crossoverChromosome(chromosome1, chromosome2);
        }
        return crossoverPopulation;
    }

    private Population mutatePopulation(Population population) {
        Population mutatePopulation = new Population(population.getChromosome().length);
        for (int x = 0; x < NUMB_OF_ELITE_CHROMOSOME; x++) {
            mutatePopulation.getChromosome()[x] = population.getChromosome()[x];
        }
        for (int x = NUMB_OF_ELITE_CHROMOSOME; x < population.getChromosome().length; x++) {
            mutatePopulation.getChromosome()[x] = mutateChromosome(population.getChromosome()[x]);
        }
        return mutatePopulation;
    }

    private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
        Chromosome crossoverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for (int x = 0; x < chromosome1.getGenes().length; x++) {
            if (Math.random() < 0.5)
                crossoverChromosome.getGenes()[x] = chromosome1.getGenes()[x];
            else
                crossoverChromosome.getGenes()[x] = chromosome2.getGenes()[x];
        }
        return crossoverChromosome;
    }

    private Chromosome mutateChromosome(Chromosome chromosome) {
        Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for (int x = 0; x < chromosome.getGenes().length; x++) {
            if (Math.random() < MUTATION_RATE) {
                if (Math.random() < 0.5)
                    mutateChromosome.getGenes()[x] = 1;
                else
                    mutateChromosome.getGenes()[x] = 0;
            } else
                mutateChromosome.getGenes()[x] = chromosome.getGenes()[x];
        }
        return mutateChromosome;
    }

    private Population selectTournamentPopulation(Population population) {
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
        for (int x = 0; x < TOURNAMENT_SELECTION_SIZE; x++) {
            tournamentPopulation.getChromosome()[x] = population
                    .getChromosome()[(int) (Math.random() * population.getChromosome().length)];
        }
        tournamentPopulation.sortChromosomesByFitness();
        return tournamentPopulation;
    }
}
