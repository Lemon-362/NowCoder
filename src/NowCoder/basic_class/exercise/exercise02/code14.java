package NowCoder.basic_class.exercise.exercise02;


import java.util.LinkedList;
import java.util.Queue;

public class code14 {
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public static class Dog extends Pet{

        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {

        public Cat() {
            super("cat");
        }
    }

    public static class PetEnterQueue {
        private Pet pet;
        private int count;

        public PetEnterQueue(Pet pet, int count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public int getCount() {
            return count;
        }
    }

    public static class DogCatQueue {
        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;
        private int count;

        public DogCatQueue() {
            this.dogQ = new LinkedList<>();
            this.catQ = new LinkedList<>();
            this.count = 0;
        }

        public void add(Pet pet){
            if (pet.getType().equals("dog")){
                dogQ.add(new PetEnterQueue(pet, count++));
            }else if (pet.getType().equals("cat")){
                catQ.add(new PetEnterQueue(pet, count++));
            }
        }

        public Pet pollAll(){
            if (!dogQ.isEmpty() && !catQ.isEmpty()){
                if (dogQ.peek().getCount() < catQ.peek().getCount()){
                    return dogQ.poll().getPet();
                }else {
                    return catQ.poll().getPet();
                }
            }else if (!dogQ.isEmpty()){
                return dogQ.poll().getPet();
            }else if (!catQ.isEmpty()){
                return catQ.poll().getPet();
            }else {
                return null;
            }
        }

        public Dog pollDog(){
            if (!dogQ.isEmpty()){
                return (Dog) dogQ.poll().getPet();
            }else {
                return null;
            }
        }

        public Cat pollCat(){
            if (!catQ.isEmpty()){
                return (Cat) catQ.poll().getPet();
            }else {
                return null;
            }
        }

        public boolean isEmpty(){
            return dogQ.isEmpty() && catQ.isEmpty();
        }
    }

    public static void main(String[] args) {
        DogCatQueue test = new DogCatQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getType());
        }
    }
}
