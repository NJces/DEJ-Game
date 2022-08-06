package Model;

import javafx.scene.effect.ImageInput;

import java.util.Random;
import java.util.Stack;

public class StructureCard {
    private String type, name;
    private ColorOfGame color;
    private ImageInput image;


    public StructureCard(String name, String type) {
        this.type = type;
        this.name = name;
        image = new ImageInput();

        //-------set card color---------//
        switch (type) {
            case "Specific" :
                color = ColorOfGame.Purple;
                break;

            case "Military" :
                color = ColorOfGame.Red;
                break;

            case "Commercial" :
                color = ColorOfGame.Green;
                break;
            case "Ritual" :
                color = ColorOfGame.Blue;
                break;
            case "Royal" :
                color = ColorOfGame.Golden;
                break;

        }

        //-------set card image---------//
        switch (name) {
            case "Palace" ://کاخ

                break;

            case "Mansion" ://عمارت

                break;

            case "Citadel" ://ارگ

                break;
            case "Altar" ://محراب

                break;
            case "Temple" ://معبد

                break;
            case "PrayerPlace" ://نیایشگاه

                break;
            case "Caravanserai" ://کاروانسرا

                break;
            case "Dock" ://بارانداز

                break;
            case "Harbour" ://بندرگاه

                break;
            case "Chamber" ://حجره

                break;
            case "Market" ://چارسوق

                break;
            case "Castle" ://قلعه

                break;
            case "Battle" ://پیکارگاه

                break;
            case "ObservationTower" ://برج دیدبانی

                break;
            case "Prison" ://زندان

                break;

        }

    }

    public ColorOfGame getColor() {
        return color;
    }

    public ImageInput getImage() {
        return image;
    }

    public static Stack<StructureCard> initStructureCardList() {
        Stack<StructureCard> cards = new Stack<>();

        cards.push(new StructureCard("", "Specific"));
        cards.push(new StructureCard("", "Specific"));
        cards.push(new StructureCard("", "Specific"));
        cards.push(new StructureCard("", "Specific"));
        cards.push(new StructureCard("", "Specific"));
        cards.push(new StructureCard("", "Specific"));
        cards.push(new StructureCard("", "Specific"));

        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));
        cards.push(new StructureCard("", "Military"));

        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));
        cards.push(new StructureCard("", "Commercial"));

        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));
        cards.push(new StructureCard("", "Ritual"));

        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));
        cards.push(new StructureCard("", "Royal"));


        return cards;
    }

    public static void randomShuffling(Stack<StructureCard> cards) {
        Random rand = new Random();  // Random value generator
        for (int i=0; i<cards.size(); i++) {
            int randomIndex = rand.nextInt(cards.size());
            StructureCard temp = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, temp);
        }
    }
}

