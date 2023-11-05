abstract class Person {

    private int age;
    private int weightInKg;
    private int heightInCm;

    public Person(int age, int weightInKg, int heightInCm){
        this.age = age;
        this.heightInCm = heightInCm;
        this.weightInKg = weightInKg;
    }

    public void setAge(int newAge);
    public void getAge();

    public void setHeightInCm(int newHeightInCm);
    public void getHeightInCm();

    public void setWeightInKg(int newWeightInKg);
    public void getWeightInKg();

}