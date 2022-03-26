package tpbitcoin;

import java.time.YearMonth;

public class MiningHardware {
    private final String name;
    private final YearMonth dateOfRelease;
    private final long teraHashRate; // in TERAhash/s
    private final long power;

    public String getName() {
        return name;
    }

    public YearMonth getDateOfRelease() {
        return dateOfRelease;
    }

    public long getTeraHashRate() {
        return teraHashRate;
    }

    public long getPower() {
        return power;
    }

    // teraHashRate in TH/s
    // power in Watt
    public MiningHardware(String name, YearMonth dateOfRelease, long teraHashRate, long power) {
        this.name = name;
        this.dateOfRelease = dateOfRelease;
        this.teraHashRate = teraHashRate;
        this.power = power;
    }

    // TODO
    public double operatingDailyCost(double unitkWhCost){
        return 1.0;
    }



    @Override
    public String toString(){
        return getName() + ": " +
                getTeraHashRate() + "Th/s " +
                getPower() + "W";
    }
}
