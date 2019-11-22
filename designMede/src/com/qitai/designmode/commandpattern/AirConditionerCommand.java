package com.qitai.designmode.commandpattern;

public class AirConditionerCommand implements Command {
    AirConditioner airConditioner;

    public AirConditionerCommand(AirConditioner airConditioner){
        this.airConditioner = airConditioner;
    }
    @Override
    public void execute() {
        airConditioner.on();
    }
}
