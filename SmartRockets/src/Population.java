import java.util.ArrayList;

public class Population
{

    public ArrayList<Rocket> rockets = new ArrayList<>();
    public ArrayList<Rocket> matingpool = new ArrayList<>();
    int popsize = 150;

    public Population()
    {
        for (int i = 0; i < popsize; i++)
        {
            this.rockets.add(new Rocket(Main.WIDTH / 2, Main.HEIGHT - 50));
        }
    }

    public void run()
    {
        for (Rocket rocket : rockets)
        {
            rocket.update();
        }
    }

    public void evaluate()
    {
        double maxFit = 0;
        for (Rocket rocket : rockets)
        {
            rocket.calcFitness();
            if (rocket.fitness > maxFit)
            {
                maxFit = rocket.fitness;
            }
        }
        System.out.println("MAX_FIT: " + maxFit);
        for (Rocket rocket : rockets)
        {
            rocket.fitness /= maxFit;
            System.out.println("ROCKET FITNESS: " + rocket.fitness);
        }
        matingpool.clear();
        for (Rocket rocket : rockets)
        {
            double n = rocket.fitness * 100;
            for (int j = 0; j < n; j++)
            {
                matingpool.add(rocket);
            }
        }
    }

    public void selection()
    {
        for (Rocket rocket : rockets)
        {
            if(matingpool.size() > 0)
            {
                DNA parentA = matingpool.get(Utils.getRandomInt(matingpool.size())).dna;
                DNA parentB = matingpool.get(Utils.getRandomInt(matingpool.size())).dna;
                //
                DNA child = parentA.crossover(parentB);
                child.mutation(child.genes);
                rocket.reset();
                rocket.dna = child;
            }
        }
    }

    public void reset()
    {/*
        for (Rocket rocket : rockets)
        {
            Main.child.remove(rocket.getBody());
        }
        rockets.clear();
        for (int i = 0; i < popsize; i++)
        {
            this.rockets.add(new Rocket(Main.WIDTH / 2, Main.HEIGHT - 50));
        }
        for (Rocket rocket : rockets)
        {
            Main.child.add(rocket.getBody());
        }*/

        for (Rocket rocket : rockets)
        {
            rocket.reset();
        }
    }
}
