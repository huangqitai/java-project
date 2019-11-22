package com.qitai.designmode.strategypattern;

/**
 * 以《headfirst设计模式》一书为主线
 * 一、鸭子游戏为应用场景，策略模式
 *不是所有的鸭子都会叫，都会飞，或者说叫声不同。如果将行为定义在Duck中，那么所有的鸭子都拥有了行为，如果对于不具有行为的鸭子子类
 * 则必须重写父类的行为方法，所以将行为设计为接口，行为的具体实现则由不同的实现类实现，而鸭子子类只需要选取对应的行为实现，
 *鸭子父类本身不识闲行为，而是委托行为接口
 */
public class Duck {
    /**
     * 所有的鸭子都具备行为，不同的是行为的具体实现。
     */
    FlyBehavior fly;
    QuackBehavior quack;

    public void doFly(){
        fly.fly();
    }
    public void doQuack(){
        quack.quack();
    }

    public void name(){
        System.out.println("比如说名字这种行为就是每个子类都不一样，都需要重写一遍，所以放在父类中");
    }

    /**
     * 可以动态设置不同的子类的行为实现，比如说原本是不会飞的可以设置一个会飞的行为实现，具有飞行行为
     * @param fly
     */
    public void setFly(FlyBehavior fly) {
        this.fly = fly;
    }

    public void setQuack(QuackBehavior quack) {
        this.quack = quack;
    }
}
