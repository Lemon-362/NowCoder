package NowCoder.basic_class.exercise;

/*
	猫狗队列：
		add方法：将dog类或cat类放入队列
		pollAll方法：将队列中所有实例按进队列先后依次弹出
		pollDog方法：将队列中dog类的实例按队列先后依次弹出
		pollCat方法：将队列中cat类的实例按队列先后依次弹出
		isEmpty方法：检查队列是否还有dog或cat实例
		isDogEmpty方法：检查队列中是否还有dog类的实例
		isCatEmpty方法：检查队列中是否还有cat类的实例
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class code14_DogCatQueue {
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

        public PetEnterQueue(Pet pet, int count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet(){
            return this.pet;
        }

        public int getCount(){
            return this.count;
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
            }else {
                return;
            }
        }

        public Pet pollAll(){
            if (!this.catQ.isEmpty() && !this.dogQ.isEmpty()){
                if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()){
                    return this.dogQ.poll().getPet();
                }else {
                    return this.catQ.poll().getPet();
                }
            }else if (!this.catQ.isEmpty()){
                return this.catQ.poll().getPet();
            } else if (!this.dogQ.isEmpty()){
                return this.dogQ.poll().getPet();
            }else {
                return null;
            }
        }

        public Dog pollDog(){
            if (!this.dogQ.isEmpty()){
                return (Dog)(this.dogQ.poll()).getPet();
            }else {
                return null;
            }
        }

        public Cat pollCat(){
            if (!this.catQ.isEmpty()){
                return (Cat)(this.catQ.poll()).getPet();
            }else {
                return null;
            }
        }

        public boolean isEmpty(){
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

