package genetic_algorithm;

import java.awt.Color;
import java.awt.Font;

import com.game.layer.Layer;
import com.game.renderer.RenderCommand;
import com.game.renderer.Renderer2D;
import com.game.time.TimeSteps;

public class ShowLayer extends Layer {
    String target;
    int popMax;
    float mutationRate;
    Population population;
    boolean isRun = true;
    Font font = new Font("Georgia", Font.BOLD, 20);
    Font answer = new Font("Georgia", Font.BOLD, 25);
    Font text = new Font("Georgia", Font.PLAIN, 16);

    protected ShowLayer(String name, LayerType type, String target, int popMax, float mutationRate) {
        super(name, type);
        this.popMax = popMax;
        this.mutationRate = mutationRate;
        this.target = target;
        this.population = new Population(target, mutationRate, popMax);
    }

    @Override
    public void onAttach() {
    }

    @Override
    public void onDettach() {
    }

    @Override
    public void onUpdate(TimeSteps ts) {
        
    }

    @Override
    public void onRender() {
        RenderCommand.clearScreen(Color.BLACK);
        if(!population.finished()){
            population.naturalSelection();
            population.generate();
            population.calcFitness();
        }
        String answer = population.getBest();
        Renderer2D.drawString("Best Phrase: ", 32, 280, font, Color.WHITE);
        Renderer2D.drawString(answer, 32, 310, this.answer, Color.WHITE);
        Renderer2D.drawString("Total generation: " + population.getGenerations(), 32, 330, text, Color.WHITE);
        Renderer2D.drawString("Average Fitness: " + population.getAverageFitness(), 32, 350, text, Color.WHITE);
        Renderer2D.drawString("Total Population: " + popMax, 32, 370, text, Color.WHITE);
        Renderer2D.drawString("Mutation Rate: " + (mutationRate * 100) + "%", 32, 390, text, Color.WHITE); 
        Renderer2D.drawString("All Phrase:\n" + population.allPhrases(), 450, 10, text);
    }

}
