import javafx.geometry.Point2D;

import java.awt.*;

public class DNA
{

    public Point2D[] genes;

    public DNA()
    {
        genes = new Point2D[Main.lifeSpan];
        for (int i = 0; i < genes.length; i++)
        {
            this.genes[i] = PVector.random2D().multiply(0.5);
        }
    }

    public DNA crossover(DNA partner)
    {
        DNA newDNA = new DNA();
        int mid = Utils.getRandomInt(genes.length);
        for (int i = 0; i < genes.length; i++)
        {
            if(i > mid)
            {
                newDNA.genes[i] = this.genes[i];
            }
            else
            {
                newDNA.genes[i] = partner.genes[i];
            }
        }
        return newDNA;
    }

    public void mutation(Point2D[] mGenes)
    {
        for (int i = 0; i < mGenes.length; i++)
        {
            if(Math.random() < 0.01)
            {
                mGenes[i] = PVector.random2D().multiply(0.5);
            }
        }
    }
}
