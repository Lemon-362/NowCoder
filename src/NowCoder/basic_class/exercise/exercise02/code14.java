package NowCoder.basic_class.exercise.exercise02;

import NowCoder.basic_class.exercise.code14_DogCatQueue;

import javax.naming.spi.DirObjectFactory;
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

    public static class Dog extends Pet {
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

        public Pet getPet() {
            return pet;
        }

        public int getCount() {
            return count;
        }

        public PetEnterQueue(Pet pet, int count) {
            this.pet = pet;
            this.count = count;
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

        public void add(Pet pet) {
            if (pet.getType().equals("dog")) {
                this.dogQ.add(new PetEnterQueue(pet, count++));
            } else if (pet.getType().equals("cat")) {
                this.catQ.add(new PetEnterQueue(pet, count++));
            } else {
                return;
            }
        }

        public Pet pollAll() {
            if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
                if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
                    return this.dogQ.poll().getPet();
                } else {
                    return this.catQ.poll().getPet();
                }
            } else if (!this.dogQ.isEmpty()) {
                return this.dogQ.poll().getPet();
            } else if (!this.catQ.isEmpty()) {
                return this.catQ.poll().getPet();
            } else {
                return null;
            }
        }

        public Dog pollDog() {
            if (!this.dogQ.isEmpty()) {
                return (Dog) this.dogQ.poll().getPet();
            } else {
                return null;
            }
        }

        public Cat pollCat() {
            if (!this.catQ.isEmpty()) {
                return (Cat) this.catQ.poll().getPet();
            } else {
                return null;
            }
        }

        public boolean isEmpty() {
            return this.dogQ.isEmpty() && this.catQ.isEmpty();
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
